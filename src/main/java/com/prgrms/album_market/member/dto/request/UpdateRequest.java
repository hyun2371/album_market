package com.prgrms.album_market.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateRequest(
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
