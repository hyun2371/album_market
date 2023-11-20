package com.prgrms.album_market.member;

import com.prgrms.album_market.member.dto.MemberRequest;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.member.dto.MemberMapper.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;
public class MemberDataFactory {
    private static final String EMAIL1 = "hyun1@gmail.com";
    private static final String EMAIL2 = "hyun2@gmail.com";
    public static Member getMember(String email){
        return new Member(email, "ddd123!", "hyun", "010-3242-3233",
                new Address("서울시", "중구", "04583"));
    }

    public static Member getMember(){
        Member member = new Member("hyun@gmail.com", "ddd123!", "hyun", "010-3242-3233",
                new Address("서울시", "중구", "04583"));
        member.chargeMoney(10000);
        return member;
    }

    public static SignUpRes getSignUpRes(){
        return toSignUpRes(getMember(EMAIL1));
    }

    public static MemberRequest.SignUpReq getSignUpReq(){
        return MemberRequest.SignUpReq.builder()
                .email(EMAIL1)
                .password("ddd1234!")
                .name("hyun")
                .phoneNumber("010-3242-3233")
                .city("서울시")
                .street("중구")
                .zipcode("04583")
                .build();
    }

    public static GetMemberRes getMemberRes(){
        return toGetMemberRes(getMember(EMAIL1));
    }

    public static GetMemberListRes getMemberListRes(){
        List<Member> members = new ArrayList<>();
        members.add(getMember(EMAIL1));
        members.add(getMember( EMAIL2));
        return toGetMemberListRes(members);
    }
}
