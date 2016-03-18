package service;

import dao.CompanyDao;
import dbpackage.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Aleksandra Marysheva on 18.03.2016.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;


    @Override
    @Transactional
    public void insert(Company company) {
        companyDao.insert(company);
    }

    @Override
    @Transactional
    public Company getName(long group_id) {
        return companyDao.getName(group_id);
    }

    @Override
    @Transactional
    public boolean find(String title) {
        return companyDao.find(title);
    }
}
