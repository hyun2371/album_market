package com.prgrms.album_market.album.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.album_market.album.dto.album.request.CreateAlbumRequest;
import com.prgrms.album_market.album.dto.album.response.CreateAlbumResponse;
import com.prgrms.album_market.album.dto.album.response.GetAlbumResponse;
import com.prgrms.album_market.album.dto.album.request.UpdateAlbumRequest;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.common.PageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static com.prgrms.album_market.album.AlbumFixture.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlbumController.class)
class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAlbum() throws Exception {
        CreateAlbumRequest requestDto = createAlbumRequest();
        CreateAlbumResponse responseDto = createAlbumRes();

        given(albumService.createAlbum(requestDto)).willReturn(responseDto);

        ResultActions response = mockMvc.perform(post("/albums")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.createdAlbumId",
                        is(responseDto.createdAlbumId().intValue())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllAlbum() throws Exception {
        Page<GetAlbumResponse> pagedAlbum = new PageImpl<>(List.of(getAlbumResponse()));
        PageResponse<GetAlbumResponse> pageResponse = PageResponse.fromPage(pagedAlbum);
        given(albumService.getAllAlbum(any())).willReturn(pageResponse);

        ResultActions response = mockMvc.perform(get("/albums")
                .param("page","0")
                .param("size", "10")
                .contentType(APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.itemSize", is((int)(pageResponse.getItemSize()))))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void getAlbumById() throws Exception {
        GetAlbumResponse dtoResponse = getAlbumResponse();
        given(albumService.getAlbumById(1L)).willReturn(dtoResponse);

        ResultActions response = mockMvc.perform(get("/albums/1")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoResponse)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(dtoResponse.title())))
                .andExpect(jsonPath("$.artist", is(dtoResponse.artist())))
                .andExpect(jsonPath("$.category", is(dtoResponse.category())))
                .andExpect(jsonPath("$.imgUrl", is(dtoResponse.imgUrl())))
                .andExpect(jsonPath("$.releaseDate", is(dtoResponse.releaseDate())))
                .andExpect(jsonPath("$.price", is(dtoResponse.price())))
                .andExpect(jsonPath("$.stock", is(dtoResponse.stock())))
                .andExpect(jsonPath("$.likeCount", is(dtoResponse.likeCount())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateAlbum() throws Exception {
        UpdateAlbumRequest dtoRequest = UpdateAlbumRequest();
        GetAlbumResponse dtoResponse = UpdateAlbumResponse();

        when(albumService.updateAlbum(1L,dtoRequest)).thenReturn(dtoResponse);
        ResultActions response = mockMvc.perform(put("/albums/1")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoRequest)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(dtoResponse.title())))
                .andExpect(jsonPath("$.artist", is(dtoResponse.artist())))
                .andExpect(jsonPath("$.category", is(dtoResponse.category())))
                .andExpect(jsonPath("$.imgUrl", is(dtoResponse.imgUrl())))
                .andExpect(jsonPath("$.releaseDate", is(dtoResponse.releaseDate())))
                .andExpect(jsonPath("$.price", is(dtoResponse.price())))
                .andExpect(jsonPath("$.stock", is(dtoResponse.stock())))
                .andExpect(jsonPath("$.likeCount", is(dtoResponse.likeCount())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteAlbum() throws Exception {
        doNothing().when(albumService).deleteAlbum(1L);

        ResultActions response = mockMvc.perform(delete("/albums/1")
                .contentType(APPLICATION_JSON));
        response.andExpect(status().isOk());
    }
}
