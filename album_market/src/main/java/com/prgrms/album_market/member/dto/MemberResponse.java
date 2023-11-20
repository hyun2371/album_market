package com.prgrms.album_market.member.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class MemberResponse {

    public record SignUpRes (
        Long createdMemberId
    ){}

    public record LoginRes (
        Long memberId
    ){}

    @Builder
    public record GetMemberRes (
        Long memberId,
        String email,
        String name,
        String phoneNumber,
        String city,
        String street,
        String zipcode,
        int balance
    ){}


    public record GetMemberListRes (
        List<GetMemberRes> members
    ){}


    public record BalanceRes (
       int balance
    ){}
}
