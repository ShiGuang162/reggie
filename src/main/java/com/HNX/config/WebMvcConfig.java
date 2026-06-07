package com.HNX.config;

import com.HNX.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        CacheControl staticCache = CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic();
        registry.addResourceHandler("/backend/**")
                .addResourceLocations("classpath:/backend/")
                .setCacheControl(staticCache);
        registry.addResourceHandler("/front/**")
                .addResourceLocations("classpath:/front/")
                .setCacheControl(staticCache);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/app/images/")
                .setCacheControl(staticCache);
    }

    /**
     * 扩展 mvc 框架的消息转换器，将 String 类型转换成 json
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用 Jackson 将 Java 对象转为 json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到 mvc 框架的转换器集合中
       converters.add(0,messageConverter);
    }


}
