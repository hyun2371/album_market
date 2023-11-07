package com.prgrms.album_market.order.entity;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.base.BaseEntity;
import com.prgrms.album_market.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.order.entity.OrderStatus.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Album album;

    private Integer totalPrice;

    @Enumerated(STRING)
    private OrderStatus status;

    public Order(Member member, Album album, Integer totalPrice) {
        this.member = member;
        this.album = album;
        this.totalPrice = totalPrice;
        this.status = ORDERED;
    }
}
