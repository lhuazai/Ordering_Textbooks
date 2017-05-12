package com.booksys.example.repository;

import com.booksys.example.model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangsc on 2017/5/12.
 */
public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {
}
