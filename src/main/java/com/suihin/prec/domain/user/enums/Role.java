package com.suihin.prec.domain.user.enums;

import com.suihin.prec.global.common.mybatis.CodeEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role implements CodeEnum {
    USER ("ROLE_USER", "일반회원"),
    MANAGER ("ROLE_MANAGER", "매니저"),
    ADMIN ("ROLE_ADMIN", "관리자");

    private final String code;

    private final String label;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}