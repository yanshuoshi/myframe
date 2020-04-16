package com.yss.common.utils;

import com.jfinal.ext.kit.excel.PoiExporter;
import com.jfinal.render.RenderException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author:Shuoshi.Yan
 * @description: 导出
 * @date: 2020/1/14 14:56
 * @param: 
 * @return: 
 */
public class ExcelRenderUtil {

    private final static String CONTENT_TYPE = "application/msexcel;charset=utf-8";
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
        response.setHeader("Content-disposition", "attachment; " + FileRenderUtil.encodeFileName(this.request, fileName));
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "x-access-token");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType(CONTENT_TYPE);
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
