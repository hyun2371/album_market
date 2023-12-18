package com.prgrms.album_market.order.entity;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.common.BaseEntity;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.common.exception.ErrorCode.*;
import static com.prgrms.album_market.order.entity.OrderStatus.*;
import static jakarta.persistence.EnumType.STRING;
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
    public Order(Member member, Album album, Integer quantity) {
        this.member = member;
        this.album = album;
        this.quantity = quantity;
        this.totalPrice = album.getPrice() * quantity;
        this.orderStatus = ORDERED;

        album.reduceStock(quantity);
        member.payMoney(totalPrice);
    }

    @Builder
    public Order(Long id, Member member, Album album, Integer quantity) {
        this.id = id;
        this.member = member;
        this.album = album;
        this.quantity = quantity;
        this.totalPrice = album.getPrice() * quantity;
        this.orderStatus = ORDERED;

        album.reduceStock(quantity);
        member.payMoney(totalPrice);
    }

    public void cancelOrder() {
        if (orderStatus == CANCELED) {
            throw new CustomException(ALREADY_CANCELED_ALBUM);
        }
        if (orderStatus == DELIVERED) {
            throw new CustomException(DELIVERED_CAN_NOT_CANCEL);
        }
        orderStatus = CANCELED;
        member.refundMoney(totalPrice);
    }

    public void deliverOrder() {
        if (orderStatus == DELIVERED) {
            throw new CustomException(CANCELED_CAN_NOT_DELIVER);
        }
        if (orderStatus == CANCELED) {
            throw new CustomException(ALREADY_CANCELED_ALBUM);
        }
        orderStatus = DELIVERED;
        member.savePoint(totalPrice);
    }
}
