package com.ericsson.fusionany.im.dao.impl;

import com.ericsson.fusionany.im.dao.IMDao;
import com.ericsson.fusionany.im.entity.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 16-5-16.
 */
@Service("imDao")
public class IMDaoImpl implements IMDao{


    @Autowired
    public SessionFactory sessionFactory ;


    @Override
    public <T> List<T> queryForList(Class<? extends T> t,int startPosition,int total,Criterion... criterions) {
        // 1.create session
        Session session = sessionFactory.openSession();
        // 2.create criteria query
        Criteria criteria = session.createCriteria(t);
//        Restrictions.
        for (Criterion criterion:criterions) {
            criteria.add(criterion);
        }
        criteria.setFirstResult(startPosition);
        criteria.setMaxResults(total);
        // 3.get result
        List<T> result = criteria.list();
        // 4.close session
        close(session);
        if (null == result ){
            return new ArrayList<>();
        }else{
            return result;
        }
    }

    @Override
    public <T> T queryById(Class<? extends T> t, Long id) {
        // 1.create session
        Session session = sessionFactory.openSession();
        // 2.create criteria query
        Criteria criteria = session.createCriteria(t);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("id",id));
        List<T> resList = criteria.list();
        // 3.close session
        close(session);
        if (null == resList || resList.size()!=1){
            return null;
        }else{
            return resList.get(0);
        }
    }

    @Override
    public <T> int getCount(Class<? extends T> t, Criterion... criterions) {
        // 1.create session
        Session session = sessionFactory.openSession();
        // 2.create criteria query
        Criteria criteria = session.createCriteria(t.getClass());
        for (Criterion criterion:criterions) {
            criteria.add(criterion);
        }
        Integer totalCount = criteria.setProjection(Projections.rowCount()).uniqueResult().hashCode();
        // 3.close session
        close(session);
        if (null!= totalCount){
            return totalCount;
        }else{
            return 0;
        }
    }

    @Override
    public void saveOrUpdateItem(String entityName,Object instance){
        Session session = sessionFactory.openSession();
//        session.persist(entityName,instance);
        session.saveOrUpdate(entityName,instance);
        session.flush();
        close(session);
    }


    //--------------------private------------------
    private void close(Session session){
       if (null!=session){
           session.close();
       }
    }


    //---------------------get-set------------------
    /**
     * get hibernate session factory
     * @return
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
