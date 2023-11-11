package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.album.AlbumResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.album.repository.AlbumLikeRepository;
import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.prgrms.album_market.album.dto.album.AlbumMapper.*;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.UpdateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.GetAlbumRes;
import static com.prgrms.album_market.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumLikeRepository albumLikeRepository;
    private final MemberService memberService;


    public ResponseEntity<CreateAlbumRes> createAlbum(CreateAlbumReq request) {
        Album album = toAlbum(request);
        if (albumRepository.existsByTitleAndArtist(album.getTitle(), album.getArtist())) {
            throw new CustomException(ALREADY_EXIST_ALBUM);
        }
        albumRepository.save(album);
        return ResponseEntity.ok(toCreateAlbumRes(album));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GetAlbumRes> getAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        return ResponseEntity.ok(toGetAlbumRes(album));
    }

    @Transactional(readOnly = true)
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

    public ResponseEntity<Boolean> likeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = createAlbumEntity(memberId, album);
        albumLikeRepository.save(albumLike);
        album.updateLikeCount(1);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> dislikeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = getAlbumLikeEntity(memberId, album);

        albumLikeRepository.delete(albumLike);
        album.updateLikeCount(-1);

        return ResponseEntity.ok(true);
    }

    private AlbumLike createAlbumEntity(Long memberId, Album album) {
        Member member = memberService.getMemberEntity(memberId);
        if (albumLikeRepository.existsByAlbumAndMember(album, member)) {
            throw new CustomException(ALREADY_LIKED_ALBUM);
        }
        return new AlbumLike(album, member);
    }

    private AlbumLike getAlbumLikeEntity(Long memberId, Album album) {
        Member member = memberService.getMemberEntity(memberId);
        return albumLikeRepository.findByAlbumAndMember(album, member)
                .orElseThrow(() -> new CustomException(ALREADY_DISLIKED_ALBUM));
    }

    public Album getAlbumEntity(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(NOT_EXISTS_ALBUM_ID));
    }

    public ResponseEntity<GetAlbumRes> increaseAlbumStock(Long albumId, Integer quantity) {
        Album album = getAlbumEntity(albumId);
        album.increaseStock(quantity);
        return ResponseEntity.ok(toGetAlbumRes(album));
    }
}
