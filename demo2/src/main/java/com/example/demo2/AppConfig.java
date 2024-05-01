package com.example.demo2;

import com.example.demo2.discount.FixDiscountPolicy;
import com.example.demo2.member.MemberService;
import com.example.demo2.member.MemberServiceImpl;
import com.example.demo2.member.MemoryMemberRepository;
import com.example.demo2.order.OrderService;
import com.example.demo2.order.OrderServiceImpl;

/**
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해
 * 구현 객체를 생성하고, 연결하는 책임을 가지는 설정 클래스
 * */
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
            new MemoryMemberRepository(),
            new FixDiscountPolicy());
    }
}
