package com.forezp.jobhandler;

import com.forezp.biz.model.ReturnT;
import com.forezp.handler.annotation.XxlJob;
import com.forezp.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * XxlJob开发示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、在Spring Bean实例中，开发Job方法，方式格式要求为 "public ReturnT<String> execute(String param)"
 * 2、为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class TestJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @XxlJob("test")
    public ReturnT<String> test(String param) {
        XxlJobLogger.log("执行成功");
        return ReturnT.SUCCESS;
    }
}
