package com.prgrms.album_market.member;

import com.prgrms.album_market.member.dto.MemberRequest;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.member.dto.MemberMapper.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;
public class MemberFixture {
    private static final String EMAIL1 = "hyun1@gmail.com";
    private static final String PASSWORD = "ddd123!!";
    private static final String NAME = "hyun";
    private static final String PHONE_NUMBER = "010-3242-3233";
    private static final String CITY = "서울시";
    private static final String STREET = "중구";
    private static final String ZIPCODE = "04583";

    private static final String EMAIL2 = "hyun2@gmail.com";
    public static Member member(String email){
        return new Member(email, PASSWORD, NAME, PHONE_NUMBER,
                new Address(CITY, STREET, ZIPCODE));
    }

    public static Member member(){
        return new Member(EMAIL1, PASSWORD, NAME, PHONE_NUMBER,
                new Address(CITY, STREET, ZIPCODE));
    }

    public static SignUpRes getSignUpRes(){
        return new SignUpRes(1L);
    }

    public static MemberRequest.SignUpReq getSignUpReq(){
        return MemberRequest.SignUpReq.builder()
                .email(EMAIL1)
                .password(PASSWORD)
                .name(NAME)
                .phoneNumber(PHONE_NUMBER)
                .city(CITY)
                .street(STREET)
                .zipcode(ZIPCODE)
                .build();
    }

    public static GetMemberRes getMemberRes(){
        return toGetMemberRes(member(EMAIL1));
    }

    public static GetMemberListRes getMemberListRes(){
        List<Member> members = new ArrayList<>();
        members.add(member(EMAIL1));
        members.add(member( EMAIL2));
        return toGetMemberListRes(members);
    }
}
