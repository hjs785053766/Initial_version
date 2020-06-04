package com.forezp.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.forezp.api.entity.Journal;
import com.forezp.api.entity.JournalSon;
import com.forezp.api.entity.RequestRecordSon;
import com.forezp.api.entity.Tablevalue;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseCanalClientUtils {

    protected final static Logger logger = LoggerFactory.getLogger(BaseCanalClientUtils.class);
    protected static final String SEP = SystemUtils.LINE_SEPARATOR;
    protected static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected volatile boolean running = false;
    protected Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {

        public void uncaughtException(Thread t, Throwable e) {
            logger.error("parse events has an error", e);
        }
    };
    protected Thread thread = null;
    protected CanalConnector connector;
    protected static String context_format = null;
    protected static String row_format = null;
    protected static String transaction_format = null;
    protected String destination;

    static {
        context_format = SEP + "****************************************************" + SEP;
        context_format += "* Batch Id: [{}] ,count : [{}] , memsize : [{}] , Time : {}" + SEP;
        context_format += "* Start : [{}] " + SEP;
        context_format += "* End : [{}] " + SEP;
        context_format += "****************************************************" + SEP;

        row_format = SEP
                + "----------------> binlog[{}:{}] , name[{},{}] , eventType : {} , executeTime : {}({}) , gtid : ({}) , delay : {} ms"
                + SEP;

        transaction_format = SEP
                + "================> binlog[{}:{}] , executeTime : {}({}) , gtid : ({}) , delay : {}ms"
                + SEP;

    }

    protected void printSummary(Message message, long batchId, int size) {
        long memsize = 0;
        for (Entry entry : message.getEntries()) {
            memsize += entry.getHeader().getEventLength();
        }

        String startPosition = null;
        String endPosition = null;
        if (!CollectionUtils.isEmpty(message.getEntries())) {
            startPosition = buildPositionForDump(message.getEntries().get(0));
            endPosition = buildPositionForDump(message.getEntries().get(message.getEntries().size() - 1));
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        logger.info(context_format, new Object[]{batchId, size, memsize, format.format(new Date()), startPosition,
                endPosition});
    }

    protected String buildPositionForDump(Entry entry) {
        long time = entry.getHeader().getExecuteTime();
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        String position = entry.getHeader().getLogfileName() + ":" + entry.getHeader().getLogfileOffset() + ":"
                + entry.getHeader().getExecuteTime() + "(" + format.format(date) + ")";
        if (StringUtils.isNotEmpty(entry.getHeader().getGtid())) {
            position += " gtid(" + entry.getHeader().getGtid() + ")";
        }
        return position;
    }

    public RequestRecordSon printEntry(List<Entry> entrys) {
        List<JournalSon> journalList = new ArrayList<JournalSon>();
        RequestRecordSon requestRecordSon = null;
        System.out.println("------------------------------------------开始记录");
        for (Entry entry : entrys) {
            System.out.println("------------------------------------------" + requestRecordSon);
            if (!entry.getHeader().getSchemaName().equals("sys_journal")||entry.getHeader().getTableName().equals("request_record")) {
                JournalSon journal = new JournalSon();

                long executeTime = entry.getHeader().getExecuteTime();
                long delayTime = new Date().getTime() - executeTime;
                Date date = new Date(entry.getHeader().getExecuteTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                    if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN) {
                        TransactionBegin begin = null;
                        try {
                            begin = TransactionBegin.parseFrom(entry.getStoreValue());
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                        }
                        // 打印事务头信息，执行的线程id，事务耗时
                        logger.info(transaction_format,
                                new Object[]{entry.getHeader().getLogfileName(),
                                        String.valueOf(entry.getHeader().getLogfileOffset()),
                                        String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
                                        entry.getHeader().getGtid(), String.valueOf(delayTime)});
                        System.out.println(" 开始 ----> Thread id: " + begin.getThreadId());
                        printXAInfo(begin.getPropsList());
                    } else if (entry.getEntryType() == EntryType.TRANSACTIONEND) {
                        TransactionEnd end = null;
                        try {
                            end = TransactionEnd.parseFrom(entry.getStoreValue());
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                        }
                        // 打印事务提交信息，事务id
                        System.out.println("----------------");
                        System.out.println(" 结束 ----> transaction id: " + end.getTransactionId());
                        printXAInfo(end.getPropsList());
                        logger.info(transaction_format,
                                new Object[]{entry.getHeader().getLogfileName(),
                                        String.valueOf(entry.getHeader().getLogfileOffset()),
                                        String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
                                        entry.getHeader().getGtid(), String.valueOf(delayTime)});
                    }

                    continue;
                }
                RowChange rowChage = null;
                try {
                    rowChage = RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }

                EventType eventType = rowChage.getEventType();
                if (entry.getEntryType() == EntryType.ROWDATA) {
                    logger.info(row_format,
                            new Object[]{entry.getHeader().getLogfileName(),
                                    String.valueOf(entry.getHeader().getLogfileOffset()), entry.getHeader().getSchemaName(),
                                    entry.getHeader().getTableName(), eventType,
                                    String.valueOf(entry.getHeader().getExecuteTime()), simpleDateFormat.format(date),
                                    entry.getHeader().getGtid(), String.valueOf(delayTime)});

                    System.out.println("\n 库 ----> " + entry.getHeader().getSchemaName());
                    System.out.println(" 表 ----> " + entry.getHeader().getTableName());
                    System.out.println(" 执行的操作 ---->" + eventType);
                    journal.setLibrary(entry.getHeader().getSchemaName());
                    journal.setSurface(entry.getHeader().getTableName());
                    journal.setEventType(eventType.toString());
                    journal.setExecuteTime(new Date(entry.getHeader().getExecuteTime()));

                    if (eventType == EventType.QUERY || rowChage.getIsDdl()) {
                        logger.info(" sql ----> " + rowChage.getSql() + SEP);
                        continue;
                    }

                    printXAInfo(rowChage.getPropsList());
                    for (RowData rowData : rowChage.getRowDatasList()) {
                        if (eventType == EventType.DELETE) {
                            System.out.println("-------删除前数据");
                            journal.setDeleteData(printColumn(rowData.getBeforeColumnsList(), journal, 0));
                        } else if (eventType == EventType.INSERT) {
                            System.out.println("-------新增数据");
                            journal.setInsertData(printColumn(rowData.getAfterColumnsList(), journal, 1));
                        } else {
                            System.out.println("-------修改前数据");
                            journal.setBeforeColumns(printColumn(rowData.getBeforeColumnsList(), journal, 0));
                            System.out.println("-------修改后数据");
                            journal.setAfterColumns(printColumn(rowData.getAfterColumnsList(), journal, 1));
                        }
                    }
                }
                System.out.println(entry.getHeader().getTableName());
                System.out.println(entry.getHeader().getSchemaName());
                if (entry.getHeader().getTableName().equals("request_record")) {
                    requestRecordSon = new RequestRecordSon();
                    for (Tablevalue tablevalue : journal.getInsertData()) {
                        switch (tablevalue.getValueName()) {
                            case "id":
                                requestRecordSon.setId(Integer.parseInt(tablevalue.getValue()));
                                break;
                            case "requestor_id":
                                requestRecordSon.setRequestorId(tablevalue.getValue());
                                break;
                            case "requestor_name":
                                requestRecordSon.setRequestorName(tablevalue.getValue());
                                break;
                            case "request_Interface":
                                requestRecordSon.setRequestInterface(tablevalue.getValue());
                                break;
                            case "request_business_id":
                                requestRecordSon.setRequestBusinessId(tablevalue.getValue());
                                break;
                            case "request_time":
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                date = null;
                                try {
                                    // 注意格式需要与上面一致，不然会出现异常
                                    date = sdf.parse(tablevalue.getValue());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                requestRecordSon.setRequestTime(date);
                                break;
                        }
                    }
                } else {
                    journalList.add(journal != null ? journal : null);
                }
            }
        }
        if (journalList.size() > 0) {
            try {
                if (requestRecordSon != null) {
                    requestRecordSon.setJournalList(journalList);
                    String str = JSON.toJSON(requestRecordSon).toString();
                    System.out.println(str);
                    return requestRecordSon;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    protected List<Tablevalue> printColumn(List<Column> columns, Journal journal, int type) {
        List<Tablevalue> tableValueList = new ArrayList<Tablevalue>();
        for (Column column : columns) {
            Tablevalue tableValue = new Tablevalue();
            StringBuilder builder = new StringBuilder();
            try {
                if (StringUtils.containsIgnoreCase(column.getMysqlType(), "BLOB")
                        || StringUtils.containsIgnoreCase(column.getMysqlType(), "BINARY")) {
                    // get value bytes
                    builder.append(column.getName() + " : "
                            + new String(column.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                    tableValue.setValueName(column.getName());
                    tableValue.setValue(new String(column.getValue().getBytes("ISO-8859-1"), "UTF-8"));
                } else {
                    builder.append(column.getName() + " : " + column.getValue());
                    tableValue.setValueName(column.getName());
                    tableValue.setValue(column.getValue());
                }
            } catch (UnsupportedEncodingException e) {
            }
            builder.append("    type=" + column.getMysqlType());
            tableValue.setValueType(column.getMysqlType());
            if (column.getUpdated()) {
                builder.append("    update=" + column.getUpdated());
                tableValue.setModify(column.getUpdated() + "");
            }
            builder.append(SEP);
            System.out.println(builder.toString());
            if (tableValue.getValueName().equals("id")) {
                journal.setMainBusinessId(tableValue.getValue());
            }
            tableValue.setType(type);
            tableValueList.add(tableValue);
        }
        return tableValueList;
    }

    protected void printXAInfo(List<Pair> pairs) {
        if (pairs == null) {
            return;
        }

        String xaType = null;
        String xaXid = null;
        for (Pair pair : pairs) {
            String key = pair.getKey();
            if (StringUtils.endsWithIgnoreCase(key, "XA_TYPE")) {
                xaType = pair.getValue();
            } else if (StringUtils.endsWithIgnoreCase(key, "XA_XID")) {
                xaXid = pair.getValue();
            }
        }

        if (xaType != null && xaXid != null) {
            logger.info(" ------> " + xaType + " " + xaXid);
        }
    }

    public void setConnector(CanalConnector connector) {
        this.connector = connector;
    }

    /**
     * 获取当前Entry的 GTID信息示例
     *
     * @param header
     * @return
     */
    public static String getCurrentGtid(Header header) {
        List<Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (Pair pair : props) {
                if ("curtGtid".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取当前Entry的 GTID Sequence No信息示例
     *
     * @param header
     * @return
     */
    public static String getCurrentGtidSn(Header header) {
        List<Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (Pair pair : props) {
                if ("curtGtidSn".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 获取当前Entry的 GTID Last Committed信息示例
     *
     * @param header
     * @return
     */
    public static String getCurrentGtidLct(Header header) {
        List<Pair> props = header.getPropsList();
        if (props != null && props.size() > 0) {
            for (Pair pair : props) {
                if ("curtGtidLct".equals(pair.getKey())) {
                    return pair.getValue();
                }
            }
        }
        return "";
    }
}
