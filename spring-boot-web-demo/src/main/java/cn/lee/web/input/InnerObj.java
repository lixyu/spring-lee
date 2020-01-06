package cn.lee.web.input;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author Lee
 * @date 2019-10-31 11:19
 */
@Data
public class InnerObj {
    @Email(message = "电子邮箱")
    private String param;

}
