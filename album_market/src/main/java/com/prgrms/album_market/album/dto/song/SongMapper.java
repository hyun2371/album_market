package com.prgrms.album_market.album.dto.song;

import com.prgrms.album_market.album.dto.song.request.CreateSongRequest;
import com.prgrms.album_market.album.dto.song.response.CreateSongResponse;
import com.prgrms.album_market.album.dto.song.response.GetSongListResponse;
import com.prgrms.album_market.album.dto.song.response.GetSongResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.Song;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SongMapper {
    public static Song toSong(CreateSongRequest request, Album album) {
        return Song.builder()
                .name(request.name())
                .isTitle(request.isTitle())
                .album(album)
                .build();
    }

    public static CreateSongResponse toCreateSongRes(Song song) {
        return new CreateSongResponse(song.getId());
    }

    public static GetSongResponse toGetSongRes(Song song) {
        return GetSongResponse.builder()
                .name(song.getName())
                .isTitle(song.getIsTitle())
                .build();
    }

    public static GetSongListResponse toGetSongListRes(List<Song> songs) {
        List<GetSongResponse> list = new ArrayList<>();
        for (Song song : songs) {
            list.add(toGetSongRes(song));
        }
        return new GetSongListResponse(list);
    }
}
