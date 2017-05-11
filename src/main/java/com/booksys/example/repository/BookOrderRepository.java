package com.booksys.example.repository;

import com.booksys.example.model.BookOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface BookOrderRepository extends JpaRepository<BookOrderEntity,Integer> {
    List<BookOrderEntity> findAllByOrderId(int orderId);
}
