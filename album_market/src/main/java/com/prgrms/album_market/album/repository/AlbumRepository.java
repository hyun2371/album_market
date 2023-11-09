package com.prgrms.album_market.album.repository;

import com.prgrms.album_market.album.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    boolean existsByTitleAndArtist(String title, String artist);
}
