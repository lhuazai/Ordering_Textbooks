package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by zhangsc on 2017/5/10.
 */
@Entity
@Table(name = "clazzUser", schema = "book_system", catalog = "")
public class ClazzUserEntity {
    private int id;
    private int clazzId;
    private int userId;
    private UserEntity userEntity;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "clazzId")
    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClazzUserEntity that = (ClazzUserEntity) o;

        if (id != that.id) return false;
        if (clazzId != that.clazzId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + clazzId;
        result = 31 * result + userId;
        return result;
    }

    @Transient
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
