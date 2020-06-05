package com.forezp.api.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

@Configuration
@Slf4j
public class FeignServiceConfig implements RequestInterceptor {
    /**
     * 获取上下文请求
     *
     * @return 请求对象
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Enumeration<String> paraNames2 = getRequest().getHeaderNames();
        for(Enumeration e=paraNames2;e.hasMoreElements();){
            String thisName=e.nextElement().toString();
            requestTemplate.header(thisName,getRequest().getHeader(thisName));
        }
        log.info("输出：" + requestTemplate.url());
    }
}
