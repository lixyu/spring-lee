package cn.lee.web.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Lee
 * @date : 2020-09-25
 */
@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate=new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

    @Bean("okHttpClient")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
