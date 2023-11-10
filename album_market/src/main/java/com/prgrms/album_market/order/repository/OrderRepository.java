package com.prgrms.album_market.order.repository;

import com.prgrms.album_market.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
