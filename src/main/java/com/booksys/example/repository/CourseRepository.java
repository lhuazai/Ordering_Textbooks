package com.booksys.example.repository;

import com.booksys.example.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
    List<CourseEntity> findAllByPlanIdOrderByIdDesc(int planId);
}
