package com.prgrms.album_market.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum ErrorCode {
    /**
     * 400 BAD_REQUEST: 잘못된 요청
     **/

    // Member
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


    NOT_EXIST_MEMBER_ID(HttpStatus.NOT_FOUND, "해당 아이디의 회원이 존재하지 않습니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "동일한 이메일의 회원이 이미 존재합니다."),

    WRONG_MEMBER_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다"),

    NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    INVALID_FORMAT_MONEY(HttpStatus.BAD_REQUEST, "1000원부터 충전이 가능합니다."),


    // Album
    EMPTY_INPUT_TITLE(HttpStatus.BAD_REQUEST, "제목을 입력해주세요."),

    EMPTY_INPUT_ARTIST(HttpStatus.BAD_REQUEST, "아티스트를 입력해주세요."),

    EMPTY_INPUT_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리를 입력해주세요."),

    EMPTY_INPUT_IMG_URL(HttpStatus.BAD_REQUEST, "이미지 주소를 입력해주세요."),

    EMPTY_INPUT_RELEASE_DATE(HttpStatus.BAD_REQUEST, "발매일을 입력해주세요."),

    EMPTY_INPUT_PRICE(HttpStatus.BAD_REQUEST, "가격을 입력해주세요."),


    INVALID_FORMAT_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리 형식이 올바르지 않습니다."),

    INVALID_FORMAT_PRICE(HttpStatus.BAD_REQUEST, "가격은 0이상이어야 합니다."),

    INVALID_FORMAT_STOCK(HttpStatus.BAD_REQUEST, "최소 입고 수량은 1개입니다."),

    NOT_EXISTS_ALBUM_ID(HttpStatus.NOT_FOUND, "해당 아이디의 앨범이 존재하지 않습니다."),
    ALREADY_EXIST_ALBUM(HttpStatus.BAD_REQUEST, "동일한 이름과 아티스트의 앨범이 이미 존재합니다."),

    //song
    NOT_EXISTS_SONG_ALBUM_ID(HttpStatus.NOT_FOUND, "해당 앨범 아이디의 노래가 존재하지 않습니다."),

    //albumLike

    ALREADY_LIKED_ALBUM(HttpStatus.BAD_REQUEST, "이미 좋아요를 누른 앨범입니다."),
    ALREADY_DISLIKED_ALBUM(HttpStatus.BAD_REQUEST, "이미 좋아요를 취소한 앨범입니다."),

    //order
    NOT_EXISTS_ORDER_ID(HttpStatus.NOT_FOUND, "해당 아이디의 주문이 존재하지 않습니다."),

    DELIVERED_CAN_NOT_CANCEL(HttpStatus.BAD_REQUEST, "이미 배달이 완료되어 취소가 되지 않는 주분입니다."),

    CANCELED_CAN_NOT_DELIVER(HttpStatus.BAD_REQUEST, "이미 취소된 앨범은 배달할 수 없습니다."),

    ALREADY_CANCELED_ALBUM(HttpStatus.BAD_REQUEST, "이미 취소된 주문입니다."),

    ALREADY_DELIVERED_ALBUM(HttpStatus.BAD_REQUEST, "이미 배달 완료된 앨범입니다."),

    INVALID_FORMAT_QUANTITY(HttpStatus.BAD_REQUEST, "최소 주문 수량은 1개입니다."),

    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),

    TMP(HttpStatus.BAD_REQUEST, "temp");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // @Valid용
    public static ErrorCode matchErrorCode(String message) {
        return Arrays.stream(ErrorCode.values())
                .filter(voucherType -> voucherType.getMessage().equals(message))
                .findFirst()
                .orElse(null);
    }
}
