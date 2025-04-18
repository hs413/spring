package com.example.demo2;

import com.example.demo2.discount.DiscountPolicy;
import com.example.demo2.discount.FixDiscountPolicy;
import com.example.demo2.discount.RateDiscountPolicy;
import com.example.demo2.member.MemberRepository;
import com.example.demo2.member.MemberService;
import com.example.demo2.member.MemberServiceImpl;
import com.example.demo2.member.MemoryMemberRepository;
import com.example.demo2.order.OrderService;
import com.example.demo2.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해
 * 구현 객체를 생성하고, 연결하는 책임을 가지는 설정 클래스
 * */
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
            memberRepository(),
            discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {

        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
