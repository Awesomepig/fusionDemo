package com.ericsson.fusionany.im.dao;


import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by eric on 16-5-16.
 */
public interface IMDao {

    /**
     * query for a list of Class extends T
     * @param t class type
     * @param startPosition position to start
     * @param total max result number
     * @param criterions  Query conditions
     * @return the result list
     */
    public <T> List<T> queryForList(Class<? extends T> t,int startPosition,int total,Criterion... criterions);


    /**
     * query for one record by id
     * @param t entity type
     * @param id primary key
     * @param <T>
     * @return the only record
     */
    public <T> T queryById(Class<? extends T> t,Long id);



    public <T> int getCount(Class<? extends T> t,Criterion... criterions);

    /**
     * save or update an item
     * @param entityName the entity name
     * @param instance the instance
     */
    public void saveOrUpdateItem(String entityName,Object instance);
}
