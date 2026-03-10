package com.projectcloud.domain.member.controller;


import com.projectcloud.domain.member.dto.MemberCreateRequest;
import com.projectcloud.domain.member.dto.MemberCreateResponse;
import com.projectcloud.domain.member.dto.MemberResponse;
import com.projectcloud.domain.member.service.MemberService;
import com.projectcloud.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<ApiResponse<MemberCreateResponse>> save(
            @Valid @RequestBody MemberCreateRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(memberService.save(request))
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(memberService.findById(id))
                );
    }


}



