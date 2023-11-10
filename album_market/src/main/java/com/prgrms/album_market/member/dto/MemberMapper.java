package com.prgrms.album_market.member.dto;

import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.member.dto.MemberRequest.SignUpReq;
import static com.prgrms.album_market.member.dto.MemberResponse.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MemberMapper {

    // entity -> dto
    public static SignUpRes toSignUpRes(Member member){
        return new SignUpRes(member.getId());
    }
    public static GetMemberRes toGetMemberRes(Member member){
        return GetMemberRes.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .balance(member.getBalance())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode()).build();
    }

    public static GetMemberListRes toGetMemberListRes(List<Member> members){
        List<GetMemberRes> list = new ArrayList<>();
        for (Member member: members) {
            list.add(toGetMemberRes(member));
        }
        return new GetMemberListRes(list);
    }

    public static LoginRes toLoginRes(Member member){
        return new LoginRes(member.getId());
    }

    public static BalanceRes toBalanceRes(int balance){
        return new BalanceRes(balance);
    }




    // dto -> entity
    public static Member toMember(SignUpReq request){
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .address(new Address(request.getCity(), request.getStreet(), request.getZipcode()))
                .build();
    }
}

