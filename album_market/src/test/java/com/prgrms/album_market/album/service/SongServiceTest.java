package com.prgrms.album_market.album.service;

import com.prgrms.album_market.album.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void testCreateSong() {


    }

    @Test
    void testGetAlbumSongs() {
    }
}
