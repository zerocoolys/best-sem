package com.perfect.service.impl;

import com.perfect.autosdk.core.CommonService;
import com.perfect.autosdk.exception.ApiException;
import com.perfect.autosdk.sms.v3.GetKeyword10QualityRequest;
import com.perfect.autosdk.sms.v3.GetKeyword10QualityResponse;
import com.perfect.autosdk.sms.v3.KeywordService;
import com.perfect.autosdk.sms.v3.Quality10Type;
import com.perfect.core.AppContext;
import com.perfect.dao.AccountManageDAO;
import com.perfect.dao.KeywordQualityDAO;
import com.perfect.dto.KeywordQualityReportDTO;
import com.perfect.dto.QualityDTO;
import com.perfect.entity.BaiduAccountInfoEntity;
import com.perfect.entity.KeywordReportEntity;
import com.perfect.service.KeywordQualityService;
import com.perfect.utils.BaiduServiceSupport;
import com.perfect.utils.JSONUtils;
import com.perfect.utils.TopN;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by baizz on 2014-08-16.
 */
@Service("keywordQualityService")
public class KeywordQualityServiceImpl implements KeywordQualityService {

    @Resource
    private AccountManageDAO<BaiduAccountInfoEntity> accountManageDAO;

    @Resource
    private KeywordQualityDAO keywordQualityDAO;

    @Resource
    private TopN<KeywordReportEntity> topN;

