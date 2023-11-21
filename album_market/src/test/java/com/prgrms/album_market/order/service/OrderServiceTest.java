package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.common.exception.CustomException;
import com.prgrms.album_market.common.exception.ErrorCode;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.prgrms.album_market.album.AlbumDataFactory.album;
import static com.prgrms.album_market.member.MemberDataFactory.member;
import static com.prgrms.album_market.order.OrderDataFactory.createOrderReq;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MemberService memberService;
    @Mock
    private AlbumService albumService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderSuccess(){
        Member member = member();
        ReflectionTestUtils.setField(member, "balance", 500000);

        Album album = album(21000);
        ReflectionTestUtils.setField(album, "stock",4);

        given(memberService.getMemberEntity(1L)).willReturn(member);
        given(albumService.getAlbumEntity(1L)).willReturn(album);

        CreateOrderRes response = orderService.createOrder(createOrderReq(3));//order생성

        assertNotNull(response);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void createOrderFail(){
        //given
        Member member = member();
        ReflectionTestUtils.setField(member, "balance", 500000);

        Album album = album(21000);
        ReflectionTestUtils.setField(album, "stock",1);

        given(memberService.getMemberEntity(1L)).willReturn(member);
        given(albumService.getAlbumEntity(1L)).willReturn(album);

        //when
        CustomException exception = assertThrows(CustomException.class, ()
                -> orderService.createOrder(createOrderReq(5)));

        //then
        assertEquals(ErrorCode.OUT_OF_STOCK,exception.getErrorCode());
    }
}
