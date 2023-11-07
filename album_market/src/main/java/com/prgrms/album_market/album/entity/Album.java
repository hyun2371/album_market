package com.prgrms.album_market.album.entity;

import com.prgrms.album_market.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
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

    public Album(String title, String artist, Category category, String imgUrl, LocalDate releaseDate) {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.imgUrl = imgUrl;
        this.releaseDate = releaseDate;
    }
}
