package com.shopease.backend.repositories;
import com.shopease.backend.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
