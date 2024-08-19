package com.example.Library;

public class Students {
    private Long id;
    private String name;
    private String surname;
    private Integer year;


    public Students() {}

    public Students(Long id, String name, String surname, Integer year) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getYear() {
        return year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
