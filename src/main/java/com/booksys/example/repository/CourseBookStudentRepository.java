package com.booksys.example.repository;

import com.booksys.example.model.CourseBookStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface CourseBookStudentRepository extends JpaRepository<CourseBookStudentEntity,Integer> {
    List<CourseBookStudentEntity> findAllByCourseBookIdAndUserId(int courseBookId,int userId);
    List<CourseBookStudentEntity> findAllByCourseBookId(int courseBookId);
    @Modifying
    @Transactional
    @Query("delete from CourseBookStudentEntity m where m.courseBookId=:qCourseBookId and m.userId=:quserId")
    void deleteAllByCourseBookIdAndUserId(@Param("qCourseBookId") int qCourseBookId, @Param("quserId") int quserId);
}
