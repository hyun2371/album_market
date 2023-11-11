package com.prgrms.album_market.album.dto.song;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class SongRequest {
    @RequiredArgsConstructor
    @Getter
    public static class CreateSongReq {
        private String name;
        private Boolean isTitle;
        private Long albumId;
    }
}
