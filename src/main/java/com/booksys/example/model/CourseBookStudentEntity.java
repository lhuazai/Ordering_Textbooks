package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by zhangsc on 2017/5/12.
 */
@Entity
@Table(name = "courseBookStudent", schema = "book_system", catalog = "")
public class CourseBookStudentEntity {
    private int id;
    private int bookId;
    private String userId;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookId")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseBookStudentEntity that = (CourseBookStudentEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
