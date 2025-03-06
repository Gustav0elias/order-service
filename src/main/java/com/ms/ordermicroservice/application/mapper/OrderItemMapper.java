package com.ms.ordermicroservice.application.mapper;

import com.ms.ordermicroservice.application.dto.request.OrderItemRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderItemResponseDTO;
import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItem toModel(OrderItemRequestDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(dto.quantity());
        orderItem.setPrice(dto.price());


        Product product = new Product();
        product.setId(dto.productId());
        orderItem.setProduct(product);

        return orderItem;
    }


    public OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
