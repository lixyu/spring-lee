package cn.lee.web.config;

import cn.lee.web.interceptor.ApiLogInterceptor;
import cn.lee.web.interceptor.RequestInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Lee
 * @date : 2020-09-25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

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

    public void addInterceptors(InterceptorRegistry registry){
        registry.addWebRequestInterceptor(new RequestInterceptor()).addPathPatterns("/*/**");
        registry.addInterceptor(new ApiLogInterceptor()).addPathPatterns("/*/**");
    }

}
