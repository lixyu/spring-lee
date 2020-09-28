package cn.lee.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Lee
 * @date 2019-10-31 11:27
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {


    private String errorMessage(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorSb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            // 异常处理
            for (ObjectError error : bindingResult.getAllErrors()) {
                String errorMessage = error.getDefaultMessage();
                if (StringUtils.isEmpty(errorMessage)) {
                    errorMessage = "input valid error";
                }
                errorSb.append(errorMessage).append(";");
            }
        }
        return errorSb.toString();
    }
}
