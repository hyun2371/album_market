package com.prgrms.album_market.album.entity;

import com.prgrms.album_market.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AlbumLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "album_like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    private Member member;

    @ManyToOne(fetch = LAZY)
    private Album album;

    private Boolean isLike;
}
