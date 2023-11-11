package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.album.AlbumMapper;
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
    private final SongRepository songRepository;
    private final MemberService memberService;


    public CreateAlbumRes createAlbum(CreateAlbumReq request) {
        Album album = toAlbum(request);
        if (albumRepository.existsByTitleAndArtist(album.getTitle(), album.getArtist())) {
            throw new CustomException(ALREADY_EXIST_ALBUM);
        }
        albumRepository.save(album);
        return toCreateAlbumRes(album);
    }

    @Transactional(readOnly = true)
    public GetAlbumRes getAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        return toGetAlbumRes(album);
    }

    @Transactional(readOnly = true)
    public PageResponse<GetAlbumRes> getAlbums(Pageable pageable) {
        Page<Album> pagedAlbums = albumRepository.findAll(pageable);
        Page<GetAlbumRes> pagedGetAlbumRes = pagedAlbums.map(AlbumMapper::toGetAlbumRes);
        return PageResponse.fromPage(pagedGetAlbumRes);
    }

    public GetAlbumRes updateAlbum(Long albumId, UpdateAlbumReq request) {
        Album album = getAlbumEntity(albumId);
        Album updatedAlbum = album.updateAlbumInfo(request);
        return toGetAlbumRes(updatedAlbum);
    }

    public void deleteAlbum(Long albumId) {
        Album album = getAlbumEntity(albumId);
        songRepository.deleteByAlbum(album);
        albumRepository.delete(album);
    }

    public void likeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = createAlbumEntity(memberId, album);
        albumLikeRepository.save(albumLike);
        album.updateLikeCount(1);
    }

    public void dislikeAlbum(Long albumId, Long memberId) {
        Album album = getAlbumEntity(albumId);
        AlbumLike albumLike = getAlbumLikeEntity(memberId, album);

        albumLikeRepository.delete(albumLike);
        album.updateLikeCount(-1);
    }

    public GetAlbumRes increaseAlbumStock(Long albumId, Integer quantity) {
        Album album = getAlbumEntity(albumId);
        album.increaseStock(quantity);
        return toGetAlbumRes(album);
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
}
