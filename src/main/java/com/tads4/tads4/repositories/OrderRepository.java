package com.tads4.tads4.repositories;

import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long>{


}
