package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by zhangsc on 2017/5/12.
 */
@Entity
@Table(name = "courseClazz", schema = "book_system", catalog = "")
public class CourseClazzEntity {
    private int id;
    private int courseId;
    private int clazzId;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "courseId")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "clazzId")
    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseClazzEntity that = (CourseClazzEntity) o;

        if (id != that.id) return false;
        if (courseId != that.courseId) return false;
        if (clazzId != that.clazzId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + courseId;
        result = 31 * result + clazzId;
        return result;
    }
}
