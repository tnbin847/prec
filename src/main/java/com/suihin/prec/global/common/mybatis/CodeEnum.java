package com.suihin.prec.global.common.mybatis;

/**
 * 타입 핸들러를 통해 매핑 처리하고자 하는 코드값을 정의한 {@link Enum}을 일관적으로 구현하기 위해 정의한 인터페이스
 *
 * @see CodeEnumTypeHandler
 */
public interface CodeEnum {

    /**
     * 데이터베이스 저장 또는 {@link Enum}과의 변환 처리를 위한 코드값을 반환한다.
     *
     * @return  문자열 타입의 코드값
     */
    String getCode();

    /**
     * 코드값에 대한 라벨을 반환한다.
     *
     * @return 코드값의 의미를 나타내는 라벨값 반환
     */
    String getLabel();
}