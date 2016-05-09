package com.usp.dbpackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Aleksandra Marysheva on 14.03.2016.
 */
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int university_id;
    private long vk_id;
    private String first_name;
    private String last_name;
    private long city_id;
    private String photo;
    private String career;
    private String universities;
//    private String city;//??????

    public Student() {
    }

    public Student(int university_id, long vk_id, String first_name, String last_name, long city_id, String photo, String career, String universities) {
        this.university_id = university_id;
        this.vk_id = vk_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city_id = city_id;
        this.photo = photo;
        this.career = career;
        this.universities = universities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(int university_id) {
        this.university_id = university_id;
    }

    public long getVk_id() {
        return vk_id;
    }

    public void setVk_id(long vk_id) {
        this.vk_id = vk_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getUniversities() {
        return universities;
    }

    public void setUniversities(String universities) {
        this.universities = universities;
    }
}
