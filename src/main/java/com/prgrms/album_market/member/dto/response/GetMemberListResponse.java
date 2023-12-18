package com.prgrms.album_market.member.dto.response;

import java.util.List;

public record GetMemberListResponse (
        List<GetMemberResponse> members
){}
