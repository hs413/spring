package com.example.demo2.order;

import com.example.demo2.discount.DiscountPolicy;
import com.example.demo2.discount.FixDiscountPolicy;
import com.example.demo2.discount.RateDiscountPolicy;
import com.example.demo2.member.Member;
import com.example.demo2.member.MemberRepository;
import com.example.demo2.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
