package com.suihin.prec.global.common.response.model;

import com.suihin.prec.global.common.response.ResponseEnum;
import lombok.Getter;

/**
 * 요청이 정상적으로 처리되었을 경우, 클라이언트에게 반환될 성공 응답의 구조를 정의한 클래스.
 *
 * @param <T>   요청 처리 결과에 따른 응답 데이터의 타입
 */

@Getter
public class ApiResponse<T> extends BaseResponse {

    /**
     * 성공 응답에만 포함될 응답 결과 데이터로서, 데이터가 존재하지 않거나 {@code null}일 경우,
     * 빈 객체 또는 빈 배열을 할당하여 반환한다.
     */
    private final T data;

    private ApiResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 성공 응답을 반환한다.
     * <blockquote><pre>
     *     {
     *         "responseDatetime" : 2025-02-20 18:00,
     *         "code" : 100000,
     *         "message" : "요청이 정상적으로 처리되었습니다.",
     *         "data" : {단일 객체} 또는 [{단일 객체}, {단일 객체}, ...]
     *
     *     }
     * </pre></blockquote>
     *
     * @param responseEnum  성공 응답 코드와 더불어 관련 상태 정보들을 정의한 {@link Enum}
     * @param data          요청 처리에 대한 응답 결과 데이터
     * @return              응답 객체
     * @param <T>           응답 결과 데이터의 타입
     */
    public static<T> ApiResponse<T> success(final ResponseEnum responseEnum, final T data) {
        return new ApiResponse<>(responseEnum.getCode(), responseEnum.getMessage(), data);
    }
}