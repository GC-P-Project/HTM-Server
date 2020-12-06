package com.gachon.htm.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private int height;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "upperTime")
    private long upperTime;

    @Column(name = "lowerTime")
    private long lowerTime;

    @Column(name = "allTime")
    private long allTime;

    @Column(name = "address_type")
    private AddressType addressType;

    public User() {}

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", age=" + age +
               ", name='" + name + '\'' +
               ", height=" + height +
               ", gender=" + gender +
               ", upperTime=" + upperTime +
               ", lowerTime=" + lowerTime +
               ", allTime=" + allTime +
               ", addressType=" + addressType +
               '}';
    }

    public enum AddressType {
        FACEBOOK, GOOGLE, KAKAO, NORMAL
    }

    public enum Gender {
        MALE, FEMALE
    }
}
