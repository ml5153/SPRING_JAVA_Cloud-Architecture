package com.projectcloud.domain.member.dto;

import com.projectcloud.domain.member.entity.Member;

public record MemberCreateResponse(
        Long id,
        String name,
        int age,
        String mbti
) {

    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}
