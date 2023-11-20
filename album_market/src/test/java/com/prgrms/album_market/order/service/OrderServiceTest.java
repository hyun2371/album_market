package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.AlbumDataFactory;
import com.prgrms.album_market.album.entity.Album;
import com.prgrms.album_market.album.service.AlbumService;
import com.prgrms.album_market.member.MemberDataFactory;
import com.prgrms.album_market.member.entity.Member;
import com.prgrms.album_market.member.service.MemberService;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.prgrms.album_market.order.OrderDataFactory.createOrderReq;
import static com.prgrms.album_market.order.OrderDataFactory.order;
import static com.prgrms.album_market.order.dto.OrderResponse.CreateOrderRes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
        Member mockMember = MemberDataFactory.getMember();
        Album mockAlbum = AlbumDataFactory.getAlbum();
        Order mockOrder = order();

        when(memberService.getMemberEntity(1L)).thenReturn(mockMember);
        when(albumService.getAlbumEntity(1L)).thenReturn(mockAlbum);
        when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

        CreateOrderRes response = orderService.createOrder(createOrderReq());

        assertEquals(mockOrder.getId(), response.getCreatedOrderId());
    }
}
