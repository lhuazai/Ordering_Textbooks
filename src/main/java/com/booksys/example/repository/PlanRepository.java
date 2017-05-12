package com.booksys.example.repository;

import com.booksys.example.model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {
    @Modifying // 说明该方法是修改操作
    @Transactional // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update PlanEntity us set us.active=:qActive where us.id=:qId")
    void updatePlanLock(@Param("qActive") int qActive,@Param("qId") int qId);
}
