package com.perfect.service;

import com.perfect.dto.adgroup.AdgroupDTO;
import com.perfect.dto.backup.KeywordBackUpDTO;
import com.perfect.dto.campaign.CampaignDTO;
import com.perfect.dto.campaign.CampaignTreeDTO;
import com.perfect.dto.keyword.KeywordDTO;
import com.perfect.dto.keyword.KeywordInfoDTO;
import com.perfect.utils.paging.PagerInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by john on 2014/8/19.
 */
public interface AssistantKeywordService {
    PagerInfo getKeyWords(String cid,String aid,Integer nowPage,Integer pageSize);

    void deleteByKwIds(List<String> kwids);

    KeywordDTO updateKeyword( KeywordDTO keywordDTO);

    Map<String,Object> validateDeleteByInput(Long accountId,String deleteInfos);

    Map<String,Object> validateDeleteKeywordByChoose(Long accountId,String chooseInfos, String keywordNames,Integer nowPage,Integer pageSize);

    List<CampaignTreeDTO> getCampaignTree(Long accountId);

    Map<String,Object> batchAddOrUpdateKeywordByChoose(Long accountId, Boolean isReplace, String chooseInfos, String keywordInfos);

    void batchAddUpdateKeyword(List<KeywordInfoDTO> insertDtos, List<KeywordInfoDTO> updateDtos, Boolean isReplace);

    Iterable<CampaignDTO> getCampaignByAccountId();

    Iterable<AdgroupDTO> getAdgroupByCid(String cid);

    void saveSearchwordKeyword(List<KeywordDTO> list);

    void setNeigWord(String agid, String keywords, Integer neigType);

    List<KeywordInfoDTO> getKeywordListByIds(List<Long> ids);

    KeywordDTO findByParams(Map<String,Object> params);

    KeywordDTO findByObjId(String obj);

    KeywordDTO findByLongId(Long id);

    void updateByObjId(KeywordDTO dto);

    void update(KeywordDTO keywordDTO,KeywordDTO keywordBackUpDTO);

    void insert(KeywordDTO dto);
}
