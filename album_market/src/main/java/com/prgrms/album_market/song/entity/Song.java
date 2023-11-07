package com.prgrms.album_market.song.entity;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Song extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Album album;

    private String title;

    private Boolean isTitle;
}
