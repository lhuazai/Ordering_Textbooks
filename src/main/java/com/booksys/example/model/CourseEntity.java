package com.booksys.example.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhangsc on 2017/5/12.
 */
@Entity
@Table(name = "course", schema = "book_system", catalog = "")
public class CourseEntity {
    private String name;
    private int id;
    private int planId;
    private int count;
    private boolean isUserChosen=false;
    private List<CourseBookEntity> courseBookList;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "planId")
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (planId != that.planId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + planId;
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
    public List<CourseBookEntity> getCourseBookList() {
        return courseBookList;
    }

    public void setCourseBookList(List<CourseBookEntity> courseBookList) {
        this.courseBookList = courseBookList;
    }
}
