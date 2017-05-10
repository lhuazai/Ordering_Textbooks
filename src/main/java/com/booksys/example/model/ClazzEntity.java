package com.booksys.example.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/5/9.
 */
@Entity
@Table(name = "class", schema = "book_system", catalog = "")
public class ClazzEntity {
    private int id;
    private String name;
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

        ClazzEntity that = (ClazzEntity) o;

        if (id != that.id) return false;
        if (inUse != that.inUse) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
