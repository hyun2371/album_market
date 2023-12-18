package com.prgrms.album_market.album.entity;

import com.prgrms.album_market.common.BaseEntity;
import com.prgrms.album_market.common.exception.CustomException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.prgrms.album_market.common.exception.ErrorCode.INVALID_FORMAT_STOCK;
import static com.prgrms.album_market.common.exception.ErrorCode.OUT_OF_STOCK;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Album extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "album_id")
    private Long id;
    private String title;
    private String artist;
    @Enumerated(STRING)
    private Category category;
    private String imgUrl;
    private LocalDate releaseDate;
    private Integer price;
    private Integer stock;
    private Integer likeCount;

    @Builder
    public Album(String title, String artist, String category, String imgUrl, String releaseDate, int price) {
        this.title = title;
        this.artist = artist;
        this.category = Category.matchCategory(category);
        this.imgUrl = imgUrl;
        this.releaseDate = LocalDate.parse(releaseDate);
        this.price = price;
        this.stock = 0;
        this.likeCount = 0;
    }

    public Album updateAlbumInfo(String title, String artist, String category, String imgUrl, String releaseDate, Integer price) {
        this.title = title;
        this.artist = artist;
        this.category = Category.matchCategory(category);
        this.imgUrl = imgUrl;
        this.releaseDate = LocalDate.parse(releaseDate);
        this.price = price;
        return this;
    }

    public void updateLikeCount(int count) {
        likeCount += count;
    }

    public void reduceStock(int quantity) {
        if (stock - quantity < 0) {
            throw new CustomException(OUT_OF_STOCK);
        }
        stock -= quantity;
    }

    public void increaseStock(int quantity) {
        if (quantity < 0) {
            throw new CustomException(INVALID_FORMAT_STOCK);
        }
        stock += quantity;
    }
}
