package com.forezp.utils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import com.forezp.manager.GenerateLogManager;
import org.slf4j.MDC;
import org.springframework.util.Assert;

/**
 * 测试基类
 *
 * @author jianghang 2013-4-15 下午04:17:12
 * @version 1.0.4
 */
public class AbstractCanalClientUtils extends BaseCanalClientUtils {

    private GenerateLogManager generateLogManager;

    public AbstractCanalClientUtils(String destination) {
        this(destination, null);
    }

    public GenerateLogManager getGenerateLogManager() {
        return generateLogManager;
    }

    public void setGenerateLogManager(GenerateLogManager generateLogManager) {
        this.generateLogManager = generateLogManager;
    }

    public AbstractCanalClientUtils(String destination, CanalConnector connector) {
        this.destination = destination;
        this.connector = connector;
    }

    public void start() {
        Assert.notNull(connector, "connector is null");
        thread = new Thread(new Runnable() {

            public void run() {
                process();
            }
        });

        thread.setUncaughtExceptionHandler(handler);
        running = true;
        thread.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // ignore
            }
        }

        MDC.remove("destination");
    }

    protected void process() {
        int batchSize = 5 * 1024;
        while (running) {
            try {
                MDC.put("destination", destination);
                connector.connect();
                connector.subscribe();
                while (running) {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        // try {
                        // Thread.sleep(1000);
                        // } catch (InterruptedException e) {
                        // }
                    } else {
                        printSummary(message, batchId, size);
                        try {
                            generateLogManager.GenerateLog(printEntry(message.getEntries()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    if (batchId != -1) {
                        connector.ack(batchId); // 提交确认
                        // connector.rollback(batchId); // 处理失败, 回滚数据
                    }
                }
            } catch (Exception e) {
                logger.error("process error!", e);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e1) {
                    // ignore
                }
            } finally {
                connector.disconnect();
                MDC.remove("destination");
            }
        }
    }

}
