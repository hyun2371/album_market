package com.prgrms.album_market.album.dto.album.response;

import lombok.Builder;

@Builder
public record GetAlbumResponse (
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