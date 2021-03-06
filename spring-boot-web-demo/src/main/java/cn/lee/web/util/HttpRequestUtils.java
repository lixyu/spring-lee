package cn.lee.web.util;

import cn.lee.web.exception.HttpRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public final class HttpRequestUtils {

    private final RestTemplate restTemplate;

    private final OkHttpClient okHttpClient;


    public String doJson(String url, String param, long timeout) throws HttpRequestException {
        return doJson(url, param, timeout, new HashMap<>(1), false);
    }

    public String doJsonSync(String url, String param, long timeout) throws HttpRequestException {
        return doJson(url, param, timeout, new HashMap<>(1), false);
    }

    public String doJson(String url, String param, long timeout, Map<String, String> headers, boolean async) {
        return doJson(url, param, timeout, headers, HttpStatus.OK, async);
    }

    public String doJson(String url, String param, long timeout, Map<String, String> headers, HttpStatus httpStatus, boolean async) {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(param, httpHeaders);
        return sync(url, entity, HttpMethod.POST, timeout, httpStatus);
    }

    public String doGet(String url, long timeout) throws HttpRequestException {
        return doGet(url, timeout, new HashMap<>(1));
    }

    public String doGet(String url, long timeout, Map<String, String> headers) throws HttpRequestException {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        return sync(url, entity, HttpMethod.GET, timeout, HttpStatus.OK);
    }

    private String sync(String url, HttpEntity<String> entity, HttpMethod httpMethod, long timeout, HttpStatus httpStatus) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(url), httpMethod, entity, String.class);
            if (responseEntity.getStatusCode() == httpStatus) {
                return responseEntity.getBody();
            } else {
                String errorMessage = MessageFormat.format("HTTP请求失败：Url:[{0}],Params:[{1}],RequestException:[{2}]", url, entity.getBody(), responseEntity.getBody());
                log.error(errorMessage);
                throw new HttpRequestException(errorMessage);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                log.error("HTTP请求异常详情FileName:[{}],LineNumber:[{}]", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
            }
            String errorMessage = MessageFormat.format("HTTP请求异常：Url:[{0}],Params:[{1}],RequestException:[{2}]", url, entity.getBody(), e);
            log.error(errorMessage);
            throw new HttpRequestException(errorMessage);
        }
    }

    public String request(String url) throws HttpRequestException {
        try {
            Request request = new Request.Builder().url(url).build();
            ResponseBody body = okHttpClient.newCall(request).execute().body();
            byte[] response = body.bytes();
            if (response.length != 0) {
                return Base64Utils.encodeToString(response);
            } else {
                String errorMessage = MessageFormat.format("HTTP请求失败：Url:[{0}],RequestException:[{1}]", url, body.byteStream());
                throw new HttpRequestException(errorMessage);
            }
        } catch (Throwable e) {
            String errorMessage = MessageFormat.format("HTTP请求异常：Url:[{0}],IOException:[{1}]", url, e);
            throw new HttpRequestException(errorMessage);
        }
    }
}
