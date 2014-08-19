package com.perfect.mongodb.base;

import com.perfect.dao.MongoCrudRepository;
import com.perfect.mongodb.base.BaseMongoTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by vbzer_000 on 2014/6/18.
 */
public abstract class AbstractUserBaseDAOImpl<T, ID extends Serializable> implements MongoCrudRepository<T, ID> {


    public abstract Class<T> getEntityClass();

    @Resource
    private MongoTemplate mongoSysTemplate;

    public MongoTemplate getSysMongoTemplate() {
        return mongoSysTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return BaseMongoTemplate.getUserMongo();
    }

    @Override
    public void delete(T t) {
        getMongoTemplate().remove(t);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        for (T t : entities) {
            getMongoTemplate().remove(t);
        }
    }

    @Override
    public void delete(ID id) {
        getMongoTemplate().remove(Query.query(Criteria.where("id").is(id)), getEntityClass());
    }

    @Override
    public void deleteAll() {
        getMongoTemplate().dropCollection(getEntityClass());
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        for (S s : entities) {
            getMongoTemplate().save(s);
        }
        return entities;
    }

    @Override
    public <S extends T> S save(S entity) {
        getMongoTemplate().save(entity);
        return entity;
    }

    @Override
    public void insert(T t) {
        getMongoTemplate().insert(t);
    }

    @Override
    public void insertAll(List<T> entities) {
        getMongoTemplate().insertAll(entities);
    }

    @Override
    public List<T> find(Map<String, Object> params, int skip, int limit) {
        Query query = new Query();
        Criteria criteria = null;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (criteria == null) {
                criteria = new Criteria(param.getKey());
                criteria.is(param.getValue());
                continue;
            }

            criteria.and(param.getKey()).is(param.getValue());
        }

        query.addCriteria(criteria).skip(skip).limit(limit);

        return getMongoTemplate().find(query, getEntityClass());
    }

    @Override
    public T findOne(ID id) {
        return getMongoTemplate().findOne(Query.query(Criteria.where("id").is(id)), getEntityClass());
    }


    @Override
    public Iterable<T> findAll() {
        return getMongoTemplate().findAll(getEntityClass());
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return getMongoTemplate().find(Query.query(Criteria.where("id").in(ids)), getEntityClass());
    }

    @Override
    public boolean exists(ID id) {
        return getMongoTemplate().exists(Query.query(Criteria.where("id").is(id)), getEntityClass());
    }

    @Override
    public long count() {
        return getMongoTemplate().count(null, getEntityClass());
    }
}
