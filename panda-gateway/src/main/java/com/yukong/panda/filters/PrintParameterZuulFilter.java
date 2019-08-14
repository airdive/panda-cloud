package com.yukong.panda.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;

@Component
public class PrintParameterZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        return "POST".equalsIgnoreCase(request.getMethod());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (null != request.getContentType()) {
            if (request.getContentType().contains("application/json")) {
                try {
                    ServletInputStream inputStream = request.getInputStream();
                    String result = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
                    System.out.println(String.format("请求URI为:%s,请求参数为:%s", request.getRequestURI(), result));
                } catch (IOException e) {
                    throw new ZuulException(e, 500, "从输入流中读取请求参数异常");
                }
            } else if (request.getContentType().contains("application/x-www-form-urlencoded")) {
                StringBuilder params = new StringBuilder();
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String name = parameterNames.nextElement();
                    params.append(name).append("=").append(request.getParameter(name)).append("&");
                }
                String result = params.toString();
                System.out.println(String.format("请求URI为:%s,请求参数为:%s", request.getRequestURI(),
                        result.substring(0, result.lastIndexOf("&"))));
            }
        }
        return null;
    }
}
