package com.projectcloud.domain.member.dto;

import com.projectcloud.domain.member.entity.Member;

public record MemberResponse(
        Long id,
        String name,
        int age,
        String mbti
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
