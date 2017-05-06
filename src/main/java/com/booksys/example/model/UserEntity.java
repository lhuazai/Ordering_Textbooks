package com.booksys.example.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by sjj on 2015/10/24 0024.
 */
@Entity
@Table(name = "user", schema = "", catalog = "book_system")
public class UserEntity {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String sex;
    private String email;
    private String qq;
    private Date signupTime;
    private Date lastSignin;
    private List<UserroleEntity> userroleEntities;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "userName", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    @Column(name = "SEX",nullable = false,insertable = true,updatable = true,length = 2)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "EMAIL",nullable = false,insertable = true,updatable = true,length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "QQ",nullable = false,insertable = true,updatable = true,length = 10)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "SIGNUP_TIME",nullable = false,insertable = true,updatable = false)
    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }
    @Basic
    @Column(name = "LAST_SIGN_IN",nullable = true,insertable = false,updatable = true)
    public Date getLastSignin() {
        return lastSignin;
    }

    public void setLastSignin(Date lastSignin) {
        this.lastSignin = lastSignin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


    public List<UserroleEntity> findUserroleEntities() {
        return userroleEntities;
    }

    public void setUserroleEntities(List<UserroleEntity> userroleEntities) {
        this.userroleEntities = userroleEntities;
    }
}
