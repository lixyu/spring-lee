package cn.lee.nosql.web;

import cn.lee.nosql.annotation.CacheLock;
import cn.lee.nosql.annotation.CacheParam;
import cn.lee.nosql.input.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lee
 * @date 2019-07-15 10:11
 */
@RestController
@RequestMapping("/no/repeat")
public class NoRepeatController {

    @CacheLock(prefix = "books",expire = 60)
    @GetMapping("/query")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    @CacheLock(prefix = "user",expire = 60)
    @PostMapping("/user")
    public String user(@CacheParam(name = "token1") @RequestBody User user) {
        return "success - " + user.getName();
    }
}
