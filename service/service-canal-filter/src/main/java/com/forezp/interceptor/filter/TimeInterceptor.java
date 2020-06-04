package com.forezp.interceptor.filter;

import com.forezp.api.entity.RequestRecord;
import com.forezp.api.service.able.RequestRecordService;
import com.forezp.util.EncryptUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Autowired
    RequestRecordService requestRecordService;

    public RequestRecordService getRequestRecordService() {
        return requestRecordService;
    }

    public void setRequestRecordService(RequestRecordService requestRecordService) {
        this.requestRecordService = requestRecordService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (requestRecordService == null) {//解决service为null无法注入问题
            System.out.println("requestRecordService is null!!!");
            BeanFactory factory = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(request.getServletContext());
            requestRecordService = (RequestRecordService) factory
                    .getBean("requestRecordServiceImpl");
        }

        try {
            System.out.print("请求类：");
            //打印类名
            System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
            System.out.print("请求接口：");
            //打印方法名
            System.out.println(((HandlerMethod) handler).getMethod().getName());
            RequestRecord requestRecord = new RequestRecord();
            requestRecord.setRequestorId(request.getHeader("id"));//用户id
            EncryptUtil des = EncryptUtil.getEncryptUtil("b068931cc450442b63f5b3d276ea4297", "utf-8");
            String name = des.decode(request.getHeader("name"));
            requestRecord.setRequestorName(name);//用户姓名
            requestRecord.setRequestInterface(request.getHeader("url"));//请求接口
            requestRecord.setRequestBusinessId("测试业务id");//业务id
            System.out.println(request.getHeader("time"));

            requestRecord.setRequestTime(new Date(Long.parseLong( request.getHeader("time"))*1000));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("requestorId", requestRecord.getRequestorId());
            map.put("requestorName", requestRecord.getRequestorName());
            map.put("requestInterface", requestRecord.getRequestInterface());
            map.put("requestBusinessId", requestRecord.getRequestBusinessId());

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("requestTime", df.format(requestRecord.getRequestTime()));
            List<RequestRecord> List = requestRecordService.getRequestRecordList(map);
            System.out.println(List.size());
            if (List.size() == 0) {
                requestRecordService.insertSelective(requestRecord);
            }
            request.setAttribute("startTime", System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}