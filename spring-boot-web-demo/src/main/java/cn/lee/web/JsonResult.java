package cn.lee.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @param <T>
 * @author Administrator
 * @ClassName: JsonResult
 * @Description: App返回转化实体类
 * @date 2017年7月17日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {

    private static final long serialVersionUID = 8318535021833217159L;
    private static String successMsg = "Success";

    private int status;

    private String message;

    private T data;

    public static <T> JsonResult<T> successResponse(T data) {
        return new JsonResult<>(200, successMsg, data);
    }

    public static <T> JsonResult<T> errorResponse(String errorMessage) {
        return new JsonResult<>(400, errorMessage, null);
    }

    public static <T> JsonResult<T> errorResponse(int status, String errorMessage) {
        return new JsonResult<>(status, errorMessage, null);
    }
}
