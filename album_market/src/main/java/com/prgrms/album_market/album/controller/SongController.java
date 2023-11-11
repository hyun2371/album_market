package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.service.SongService;
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

    @PostMapping
    public ResponseEntity<CreateSongRes> createSong(@Valid @RequestBody CreateSongReq request){
        CreateSongRes response = songService.createSong(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<GetSongListRes> getAlbumSongs(@PathVariable Long albumId){
        GetSongListRes response = songService.getAlbumSongs(albumId);
        return ResponseEntity.ok(response);
    }
}
