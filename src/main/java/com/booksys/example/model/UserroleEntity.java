package com.booksys.example.model;

import javax.management.relation.Role;
import javax.persistence.*;

/**
 * Created by Administrator on 2017/4/24.
 */
@Entity
@Table(name = "userrole", schema = "book_system", catalog = "")
public class UserroleEntity {
    private int id;
    private int roleId;
    private int userId;
    private UserEntity userEntity;
    private RoleEntity roleEntity;
    @Id
    @Column(name = "id",nullable = false,updatable = false,insertable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "roleId",nullable = false,updatable = false,insertable = true)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "userId",nullable = false,updatable = false,insertable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToOne(targetEntity = UserEntity.class)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @OneToOne(targetEntity = RoleEntity.class)
    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserroleEntity that = (UserroleEntity) o;

        if (id != that.id) return false;
        if (roleId != that.roleId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + roleId;
        result = 31 * result + userId;
        return result;
    }
}
