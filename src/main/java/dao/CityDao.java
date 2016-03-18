package dao;

import dbpackage.City;

/**
 * Created by Aleksandra Marysheva on 15.03.2016.
 */
public interface CityDao {

    void insert(City city);
    City find(long id);
}
