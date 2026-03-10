package com.projectcloud.domain.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberCreateRequest(
        @NotBlank(message = "이름은 필수 입력 항목입니다.")
        String name,

        @Min(1)
        int age,

        @NotBlank(message = "mbti는 필수 입력 항목입니다.")
        String mbti
){}
