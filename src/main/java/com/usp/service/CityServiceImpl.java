package com.usp.service;

import com.usp.dao.CityDao;
import com.usp.dbpackage.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aleksandra Marysheva on 15.03.2016.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    @Transactional
    public void insert(City city) {
        cityDao.insert(city);
    }

    @Override
    @Transactional
    public City find(long id) {
        return cityDao.find(id);
    }




}
