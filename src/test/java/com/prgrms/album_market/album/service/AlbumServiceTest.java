package com.prgrms.album_market.album.service;

import static com.prgrms.album_market.album.AlbumFixture.createAlbumRequest;
import static com.prgrms.album_market.member.MemberFixture.member;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.prgrms.album_market.album.AlbumFixture;
import com.prgrms.album_market.album.dto.album.request.CreateAlbumRequest;
import com.prgrms.album_market.album.dto.album.response.CreateAlbumResponse;
import com.prgrms.album_market.album.dto.album.response.GetAlbumResponse;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.album.repository.AlbumLikeRepository;
import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.common.exception.ErrorCode;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    private static final Album ALBUM1 = AlbumFixture.album();
    private static final String KEYWORD = "f";

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumLikeRepository albumLikeRepository;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void createAlbumSuccess() {
        CreateAlbumRequest dto = AlbumFixture.createAlbumRequest();

        when(albumRepository.existsByTitleAndArtist(dto.title(), dto.artist())).thenReturn(false);

        CreateAlbumResponse createdAlbumRes = albumService.createAlbum(dto);

        assertEquals(ALBUM1.getId(), createdAlbumRes.createdAlbumId());
    }

    @Test
    void createAlbumFail() {
        CreateAlbumRequest dto = createAlbumRequest();

        when(albumRepository.existsByTitleAndArtist(dto.title(), dto.artist()))
            .thenReturn(true);

        CustomException exception = assertThrows(CustomException.class,
            () -> albumService.createAlbum(dto));
        assertEquals(ErrorCode.ALREADY_EXIST_ALBUM, exception.getErrorCode());

    }

//    @Test
//    void deleteAlbumSuccess(){
//        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.of(ALBUM1));
//        albumService.deleteAlbum(ALBUM1.getId());
//
//        verify(albumRepository).findById(ALBUM1.getId());
//        verify(albumRepository).delete(ALBUM1);
//    }

    @Test
    void deleteAlbumFail() {
        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, ()
            -> albumService.deleteAlbum(ALBUM1.getId()));
        assertEquals(ErrorCode.NOT_EXISTS_ALBUM_ID, exception.getErrorCode());
    }

    @Test
    void findAlbum() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Album> albums = AlbumFixture.albums();
        Page<Album> pagedAlbums = new PageImpl<>(albums, pageable, albums.size());
        when(albumRepository.findByTitleContaining(KEYWORD, pageable))
            .thenReturn(pagedAlbums);

        PageResponse<GetAlbumResponse> pageResponse = albumService.findAlbum(KEYWORD, pageable);
        assertEquals(albums.size(), pageResponse.getItems().size());
        assertEquals(pagedAlbums.getTotalElements(), pageResponse.getItemSize());
    }

    @Test
    void likeAlbum() {
        Member member = member("hyun@gmail.com");
        AlbumLike albumLike = new AlbumLike(ALBUM1, member);
        when(memberService.getMemberEntity(member.getId())).thenReturn(member);
        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.of(ALBUM1));
        when(albumLikeRepository.save(any(AlbumLike.class))).thenReturn(albumLike);

        albumService.likeAlbum(ALBUM1.getId(), member.getId());
        assertEquals(1, ALBUM1.getLikeCount());
    }
}
