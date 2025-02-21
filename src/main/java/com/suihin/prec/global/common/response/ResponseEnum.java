package com.suihin.prec.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 성공 및 에러 응답에 대한 코드 및 관련 상태 정보들을 정의한 {@link Enum}
 */

@RequiredArgsConstructor
@Getter
public enum ResponseEnum {

    /**
     * 공통 성공 응답 코드 및 관련 상태 정보
     */
    SUCCESS (100000, "요청이 정상적으로 처리되었습니다.", HttpStatus.OK),

    /**
     * 공통 에러 응답 코드 및 관련 상태 정보
     */
    INVALID_REQUEST_PARAM_VALUE (-800010, "요청 파라미터의 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAM_TYPE (-800011, "유효하지 않은 타입의 요청 파라미터입니다.", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND (-804020, "요청하신 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_SUPPORTED_METHOD (-805020, "해당 요청은 %s를 지원하지 않습니다. 지원하는 메소드는 %s 입니다.", HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_SERVER_ERROR (-900000, "내부 서버에 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * 사용자 에러 응답 코드 및 관련 상태 정보
     */
    ALREADY_EXISTS_EMAIL (-809112, "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
    USER_NOT_FOUND (-804120, "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    LOGIN_REQUIRED (-801130, "로그인이 필요한 서비스입니다.", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED (-803130, "접근 권한이 없습니다.", HttpStatus.FORBIDDEN)
    ;

    private final int code;

    private final String message;

    private final HttpStatus status;

    /**
     * 응답 메시지를 동적으로 포맷팅하여 반환한다.
     *
     * @param args  포맷 작업시 삽입될 값들
     * @return      포맷팅된 메시지
     */
    public String getFormattedMessage(Object... args) {
        return String.format(this.message, args);
    }
}