package com.prgrms.album_market.album.repository;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAlbum(Album album);
}
