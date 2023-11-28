package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.song.request.CreateSongRequest;
import com.prgrms.album_market.album.dto.song.response.CreateSongResponse;
import com.prgrms.album_market.album.dto.song.response.GetSongListResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import com.prgrms.album_market.album.repository.SongRepository;
import com.prgrms.album_market.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.album.dto.song.SongMapper.*;
import static com.prgrms.album_market.common.exception.ErrorCode.ALREADY_EXISTS_SONG;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumService albumService;

    @Transactional
    public CreateSongResponse createSong(CreateSongRequest request) {
        Album album = albumService.getAlbumEntity(request.albumId());
        if (songRepository.existsByAlbumAndName(album, request.name())) {
            throw new CustomException(ALREADY_EXISTS_SONG);
        }
        Song song = toSong(request, album);
        songRepository.save(song);

        return toCreateSongRes(song);
    }

    @Transactional(readOnly = true)
    public GetSongListResponse getAlbumSongs(Long albumId) {
        Album album = albumService.getAlbumEntity(albumId);
        List<Song> songs = songRepository.findByAlbum(album);

        return toGetSongListRes(songs);
    }
}
