package com.usp.dao;


import com.usp.dbpackage.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student student);
    List findUniversityResults(int id);
}
