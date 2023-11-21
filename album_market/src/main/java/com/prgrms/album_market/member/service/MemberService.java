package com.prgrms.album_market.member.service;

import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.repository.MemberRepository;
import com.prgrms.album_market.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.common.exception.ErrorCode.*;
import static com.prgrms.album_market.member.dto.MemberMapper.*;
import static com.prgrms.album_market.member.dto.MemberRequest.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public SignUpRes signUp(SignUpReq request) {
        validateEmail(request.email());
        String encodedPassword = encoder.encode(request.password());
        Member savedMember = memberRepository.save(toMember(request, encodedPassword));
        return toSignUpRes(savedMember);
    }

    @Transactional
    public LoginRes login(LoginReq request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER_EMAIL));
        if (!encoder.matches(request.password(), member.getPassword())) {
            throw new CustomException(WRONG_MEMBER_PASSWORD);
        }
        return toLoginRes(member);
    }

    @Transactional(readOnly = true)
    public GetMemberRes getMemberById(Long memberId) {
        Member member = getMemberEntity(memberId);
        return toGetMemberRes(member);
    }

    @Transactional(readOnly = true)
    public GetMemberListRes getMembers() {
        List<Member> members = memberRepository.findAll();

        return toGetMemberListRes(members);
    }
    @Transactional
    public GetMemberRes updateMember(Long memberId, UpdateReq request) {
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

        return toGetMemberRes(updatedMember);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = getMemberEntity(memberId);
        orderRepository.deleteByMember(member);
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public BalanceRes chargeMoney(Long memberId, int amount) {
        Member member = getMemberEntity(memberId);
        int balance = member.chargeMoney(amount);
        return toBalanceRes(balance);
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
