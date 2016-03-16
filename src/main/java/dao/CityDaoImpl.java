package dao;

import dbpackage.City;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Aleksandra Marysheva on 15.03.2016.
 */
@Repository
public class CityDaoImpl implements CityDao{


    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void insert(City city) {
        sessionFactory.getCurrentSession().saveOrUpdate(city);
    }

    @Override
    public City find(int id){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
        Object city = criteria.add(Restrictions.eq("vk_id", 213))
                .uniqueResult();
        return (City)city;
    }

}
