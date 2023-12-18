package com.prgrms.album_market.member.dto.response;

import lombok.Builder;

@Builder
public record GetMemberResponse (
        Long memberId,
        String email,
        String name,
        String phoneNumber,
        String city,
        String street,
        String zipcode,
        int balance
){}
