package com.booksys.example.repository;

import com.booksys.example.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
}
