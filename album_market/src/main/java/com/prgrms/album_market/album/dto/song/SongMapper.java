package com.prgrms.album_market.album.dto.song;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.prgrms.album_market.album.dto.song.SongRequest.CreateSongReq;
import static com.prgrms.album_market.album.dto.song.SongResponse.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SongMapper {
    public static Song toSong(CreateSongReq request, Album album) {
        return Song.builder()
                .name(request.getName())
                .isTitle(request.getIsTitle())
                .album(album)
                .build();
    }

    public static CreateSongRes toCreateSongRes(Song song) {
        return new CreateSongRes(song.getId());
    }

    public static GetSongRes toGetSongRes(Song song) {
        return GetSongRes.builder()
                .name(song.getName())
                .isTitle(song.getIsTitle())
                .build();
    }

    public static GetSongListRes toGetSongListRes(List<Song> songs) {
        List<GetSongRes> list = new ArrayList<>();
        for (Song song : songs) {
            list.add(toGetSongRes(song));
        }
        return new GetSongListRes(list);
    }
}
