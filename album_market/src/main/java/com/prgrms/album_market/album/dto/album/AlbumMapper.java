package com.prgrms.album_market.album.dto.album;

import com.prgrms.album_market.album.entity.Album;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.GetAlbumRes;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AlbumMapper {
    // entity -> dto
    public static CreateAlbumRes toCreateAlbumRes(Album album) {
        return new CreateAlbumRes(album.getId());
    }

    public static GetAlbumRes toGetAlbumRes(Album album) {
        return GetAlbumRes.builder()
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
    public static Album toAlbum(AlbumRequest.CreateAlbumReq request) {
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
