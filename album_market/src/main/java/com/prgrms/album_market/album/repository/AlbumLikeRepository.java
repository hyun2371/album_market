package com.prgrms.album_market.album.repository;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumLikeRepository extends JpaRepository<AlbumLike, Long> {
    Optional<AlbumLike> findByAlbumAndMember(Album album, Member member);
    boolean existsByAlbumAndMember(Album album, Member member);
    int countByAlbum(Album album);
}
