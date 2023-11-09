package com.prgrms.album_market.member.controller;

import com.prgrms.album_market.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.member.dto.MemberRequest.*;
import static com.prgrms.album_market.member.dto.MemberResponse.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SignUpRes> signUp(@Valid @RequestBody SignUpReq request) {
        return memberService.signUp(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginReq request) {
        return memberService.login(request);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @GetMapping
    public ResponseEntity<GetMemberListRes> getMembers() {
        return memberService.getMembers();
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> update(@PathVariable Long memberId, @RequestBody UpdateReq request){
        return memberService.update(memberId, request);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long memberId){
        return memberService.delete(memberId);
    }
}
