package com.nimoh.commercetoy.order.dto;

import com.nimoh.commercetoy.address.domain.Address;
import com.nimoh.commercetoy.order.enums.OrderStatus;
import com.nimoh.commercetoy.product.domain.Product;
import com.nimoh.commercetoy.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderUpdateRequestDto {

    private Product product;

    private OrderStatus orderStatus;

    private int amount;

    private int totalPrice;

    private Address address;

    @Builder
    public OrderUpdateRequestDto(Product product, OrderStatus orderStatus, int amount, int totalPrice, Address address) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.address = address;
    }
}
