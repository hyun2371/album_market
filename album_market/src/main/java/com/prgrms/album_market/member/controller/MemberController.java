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
        SignUpRes response = memberService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginReq request) {
        LoginRes response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> getMemberById(@PathVariable Long memberId) {
        GetMemberRes response = memberService.getMemberById(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetMemberListRes> getMembers() {
        GetMemberListRes response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> updateMember(@PathVariable Long memberId, @RequestBody UpdateReq request){
        GetMemberRes response = memberService.updateMember(memberId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/charge/{memberId}")
    public ResponseEntity<BalanceRes> chargeMoney(@PathVariable Long memberId, @RequestParam int amount){
        BalanceRes response = memberService.chargeMoney(memberId, amount);
        return ResponseEntity.ok(response);
    }

}
