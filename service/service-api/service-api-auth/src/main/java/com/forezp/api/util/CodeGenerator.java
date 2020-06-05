package com.forezp.api.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Scanner;

// 逆向工程
public class CodeGenerator {


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        File f = new File(CodeGenerator.class.getClass().getResource("/").getPath());
        String url = f.toString();
        url = url.replaceAll("classes", "");
        url = url.replaceAll("target", "");
        url = url.substring(0, url.length() - 2);
        try {
            url = URLDecoder.decode(url, "UTF-8");
            System.out.println(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(url + "/src/main/java");
        gc.setAuthor("hjs");                    //作者
        gc.setFileOverride(true);                //是否覆蓋已有文件 默认值：false
        gc.setOpen(false);                        //是否打开输出目录 默认值:true

        gc.setBaseColumnList(true);                //开启 baseColumnList 默认false
        gc.setBaseResultMap(true);                //开启 BaseResultMap 默认false
        gc.setEntityName("%s");            //实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
        gc.setMapperName("%sMapper");            //mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
        gc.setXmlName("%sMapper");                //Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
        gc.setServiceName("%sService");            //service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
        gc.setServiceImplName("%sServiceImpl");    //service impl 命名方式  默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.79.130:3306/sys_auth?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Ai1314520..");
        mpg.setDataSource(dsc);

        String tableName = scanner("表名");

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.forezp.api");
        pc.setEntity("entity."+tableName);
        pc.setService("service.able");
        pc.setServiceImpl("service.impl." + tableName);
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //控制 不生成 controller
        templateConfig.setController("");
        mpg.setTemplate(templateConfig);

        // 策略配置	数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);    //表名生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setRestControllerStyle(false);    //生成 @RestController 控制器
        strategy.setInclude(tableName);        //需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setControllerMappingHyphenStyle(true);    //驼峰转连字符
        strategy.setTablePrefix(pc.getModuleName() + "_");    //是否生成实体时，生成字段注解
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
