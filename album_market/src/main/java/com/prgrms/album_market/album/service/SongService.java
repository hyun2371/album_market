package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.song.SongRequest;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import com.prgrms.album_market.album.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.prgrms.album_market.album.dto.song.SongMapper.toCreateSongRes;
import static com.prgrms.album_market.album.dto.song.SongMapper.toSong;
import static com.prgrms.album_market.album.dto.song.SongResponse.CreateSongRes;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumService albumService;
    @Transactional
    public CreateSongRes createSong(SongRequest.CreateSongReq request) {
        Album album = albumService.getAlbumEntity(request.getAlbumId());
        Song song = toSong(request, album);
        songRepository.save(song);

        return toCreateSongRes(song);
    }
}
