package com.prgrms.album_market.album.entity;

import com.prgrms.album_market.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @NoArgsConstructor
public class AlbumLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "album_like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public AlbumLike(Album album, Member member) {
        this.album = album;
        this.member = member;
    }
}
