package com.suihin.prec.global.common.response.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 응답의 종류에 상관 없이 반드시 포함되어야 할 필드들을 정의한 기본 응답 클래스
 */

@Getter
public abstract class BaseResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime responseDatetime;

    /**
     * 응답 구분을 위해 자체 정의한 응답 코드로서, {@link com.suihin.prec.global.common.response.ResponseEnum}에 정의된
     * {@code code}값이 담기게 된다.
     */
    private final int code;

    /**
     * 응답의 구분을 위해 정의한 응답 메시지로서, {@link com.suihin.prec.global.common.response.ResponseEnum}에 정의된
     * {@code message}값이 담기게 된다.
     */
    private final String message;

    public BaseResponse(int code, String message) {
        this.responseDatetime = LocalDateTime.now();
        this.code = code;
        this.message = message;
    }
}