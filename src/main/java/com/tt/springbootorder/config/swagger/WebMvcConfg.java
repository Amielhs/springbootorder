package com.tt.springbootorder.config.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: WebMvcConfg
 * @Description:mvc的配置
 * @Author: amielhs
 * @Date: 2019-07-31 17:54
 */
@Configuration
//public class WebMvcConfg extends WebMvcConfigurationSupport {
public class WebMvcConfg extends WebMvcConfigurerAdapter {

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);

    }*/

    /*增加swagger的支持*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
