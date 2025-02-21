package com.suihin.prec.domain.user.enums;

import com.suihin.prec.global.common.mybatis.CodeEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccountType implements CodeEnum {
    LOCAL ("LOCAL", "일반"),
    SOCIAL ("OAUTH", "소셜");

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