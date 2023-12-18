package com.prgrms.album_market.member.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import static lombok.AccessLevel.*;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
