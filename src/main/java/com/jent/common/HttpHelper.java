package com.jent.common;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHelper {
	private static final Logger log = LoggerFactory.getLogger(HttpHelper.class);

    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getData(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000).build();
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        HttpResponse response = client.execute(request);
        String resData = IOUtils.toString(response.getEntity().getContent());
        log.info(MessageFormat.format("访问外连{0}，获取数据为{1}", url, resData));
        request.releaseConnection();
        return resData;
    }
}
