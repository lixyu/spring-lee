package cn.lee.web.controller;

import cn.lee.web.input.VerificationInput;
import cn.lee.web.util.HttpRequestUtils;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Lee
 * @date 2019-10-31 11:00
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class InputController {

    private final HttpRequestUtils httpRequestUtils;

    @PostMapping("/check/input")
    public R<Object> checkInput(@RequestBody @Valid VerificationInput input){
        log.info(JSON.toJSONString(input));
        return  R.success();
    }

    @GetMapping("/test/http/{what}")
    public R<?> testHttp(@PathVariable String what){
        String url="http://localhost:8080/test/response/"+what;
        String result=httpRequestUtils.doGet(url,10);
        log.info("url:{},output param:{}",url,result);
        return R.success();
    }
    @GetMapping("/test/response/{param}")
    public R<?> testResponse(@PathVariable String param){

        return R.success(param);
    }
}
