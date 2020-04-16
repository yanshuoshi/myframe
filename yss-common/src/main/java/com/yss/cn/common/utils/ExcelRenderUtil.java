package com.yss.cn.common.utils;

import com.jfinal.ext.kit.excel.PoiExporter;
import com.jfinal.render.RenderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author:Shuoshi.Yan
 * @description: 导出Excel
 * @date: 2020/1/14 14:56
 */
public class ExcelRenderUtil {

    private List<?>[] data;
    private String[][] headers;
    private String[] sheetNames = new String[]{};
    private int cellWidth;
    private String[] columns = new String[]{};
    private String fileName = "file.xls";
    private int headerRow;
    private String version;
    protected String view;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public ExcelRenderUtil(HttpServletRequest request, HttpServletResponse response, List<?>[] data) {
        this.request = request;
        this.response = response;
        this.data = data;
    }

    public static ExcelRenderUtil me(HttpServletRequest request, HttpServletResponse response, List<?>... data) {
        return new ExcelRenderUtil(request, response, data);
    }

    public void render() {
        response.reset();
        response.setHeader("Content-disposition", "attachment; " + this.encodeFileName(this.request, fileName));
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "x-access-token");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/msexcel;charset=utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            PoiExporter.data(data).version(version).sheetNames(sheetNames).headerRow(headerRow).headers(headers).columns(columns)
                    .cellWidth(cellWidth).export().write(os);
        } catch (Exception e) {
            throw new RenderException(e);
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public static String encodeFileName(HttpServletRequest request, String fileName) {
        String userAgent = request.getHeader("User-Agent");

        try {
            String e = URLEncoder.encode(fileName, "UTF8");
            if (userAgent == null) {
                return "filename=\"" + e + "\"";
            } else {
                userAgent = userAgent.toLowerCase();
                return userAgent.indexOf("msie") != -1 ? "filename=\"" + e + "\"" : (userAgent.indexOf("opera") != -1 ? "filename*=UTF-8\'\'" + e : (userAgent.indexOf("safari") == -1 && userAgent.indexOf("applewebkit") == -1 && userAgent.indexOf("chrome") == -1 ? (userAgent.indexOf("mozilla") != -1 ? "filename*=UTF-8\'\'" + e : "filename=\"" + e + "\"") : "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\""));
            }
        } catch (UnsupportedEncodingException var5) {
            throw new RuntimeException(var5);
        }
    }
    public ExcelRenderUtil headers(String[]... headers) {
        this.headers = headers;
        return this;
    }

    public ExcelRenderUtil headerRow(int headerRow) {
        this.headerRow = headerRow;
        return this;
    }

    public ExcelRenderUtil columns(String... columns) {
        this.columns = columns;
        return this;
    }

    public ExcelRenderUtil sheetName(String... sheetName) {
        this.sheetNames = sheetName;
        return this;
    }

    public ExcelRenderUtil cellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        return this;
    }

    public ExcelRenderUtil fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ExcelRenderUtil version(String version) {
        this.version = version;
        return this;
    }
}
