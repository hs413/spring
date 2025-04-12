package com.example.demo2;

import com.example.demo2.member.Grade;
import com.example.demo2.member.Member;
import com.example.demo2.member.MemberService;
import com.example.demo2.member.MemberServiceImpl;
import com.example.demo2.order.Order;
import com.example.demo2.order.OrderService;
import com.example.demo2.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
