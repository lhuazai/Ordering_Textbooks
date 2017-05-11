package com.booksys.example.repository;

import com.booksys.example.model.UserBookOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface UserBookOrderRepository extends JpaRepository<UserBookOrderEntity,Integer> {
}
