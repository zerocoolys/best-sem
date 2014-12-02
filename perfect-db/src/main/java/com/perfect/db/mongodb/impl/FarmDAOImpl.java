package com.perfect.db.mongodb.impl;

import com.perfect.dao.FarmDAO;
import com.perfect.dto.UrlDTO;
import com.perfect.db.mongodb.base.AbstractSysBaseDAOImpl;
import com.perfect.utils.paging.Pager;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by vbzer_000 on 2014/9/24.
 */
@Component("farmDAO")
public class FarmDAOImpl extends AbstractSysBaseDAOImpl<UrlDTO, String> implements FarmDAO {
    @Override
    public Class<UrlDTO> getEntityClass() {
        return UrlDTO.class;
    }

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public UrlDTO takeOne() {
        return getSysMongoTemplate().findAndModify(
                Query.query(
                        Criteria.where("i").is(true)
                                .and("f").lte(System.currentTimeMillis())).limit(1).with(new Sort(Sort.Direction.ASC, "f")),
                Update.update("i", false),
                FindAndModifyOptions.options().returnNew(true),
                getEntityClass());
    }

    @Override
    public void returnOne(UrlDTO urlEntity) {
        urlEntity.setIdle(true);
        getSysMongoTemplate().save(urlEntity);
    }

    @Override
    /*
     查询最后执行时间在5分钟之前的账号
     */
    public List<UrlDTO> allUnused() {
        return getSysMongoTemplate()
                .find(Query.query(Criteria.where("f").lte(System.currentTimeMillis() - 5 * 60 * 1000))
                        .with(new Sort(Sort.Direction.ASC, "f")), getEntityClass());
    }

}
