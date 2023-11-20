package com.prgrms.album_market.member.entity;

import com.prgrms.album_market.common.BaseEntity;
import com.prgrms.album_market.common.exception.CustomException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.common.exception.ErrorCode.INVALID_FORMAT_MONEY;
import static com.prgrms.album_market.common.exception.ErrorCode.NOT_ENOUGH_MONEY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Integer balance;
    private Address address;

    @Builder
    public Member(String email, String password, String name, String phoneNumber, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = 0;
    }

    @Builder
    public Member(Long id, String email, String password, String name, String phoneNumber, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = 0;
    }

    public void payMoney(int amount) {
        if (balance - amount < 0) {
            throw new CustomException(NOT_ENOUGH_MONEY);
        }
        balance -= amount;
    }

    public int chargeMoney(int amount) {
        if (amount < 1000) {//total price받고 0.1*
            throw new CustomException(INVALID_FORMAT_MONEY);
        }
        balance += amount;
        return balance;
    }
    public void refundMoney(int amount){
        balance += amount;
    }

    public void savePoint(int totalPrice) {
        balance += (int) (totalPrice * 0.1);
    }

    public Member updateInfo(final String email, final String name, final String phoneNumber, final String city, final String street, final String zipcode){
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address.setCity(city);
        this.address.setStreet(street);
        this.address.setZipcode(zipcode);
        return this;
    }
}
