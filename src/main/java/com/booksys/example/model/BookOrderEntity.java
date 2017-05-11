package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/11.
 */
@Entity
@Table(name = "bookOrder", schema = "book_system", catalog = "")
public class BookOrderEntity {
    private int id;
    private int orderId;
    private int bookId;
    private String buildTime;
    private int buildUserId;
    private BookEntity bookEntity;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orderId")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookOrderEntity that = (BookOrderEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (bookId != that.bookId) return false;
        if (buildUserId != that.buildUserId) return false;
        if (buildTime != null ? !buildTime.equals(that.buildTime) : that.buildTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + bookId;
        result = 31 * result + (buildTime != null ? buildTime.hashCode() : 0);
        result = 31 * result + buildUserId;
        return result;
    }

    @Transient
    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
}
