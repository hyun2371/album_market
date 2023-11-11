package com.prgrms.album_market.album.controller;

import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<CreateAlbumRes> createAlbum(@Valid @RequestBody CreateAlbumReq request){
        CreateAlbumRes response = albumService.createAlbum(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<GetAlbumRes> getAlbum(@PathVariable Long albumId) {
        GetAlbumRes response = albumService.getAlbum(albumId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<GetAlbumRes>> getAlbums(Pageable pageable){
        PageResponse<GetAlbumRes> response = albumService.getAlbums(pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{albumId}")
    public ResponseEntity<GetAlbumRes> updateAlbum(@PathVariable Long albumId, @RequestBody UpdateAlbumReq request){
        GetAlbumRes response = albumService.updateAlbum(albumId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Boolean> deleteAlbum(@PathVariable Long albumId){
        albumService.deleteAlbum(albumId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/like")
    public ResponseEntity<Boolean> likeAlbum(@RequestParam Long albumId, @RequestParam Long memberId){
        albumService.likeAlbum(albumId, memberId);
        return ResponseEntity.ok(true);
    }
    @DeleteMapping("/like")
    public ResponseEntity<Boolean> dislikeAlbum(@RequestParam Long albumId, @RequestParam Long memberId){
        albumService.dislikeAlbum(albumId, memberId);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/{albumId}/stock")
    public ResponseEntity<GetAlbumRes> increaseAlbumStock(@PathVariable Long albumId, @RequestParam Integer quantity){
        GetAlbumRes response = albumService.increaseAlbumStock(albumId, quantity);
        return ResponseEntity.ok(response);
    }

}
