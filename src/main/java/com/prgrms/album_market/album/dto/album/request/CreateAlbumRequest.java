package com.prgrms.album_market.album.dto.album.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateAlbumRequest(
        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @NotBlank(message = "아티스트를 입력해주세요.")
        String artist,

        @NotBlank(message = "카테고리를 입력해주세요.")
        String category,

        @NotBlank(message = "이미지 주소를 입력해주세요.")
        String imgUrl,

        @NotBlank(message = "발매일을 입력해주세요.")
        String releaseDate,

        @NotNull(message = "가격을 입력해주세요.")
        @Min(value = 0, message = "가격은 0이상이어야 합니다.")
        Integer price
) { }
