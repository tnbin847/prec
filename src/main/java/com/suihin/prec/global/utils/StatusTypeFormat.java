package com.suihin.prec.global.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 데이터베이스 테이블의 상태여부 컬럼 값을 코드단에서 처리하기 위해 서로 다른 타입의 값들을 상응되는 의미별로 정의한 {@link Enum}
 */

@RequiredArgsConstructor
@Getter
public enum StatusTypeFormat {

    YES (1, "Y", true),
    NO (0, "N", false);

    private final int number;

    private final String symbol;

    private final boolean bool;

    /**
     * 전달된 정수형의 인자값에 해당하는 논리형 상태값을 반환한다.
     *
     * @param value 논리형 상태값으로 변환하고자 하는 정수값으로 {@code 1} 또는 {@code 0}이어야 한다.
     * @return      전달된 값이 {@code 1}일 경우 {@code true}를, {@code 0}일 경우 {@code false}를 반환
     */
    public boolean toBool(int value) {
        return Arrays.stream(values())
                .filter(typeFormat -> typeFormat.getNumber() == value)
                .findFirst()
                .map(StatusTypeFormat::isBool)
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert " + value + " to boolean."));
    }
}