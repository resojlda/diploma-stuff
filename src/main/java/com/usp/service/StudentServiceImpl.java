package com.usp.service;

import com.usp.dao.StudentDao;
import com.usp.dbpackage.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Aleksandra Marysheva on 18.03.2016.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public StudentDao studentDao;

    @Override
    @Transactional
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    @Transactional
    public List findUniversityResults(int id) {
        return studentDao.findUniversityResults(id);
    }

}
