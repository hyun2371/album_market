package com.prgrms.album_market.member.entity;

import com.prgrms.album_market.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import static com.prgrms.album_market.member.dto.MemberRequest.UpdateReq;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@DynamicInsert
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
    }

    public Member updateMember(UpdateReq request) {
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getPhoneNumber() != null) {
            this.phoneNumber = request.getPhoneNumber();
        }
        if (request.getCity() != null) {
            this.address.setCity(request.getCity());
        }
        if (request.getStreet() != null) {
            this.address.setStreet(request.getStreet());
        }
        if (request.getZipcode() != null) {
            this.address.setZipcode(request.getZipcode());
        }
        return this;
    }
}
