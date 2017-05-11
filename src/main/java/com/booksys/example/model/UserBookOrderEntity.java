package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/11.
 */
@Entity
@Table(name = "userBookOrder", schema = "book_system", catalog = "")
public class UserBookOrderEntity {
    private int id;
    private int bookOrderId;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookOrderId")
    public int getBookOrderId() {
        return bookOrderId;
    }

    public void setBookOrderId(int bookOrderId) {
        this.bookOrderId = bookOrderId;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBookOrderEntity that = (UserBookOrderEntity) o;

        if (id != that.id) return false;
        if (bookOrderId != that.bookOrderId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookOrderId;
        result = 31 * result + userId;
        return result;
    }
}
