package com.prgrms.album_market.album.dto.song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class SongResponse {
    @AllArgsConstructor
    @Getter
    public static class CreateSongRes {
        private Long createdSongId;
    }
}
