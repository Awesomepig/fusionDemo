package com.ericsson.fusionany.im.dao;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

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
    <T> List<T> queryForList(Class<? extends T> t, int startPosition, int total, Order order, Criterion... criterions);


    /**
     * query for one record by id
     * @param t entity type
     * @param id primary key
     * @param <T>
     * @return the only record
     */
    <T> T queryById(Class<? extends T> t, Long id);


    /**
     * get count of message in <b>t</b> type
     * @param t entity type
     *          <ul>
     *              <li>MsgText.class: for text message</li>
     *              <li>MsgEmoji.class: for Emoji message</li>
     *              <li>MsgFile.class: for File message</li>
     *          </ul>
     *          for types not in the list will be Neglected,and count for whole message types
     * @param criterions  Query conditions
     * @param <T> entity class
     * @return the count result
     */
    <T> int getCount(Class<? extends T> t, Criterion... criterions);

    /**
     * save or update an item
     * @param entityName the entity name
     * @param instance the instance
     */
    void saveOrUpdateItem(String entityName, Object instance);
}
