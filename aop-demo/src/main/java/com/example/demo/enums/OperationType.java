package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Lee
 * @date : 2020-03-27
 */
@Getter
@AllArgsConstructor
public enum OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

}
