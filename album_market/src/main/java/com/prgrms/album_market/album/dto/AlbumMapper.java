package com.prgrms.album_market.album.dto;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Category;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.album.dto.AlbumResponse.*;
import static com.prgrms.album_market.album.dto.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.AlbumResponse.GetAlbumRes;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AlbumMapper {
    // entity -> dto
    public static CreateAlbumRes toCreateAlbumRes(Album album){
        return new CreateAlbumRes(album.getId());
    }

    public static GetAlbumRes toGetAlbumRes(Album album){
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

    public static GetAlbumListRes toGetAlbumListRes(List<Album>albums){
        List<GetAlbumRes> list = new ArrayList<>();
        for (Album album : albums){
            list.add(toGetAlbumRes(album));
        }
        return new GetAlbumListRes(list);
    }

    // dto->entity
    public static Album toAlbum(AlbumRequest.CreateAlbumReq request) {
        return Album.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .category(Category.matchCategory(request.getCategory()))
                .imgUrl(request.getImgUrl())
                .releaseDate(LocalDate.parse(request.getReleaseDate()))
                .price(request.getPrice())
                .build();
    }
}
