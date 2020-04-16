package com.yss.cn.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuoshi.Yan
 * @package:com.wangtiansoft.stla.provider.utils
 * @className:使用ftl导出world
 * @description:
 * @date 2020-03-12 14:46
 * @version:V1.0
 * @NOTICE：本内容仅限于xxx有限公司内部传阅,禁止外泄以及用于其他的商业项目
 * @ Copyright  xxx. All rights reserved.
 **/

public class WorldUtil {
    /**
     * 导出world
     * @param dataMap 数据集
     * @param templateName 模板名称
     * @param filePath 模板路径
     * @param fileName 文件名
     * @param response
     */
    public static void exportDoc(Map dataMap, String templateName, String filePath, String fileName, HttpServletResponse response, HttpServletRequest request){
        try {
            //创建配置实例
            Configuration configuration = new Configuration();
            //设置编码
            configuration.setDefaultEncoding("UTF-8");
            //ftl模板文件
            configuration.setClassForTemplateLoading(WorldUtil.class,"/");
            //获取模板
            Template template = configuration.getTemplate(templateName,"UTF-8");
            //输出文件
            File outFile = new File(filePath+File.separator+fileName);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }
            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
            //生成文件
            template.process(dataMap, out);
            //关闭流
            out.flush();
            out.close();
            InputStream inputStream = new FileInputStream(outFile);
//            response.setContentType("application/zip");
//            response.setContentType("application/octet-stream");
            response.setContentType("application/msword");
            OutputStream out1 = response.getOutputStream();
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            int b = 0;
            byte[] buffer = new byte[1000000];
            while (b != -1) {
                b = inputStream.read(buffer);
                if(b!=-1) out1.write(buffer, 0, b);
            }
            inputStream.close();
            out1.close();
            out1.flush();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map m =  new HashMap();
        m.put("data1","12121");
        m.put("data2","12121");
        m.put("fileName","12121");
//        WorldUtil.exportDoc(dataMap, "word1.ftl", filePath, fileName, response, request);
    }
}
