package com.yss.cn.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * created by yss on 2020-03-25
 * 获取IP
 */
public class NetworkUtil {
    public static final String LOCALHOST = "localhost";
    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     * @throws IOException
     */
    public final static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = null;
        try {
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip) || LOCALHOST.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip) || LOCALHOST.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip) || LOCALHOST.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip) || LOCALHOST.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getFilename(String filename, String userAgent) throws UnsupportedEncodingException {
        String new_filename = URLEncoder.encode(filename, "UTF8");
        // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
        String rtn = "filename=\"" + new_filename + "\"";
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            // IE浏览器，只能采用URLEncoder编码
            if (userAgent.indexOf("msie") != -1) {
                rtn = "filename=\"" + new_filename + "\"";
            }
            // Opera浏览器只能采用filename*
            else if (userAgent.indexOf("opera") != -1) {
                rtn = "filename*=UTF-8''" + new_filename;
            }
            // Safari浏览器，只能采用ISO编码的中文输出
            else if (userAgent.indexOf("safari") != -1) {
                rtn = "filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"";
            }
            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			/*else if (userAgent.indexOf("applewebkit") != -1 ) 
			{ 
				new_filename = MimeUtility.encodeText(filename, "UTF8", "B"); 
				rtn = "filename=\"" + new_filename + "\""; 
			} */
            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
            else if (userAgent.indexOf("mozilla") != -1) {
                rtn = "filename*=UTF-8''" + new_filename;
            }
        }
        return rtn;
    }

}  
