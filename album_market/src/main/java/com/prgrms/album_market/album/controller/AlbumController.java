package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.album.dto.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.album.dto.AlbumResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<CreateAlbumRes> createAlbum(@Valid @RequestBody CreateAlbumReq albumReq){
        return albumService.createAlbum(albumReq);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<GetAlbumRes> getAlbum(@PathVariable Long albumId) {
        return albumService.getAlbum(albumId);
    }

    @GetMapping
    public ResponseEntity<GetAlbumListRes> getAlbums(){
        return albumService.getAlbums();
    }

    @PatchMapping("/{albumId}")
    public ResponseEntity<GetAlbumRes> updateAlbum(@PathVariable Long albumId, @RequestBody UpdateAlbumReq request){
        return albumService.updateAlbum(albumId, request);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Boolean> deleteAlbum(@PathVariable Long albumId){
        return albumService.deleteAlbum(albumId);
    }

}
