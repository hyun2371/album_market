package com.prgrms.album_market.album;

import com.prgrms.album_market.album.entity.Album;

import java.util.List;

import static com.prgrms.album_market.album.dto.album.AlbumMapper.toGetAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.GetAlbumRes;

public class AlbumFixture {
    private static final String TITLE1 = "folklore";
    private static final String ARTIEST1 = "Taylor Swift";
    private static final String CATEGORY1 = "FOLK";
    private static final String IMG_URL1 = "https://image.yes24.com/goods/91922081/XL";
    private static final String RELEASE_DATE1 = "2020-08-28";
    private static final int PRICE1 = 21000;
    private static final String TITLE2 = "Midnights";
    private static final String ARTIEST2 = "Taylor Swift";
    private static final String CATEGORY2 = "POP";
    private static final String IMG_URL2 = "https://image.yes24.com/goods/91922081/XL";
    private static final String RELEASE_DATE2 = "2022-10-21";
    private static final int PRICE2 = 20900;

    public static Album album(int price){
        return Album.builder()
                .title(TITLE1)
                .artist(ARTIEST1)
                .category(CATEGORY1)
                .imgUrl(IMG_URL1)
                .releaseDate(RELEASE_DATE1)
                .price(price)
                .build();
    }

    public static Album album(){
        return Album.builder()
                .title(TITLE1)
                .artist(ARTIEST1)
                .category(CATEGORY1)
                .imgUrl(IMG_URL1)
                .releaseDate(RELEASE_DATE1)
                .price(PRICE1)
                .build();
    }

    public static List<Album> getAlbums(){
        Album album1 = album();
        Album album2 = Album.builder()
                .title(TITLE2)
                .artist(ARTIEST2)
                .category(CATEGORY2)
                .imgUrl(IMG_URL2)
                .releaseDate(RELEASE_DATE2)
                .price(PRICE2)
                .build();
        return List.of(album1, album2);
    }

    public static CreateAlbumReq getCreateAlbumReq() {
        return CreateAlbumReq.builder()
                .title(TITLE1)
                .artist(ARTIEST1)
                .category(CATEGORY1)
                .imgUrl(IMG_URL1)
                .releaseDate(RELEASE_DATE1)
                .price(PRICE1)
                .build();
    }

    public static GetAlbumRes getAlbumRes(){
        return toGetAlbumRes(album());
    }

    public static GetAlbumRes getUpdateAlbumRes() {
        return GetAlbumRes.builder()
                .title(TITLE2)
                .artist(ARTIEST2)
                .category(CATEGORY2)
                .imgUrl(IMG_URL2)
                .releaseDate(RELEASE_DATE2)
                .price(PRICE2)
                .build();
    }
    public static UpdateAlbumReq getUpdateAlbumReq(){
        return UpdateAlbumReq.builder()
                .title(TITLE2)
                .artist(ARTIEST2)
                .category(CATEGORY2)
                .imgUrl(IMG_URL2)
                .releaseDate(RELEASE_DATE2)
                .price(PRICE2)
                .build();
    }

    public static CreateAlbumRes getCreateAlbumRes(){
        return new CreateAlbumRes(1L);
    }


}
