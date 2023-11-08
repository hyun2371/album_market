package com.prgrms.album_market.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorCode {
    /*
    400 BAD_REQUEST: 잘못된 요청
     */
    EMPTY_INPUT_EMAIL(HttpStatus.BAD_REQUEST, "이메일을 입력해주세요."),

    EMPTY_INPUT_NAME(HttpStatus.BAD_REQUEST, "이름을 입력해주세요."),

    EMPTY_INPUT_PWD(HttpStatus.BAD_REQUEST, "비밀번호를 입력해주세요."),

    EMPTY_INPUT_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "휴대폰 번호를 입력해주세요."),

    EMPTY_INPUT_CITY(HttpStatus.BAD_REQUEST, "도시를 입력해주세요."),

    EMPTY_INPUT_STREET(HttpStatus.BAD_REQUEST, "도로를 입력해주세요."),

    EMPTY_INPUT_ZIPCODE(HttpStatus.BAD_REQUEST, "우편번호를 입력해주세요."),



    INVALID_FORMAT_EMAIL(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),

    INVALID_FORMAT_PWD(HttpStatus.BAD_REQUEST, "비밀번호 형식이 올바르지 않습니다."),

    INVALID_FORMAT_PN(HttpStatus.BAD_REQUEST, "휴대폰 번호 형식이 올바르지 않습니다."),

    INVALID_FORMAT_ZIP_CODE(HttpStatus.BAD_REQUEST, "우편번호 형식이 올바르지 않습니다."),


    NOT_EXIST_MEMBER_ID(HttpStatus.NOT_FOUND, "해당 아이디의 회원이 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorCode matchErrorCode(String message) {
        return Arrays.stream(ErrorCode.values())
                .filter(voucherType -> voucherType.getMessage().equals(message))
                .findFirst()
                .orElse(null);
    }
}
