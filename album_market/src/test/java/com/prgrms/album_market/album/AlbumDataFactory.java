package com.prgrms.album_market.album;

import com.prgrms.album_market.album.entity.Album;

import java.util.List;

import static com.prgrms.album_market.album.dto.album.AlbumMapper.toGetAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.GetAlbumRes;

public class AlbumDataFactory {
    public static Album getAlbum(){
        return Album.builder()
                .title("folklore")
                .artist("Taylor Swift")
                .category("FOLK")
                .imgUrl("https://image.yes24.com/goods/91922081/XL")
                .releaseDate("2020-08-28")
                .price(21900)
                .build();
    }

    public static List<Album> getAlbums(){
        Album album2 = Album.builder()
                .title("Different World")
                .artist("Alan Walker")
                .category("ELEC")
                .imgUrl("https://image.yes24.com/goods/67463935/XL")
                .releaseDate("2018-12-14")
                .price(19300)
                .build();
        return List.of(getAlbum(), album2);
    }

    public static CreateAlbumReq getCreateAlbumReq() {
        return CreateAlbumReq.builder()
                .title("folklore")
                .artist("Taylor Swift")
                .category("FOLK")
                .imgUrl("https://image.yes24.com/goods/91922081/XL")
                .releaseDate("2020-08-28")
                .price(21900)
                .build();
    }

    public static GetAlbumRes getUpdateAlbumRes() {
        return GetAlbumRes.builder()
                .title("Midnights")
                .artist("Taylor Swift")
                .category("POP")
                .imgUrl("https://image.yes24.com/goods/91922081/XL")
                .releaseDate("2022-10-21")
                .price(20900)
                .build();
    }

    public static GetAlbumRes getAlbumRes(){
        return toGetAlbumRes(getAlbum());
    }

    public static UpdateAlbumReq getUpdateAlbumReq(){
        return UpdateAlbumReq.builder()
                .title("folklore")
                .artist("Taylor Swift")
                .category("FOLK")
                .imgUrl("https://image.yes24.com/goods/91922081/XL")
                .releaseDate("2020-08-28")
                .price(20900)
                .build();
    }

    public static CreateAlbumRes getCreateAlbumRes(){
        return new CreateAlbumRes(1L);
    }


}
