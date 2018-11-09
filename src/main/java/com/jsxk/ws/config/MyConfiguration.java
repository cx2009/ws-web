package com.jsxk.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;
import java.util.Locale;

@Configuration
public class MyConfiguration  implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
       // registry.addResourceHandler("/OTA/**").addResourceLocations("file: /home/OTA/");

        registry.addResourceHandler("/static/**").addResourceLocations("file:/home/OTA/").addResourceLocations("classpath:/META-INF/resources/");



    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public Date parse(String date, Locale locale) {
                return new Date(Long.parseLong(date));
            }

            @Override
            public String print(Date date, Locale locale) {
                return Long.valueOf(date.getTime()).toString();
            }
        });
    }




}
