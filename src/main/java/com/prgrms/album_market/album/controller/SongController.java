package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.dto.song.request.CreateSongRequest;
import com.prgrms.album_market.album.dto.song.response.CreateSongResponse;
import com.prgrms.album_market.album.dto.song.response.GetSongListResponse;
import com.prgrms.album_market.album.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @Operation(summary = "노래 생성")
    @PostMapping
    public ResponseEntity<CreateSongResponse> createSong(@Valid @RequestBody CreateSongRequest request) {
        CreateSongResponse response = songService.createSong(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "앨범 노래 조회")
    @GetMapping("/{albumId}")
    public ResponseEntity<GetSongListResponse> getAlbumSongs(@PathVariable Long albumId) {
        GetSongListResponse response = songService.getAlbumSongs(albumId);
        return ResponseEntity.ok(response);
    }
}
