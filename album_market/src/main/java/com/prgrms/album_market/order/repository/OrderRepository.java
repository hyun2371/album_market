package com.prgrms.album_market.order.repository;

import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    Page<Order> findByMember(Member member, Pageable pageable);
    void deleteByMember(Member member);

    Page<Order> findAll(Pageable pageable);
}
