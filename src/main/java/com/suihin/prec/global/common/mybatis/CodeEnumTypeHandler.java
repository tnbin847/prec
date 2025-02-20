package com.suihin.prec.global.common.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link CodeEnum}인터페이스를 구현한 {@link Enum}을 대상으로 해당 {@link Enum}에 정의되어 있는 코드값을
 * 데이터베이스에 저장하거나, 데이터베이스로부터 가져온 코드값을 {@link Enum}으로 변환하기 위해 매핑 처리를 수행하는 타입 핸들러
 *
 * @param <E>   타입 핸들러에서 처리하고자 하는 {@link Enum}
 */

@MappedTypes(CodeEnum.class)
public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {
    private final Class<E> type;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertCodeToEnum(rs.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertCodeToEnum(rs.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertCodeToEnum(cs.getString(columnIndex));
    }

    /**
     * 데이터베이스로부터 가져온 코드값과 일치한 상수값을 정의한 {@link Enum}을 반환한다.
     *
     * @param code  데이터베이스로부터 가져온 코드값
     * @return      일치한 상수값이 존재할 경우, 해당 상수값을 정의한 {@link Enum}을, 존재하지 않을 경우 {@code null}을 반환
     */
    private E convertCodeToEnum(final String code) {
        try {
            final E[] constants = type.getEnumConstants();
            for (E codeEnum : constants) {
                if (codeEnum.getCode().equals(code)) {
                    return codeEnum;
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + ".", e);
        }
    }
}