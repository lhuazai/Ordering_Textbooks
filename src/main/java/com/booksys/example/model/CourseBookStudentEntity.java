package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by zhangsc on 2017/5/12.
 */
@Entity
@Table(name = "courseBookStudent", schema = "book_system", catalog = "")
public class CourseBookStudentEntity {
    private int id;
    private int userId;
    private int courseBookId;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "courseBookId")
    public int getCourseBookId() {
        return courseBookId;
    }

    public void setCourseBookId(int courseBookId) {
        this.courseBookId = courseBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseBookStudentEntity that = (CourseBookStudentEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (courseBookId != that.courseBookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + courseBookId;
        return result;
    }
}
