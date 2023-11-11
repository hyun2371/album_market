package com.prgrms.album_market.member.service;

import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.dto.MemberMapper;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.common.exception.ErrorCode.ALREADY_EXIST_EMAIL;
import static com.prgrms.album_market.common.exception.ErrorCode.NOT_EXIST_MEMBER_ID;
import static com.prgrms.album_market.member.dto.MemberMapper.*;
import static com.prgrms.album_market.member.dto.MemberRequest.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public SignUpRes signUp(SignUpReq request) {
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(m -> {
                    throw new CustomException(ALREADY_EXIST_EMAIL);
                });
        Member savedMember = memberRepository.save(MemberMapper.toMember(request));
        return toSignUpRes(savedMember);
    }

    public LoginRes login(LoginReq request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);

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

    public GetMemberRes updateMember(Long memberId, UpdateReq request) {
        Member member = getMemberEntity(memberId);
        Member updatedMember = member.updateMember(request);

        return toGetMemberRes(updatedMember);
    }

    public void deleteMember(Long memberId) {
        Member member = getMemberEntity(memberId);
        memberRepository.delete(member);
    }

    public BalanceRes chargeMoney(Long memberId, int amount) {
        Member member = getMemberEntity(memberId);
        int balance = member.chargeMoney(amount);
        return toBalanceRes(balance);
    }

    public Member getMemberEntity(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER_ID));
    }
}
