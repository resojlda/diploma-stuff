package com.usp.dao;

import com.usp.dbpackage.University;

public interface UniversityDao {
    void insert(University university);
    University getName(long id);
}