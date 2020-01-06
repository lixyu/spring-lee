package cn.lee.web.httpclient.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import cn.lee.web.util.ApplicationContextProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import net.dreamlu.mica.core.utils.$;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

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
 * @date 7/11/2019 20:12
 */
public class HttpMongoLogAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        MongoTemplate mongoTemplate = ApplicationContextProvider.getBean(MongoTemplate.class);
        final HttpRequestLog httpRequestLog = $.readJson(eventObject.getFormattedMessage(), new TypeReference<HttpRequestLog>() {
        });
        httpRequestLog.setRequestId(UUID.randomUUID().toString());
        httpRequestLog.setLogLevel(eventObject.getLevel().levelStr);
        httpRequestLog.setCreateTime(LocalDateTime.now());
        mongoTemplate.insert(httpRequestLog);
    }
}