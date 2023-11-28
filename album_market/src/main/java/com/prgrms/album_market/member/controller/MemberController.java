package com.prgrms.album_market.member.controller;

import com.prgrms.album_market.member.dto.request.LoginRequest;
import com.prgrms.album_market.member.dto.request.SignUpRequest;
import com.prgrms.album_market.member.dto.request.UpdateRequest;
import com.prgrms.album_market.member.dto.response.GetMemberListResponse;
import com.prgrms.album_market.member.dto.response.GetMemberResponse;
import com.prgrms.album_market.member.dto.response.LoginResponse;
import com.prgrms.album_market.member.dto.response.SignUpResponse;
import com.prgrms.album_market.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse response = memberService.signUp(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "아이디로 회원 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<GetMemberResponse> getMemberById(@PathVariable Long memberId) {
        GetMemberResponse response = memberService.getMemberById(memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "모든 회원 조회")
    @GetMapping
    public ResponseEntity<GetMemberListResponse> getMembers() {
        GetMemberListResponse response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 업데이트")
    @PutMapping("/{memberId}")
    public ResponseEntity<GetMemberResponse> updateMember(@PathVariable Long memberId, @Valid @RequestBody UpdateRequest request) {
        GetMemberResponse response = memberService.updateMember(memberId, request);
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
    public ResponseEntity<GetMemberResponse> chargeMoney(@PathVariable Long memberId, @RequestParam int amount) {
        GetMemberResponse response = memberService.chargeMoney(memberId, amount);
        return ResponseEntity.ok(response);
    }

}
