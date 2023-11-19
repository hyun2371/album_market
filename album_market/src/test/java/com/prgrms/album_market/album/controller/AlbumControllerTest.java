package com.prgrms.album_market.album.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.album_market.album.service.AlbumService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.prgrms.album_market.TestDataFactory.getCreateAlbumReq;
import static com.prgrms.album_market.TestDataFactory.getCreateAlbumRes;
import static com.prgrms.album_market.album.dto.album.AlbumRequest.CreateAlbumReq;
import static com.prgrms.album_market.album.dto.album.AlbumResponse.CreateAlbumRes;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlbumController.class)
@AutoConfigureMockMvc(addFilters = false)
class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAlbum() throws Exception {
        CreateAlbumReq requestDto = getCreateAlbumReq();
        CreateAlbumRes responseDto = getCreateAlbumRes();

        // Arrange
        given(albumService.createAlbum(requestDto)).willReturn(getCreateAlbumRes());

        // Act
        ResultActions response = mockMvc.perform(post("/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.createdAlbumId", CoreMatchers.is(responseDto.getCreatedAlbumId().intValue())))
                .andDo(MockMvcResultHandlers.print());
    }
}
