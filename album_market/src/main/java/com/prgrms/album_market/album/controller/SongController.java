package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.prgrms.album_market.album.dto.song.SongRequest.CreateSongReq;
import static com.prgrms.album_market.album.dto.song.SongResponse.CreateSongRes;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    public ResponseEntity<CreateSongRes> createSong(@Valid @RequestBody CreateSongReq request){
        CreateSongRes response = songService.createSong(request);
        ResponseEntity.ok(response);
    }
}
