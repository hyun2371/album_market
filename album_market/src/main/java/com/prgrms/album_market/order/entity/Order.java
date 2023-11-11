package com.prgrms.album_market.order.entity;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.common.BaseEntity;
import com.prgrms.album_market.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.order.entity.OrderStatus.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "orders")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "album_id")
    private Album album;
    private Integer quantity;
    private int totalPrice;

    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(Member member, Album album, Integer quantity, int totalPrice) {
        this.member = member;
        this.album = album;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = ORDERED;
    }
}
