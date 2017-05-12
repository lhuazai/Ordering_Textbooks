package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by zhangsc on 2017/5/12.
 */
@Entity
@Table(name = "courseBook", schema = "book_system", catalog = "")
public class CourseBookEntity {
    private int id;
    private int bookId;
    private int courseId;
    private BookEntity book;
    private boolean isChosen;
    private boolean teacherAdd;
    private int count;
    private float totalPrice;

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
    @Column(name = "courseId")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseBookEntity that = (CourseBookEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;
        if (courseId != that.courseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + courseId;
        return result;
    }

    @Transient
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @Transient
    public boolean getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean chosen) {
        isChosen = chosen;
    }

    @Transient
    public boolean getTeacherAdd() {
        return teacherAdd;
    }

    public void setTeacherAdd(boolean teacherAdd) {
        this.teacherAdd = teacherAdd;
    }


    @Transient
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Transient
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
