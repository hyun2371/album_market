package com.prgrms.album_market.order.service;

import com.prgrms.album_market.album.repository.AlbumRepository;
import com.prgrms.album_market.member.repository.MemberRepository;
import com.prgrms.album_market.order.dto.OrderRequest;
import com.prgrms.album_market.order.dto.OrderResponse;
import com.prgrms.album_market.order.entity.Order;
import com.prgrms.album_market.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.prgrms.album_market.album.AlbumDataFactory.getAlbum;
import static com.prgrms.album_market.member.MemberDataFactory.getMember;
import static com.prgrms.album_market.order.OrderDataFactory.order;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderSuccess(){
        getAlbum().increaseStock(3);
        getMember().chargeMoney(2000000);
        when(memberRepository.findById(1L))
                .thenReturn(Optional.of(getMember()));
        when(albumRepository.findById(1L))
                .thenReturn(Optional.of(getAlbum()));
        when(orderRepository.save(any(Order.class))).thenReturn(order());

        OrderRequest.CreateOrderReq request = OrderRequest.CreateOrderReq.builder()
                .quantity(1)
                .albumId(1L)
                .memberId(1L)
                .build();
        OrderResponse.CreateOrderRes createOrderRes = orderService.createOrder(request);
        assertEquals(createOrderRes.getCreatedOrderId(), order().getId());

    }
}
