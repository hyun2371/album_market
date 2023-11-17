package com.prgrms.album_market.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MemberRequest {
    public record SignUpReq(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[0-9]).{8,16}$", message = "비밀번호 형식이 올바르지 않습니다.")
        String password,

        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "^010-?([0-9]{4})-?([0-9]{4})$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
        String phoneNumber,

        @NotBlank(message = "도시를 입력해주세요.")
        String city,

        @NotBlank(message = "도로를 입력해주세요.")
        String street,

        @NotBlank(message = "우편번호를 입력해주세요.")
        @Pattern(regexp = "\\d{5}", message = "우편번호 형식이 올바르지 않습니다.")
        String zipcode
    ) {}

    public record LoginReq(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        String password
    ) {}

    public record UpdateReq(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "^010-?([0-9]{4})-?([0-9]{4})$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
        String phoneNumber,

        @NotBlank(message = "도시를 입력해주세요.")
        String city,

        @NotBlank(message = "도로를 입력해주세요.")
        String street,

        @NotBlank(message = "우편번호를 입력해주세요.")
        @Pattern(regexp = "\\d{5}", message = "우편번호 형식이 올바르지 않습니다.")
        String zipcode
    ) {}
}
