package dao;

import dbpackage.Student;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void insert(Student student) {
        sessionFactory.getCurrentSession().saveOrUpdate(student);
    }

    @Override
    public List findUniversityResults(int id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        return criteria.add(Restrictions.eq("university_id", id)).list();
    }
}
