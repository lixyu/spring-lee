package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : Lee
 * @date : 2020-03-27
 */
@Getter
@AllArgsConstructor
public enum OperationUnit {
    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    Redis("redis");

    private String value;
}
