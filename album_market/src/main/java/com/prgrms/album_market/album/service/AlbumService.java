package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.album.*;
import com.prgrms.album_market.album.dto.album.request.CreateAlbumRequest;
import com.prgrms.album_market.album.dto.album.request.UpdateAlbumRequest;
import com.prgrms.album_market.album.dto.album.response.CreateAlbumResponse;
import com.prgrms.album_market.album.dto.album.response.GetAlbumResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.album.repository.AlbumLikeRepository;
import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.album.repository.SongRepository;
import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.prgrms.album_market.album.dto.album.AlbumMapper.*;
import static com.prgrms.album_market.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumLikeRepository albumLikeRepository;
    private final SongRepository songRepository;
    private final MemberService memberService;


    @Transactional
    public CreateAlbumResponse createAlbum(CreateAlbumRequest request) {
        if (albumRepository.existsByTitleAndArtist(request.title(), request.artist())) {
            throw new CustomException(ALREADY_EXIST_ALBUM);
        }
        Album savedAlbum = albumRepository.save(toAlbum(request));
        return toCreateAlbumResponse(savedAlbum);
    }

    @Transactional(readOnly = true)
    public GetAlbumResponse getAlbumById(Long albumId) {
        Album album = getAlbumEntity(albumId);
        return toGetAlbumResponse(album);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetAlbumResponse> getAllAlbum(Pageable pageable) {
        Page<Album> pagedAlbums = albumRepository.findAll(pageable);
        Page<GetAlbumResponse> pagedGetAlbumResponse = pagedAlbums.map(AlbumMapper::toGetAlbumResponse);
        return PageResponse.fromPage(pagedGetAlbumResponse);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetAlbumResponse> findAlbum(String keyword, Pageable pageable) {
        Page<Album> pagedAlbums = albumRepository.findByTitleContaining(keyword, pageable);
        Page<GetAlbumResponse> pagedGetAlbumRes = pagedAlbums.map(AlbumMapper::toGetAlbumResponse);
        return PageResponse.fromPage(pagedGetAlbumRes);
    }

    @Transactional
    public GetAlbumResponse updateAlbum(Long albumId, UpdateAlbumRequest request) {
        Album album = getAlbumEntity(albumId);
        Album updatedAlbum = album.updateAlbumInfo(request.title(),
                request.artist(),
                request.category(),
                request.imgUrl(),
                request.releaseDate(),
                request.price());
        return toGetAlbumResponse(updatedAlbum);
    }

    @Transactional
    public void deleteAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        songRepository.deleteByAlbum(album);
        albumRepository.delete(album);
    }

    @Transactional
    public void likeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = createAlbumLikeEntity(memberId, album);
        albumLikeRepository.save(albumLike);
        album.updateLikeCount(1);
    }

    @Transactional
    public void dislikeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = getAlbumLikeEntity(memberId, album);

        albumLikeRepository.delete(albumLike);
        album.updateLikeCount(-1);
    }

    @Transactional
    public GetAlbumResponse increaseAlbumStock(Long albumId, Integer quantity) {
        Album album = getAlbumEntity(albumId);
        album.increaseStock(quantity);
        return toGetAlbumResponse(album);
    }

    @Transactional(readOnly = true)
    public AlbumLike createAlbumLikeEntity(Long memberId, Album album) {
        Member member = memberService.getMemberEntity(memberId);
        if (albumLikeRepository.existsByAlbumAndMember(album, member)) {
            throw new CustomException(ALREADY_LIKED_ALBUM);
        }
        return new AlbumLike(album, member);
    }

    @Transactional(readOnly = true)
    public AlbumLike getAlbumLikeEntity(Long memberId, Album album) {
        Member member = memberService.getMemberEntity(memberId);
        return albumLikeRepository.findByAlbumAndMember(album, member)
                .orElseThrow(() -> new CustomException(ALREADY_DISLIKED_ALBUM));
    }

    @Transactional(readOnly = true)
    public Album getAlbumEntity(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(NOT_EXISTS_ALBUM_ID));
    }
}
