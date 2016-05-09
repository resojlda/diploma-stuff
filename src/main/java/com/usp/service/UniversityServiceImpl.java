package com.usp.service;

import com.usp.dao.UniversityDao;
import com.usp.dbpackage.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aleksandra Marysheva on 18.03.2016.
 */
@Service
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    public UniversityDao universityDao;

    @Override
    @Transactional
    public void insert(University university) {
        universityDao.insert(university);
    }

    @Override
    @Transactional
    public University getName(long id) {
        return universityDao.getName(id);
    }
}
