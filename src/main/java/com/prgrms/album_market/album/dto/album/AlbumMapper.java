package com.prgrms.album_market.album.dto.album;

import com.prgrms.album_market.album.dto.album.request.CreateAlbumRequest;
import com.prgrms.album_market.album.dto.album.response.CreateAlbumResponse;
import com.prgrms.album_market.album.dto.album.response.GetAlbumResponse;
import com.prgrms.album_market.album.entity.Album;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AlbumMapper {
    // entity -> dto
    public static CreateAlbumResponse toCreateAlbumResponse(Album album) {
        return new CreateAlbumResponse(album.getId());
    }

    public static GetAlbumResponse toGetAlbumResponse(Album album) {
        return GetAlbumResponse.builder()
                .albumId(album.getId())
                .title(album.getTitle())
                .artist(album.getArtist())
                .category(album.getCategory().toString())
                .imgUrl(album.getImgUrl())
                .releaseDate(album.getReleaseDate().toString())
                .price(album.getPrice())
                .stock(album.getStock())
                .likeCount(album.getLikeCount())
                .build();
    }

    // dto->entity
    public static Album toAlbum(CreateAlbumRequest request) {
        return Album.builder()
                .title(request.title())
                .artist(request.artist())
                .category(request.category())
                .imgUrl(request.imgUrl())
                .releaseDate(request.releaseDate())
                .price(request.price())
                .build();
    }
}
