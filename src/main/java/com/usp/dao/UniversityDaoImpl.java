package com.usp.dao;

import com.usp.dbpackage.University;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityDaoImpl implements UniversityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(University university) {
        sessionFactory.getCurrentSession().saveOrUpdate(university);
    }

    @Override
    public University getName(long id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(University.class);
        Object university = criteria.add(Restrictions.eq("university_id", id))
                .uniqueResult();
        return (University)university;
    }
}
