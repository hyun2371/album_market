package com.prgrms.album_market.member.controller;

import com.prgrms.album_market.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<SignUpRes> signUp(@Valid @RequestBody SignUpReq request) {
        SignUpRes response = memberService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginReq request) {
        LoginRes response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "아이디로 회원 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> getMemberById(@PathVariable Long memberId) {
        GetMemberRes response = memberService.getMemberById(memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 회원 조회")
    @GetMapping
    public ResponseEntity<GetMemberListRes> getMembers() {
        GetMemberListRes response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 업데이트")
    @PutMapping("/{memberId}")
    public ResponseEntity<GetMemberRes> updateMember(@PathVariable Long memberId, @Valid @RequestBody UpdateReq request) {
        GetMemberRes response = memberService.updateMember(memberId, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "회원 금액 충전")
    @PostMapping("/charge/{memberId}")
    public ResponseEntity<BalanceRes> chargeMoney(@PathVariable Long memberId, @RequestParam int amount) {
        BalanceRes response = memberService.chargeMoney(memberId, amount);
        return ResponseEntity.ok(response);
    }

}
