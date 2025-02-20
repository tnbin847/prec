package com.suihin.prec.global.config.security;

/**
 * 스프링 시큐리티 관련 처리시 사용될 상수들을 정의한 상수 클래스
 */

public final class SecurityConstants {

    /**
     * 보안 필터를 적용할 필요가 없는 리소스 매핑 URL들을 정의한 상수 배열
     */
    public static final String[] RESOURCE_MAPPING_MATCHERS = {
            "/css/**", "/fonts/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console"
    };

    /**
     * 별도의 인증 요청 없이 접근을 허용할 URL들을 정의한 상수 배열
     */
    public static final String[] PUBLICY_REQUEST_MATCHERS = {
            "/", "/sign-up", "/api/v1/auth/login", "/api/v1/auth/logout", "/api/v1/user/exists/**", "/api/v1/user"
    };
}