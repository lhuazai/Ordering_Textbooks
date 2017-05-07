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
    private int roleId;

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
    @Column(name = "roleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolemenuEntity that = (RolemenuEntity) o;

        if (id != that.id) return false;
        if (menuId != that.menuId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + menuId;
        result = 31 * result + roleId;
        return result;
    }
}
