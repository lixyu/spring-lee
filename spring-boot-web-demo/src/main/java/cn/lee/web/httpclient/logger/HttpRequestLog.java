package cn.lee.web.httpclient.logger;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Isaac
 */
@Data
@NoArgsConstructor
//@Document(collection = "http_request_log")
public class HttpRequestLog {

    //@Id
    private String requestId;

    private String traceId;

    private String spanId;

    private String parentSpanId;
    /**
     * 日志类型
     */
    private String logLevel;

    /**
     * 日志简单描述
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestHeaders;

    /**
     * 对接接口返回
     */
    private int responseCode;

    /**
     * 请求入参
     */
    private String requestParam;

    /**
     * 请求出参
     */
    private String response;

    /**
     * 执行时间
     */
    private long runTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
