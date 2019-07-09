package cn.lee.nosql.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Lee
 * @date 2019-07-09 17:48
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @GetMapping("/set/{session}")
    public String setSession(HttpServletRequest request, @PathVariable String session){
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("sessionId",session);
        return "success";
    }

    @GetMapping("/get")
    public String getSession(HttpServletRequest request){
        HttpSession httpSession=request.getSession();
        String session=(String) httpSession.getAttribute("sessionId");
        return "sessionId:"+session;
    }
}