    @Override
    public Map<String, Object> find(String fieldName, int n, int skip, int sort) {
        fieldName = "pc" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        List<KeywordReportEntity> list = keywordQualityDAO.findYesterdayKeywordReport();
        if (list.size() == 0)
            return null;

        //getYesterdayAllKeywordId
        List<Long> keywordIds = new ArrayList<>();
        ForkJoinPool forkJoinPool1 = new ForkJoinPool();
        try {
            KeywordIdTask task = new KeywordIdTask(list, 0, list.size());
            Future<List<Long>> result = forkJoinPool1.submit(task);
            keywordIds = result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool1.shutdown();
        }

        Map<String, KeywordReportEntity> map = new LinkedHashMap<>();
        ForkJoinPool forkJoinPool2 = new ForkJoinPool();
        try {
            CalculateTask task = new CalculateTask(list, 0, list.size());
            Future<Map<String, KeywordReportEntity>> result = forkJoinPool2.submit(task);
            map = result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool2.shutdown();
        }

//        //计算点击率和平均点击价格
//        for (Map.Entry<String, KeywordReportEntity> entry : map.entrySet()) {
//            KeywordReportEntity vo = entry.getValue();
//            Double cost = vo.getPcCost();
//            Double ctr = (vo.getPcClick() + .0) / vo.getPcImpression();
//            Double cpc = .0;
//            cost = new BigDecimal(cost).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            ctr = new BigDecimal(ctr * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            if (vo.getPcClick() > 0)
//                cpc = vo.getPcCost() / vo.getPcClick();
//            cpc = new BigDecimal(cpc).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            vo.setPcCost(cost);
//            vo.setPcCtr(ctr);
//            vo.setPcCpc(cpc);
//            entry.setValue(vo);
//        }

        list = new ArrayList<>(map.values());
        QualityDTO allQualityData = getQualityData(list);

        //获取关键词质量度
        List<Quality10Type> quality10Types = getKeyword10Quality(keywordIds);

        Map<Integer, List<KeywordReportEntity>> tempMap = new HashMap<>();
        for (int i = 0; i <= 10; i++) {
            tempMap.put(i, new ArrayList<KeywordReportEntity>());
        }

        for (Quality10Type quality10Type : quality10Types) {
            tempMap.get(quality10Type.getPcQuality()).add(map.get(quality10Type.getId().toString()));
        }

        Map<String, Object> results = new HashMap<>();
        List<QualityDTO> qualityList = new ArrayList<>();
        List<KeywordQualityReportDTO> reportList = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            List<KeywordReportEntity> tempList = tempMap.get(i);
            if (!tempList.isEmpty()) {

                //质量度级别信息计算
                QualityDTO qualityDTO = getQualityData(tempList);
                if (qualityDTO.getCost() > 0) {
                    Double cost = new BigDecimal(qualityDTO.getCost()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    qualityDTO.setCost(cost);
                }
                if (qualityDTO.getImpression() > 0) {
                    Double ctr = (qualityDTO.getClick() + .0) / qualityDTO.getImpression();
                    ctr = new BigDecimal(ctr * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    qualityDTO.setCtr(ctr);
                }
                if (qualityDTO.getClick() > 0) {
                    Double cpc = qualityDTO.getCost() / qualityDTO.getClick();
                    cpc = new BigDecimal(cpc * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    qualityDTO.setCpc(cpc);
                }
                Double keywordQtyRate = (tempList.size() + .0) / list.size();
                keywordQtyRate = new BigDecimal(keywordQtyRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                qualityDTO.setKeywordQtyRate(keywordQtyRate);

                Double impressionRate = (qualityDTO.getImpression() + .0) / allQualityData.getImpression();
                impressionRate = new BigDecimal(impressionRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                qualityDTO.setImpressionRate(impressionRate);

                Double clickRate = (qualityDTO.getClick() + .0) / allQualityData.getClick();
                clickRate = new BigDecimal(clickRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                qualityDTO.setClickRate(clickRate);

                Double costRate = (qualityDTO.getCost() + .0) / allQualityData.getCost();
                costRate = new BigDecimal(costRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                qualityDTO.setCostRate(costRate);

                Double conversionRate = (qualityDTO.getConversion() + .0) / allQualityData.getConversion();
                conversionRate = new BigDecimal(conversionRate * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                qualityDTO.setConversionRate(conversionRate);

                qualityDTO.setGrade(i);
                qualityList.add(qualityDTO);

                //每个质量度下具体的关键词信息
                KeywordReportEntity topNData[] = topN.getTopN(tempList.toArray(new KeywordReportEntity[tempList.size()]), n, fieldName, sort);

                if ((skip + 1) * n > topNData.length) {
                    List<KeywordReportEntity> data = new ArrayList<>();
                    for (int j = skip * n; j < topNData.length; j++) {
                        data.add(topNData[j]);
                    }
                    reportList.add(new KeywordQualityReportDTO(i, data));
                } else {
                    KeywordReportEntity arrData[] = new KeywordReportEntity[n];
                    System.arraycopy(topNData, skip * n, arrData, 0, n);
                    reportList.add(new KeywordQualityReportDTO(i, Arrays.asList(arrData)));
                }
            }

        }

        results.put("qualityDTO", JSONUtils.getJsonObjectArray(qualityList));
        results.put("report", JSONUtils.getJsonObjectArray(reportList));

        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Quality10Type> getKeyword10Quality(List<Long> keywordIds) {
        CommonService commonService = BaiduServiceSupport.getCommonService(accountManageDAO.findByBaiduUserId(AppContext.getAccountId()));
        try {
            KeywordService keywordService = commonService.getService(KeywordService.class);
            GetKeyword10QualityRequest request = new GetKeyword10QualityRequest();
            request.setIds(keywordIds);
            request.setDevice(0);
            request.setType(11);
            request.setHasScale(false);
            GetKeyword10QualityResponse response = keywordService.getKeyword10Quality(request);

            if (response == null) {
                return Collections.EMPTY_LIST;
            }

            return response.getKeyword10Quality();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private QualityDTO getQualityData(List<KeywordReportEntity> list) {
        QualityDTO qualityDTO = new QualityDTO(list.size(), .0, 0, .0, 0, .0, .0, .0, .0, .0, .0, .0);
        for (KeywordReportEntity entity : list) {
            qualityDTO.setImpression(qualityDTO.getImpression() + entity.getPcImpression());
            qualityDTO.setClick(qualityDTO.getClick() + entity.getPcClick());
            qualityDTO.setCost(qualityDTO.getCost() + entity.getPcCost());
            qualityDTO.setConversion(qualityDTO.getConversion() + entity.getPcConversion());
        }
        return qualityDTO;
    }

    class KeywordIdTask extends RecursiveTask<List<Long>> {

        private int start;
        private int end;
        private List<KeywordReportEntity> list;

        KeywordIdTask(List<KeywordReportEntity> list, int start, int end) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected List<Long> compute() {
            List<Long> ids = new ArrayList<>();
            if (end - start < 1_000) {
                for (int i = start; i < end; i++) {
                    ids.add(list.get(i).getKeywordId());
                }
            } else {
                int middle = (end - start) / 2;
                KeywordIdTask task1 = new KeywordIdTask(list, start, start + middle);
                KeywordIdTask task2 = new KeywordIdTask(list, start + middle, end);

                invokeAll(task1, task2);

                ids.clear();
                ids.addAll(task1.join());
                ids.addAll(task2.join());
            }
            return ids;
        }
    }

    class CalculateTask extends RecursiveTask<Map<String, KeywordReportEntity>> {

        private static final int threshold = 1_000;

        private int start;
        private int end;
        private List<KeywordReportEntity> list;

        CalculateTask(List<KeywordReportEntity> list, int start, int end) {
            this.start = start;
            this.end = end;
            this.list = list;
        }

        @Override
        protected Map<String, KeywordReportEntity> compute() {
            Map<String, KeywordReportEntity> map = new HashMap<>();
            if (end - start < threshold) {
                for (int i = start; i < end; i++) {
                    KeywordReportEntity vo = list.get(i);
                    map.put(vo.getKeywordId().toString(), vo);
//                    String keywordId = vo.getKeywordId().toString();
//                    KeywordReportEntity _vo = map.get(keywordId);
//                    if (_vo != null) {
//                        _vo.setPcImpression(_vo.getPcImpression() + vo.getPcImpression());
//                        _vo.setPcClick(_vo.getPcClick() + vo.getPcClick());
//                        _vo.setPcCtr(0.);
//                        _vo.setPcCost(_vo.getPcCost() + vo.getPcCost());
//                        _vo.setPcCpc(0.);
//                        _vo.setPcPosition(_vo.getPcPosition() + vo.getPcPosition());
//                        _vo.setPcConversion(_vo.getPcConversion() + vo.getPcConversion());
//                        map.put(keywordId, _vo);
//                    } else {
//                        map.put(keywordId, vo);
//                    }
                }
            } else {
                int middle = (end - start) / 2;
                CalculateTask task1 = new CalculateTask(list, start, start + middle);
                CalculateTask task2 = new CalculateTask(list, start + middle, end);

                invokeAll(task1, task2);

                //map合并处理
                map.clear();
                map.putAll(task1.join());
                map.putAll(task2.join());
//                map = merge(task1.join(), task2.join());
            }
            return map;
        }

        @Deprecated
        private Map<String, KeywordReportEntity> merge(Map<String, KeywordReportEntity> map1, Map<String, KeywordReportEntity> map2) {
            Map<String, KeywordReportEntity> _map = new HashMap<>();
            for (Iterator<Map.Entry<String, KeywordReportEntity>> iterator1 = map1.entrySet().iterator(); iterator1.hasNext(); ) {
                KeywordReportEntity vo = iterator1.next().getValue();
                for (Iterator<Map.Entry<String, KeywordReportEntity>> iterator2 = map2.entrySet().iterator(); iterator2.hasNext(); ) {
                    KeywordReportEntity _vo = iterator2.next().getValue();
                    if (_vo.getKeywordId().compareTo(vo.getKeywordId()) == 0) {
                        _vo.setPcImpression(_vo.getPcImpression() + vo.getPcImpression());
                        _vo.setPcClick(_vo.getPcClick() + vo.getPcClick());
                        _vo.setPcCtr(0.);
                        _vo.setPcCost(_vo.getPcCost() + vo.getPcCost());
                        _vo.setPcCpc(0.);
                        _vo.setPcPosition(_vo.getPcPosition() + vo.getPcPosition());
                        _vo.setPcConversion(_vo.getPcConversion() + vo.getPcConversion());
                        _map.put(_vo.getKeywordId().toString(), _vo);
                        iterator1.remove();
                        iterator2.remove();
                        break;
                    }
                }
            }

            for (Map.Entry<String, KeywordReportEntity> entry : map1.entrySet()) {
                KeywordReportEntity vo = entry.getValue();
                _map.put(vo.getKeywordId().toString(), vo);
            }

            for (Map.Entry<String, KeywordReportEntity> entry : map2.entrySet()) {
                KeywordReportEntity vo = entry.getValue();
                _map.put(vo.getKeywordId().toString(), vo);
            }

            return _map;
        }

    }
}