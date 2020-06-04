package com.forezp.utils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.forezp.canal.SimpleCanalClient;
import com.forezp.manager.GenerateLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Component
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private GenerateLogManager generateLogManager;

    /**
     * 初始启动监听
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            // 根据ip，直接创建链接，无HA的功能
            String destination = "example";
            String ip = "192.168.79.130";
//            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, 11111), destination, "canal", "canal");
            // 基于zookeeper动态获取canal server的地址，建立链接，其中一台server发生crash，可以支持failover
            CanalConnector connector = CanalConnectors.newClusterConnector("192.168.79.130:2181", destination, "canal", "canal");


            final SimpleCanalClient clientTest = new SimpleCanalClient(destination);
            clientTest.setConnector(connector);
            clientTest.setGenerateLogManager(generateLogManager);
            clientTest.start();
            Runtime.getRuntime().addShutdownHook(new Thread() {

                public void run() {
                    try {
                        clientTest.stop();
                    } catch (Throwable e) {
                    } finally {
                    }
                }

            });
            System.out.println("任务已经启动...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}