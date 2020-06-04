package com.forezp.interceptor.util;

import com.forezp.api.service.able.RequestRecordService;
import com.forezp.interceptor.filter.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhn on 2017/12/3.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RequestRecordService requestRecordService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("------------------------------------------------------开启拦截器"+requestRecordService);
        TimeInterceptor timeInterceptor = new TimeInterceptor();
        timeInterceptor.setRequestRecordService(requestRecordService);
        registry.addInterceptor(timeInterceptor);
    }
}