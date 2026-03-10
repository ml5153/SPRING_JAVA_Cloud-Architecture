package com.projectcloud.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * [HTTP Status Code 기반 에러 정의 가이드]
 * * 400 (Bad Request): "데이터 형식 오류"
 * - 클라이언트가 필수 값을 누락하거나, 유효하지 않은 형식(이메일 규칙 등)을 보냈을 때 사용.
 * * 401 (Unauthorized): "인증 실패"
 * - 아이디/비밀번호 불일치, 토큰 누락 또는 만료 등 '누구인지' 증명하지 못한 경우.
 * * 403 (Forbidden): "권한 부족 / 거부"
 * - 로그인(인증)은 했으나 해당 리소스에 접근 권한이 없는 경우 (예: 승인 대기, 활동 정지).
 * * 404 (Not Found): "리소스 없음"
 * - 요청한 URL 자체가 잘못되었거나, DB에 해당 ID(PK)를 가진 데이터가 존재하지 않을 때.
 * * 409 (Conflict): "비즈니스 충돌"
 * - 요청은 정상이나 서버 상태와 충돌될 때 (예: 이메일 중복 가입, 중복 데이터 등록 시도).
 * * 500 (Internal Server Error): "서버 내부 오류"
 * - 비즈니스 로직에서 처리하지 못한 예외(NullPointerException 등)가 발생한 경우.
 */
@Getter
@RequiredArgsConstructor
public enum CommonError {

    // -- 1000:  --
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"S1001","서버 내부 오류가 발생했습니다."),


    // -- 3000: Member --
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST,"C3001","존재하지 않는 유저입니다."),
    INVALID_MEMBER_UPDATE(HttpStatus.BAD_REQUEST,"C3002","유효하지 않은 유저 정보 또는 상태 변경 요청입니다."),
    DUPLICATE_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST,"c3003","이미 사용중인 이메일입니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;

}

