package com.usp.dao;

import com.usp.dbpackage.City;

/**
 * Created by Aleksandra Marysheva on 15.03.2016.
 */
public interface CityDao {

    void insert(City city);
    City find(int id);
}
