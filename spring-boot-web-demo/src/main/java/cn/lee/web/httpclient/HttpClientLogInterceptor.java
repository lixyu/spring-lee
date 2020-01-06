package cn.lee.web.httpclient;


import brave.propagation.CurrentTraceContext;
import cn.lee.web.httpclient.logger.HttpRequestLog;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author Isaac
 * @description ---------------------------------
 * @date 7/19/2019 09:16
 */
@Slf4j
public class HttpClientLogInterceptor implements ClientHttpRequestInterceptor {

    private CurrentTraceContext currentTraceContext;

    public HttpClientLogInterceptor() {
    }

    public HttpClientLogInterceptor(CurrentTraceContext currentTraceContext) {
        this.currentTraceContext = currentTraceContext;
    }

    @NotNull
    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestLog httpRequestLog = new HttpRequestLog();
        httpRequestLog.setTraceId(currentTraceContext.get().traceIdString());
        httpRequestLog.setSpanId(currentTraceContext.get().spanIdString());
        httpRequestLog.setParentSpanId(currentTraceContext.get().parentIdString());
        traceRequest(request, body, httpRequestLog);
        request.getHeaders().add(OkHttpMetricsEventListener.URI_PATTERN, request.getURI().getPath());
        long start = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        long end = System.currentTimeMillis();
        httpRequestLog.setRunTime(end - start);
        response = traceResponse(response, httpRequestLog);
        log.info($.toJson(httpRequestLog));
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body, HttpRequestLog httpRequestLog) {
        httpRequestLog.setRequestParam(new String(body, StandardCharsets.UTF_8));
        httpRequestLog.setRequestUrl(request.getURI().toString());
    }

    private ClientHttpResponse traceResponse(ClientHttpResponse response, HttpRequestLog httpRequestLog) throws IOException {
        httpRequestLog.setResponseCode(response.getStatusCode().value());
        try {
            BufferingClientHttpResponseWrapper responseWrapper = new BufferingClientHttpResponseWrapper(response);
            httpRequestLog.setResponse(StreamUtils.copyToString(responseWrapper.getBody(), StandardCharsets.UTF_8));
            return responseWrapper;
        } catch (IOException io) {
            return response;
        }
    }
}
