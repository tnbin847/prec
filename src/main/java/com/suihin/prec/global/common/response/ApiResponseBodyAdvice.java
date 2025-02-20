package com.suihin.prec.global.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.suihin.prec.global.common.response.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;

/**
 * 요청이 정상적으로 처리되어 성공 응답을 클라이언트에게 반환할 때 응답 데이터가 존재하지 않거나 {@code null}일 경우,
 * 컬렉션 프레임워크를 이용해 빈 객체 또는 빈 배열을 할당하여 응답을 반환하기 위해 구현된 클래스
 */
@RestControllerAdvice(basePackages = "com.suihin.prec.domain")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 컨트롤러 클래스 내의 메소드의 반환 타입이 {@link ApiResponse}나 {@link ApiResponse}를 상속 또는 구현한 클래스가
     * 아닐 경우, {@code true}를 반환한다.
     * <p>
     * 해당 메소드의 반횐되는 결과가 {@code true}일 경우, 응답 데이터의 변환 처리를 위한 {@code beforeBodyWrite()}를 호출한다.
     * </p>
     *
     * @return  {@link ApiResponse}를 구현 또는 상속한 클래스가 아니라면 {@code true}를, 구현 또는 상속한 클래스라면 {@code false}를 반환
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * 특정 컨버터가 선택되어 응답 바디부를 변환하기 전, 응답 바디부 내의 {@code data} 값의 유무를 판단하여 존재하지 않거나 {@code null}일 경우
     * 빈 객체 또는 빈 컬렉션을 {@code data}에 할당하여 성공 응답을 반환한다.
     *
     * @return  성공 응답 객체
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        body = (body == null) ? generateEmptyCollection(returnType.getParameterType()) : body;
        final ApiResponse<?> apiResponse = ApiResponse.success(ResponseEnum.SUCCESS, body);

        if (MappingJackson2HttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            return apiResponse;
        }

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialized an object to JSON.", e);
        }
    }

    private Object generateEmptyCollection(final Class<?> returnType) {
        return List.class.isAssignableFrom(returnType) ? Collections.emptyList() : Collections.emptyMap();
    }
}