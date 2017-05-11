package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/11.
 */
@Entity
@Table(name = "order", schema = "book_system", catalog = "")
public class OrderEntity {
    private int id;
    private String name;
    private String buildTime;
    private int buildUserId;
    private int inUse;
    private int count;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "buildTime")
    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    @Basic
    @Column(name = "buildUserId")
    public int getBuildUserId() {
        return buildUserId;
    }

    public void setBuildUserId(int buildUserId) {
        this.buildUserId = buildUserId;
    }

    @Basic
    @Column(name = "inUse")
    public int getInUse() {
        return inUse;
    }

    public void setInUse(int inUse) {
        this.inUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (inUse != that.inUse) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;
        if (buildUserId != that.buildUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + buildUserId;
        result = 31 * result + inUse;
        return result;
    }


    @Transient
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
