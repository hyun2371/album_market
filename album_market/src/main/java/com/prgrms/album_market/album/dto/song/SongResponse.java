package com.prgrms.album_market.album.dto.song;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class SongResponse {

    public record CreateSongRes(
            Long createdSongId
    ) {}

    @Builder
    public record GetSongRes(
            String name,
            Boolean isTitle
    ) {}


    public record GetSongListRes(
            List<GetSongRes> songs
    ) {}
}
