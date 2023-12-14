package com.prgrms.album_market.member.service;

import static com.prgrms.album_market.common.exception.ErrorCode.*;
import static com.prgrms.album_market.member.dto.MemberMapper.*;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.album_market.album.repository.AlbumLikeRepository;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.dto.request.LoginRequest;
import com.prgrms.album_market.member.dto.request.SignUpRequest;
import com.prgrms.album_market.member.dto.request.UpdateRequest;
import com.prgrms.album_market.member.dto.response.GetMemberListResponse;
import com.prgrms.album_market.member.dto.response.GetMemberResponse;
import com.prgrms.album_market.member.dto.response.LoginResponse;
import com.prgrms.album_market.member.dto.response.SignUpResponse;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.repository.MemberRepository;
import com.prgrms.album_market.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final AlbumLikeRepository albumLikeRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        validateEmail(request.email());
        String encodedPassword = encoder.encode(request.password());
        Member savedMember = memberRepository.save(toMember(request, encodedPassword));
        return toSignUpResponse(savedMember);
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER_EMAIL));
        if (!encoder.matches(request.password(), member.getPassword())) {
            throw new CustomException(WRONG_MEMBER_PASSWORD);
        }
        return toLoginResponse(member);
    }

    @Transactional(readOnly = true)
    public GetMemberResponse getMemberById(Long memberId) {
        Member member = getMemberEntity(memberId);
        return toGetMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public GetMemberListResponse getMembers() {
        List<Member> members = memberRepository.findAll();

        return toGetMemberListRes(members);
    }
    @Transactional
    public GetMemberResponse updateMember(Long memberId, UpdateRequest request) {
        Member member = getMemberEntity(memberId);
        if (!request.email().equals(member.getEmail())){
            validateEmail(request.email());
        }

        Member updatedMember = member.updateInfo(
                request.email(),
                request.name(),
                request.phoneNumber(),
                request.city(),
                request.street(),
                request.zipcode());

        return toGetMemberResponse(updatedMember);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = getMemberEntity(memberId);
        albumLikeRepository.deleteByMember(member);
        orderRepository.deleteByMember(member);
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public GetMemberResponse chargeMoney(Long memberId, int amount) {
        Member member = getMemberEntity(memberId);
        member.chargeMoney(amount);
        return toGetMemberResponse(member);
    }

    @Transactional(readOnly = true)
    public Member getMemberEntity(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER_ID));
    }

    @Transactional(readOnly = true)
    public void validateEmail(String email) {
        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new CustomException(ALREADY_EXIST_EMAIL);
        });
    }
}
