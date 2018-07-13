package com.chuanyunbang.crawer.interceptor;

import com.chuanyunbang.crawer.util.LoggerFactoryUtils;
import com.chuanyunbang.crawer.util.ParamUtils;
import com.google.gson.Gson;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * @author Luffy
 * @date 2018/4/13
 * @description 日志log拦截器
 */
public class MdcInterceptor extends HandlerInterceptorAdapter {

    private static final LoggerFactoryUtils LOGGER = LoggerFactoryUtils.getInstance();

    public static final String LOGID = "logId";
    public static final String URI = "uri";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        MDC.put(LOGID, ParamUtils.logId());
        response.setHeader("LOG-ID", MDC.get(LOGID));

        String uri = request.getRequestURI();
        //日志中增加请求接口的路径
        MDC.put(URI, uri);
        Map<String, String[]> parameters = request.getParameterMap();
        if (!Objects.isNull(parameters) && !parameters.isEmpty()) {
            LOGGER.info("MdcInterceptor 拦截URI = {} , parameters = {}", uri, new Gson().toJson(parameters));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
