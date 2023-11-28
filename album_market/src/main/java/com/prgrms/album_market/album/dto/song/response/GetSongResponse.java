package com.prgrms.album_market.album.dto.song.response;

import lombok.Builder;

@Builder
public record GetSongResponse(
        String name,
        Boolean isTitle
) {}

