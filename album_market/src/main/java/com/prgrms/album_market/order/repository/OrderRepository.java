package com.prgrms.album_market.order.repository;

import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByMember(Member member);
    void deleteByMember(Member member);
}
