package dao;

import dbpackage.Company;

public interface CompanyDao {

    void insert(Company company);
    Company getName(long group_id);
    boolean find(String title);
}
