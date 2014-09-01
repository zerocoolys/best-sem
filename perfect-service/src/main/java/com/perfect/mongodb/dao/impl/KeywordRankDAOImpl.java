package com.perfect.mongodb.dao.impl;

import com.perfect.dao.KeywordRankDAO;
import com.perfect.entity.bidding.KeywordRankEntity;
import com.perfect.mongodb.base.AbstractUserBaseDAOImpl;
import com.perfect.mongodb.utils.Pager;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.perfect.mongodb.utils.EntityConstants.KEYWORD_ID;

/**
 * Created by vbzer_000 on 2014/8/31.
 */
@Component
public class KeywordRankDAOImpl extends AbstractUserBaseDAOImpl<KeywordRankEntity, String> implements KeywordRankDAO {
    @Override
    public Class<KeywordRankEntity> getEntityClass() {
        return KeywordRankEntity.class;
    }

    @Override
    public Pager findByPager(int start, int pageSize, Map<String, Object> q, int orderBy) {
        return null;
    }

    @Override
    public KeywordRankEntity findByKeywordId(Long id) {
        return getMongoTemplate().findOne(Query.query(Criteria.where(KEYWORD_ID).is(id)), getEntityClass());
    }
}
