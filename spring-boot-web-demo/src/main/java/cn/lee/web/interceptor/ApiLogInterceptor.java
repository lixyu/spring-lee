package cn.lee.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.isrsal.logging.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

/**
 * (自定义)拦截器
 * @author : Lee
 * @date : 2020-10-15
 */
@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime.set(System.currentTimeMillis());
        String uri = request.getRequestURI();
        Map paramMap = request.getParameterMap();
        log.info("用户访问地址:{}, 来路地址: {}, 请求参数: {}", uri, request.getRequestURI(), JSON.toJSON(paramMap));
        log.info("----------------请求头.start.....");
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement();
            log.info(name + ": {}", request.getHeader(name));
        }
        log.info("----------------请求头.end!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ResponseWrapper responseWrapper = new ResponseWrapper(Thread.currentThread().getId(), response);
        String result=new String(responseWrapper.toByteArray(), StandardCharsets.UTF_8);
        log.info("postHandle response:{}",result);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ResponseWrapper responseWrapper = new ResponseWrapper(Thread.currentThread().getId(), response);
        String result=new String(responseWrapper.toByteArray(), StandardCharsets.UTF_8);
        log.info(result);
    }
}
