package com.usp.dao;

import com.usp.dbpackage.Company;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insert(Company company) {
        sessionFactory.getCurrentSession().saveOrUpdate(company);
    }

    @Override
    public Company getName(long group_id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class);
        Object company = criteria.add(Restrictions.eq("group_id", group_id))
                .uniqueResult();
        return (Company)company;
    }

    @Override
    public boolean find(String title) {
        return (boolean)this.sessionFactory.getCurrentSession()
                .createQuery("select case when (count(scen) > 0)  then true else false end  \n" +
                        "from Company scen where scen.title = :title")
                .setParameter("title", title)
                .uniqueResult();
    }
}
