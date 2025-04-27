package com.example.demo2.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
