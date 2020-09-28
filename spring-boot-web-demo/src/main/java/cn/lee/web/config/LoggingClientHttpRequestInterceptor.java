package cn.lee.web.config;

import cn.lee.web.httpclient.BufferingClientHttpResponseWrapper;
import cn.lee.web.httpclient.logger.HttpRequestLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author : Lee
 * @date : 2020-09-27
 */
@Slf4j
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        long start = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        long runtime=System.currentTimeMillis() - start;
        response = traceResponse(response, runtime);
        return response;
    }

    private void traceRequest(HttpRequest request,byte[] body) {
        log.info("=============================================");
        log.info("URL:{}",request.getURI());
        log.info("METHOD:{}",request.getMethod());
        log.info("Headers:{}",request.getHeaders());
        log.info("Body:{}",new String(body, StandardCharsets.UTF_8));
        log.info("=============================================");
    }

    private void traceRequest(HttpRequest request, byte[] body, HttpRequestLog httpRequestLog) {
        httpRequestLog.setRequestParam(new String(body, StandardCharsets.UTF_8));
        httpRequestLog.setRequestUrl(request.getURI().toString());
    }

    private ClientHttpResponse traceResponse(ClientHttpResponse response,long runtime) throws IOException {
        log.info("responseCode:{}",response.getStatusCode().value());
        try {
            BufferingClientHttpResponseWrapper responseWrapper = new BufferingClientHttpResponseWrapper(response);
            String responseBody= StreamUtils.copyToString(responseWrapper.getBody(), StandardCharsets.UTF_8);
            log.info("RESULT:{},RUNTIME:{}ms",responseBody,runtime);
            return responseWrapper;
        } catch (IOException io) {
            return response;
        }
    }
}
