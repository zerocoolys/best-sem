package com.perfect.mongodb.dao.impl;

import com.perfect.dao.AdgroupBackUpDAO;
import com.perfect.entity.backup.AdgroupBakcUpEntity;
import com.perfect.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.mongodb.utils.Pager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by XiaoWei on 2014/9/4.
 */
@Component
public class AdgroupBackUpDAOImpl extends AbstractUserBaseDAOImpl<AdgroupBakcUpEntity,Long> implements AdgroupBackUpDAO{

    @Override
    public Class<AdgroupBakcUpEntity> getEntityClass() {
        return null;
    }

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }
}
