package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.song.SongRequest;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import com.prgrms.album_market.album.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.album.dto.song.SongMapper.*;
import static com.prgrms.album_market.album.dto.song.SongResponse.CreateSongRes;
import static com.prgrms.album_market.album.dto.song.SongResponse.GetSongListRes;

@Service
@RequiredArgsConstructor
@Transactional
public class SongService {
    private final SongRepository songRepository;
    private final AlbumService albumService;
    public CreateSongRes createSong(SongRequest.CreateSongReq request) {
        Album album = albumService.getAlbumEntity(request.getAlbumId());
        Song song = toSong(request, album);
        songRepository.save(song);

        return toCreateSongRes(song);
    }

    @Transactional(readOnly = true)
    public GetSongListRes getAlbumSongs(Long albumId) {
        Album album = albumService.getAlbumEntity(albumId);
        List<Song> songs = songRepository.findByAlbum(album);

        return toGetSongListRes(songs);
    }
}
