package com.chuanyunbang.crawer.util;

import com.yzb.base.commons.lang.StrFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author pengsong
 * @date 2018/4/13
 * @description 日志工厂
 */
public class LoggerFactoryUtils {

    private static Logger abTestInfoLogger = LoggerFactory.getLogger("chuanyunbangLogger");
    private static Logger warnLogger = LoggerFactory.getLogger("chuanyunbangLogger");
    private static Logger errorLogger = LoggerFactory.getLogger("chuanyunbangLogger");


    public void info(String format, Object... objs) {
        String log = StrFormatter.format(format, objs);
        abTestInfoLogger.info(log);
    }

    private static LoggerFactoryUtils instance = null;

    private LoggerFactoryUtils() {
    }

    public static final LoggerFactoryUtils getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LoggerFactoryUtils();
        }
        return instance;
    }

    public void warn(String format, Object... objs) {
        warnLogger.warn(format, objs);
    }

    public void error(String format, Object... objs) {
        errorLogger.error(format, objs);
    }
}
