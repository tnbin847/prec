package com.suihin.prec.global.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;

/**
 * 예외 처리를 지원하는 유틸리티 클래스
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExceptionUtils {

    /**
     * 전달된 값의 문자열 형태를 반환한다.
     *
     * @param value 문자열 형태로 변환하고자 하는 값
     * @return      전달된 값이 {@code null}일 경우 빈 문자열을, 아닐 경우 값의 문자열 형태 반환
     */
    public static String valueAsString(final Object value) {
        return value == null ? "" : value.toString();
    }

    /**
     * 발생된 예외의 첫번째 스택 트레이스 정보를 반환한다.
     *
     * @param t 발생된 예외
     * @return  예외의 첫번째 스택 트레이스 정보를 문자열로 반환
     */
    public static String getRootStackTrace(final Throwable t) {
        final StackTraceElement[] traceElements = t.getStackTrace();
        final StringBuilder sb = new StringBuilder();

        sb.append("EXCEPTION TRACE\n").append(traceElements[0].getClassName()).append(" In ")
          .append(traceElements[0].getLineNumber()).append("Line, in ")
          .append(traceElements[0].getMethodName()).append("()");
        return sb.toString();
    }

    /**
     * 발생된 예외의 스택 트레이스 정보와 더불어 발생 원인과 메시지 등을 함께 반환한다.
     *
     * @param t         발생된 예외
     * @param logger    로거 객체
     * @return          발생된 예외의 첫번째 스택 트레이스 정보와 더불어 발생 원인 및 예외 메시지를 결합한 문자열 반환
     */
    public static String getRootCauseStackTrace(final Throwable t, final Logger logger) {
        final StringBuilder sb = new StringBuilder(getRootStackTrace(t));
        if (logger.isWarnEnabled()) {
            final Throwable cause = t.getCause();
            if (cause == null) {
                sb.append("\n").append("Cause : No cause available.");
            } else {
                sb.append("\n").append("Cause : ").append(cause);
            }
            sb.append("\n").append("EXCEPTION MESSAGE : ").append(t.getMessage());
        }
        return sb.toString();
    }
}