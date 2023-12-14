package com.prgrms.album_market.album.controller;

import static com.prgrms.album_market.album.AlbumFixture.createAlbumRequest;
import static com.prgrms.album_market.album.AlbumFixture.createAlbumRes;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.prgrms.album_market.album.dto.album.request.CreateAlbumRequest;
import com.prgrms.album_market.album.dto.album.response.CreateAlbumResponse;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

class AlbumControllerDocsTest extends RestDocsSupport {
    private final AlbumService albumService = mock(AlbumService.class);
    @Override
    protected Object initController() {
        return new AlbumController(albumService);
    }

    @DisplayName("앨범 생성 API")
    @Test
    void createAlbum() throws Exception {
        CreateAlbumRequest requestDto = createAlbumRequest();
        CreateAlbumResponse responseDto = createAlbumRes();

        given(albumService.createAlbum(requestDto)).willReturn(responseDto);

        mockMvc.perform(post("/albums")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andDo(document("album-create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("artist").type(JsonFieldType.STRING)
                        .description("아티스트"),
                    fieldWithPath("category").type(JsonFieldType.STRING)
                            .description("카테고리"),
                    fieldWithPath("imgUrl").type(JsonFieldType.STRING)
                        .description("이미지 url 주소"),
                    fieldWithPath("releaseDate").type(JsonFieldType.STRING)
                        .description("발매일"),
                    fieldWithPath("price").type(JsonFieldType.NUMBER)
                        .description("가격")
                ),
                responseFields(
                    fieldWithPath("createdAlbumId").type(JsonFieldType.NUMBER)
                        .description("생성된 앨범 아이디")
                )
            ));


    }

}
