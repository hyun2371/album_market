package com.prgrms.album_market.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class MemberRequest {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpReq {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[0-9]).{8,16}$", message = "비밀번호 형식이 올바르지 않습니다.")
        private String password;

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "^010-?([0-9]{4})-?([0-9]{4})$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
        private String phoneNumber;

        @NotBlank(message = "도시를 입력해주세요.")
        private String city;

        @NotBlank(message = "도로를 입력해주세요.")
        private String street;

        @NotBlank(message = "우편번호를 입력해주세요.")
        @Pattern(regexp = "\\d{5}", message = "우편번호 형식이 올바르지 않습니다.")
        private String zipcode;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginReq {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReq {
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        private String name;

        @Pattern(regexp = "^010-?([0-9]{4})-?([0-9]{4})$", message = "휴대폰 번호 형식이 올바르지 않습니다.")
        private String phoneNumber;

        private String city;

        private String street;

        @Pattern(regexp = "\\d{5}", message = "우편번호 형식이 올바르지 않습니다.")
        private String zipcode;
    }
}
