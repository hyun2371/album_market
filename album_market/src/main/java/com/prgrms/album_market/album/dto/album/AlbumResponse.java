package com.prgrms.album_market.album.dto.album;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class AlbumResponse {
    @AllArgsConstructor
    @Getter
    public static class CreateAlbumRes {
        private Long createdAlbumId;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class GetAlbumRes {
        private Long albumId;
        private String title;
        private String artist;
        private String category;
        private String imgUrl;
        private String releaseDate;
        private int price;
        private int stock;
        private int likeCount;
    }

    @AllArgsConstructor
    @Getter
    public static class GetAlbumListRes {
        private List<GetAlbumRes> albums;
    }
}
