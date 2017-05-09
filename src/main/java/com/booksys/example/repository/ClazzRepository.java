package com.booksys.example.repository;

import com.booksys.example.model.ClazzEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface ClazzRepository extends JpaRepository<ClazzEntity,Integer> {
}
