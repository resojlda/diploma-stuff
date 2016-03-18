package dao;

import dbpackage.University;

public interface UniversityDao {
    void insert(University university);
    University getName(long id);
}