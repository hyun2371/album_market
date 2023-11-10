package com.prgrms.album_market.album.dto.album;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class AlbumRequest {
    @RequiredArgsConstructor
    @Getter
    public static class CreateAlbumReq {
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "아티스트를 입력해주세요.")
        private String artist;

        @NotBlank(message = "카테고리를 입력해주세요.")
        private String category;

        @NotBlank(message = "이미지 주소를 입력해주세요.")
        private String imgUrl;

        @NotBlank(message = "발매일을 입력해주세요.")
        private String releaseDate;

        @NotNull(message = "가격을 입력해주세요.")
        @Min(value = 0, message = "가격은 0이상이어야 합니다.")
        private Integer price;
    }

    @RequiredArgsConstructor
    @Getter
    public static class UpdateAlbumReq {
        private String title;

        private String artist;

        private String category;

        private String imgUrl;

        private String releaseDate;

        private Integer price;
    }
}
