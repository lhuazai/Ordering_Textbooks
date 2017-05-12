package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/12.
 */
@Entity
@Table(name = "plan", schema = "book_system", catalog = "")
public class PlanEntity {
    private int id;
    private String name;
    private String buildTime;
    private int buildUserId;
    private int active;
    private int count;
    private String activeStr;

    @Id
    @Column(name = "Id")
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
    @Column(name = "active")
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanEntity that = (PlanEntity) o;

        if (id != that.id) return false;
        if (buildUserId != that.buildUserId) return false;
        if (active != that.active) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + buildUserId;
        result = 31 * result + active;
        return result;
    }

    @Transient
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Transient
    public String getActiveStr() {
        return activeStr;
    }

    public void setActiveStr(String activeStr) {
        this.activeStr = activeStr;
    }
}
