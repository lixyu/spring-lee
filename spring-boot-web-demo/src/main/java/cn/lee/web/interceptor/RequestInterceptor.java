package cn.lee.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * web请求拦截器
 * @author : Lee
 * @date : 2020-10-15
 */
@Slf4j
public class RequestInterceptor  implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
        request.getParameterMap();

    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        //request.
    }
}
