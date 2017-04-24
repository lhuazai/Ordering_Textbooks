package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/4/24.
 */
@Entity
@Table(name = "rolemenu", schema = "book_system", catalog = "")
public class RolemenuEntity {
    private int id;
    private int menuId;
    private int userRoleId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "menuId")
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "userRoleId")
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolemenuEntity that = (RolemenuEntity) o;

        if (id != that.id) return false;
        if (menuId != that.menuId) return false;
        if (userRoleId != that.userRoleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + menuId;
        result = 31 * result + userRoleId;
        return result;
    }
}
