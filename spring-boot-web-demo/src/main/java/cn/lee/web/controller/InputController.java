package cn.lee.web.controller;

import cn.lee.web.input.VerificationInput;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Lee
 * @date 2019-10-31 11:00
 */
@Slf4j
@RestController
public class InputController {


    @PostMapping("/check/input")
    public R<Object> checkInput(@RequestBody @Valid VerificationInput input){
        log.info(JSON.toJSONString(input));
        return  R.success();
    }
}
