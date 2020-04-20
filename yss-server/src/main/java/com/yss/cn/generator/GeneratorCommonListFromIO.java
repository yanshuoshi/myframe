package com.yss.cn.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author Shuoshi.Yan
 * @package:com.plus.yss
 * @className:
 * @description:
 * @date 2019-12-11 15:19
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/
public class GeneratorCommonListFromIO {

    public static void main(String[] args) {

        AutoGenerator generator = new AutoGenerator();

        // 全局变量配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //当前项目
        gc.setOutputDir(projectPath+"/yss-common/src/main/java"); // 输出路径
        gc.setFileOverride(false); // 默认 false ,是否覆盖已生成文件
        gc.setOpen(false); //默认true ,是否打开输出目录
        gc.setEnableCache(false); // 默认false,是否开启二级缓存
        gc.setAuthor("ShuoShi Yan"); // 作者
        gc.setSwagger2(true); //默认false
        gc.setBaseResultMap(true); // 默认false
        gc.setDateType(DateType.TIME_PACK); // 时间策略 默认TIME_PACK
        gc.setBaseColumnList(true); //默认false  和basemodel相似
//        gc.setEntityName("%sIO");
        gc.setControllerName("%sListFromIO");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
        gc.setIdType(IdType.ID_WORKER_STR); // 指定生成的主键类型
        generator.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dc = new DataSourceConfig();
        dc.setDbQuery(new MySqlQuery()); // 数据库信息查询 //默认mysql
        dc.setDbType(DbType.MYSQL);// 数据库类型
        dc.setTypeConvert(new MySqlTypeConvert()); //类型转换 默认mysql
        dc.setUrl("jdbc:mysql://localhost:3306/tablego?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dc.setDriverName("com.mysql.jdbc.Driver");
        dc.setUsername("root");
        dc.setPassword("root");
        generator.setDataSource(dc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.yss.cn.api.io");
        pc.setController("%s");
//        pc.setModuleName("%s"); //此处是所属模块名称
//        pc.setEntity("$s"); //默认entity,controller,service,service.impl,mapper,mapper.xml
        generator.setPackageInfo(pc);
        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//                Map map = new HashMap();
//            }
//        };
        /**
         * 将xml生成到resource下面
         */
//        String templatePath = "/templates/mapper.xml.ftl"; // framemark
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/yss-common/src/main/resources/mapper/"
////                        + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();
//        tc.setController("");
        tc.setServiceImpl("");
        tc.setService("");
        tc.setMapper("");
        tc.setXml("");
        tc.setController("templates/ListFromIO.java");// /templates/entity.java 模板路径配置，默认再templates
//        tc.setService("templates/service.java");
//        tc.setServiceImpl("templates/serviceImpl.java");
//        tc.setMapper("templates/mapper.java");
//        tc.setXml("templates/mapper.xml");
        tc.setEntity("");

//        tc.setEntityKt("");
    generator.setTemplate(tc);

        // 数据库表配置
        StrategyConfig sc = new StrategyConfig();
        sc.setCapitalMode(false); //是否大写命名 默认false
        sc.setSkipView(true); //是否跳过试图 默认false
        sc.setNaming(NamingStrategy.underline_to_camel);// 表映射 驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel); // 字段映射 驼峰
        sc.setEntityLombokModel(true); //默认false
        sc.setRestControllerStyle(true); // 默认false
        sc.setEntitySerialVersionUID(true); //默认true
        sc.setEntityColumnConstant(true); //默认false
        sc.setInclude("yss_account"); //表名，用，隔开  需要生产
        //     sc.setExclude(""); //                 不需要生成  二选一
        sc.setEntityTableFieldAnnotationEnable(true); // 默认false 注释
        sc.setControllerMappingHyphenStyle(false); //默认false
//        sc.setLogicDeleteFieldName("status"); // 逻辑删除字段名称
        generator.setStrategy(sc);

        // 模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
