package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/9.
 */
@Entity
@Table(name = "book", schema = "book_system", catalog = "")
public class BookEntity {
    private int id;
    private String name;
    private String publisher;
    private int addUserId;
    private String addTime;
    private int inUse;
    private UserEntity user;

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
    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "addUserId")
    public int getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(int addUserId) {
        this.addUserId = addUserId;
    }

    @Basic
    @Column(name = "addTime")
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
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

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (addUserId != that.addUserId) return false;
        if (inUse != that.inUse) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (addTime != null ? !addTime.equals(that.addTime) : that.addTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + addUserId;
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + inUse;
        return result;
    }


    @Transient
    public UserEntity getUser(){
        return user;
    }
    public void setUser(UserEntity user){
        this.user=user;
    }
}
