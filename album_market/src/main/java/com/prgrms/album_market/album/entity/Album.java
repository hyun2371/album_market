package com.prgrms.album_market.album.entity;

import com.prgrms.album_market.common.BaseEntity;
import com.prgrms.album_market.common.exception.CustomException;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

import static com.prgrms.album_market.album.dto.album.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.common.exception.ErrorCode.*;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@DynamicInsert
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
    public Album(String title, String artist, Category category, String imgUrl, LocalDate releaseDate, int price) {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.imgUrl = imgUrl;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Album updateAlbumInfo(UpdateAlbumReq request) {
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if (request.getArtist() != null) {
            this.artist = request.getArtist();
        }
        if (request.getCategory() != null) {
            this.category = Category.matchCategory(request.getCategory());
        }
        if (request.getReleaseDate() != null) {
            this.releaseDate = LocalDate.parse(request.getReleaseDate());
        }
        if (request.getPrice() != null) {
            if (request.getPrice() < 0) {
                throw new CustomException(INVALID_FORMAT_PRICE);
            }
            this.price = request.getPrice();
        }
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
