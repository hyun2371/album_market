package com.prgrms.album_market.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgrms.album_market.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.prgrms.album_market.member.MemberDataFactory.*;
import static com.prgrms.album_market.member.dto.MemberRequest.SignUpReq;
import static com.prgrms.album_market.member.dto.MemberResponse.GetMemberListRes;
import static com.prgrms.album_market.member.dto.MemberResponse.SignUpRes;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createMember() throws Exception {
        SignUpRes responseDto = getSignUpRes();
        SignUpReq requestDto = getSignUpReq();

        given(memberService.signUp(requestDto)).willReturn(responseDto);

        ResultActions response = mockMvc.perform(post("/members")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.createdMemberId",
                        is(responseDto.getCreatedMemberId().intValue())));
    }

    @Test
    void getAllMember() throws Exception {
        GetMemberListRes responseDTO = getMemberListRes();
        given(memberService.getMembers()).willReturn(responseDTO);

        ResultActions response = mockMvc.perform(get("/members")
                .contentType(APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.members[0].memberId",
                        is(responseDTO.getMembers().get(0).getMemberId().intValue())))
                .andExpect(jsonPath("$.members[0].email",
                        is(responseDTO.getMembers().get(0).getEmail())));
    }
}
