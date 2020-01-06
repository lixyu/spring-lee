package cn.lee.web.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Lee
 * @date 2019-10-31 11:02
 */
@Data
public class VerificationInput {
    @NotBlank(message="姓名不能为空")
    private String name;
    private int age;
    @Pattern(regexp = "^1\\d{10}$",message = "手机格式不正确")
    private String mobile;

    @Valid
    private InnerObj obj;
}
