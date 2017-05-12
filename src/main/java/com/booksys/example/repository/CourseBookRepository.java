package com.booksys.example.repository;

import com.booksys.example.model.CourseBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface CourseBookRepository extends JpaRepository<CourseBookEntity,Integer> {
    List<CourseBookEntity> findAllByCourseId(int courseId);
}
