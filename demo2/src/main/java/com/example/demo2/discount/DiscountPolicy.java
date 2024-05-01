package com.example.demo2.discount;

import com.example.demo2.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
