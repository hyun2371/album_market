package com.prgrms.album_market.member.service;

import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.dto.MemberMapper;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.prgrms.album_market.common.exception.ErrorCode.*;
import static com.prgrms.album_market.member.dto.MemberMapper.*;
import static com.prgrms.album_market.member.dto.MemberRequest.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ResponseEntity<SignUpRes> signUp(SignUpReq request){
        memberRepository.findByEmail(request.getEmail())
                .ifPresent(m -> {
                    throw new CustomException(ALREADY_EXIST_EMAIL);
                });
        Member savedMember = memberRepository.save(MemberMapper.toMember(request));
        return ResponseEntity.ok(toSignUpRes(savedMember));
    }

    public ResponseEntity<LoginRes> login(LoginReq request){
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(RuntimeException::new);

        return ResponseEntity.ok(toLoginRes(member));
    }

    public ResponseEntity<GetMemberRes> getMemberById(Long memberId){
        Member member = getMemberEntity(memberId);
        return ResponseEntity.ok(toGetMemberRes(member));
    }

    public ResponseEntity<GetMemberListRes> getMembers(){
        List<Member> members = memberRepository.findAll();

        return ResponseEntity.ok(toGetMemberListRes(members));
    }

    public ResponseEntity<GetMemberRes> update(Long memberId, UpdateReq request){
        Member member = getMemberEntity(memberId);
        Member updatedMember = member.updateMember(request);

        return ResponseEntity.ok(toGetMemberRes(updatedMember));
    }
    public ResponseEntity<Boolean> delete(Long memberId){
        Member member = getMemberEntity(memberId);
        memberRepository.delete(member);

        return ResponseEntity.ok(true);
    }

    private Member getMemberEntity(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER_ID));
    }
}
