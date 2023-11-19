package com.prgrms.album_market.album.dto.album;

import lombok.Builder;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AlbumResponse {

    public record CreateAlbumRes (
        Long createdAlbumId
    ){}


    @Builder
    public record GetAlbumRes (
        Long albumId,
        String title,
        String artist,
        String category,
        String imgUrl,
        String releaseDate,
        int price,
        int stock,
        int likeCount
    ){}
}
