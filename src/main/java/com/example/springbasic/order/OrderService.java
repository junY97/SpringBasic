package com.example.springbasic.order;

/**
 * @author junyeong.jo .
 * @since 2023-06-08
 */
public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
