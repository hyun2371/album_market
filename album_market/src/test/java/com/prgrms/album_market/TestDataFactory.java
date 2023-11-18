package com.prgrms.album_market;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;

import java.util.List;

import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;

public class TestDataFactory {
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

    public static Member getMember(){
        return new Member("hyun14@gmail.com", "ddd123!", "hyun", "010-3242-3233",
                            new Address("서울시", "중구", "04583"));
    }
}
