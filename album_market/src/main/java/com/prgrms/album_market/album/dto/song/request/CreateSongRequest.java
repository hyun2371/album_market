package com.prgrms.album_market.album.dto.song.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateSongRequest(
        @NotBlank(message = "노래 제목을 입력해주세요.") String name,
        @NotNull(message = "타이틀 여부를 입력해주세요.") Boolean isTitle,
        Long albumId
) { }
