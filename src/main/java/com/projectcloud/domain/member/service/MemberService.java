package com.projectcloud.domain.member.service;


import com.projectcloud.domain.member.dto.MemberCreateRequest;
import com.projectcloud.domain.member.dto.MemberCreateResponse;
import com.projectcloud.domain.member.dto.MemberResponse;
import com.projectcloud.domain.member.entity.Member;
import com.projectcloud.domain.member.repository.MemberRepository;
import com.projectcloud.global.error.CommonError;
import com.projectcloud.global.error.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;


    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request) {
        Member newMember = repository.save(
                new Member(
                        request.name(),
                        request.age(),
                        request.mbti()
                )
        );
        return MemberCreateResponse.from(newMember);
    }

    @Transactional
    public MemberResponse findById(Long id) {
        Member member = repository.findById(id).orElseThrow(
                () -> new CommonException(CommonError.MEMBER_NOT_FOUND)
        );
        return MemberResponse.from(member);
    }

}
