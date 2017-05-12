package com.booksys.example.repository;

import com.booksys.example.model.CourseBookStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface CourseBookStudentRepository extends JpaRepository<CourseBookStudentEntity,Integer> {
}
