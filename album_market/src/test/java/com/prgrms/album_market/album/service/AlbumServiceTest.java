package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.entity.AlbumLike;
import com.prgrms.album_market.album.repository.AlbumLikeRepository;
import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.album.repository.SongRepository;
import com.prgrms.album_market.common.PageResponse;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.common.exception.ErrorCode;
import com.prgrms.album_market.member.entity.Address;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.GetAlbumRes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {
    private static final String TITLE = "folklore";
    private static final String ARTIEST = "Taylor Swift";
    private static final String CATEGORY = "FOLK";
    private static final String IMG_URL = "https://image.yes24.com/goods/91922081/XL";
    private static final String RELEASE_DATE = "2020-08-28";
    private static final Integer PRICE = 21900;
    private static final Album ALBUM1 = new Album(TITLE, ARTIEST, CATEGORY, IMG_URL, RELEASE_DATE, PRICE);
    private static final Album ALBUM2 = new Album("Different World", ARTIEST, CATEGORY, IMG_URL, RELEASE_DATE, PRICE);
    private static final String KEYWORD = "f";

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private AlbumLikeRepository albumLikeRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void createAlbumSuccess(){
        CreateAlbumReq dto = getCreateAlbumReq();

        when(albumRepository.existsByTitleAndArtist(dto.title(), dto.artist())).thenReturn(false);
        when(albumRepository.save(any(Album.class))).thenReturn(ALBUM1);

        CreateAlbumRes createdAlbumRes = albumService.createAlbum(dto);

        assertEquals(ALBUM1.getId(), createdAlbumRes.getCreatedAlbumId());
    }

    @Test
    void createAlbumFail(){
        CreateAlbumReq dto = getCreateAlbumReq();

        when(albumRepository.existsByTitleAndArtist(dto.title(), dto.artist())).thenReturn(true);
        CustomException exception = assertThrows(CustomException.class, () -> albumService.createAlbum(dto));
        assertEquals(ErrorCode.ALREADY_EXIST_ALBUM,exception.getErrorCode());

    }

    @Test
    void deleteAlbumSuccess(){
        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.of(ALBUM1));
        albumService.deleteAlbum(ALBUM1.getId());

        verify(albumRepository).findById(ALBUM1.getId());
        verify(albumRepository).delete(ALBUM1);
    }

    @Test
    void deleteAlbumFail(){
        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, ()
                -> albumService.deleteAlbum(ALBUM1.getId()));
        assertEquals(ErrorCode.NOT_EXISTS_ALBUM_ID,exception.getErrorCode());
    }

    @Test
    void findAlbum(){
        Pageable pageable = PageRequest.of(0, 10);
        List<Album> albums = List.of(ALBUM1, ALBUM2);
        Page<Album> pagedAlbums = new PageImpl<>(albums, pageable, albums.size());
        when(albumRepository.findByTitleContaining(KEYWORD, pageable))
                .thenReturn(pagedAlbums);

        PageResponse<GetAlbumRes> pageResponse = albumService.findAlbum(KEYWORD, pageable);
        assertEquals(albums.size(), pageResponse.getItems().size());
        assertEquals(pagedAlbums.getTotalElements(), pageResponse.getTotalItems());
    }

    @Test
    void likeAlbum(){
        Member member = new Member("77sghyun@gmail.com", "ddd123!", "ddd", "010-2323-2322",
                new Address("dkdkdkd", "dkdkdkd", "20302"));
        AlbumLike albumLike = new AlbumLike(ALBUM1,member);

        when(memberService.getMemberEntity(member.getId())).thenReturn(member);
        when(albumRepository.findById(ALBUM1.getId())).thenReturn(Optional.of(ALBUM1));
        when(albumLikeRepository.save(any(AlbumLike.class))).thenReturn(albumLike);

        albumService.likeAlbum(ALBUM1.getId(), member.getId());
        assertEquals(1, ALBUM1.getLikeCount());
    }

    private static CreateAlbumReq getCreateAlbumReq() {
        return CreateAlbumReq.builder()
                .title(TITLE)
                .artist(ARTIEST)
                .category(CATEGORY)
                .imgUrl(IMG_URL)
                .releaseDate(RELEASE_DATE)
                .price(PRICE)
                .build();
    }
}