package com.prgrms.album_market.album.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.member.entity.Member;

public interface AlbumLikeRepository extends JpaRepository<AlbumLike, Long> {
    Optional<AlbumLike> findByAlbumAndMember(Album album, Member member);

    void deleteByAlbum(Album album);

    void deleteByMember(Member member);
    boolean existsByAlbumAndMember(Album album, Member member);
}
