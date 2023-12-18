package com.prgrms.album_market.album.dto.song.response;

import java.util.List;

public record GetSongListResponse(
        List<GetSongResponse> songs
) {}
