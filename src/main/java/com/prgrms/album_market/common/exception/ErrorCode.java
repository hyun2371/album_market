package com.prgrms.album_market.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_EXIST_MEMBER_ID(HttpStatus.NOT_FOUND, "해당 아이디의 회원이 존재하지 않습니다."),
    NOT_EXIST_MEMBER_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일의 회원이 존재하지 않습니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "동일한 이메일의 회원이 이미 존재합니다."),

    WRONG_MEMBER_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다"),

    NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    INVALID_FORMAT_MONEY(HttpStatus.BAD_REQUEST, "1000원부터 충전이 가능합니다."),

    INVALID_FORMAT_CATEGORY(HttpStatus.BAD_REQUEST, "카테고리 형식이 올바르지 않습니다."),

    INVALID_FORMAT_PRICE(HttpStatus.BAD_REQUEST, "가격은 0이상이어야 합니다."),

    INVALID_FORMAT_STOCK(HttpStatus.BAD_REQUEST, "최소 입고 수량은 1개입니다."),

    NOT_EXISTS_ALBUM_ID(HttpStatus.NOT_FOUND, "해당 아이디의 앨범이 존재하지 않습니다."),
    ALREADY_EXIST_ALBUM(HttpStatus.BAD_REQUEST, "동일한 이름과 아티스트의 앨범이 이미 존재합니다."),

    //song
    ALREADY_EXISTS_SONG(HttpStatus.BAD_REQUEST, "앨범에 해당 노래가 이미 존재합니다."),

    //albumLike
    ALREADY_LIKED_ALBUM(HttpStatus.BAD_REQUEST, "이미 좋아요를 누른 앨범입니다."),
    ALREADY_DISLIKED_ALBUM(HttpStatus.BAD_REQUEST, "이미 좋아요를 취소한 앨범입니다."),

    //order
    NOT_EXISTS_ORDER_ID(HttpStatus.NOT_FOUND, "해당 아이디의 주문이 존재하지 않습니다."),

    DELIVERED_CAN_NOT_CANCEL(HttpStatus.BAD_REQUEST, "이미 배달이 완료되어 취소가 되지 않는 주문입니다."),

    CANCELED_CAN_NOT_DELIVER(HttpStatus.BAD_REQUEST, "이미 취소된 앨범은 배달할 수 없습니다."),

    ALREADY_CANCELED_ALBUM(HttpStatus.BAD_REQUEST, "이미 취소된 주문입니다."),

    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),

    // common
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다.");

    private final HttpStatus status;
    private final String message;
}
