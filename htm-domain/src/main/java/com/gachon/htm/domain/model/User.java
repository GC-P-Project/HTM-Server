package com.gachon.htm.domain.model;

import lombok.Setter;

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

    @Setter
    @Column(name = "upperTime")
    private Long upperTime;

    @Setter
    @Column(name = "lowerTime")
    private Long lowerTime;

    @Setter
    @Column(name = "allTime")
    private Long allTime;

    @Column(name = "address_type")
    private AddressType addressType;

    public User() {}

    public User with(String password) {
        this.password = password;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public Gender getGender() {
        return gender;
    }

    public Long getUpperTime() {
        return upperTime;
    }

    public Long getLowerTime() {
        return lowerTime;
    }

    public Long getAllTime() {
        return allTime;
    }

    public AddressType getAddressType() {
        return addressType;
    }

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
