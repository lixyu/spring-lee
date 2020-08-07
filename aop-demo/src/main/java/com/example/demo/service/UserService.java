package com.example.demo.service;

import com.example.demo.annotation.OperationLogDetail;
import com.example.demo.enums.OperationType;
import com.example.demo.enums.OperationUnit;
import org.springframework.stereotype.Service;

/**
 * @author : Lee
 * @date : 2020-03-27
 */
@Service
public class UserService {
    @OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = OperationUnit.USER,operationType = OperationType.SELECT)
    public String findUserName(String tel) {
        System.out.println("tel:" + tel);
        return "Lee";
    }

    public
}
