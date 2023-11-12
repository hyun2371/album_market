package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.album.dto.song.SongRequest.CreateSongReq;
import static com.prgrms.album_market.album.dto.song.SongResponse.CreateSongRes;
import static com.prgrms.album_market.album.dto.song.SongResponse.GetSongListRes;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @Operation(summary = "노래 생성")
    @PostMapping
    public ResponseEntity<CreateSongRes> createSong(@Valid @RequestBody CreateSongReq request){
        CreateSongRes response = songService.createSong(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "앨범 노래 조회")
    @GetMapping("/{albumId}")
    public ResponseEntity<GetSongListRes> getAlbumSongs(@PathVariable Long albumId){
        GetSongListRes response = songService.getAlbumSongs(albumId);
        return ResponseEntity.ok(response);
    }
}
