package com.booksys.example.repository;

import com.booksys.example.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface BookRepository extends JpaRepository<BookEntity,Integer>{
}
