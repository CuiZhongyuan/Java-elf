package com.interfaceproject.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * log4j
 * @author jw
 *
 */
public class Log4jUtil {


    public static void info(Object str_info) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(Log4jUtil.class.getName(), Level.INFO, str_info, null);
    }

    public static void error(Object str_err) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(Log4jUtil.class.getName(), Level.ERROR, str_err, null);
    }

    public static void debug(Object str_debug) {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        Logger logger = Logger.getLogger(stack[1].getClassName());
        logger.log(Log4jUtil.class.getName(), Level.DEBUG, str_debug, null);
    }
}
