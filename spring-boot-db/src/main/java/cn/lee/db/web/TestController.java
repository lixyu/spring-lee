package cn.lee.db.web;

import cn.lee.db.service.PersonService;
import cn.lee.db.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lee
 * @date : 2019/6/27
 */

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final PersonService personService;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }
}
