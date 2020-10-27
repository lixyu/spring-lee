package cn.lee.web.interceptor;

import cn.lee.web.config.Session;
import cn.lee.web.service.AppTokenService;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.config.SpringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AppTokenIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppTokenService appTokenService = SpringUtils.getBean("appTokenService");
        String token = request.getHeader("accessToken");
        if (null == token) {
            token = request.getParameter("accessToken");
        }



        Session session =  appTokenService.decodeToken(token);
        Session s = (Session) request.getSession().getAttribute("session");
        if (null == s || !(s.getCustId().equals(session.getCustId()))) {
            request.getSession().setAttribute("session", session);
        }
        return true;
    }
}
