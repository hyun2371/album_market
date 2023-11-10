package com.prgrms.album_market.album.dto.song;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import lombok.NoArgsConstructor;

import static com.prgrms.album_market.album.dto.song.SongRequest.CreateSongReq;
import static com.prgrms.album_market.album.dto.song.SongResponse.CreateSongRes;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SongMapper {
    public static Song toSong(CreateSongReq request, Album album) {
        return Song.builder()
                .title(request.getTitle())
                .isTitle(request.getIsTitle())
                .album(album)
                .build();
    }

    public static CreateSongRes toCreateSongRes(Song song) {
        return new CreateSongRes(song.getId());
    }
}
