package com.perfect.db.mongodb.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.perfect.commons.constants.MongoEntityConstants;
import com.perfect.core.AppContext;
import com.perfect.core.SystemUserInfo;
import com.perfect.dao.sys.SystemLogDAO;
import com.perfect.db.mongodb.base.AbstractSysBaseDAOImpl;
import com.perfect.dto.sys.SystemLogDTO;
import com.perfect.entity.sys.SystemLogEntity;
import com.perfect.param.SystemLogParams;
import com.perfect.utils.ObjectUtils;
import com.perfect.utils.paging.BootStrapPagerInfo;
import com.perfect.utils.paging.PagerInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yousheng on 15/12/16.
 */
@Repository
public class SystemLogDAOImpl extends AbstractSysBaseDAOImpl<SystemLogDTO, String> implements SystemLogDAO {

    @Override
    public Class<SystemLogEntity> getEntityClass() {
        return SystemLogEntity.class;
    }

    @Override
    public Class<SystemLogDTO> getDTOClass() {
        return SystemLogDTO.class;
    }

    @Override
    public List<SystemLogDTO> list(SystemLogParams params, int offset, int limit, String sort, String order) {

        Query query = new Query();
        if (params != null) {
            if (params.getStart() != null && params.getStart() != null) {
                query = query.addCriteria(Criteria.where("time").gte(params.getStart()).lte(params.getEnd()));
            } else if (params.getStart() != null && params.getEnd() == null) {
                query = query.addCriteria(Criteria.where("time").gte(params.getStart()));
            } else if (params.getStart() == null && params.getEnd() != null) {
                query = query.addCriteria(Criteria.where("time").lte(params.getEnd()));
            }


            if (!Strings.isNullOrEmpty(params.getUser())) {
                query = query.addCriteria(Criteria.where("user").is(params.getUser()));
            }
        }
//        PageRequest pageRequest = new PageRequest(page, size, (asc) ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        query.with(new Sort((order.equals("asc")) ? Sort.Direction.ASC : Sort.Direction.DESC, sort));

        query.skip(offset);
        query.limit(limit);
        List<SystemLogEntity> systemLogEntities = getSysMongoTemplate().find(query, getEntityClass());


        List<SystemLogDTO> systemLogDTOs = ObjectUtils.convert(systemLogEntities, getDTOClass());


        return systemLogDTOs;
    }


    @Override
    public void log(String txt) {
        SystemUserInfo systemUserInfo = AppContext.getSystemUserInfo();
        if (systemUserInfo == null) {
            return;
        }

        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setIp(AppContext.getRemote());
        systemLogEntity.setUser(systemUserInfo.getUser());
        systemLogEntity.setTime(System.currentTimeMillis());
        systemLogEntity.setDesc(txt);

        getSysMongoTemplate().insert(systemLogEntity);
        return;
    }

    @Override
    public Long getListTotalCount(SystemLogParams params) {
        Query query = new Query();
        if (params != null) {
            if (params.getStart() != null && params.getStart() != null) {
                query = query.addCriteria(Criteria.where("time").gte(params.getStart()).lte(params.getEnd()));
            } else if (params.getStart() != null && params.getEnd() == null) {
                query = query.addCriteria(Criteria.where("time").gte(params.getStart()));
            } else if (params.getStart() == null && params.getEnd() != null) {
                query = query.addCriteria(Criteria.where("time").lte(params.getEnd()));
            }
            if (!Strings.isNullOrEmpty(params.getUser())) {
                query = query.addCriteria(Criteria.where("user").is(params.getUser()));
            }
        }
        return getSysMongoTemplate().count(query, getEntityClass());
    }
}
