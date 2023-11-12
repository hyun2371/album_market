package com.prgrms.album_market.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class MemberResponse {
    @AllArgsConstructor
    @Getter
    public static class SignUpRes {
        private Long createdId;
    }

    @AllArgsConstructor
    @Getter
    public static class LoginRes {
        private Long memberId;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class GetMemberRes {
        private Long memberId;
        private String email;
        private String name;
        private String phoneNumber;
        private String city;
        private String street;
        private String zipcode;
        private int balance;
    }

    @AllArgsConstructor
    @Getter
    public static class GetMemberListRes {
        private List<GetMemberRes> members;
    }

    @AllArgsConstructor
    @Getter
    public static class BalanceRes {
        private int balance;
    }
}
