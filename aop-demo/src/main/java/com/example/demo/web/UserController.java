package com.example.demo.web;

import com.example.demo.annotation.Push;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Lee
 * @date : 2020-03-27
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 访问路径 localhost:8279/user/findUserNameByTel/123456
     * @param tel 手机号
     * @return userName
     */
    @RequestMapping("/findUserNameByTel/{phone}")
    public String findUserNameByTel(@PathVariable String phone){
        return userService.findUserName(phone);
    }


    @Push
    @GetMapping("/test/{var}")
    public String test(@PathVariable String var){

        return var+"1";
    }
}
