package com.prgrms.album_market.member;

import static com.prgrms.album_market.member.dto.MemberMapper.toGetMemberListRes;
import static com.prgrms.album_market.member.dto.MemberMapper.toGetMemberResponse;

import com.prgrms.album_market.member.dto.request.SignUpRequest;
import com.prgrms.album_market.member.dto.response.GetMemberListResponse;
import com.prgrms.album_market.member.dto.response.GetMemberResponse;
import com.prgrms.album_market.member.dto.response.SignUpResponse;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberFixture {

    private static final String EMAIL1 = "hyun1@gmail.com";
    private static final String PASSWORD = "ddd123!!";
    private static final String NAME = "hyun";
    private static final String PHONE_NUMBER = "010-3242-3233";
    private static final String CITY = "서울시";
    private static final String STREET = "중구";
    private static final String ZIPCODE = "04583";

    private static final String EMAIL2 = "hyun2@gmail.com";

    public static Member member(String email) {
        return new Member(email, PASSWORD, NAME, PHONE_NUMBER,
            new Address(CITY, STREET, ZIPCODE));
    }

    public static Member member() {
        return new Member(EMAIL1, PASSWORD, NAME, PHONE_NUMBER,
            new Address(CITY, STREET, ZIPCODE));
    }

    public static SignUpResponse getSignUpRes() {
        return new SignUpResponse(1L);
    }

    public static SignUpRequest getSignUpReq() {
        return SignUpRequest.builder()
            .email(EMAIL1)
            .password(PASSWORD)
            .name(NAME)
            .phoneNumber(PHONE_NUMBER)
            .city(CITY)
            .street(STREET)
            .zipcode(ZIPCODE)
            .build();
    }

    public static GetMemberResponse getMemberRes() {
        return toGetMemberResponse(member(EMAIL1));
    }

    public static GetMemberListResponse getMemberListRes() {
        List<Member> members = new ArrayList<>();
        members.add(member(EMAIL1));
        members.add(member(EMAIL2));
        return toGetMemberListRes(members);
    }
}
