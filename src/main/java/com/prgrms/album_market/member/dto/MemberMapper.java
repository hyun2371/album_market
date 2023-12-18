package com.prgrms.album_market.member.dto;

import com.prgrms.album_market.member.dto.request.SignUpRequest;
import com.prgrms.album_market.member.dto.response.GetMemberListResponse;
import com.prgrms.album_market.member.dto.response.GetMemberResponse;
import com.prgrms.album_market.member.dto.response.LoginResponse;
import com.prgrms.album_market.member.dto.response.SignUpResponse;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MemberMapper {

    // entity -> dto
    public static SignUpResponse toSignUpResponse(Member member) {
        return new SignUpResponse(member.getId());
    }

    public static GetMemberResponse toGetMemberResponse(Member member) {
        return GetMemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .balance(member.getBalance())
                .city(member.getAddress().getCity())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode()).build();
    }

    public static GetMemberListResponse toGetMemberListRes(List<Member> members) {
        List<GetMemberResponse> list = new ArrayList<>();
        for (Member member : members) {
            list.add(toGetMemberResponse(member));
        }
        return new GetMemberListResponse(list);
    }

    public static LoginResponse toLoginResponse(Member member) {
        return new LoginResponse(member.getId());
    }


    // dto -> entity
    public static Member toMember(SignUpRequest request, String encodedPassword) {
        return Member.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .phoneNumber(request.phoneNumber())
                .address(new Address(request.city(), request.street(), request.zipcode()))
                .build();
    }
}

