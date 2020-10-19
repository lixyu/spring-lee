//package cn.lee.web.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * @author : Lee
// * @date : 2020-10-19
// */
//@Slf4j
//@Component
//@WebFilter(filterName = "logFilter",urlPatterns = "/*")
//public class LogFilter implements Filter {
//
//    private static final String ignoreUrlRegex = ".*((pay/)|(/index)|(/index/.*)|([.]((html)|(jsp)|(css)|(js)|(gif)|(png))))$";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        /*ThreadContext.put("TId", UUID.randomUUID().toString());
//        ResponseWrapper responseWrapper = new ResponseWrapper(Thread.currentThread().getId(), (HttpServletResponse) request);
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        RequestWrapper requestWrapper = new RequestWrapper(Thread.currentThread().getId(), httpServletRequest);
//
//        // 请求html页面、js不打印日志
//        if (httpServletRequest.getRequestURI().matches(ignoreUrlRegex)) {
//            ThreadContext.clearAll();
//            chain.doFilter(request, responseWrapper);
//            return;
//        }
//
//        Map params;
//        // 记录入参
//        log.info("请求的URL：" + httpServletRequest.getRequestURI());
//
//        chain.doFilter(requestWrapper, responseWrapper);
//
//        // 打印from格式的入参信息
//        params = request.getParameterMap();
//        if (null != params && params.size() != 0) {
//            log.info("入参：" + JSON.toJSONString(params));
//        } else {
//            // 打印json格式的入参信息
//            String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding() : "UTF-8";
//            log.info("入参" + new String(requestWrapper.toByteArray(), charEncoding));
//        }
//
//        // 记录出参
//        String outParam = new String();
//        // 记录出参响应头
//        params = new HashMap();
//        // 如果响应头存在errorCode则打印，除文件下载外均不存在
//
//        params.put("errorCode", ((ResponseFacade) request).getHeader("errorCode"));
//        params.put("errorMsg", (URLDecoder.decode(((ResponseFacade) response).getHeader("errorMsg"), StandardCharsets.UTF_8)));
//        outParam = JSON.toJSONString(params);
//
//
//        // 记录出参响应体
//        if (params.size() < 2) {
//            outParam = outParam + new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
//        }
//
//        log.info("出参：" + outParam);
//
//        ThreadContext.clearAll();*/
//    }
//}
