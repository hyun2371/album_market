package com.prgrms.album_market.album.dto.song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class SongResponse {
    @AllArgsConstructor
    @Getter
    public static class CreateSongRes {
        private Long createdSongId;
    }

    @AllArgsConstructor
    @Getter @Builder
    public static class GetSongRes{
        private String name;
        private Boolean isTitle;
    }

    @AllArgsConstructor @Getter
    public static class GetSongListRes{
        List<GetSongRes> songs;
    }
}
