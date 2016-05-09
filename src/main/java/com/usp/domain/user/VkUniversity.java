package com.usp.domain.user;

/**
 * Created by aleksandr on 09.05.16.
 */
public class VkUniversity {
    private int id_university;
    private int country;
    private int city;
    private String name;
    private int faculty;
    private String faculty_name;
    private String education_form;
    private String education_status;
    private int chair;
    private String chair_name;
    private int graduation;

    public int getId_university() {
        return id_university;
    }

    public int getCountry() {
        return country;
    }

    public int getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getFaculty() {
        return faculty;
    }

    public String getEducation_form() {
        return education_form;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public String getEducation_status() {
        return education_status;
    }

    public int getChair() {
        return chair;
    }

    public String getChair_name() {
        return chair_name;
    }

    public int getGraduation() {
        return graduation;
    }

    public VkUniversity(int id_university, int country, int city, String name, int faculty, String faculty_name, String education_form, String education_status, int chair, String chair_name, int graduation) {
        this.id_university = id_university;
        this.country = country;
        this.city = city;
        this.name = name;
        this.faculty_name = faculty_name;
        this.faculty = faculty;
        this.education_form = education_form;
        this.education_status = education_status;
        this.chair = chair;

        this.chair_name = chair_name;
        this.graduation = graduation;
    }

}
