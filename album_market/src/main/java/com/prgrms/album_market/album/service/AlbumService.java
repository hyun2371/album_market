package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.AlbumResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.prgrms.album_market.album.dto.AlbumMapper.*;
import static com.prgrms.album_market.album.dto.AlbumRequest.*;
import static com.prgrms.album_market.album.dto.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.AlbumResponse.GetAlbumRes;
import static com.prgrms.album_market.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public ResponseEntity<CreateAlbumRes> createAlbum(CreateAlbumReq request) {
        Album album = toAlbum(request);
        if (albumRepository.existsByTitleAndArtist(album.getTitle(), album.getArtist())) {
            throw new CustomException(ALREADY_EXIST_ALBUM);
        }
        albumRepository.save(album);
        return ResponseEntity.ok(toCreateAlbumRes(album));
    }

    public ResponseEntity<GetAlbumRes> getAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        return ResponseEntity.ok(toGetAlbumRes(album));
    }

    private Album getAlbumEntity(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(NOT_EXISTS_ALBUM_ID));
    }

    public ResponseEntity<AlbumResponse.GetAlbumListRes> getAlbums() {
        List<Album> albums = albumRepository.findAll();
        return ResponseEntity.ok(toGetAlbumListRes(albums));
    }

    public ResponseEntity<GetAlbumRes> updateAlbum(Long albumId, UpdateAlbumReq request) {
        Album album = getAlbumEntity(albumId);
        Album updatedAlbum = album.updateAlbumInfo(request);

        return ResponseEntity.ok(toGetAlbumRes(updatedAlbum));
    }

    public ResponseEntity<Boolean> deleteAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        albumRepository.delete(album);

        return ResponseEntity.ok(true);
    }
}
