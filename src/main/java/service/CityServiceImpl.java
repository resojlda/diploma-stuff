package service;

import dao.CityDao;
import dbpackage.City;
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
    public City find(int id) {
        return cityDao.find(id);
    }




}
