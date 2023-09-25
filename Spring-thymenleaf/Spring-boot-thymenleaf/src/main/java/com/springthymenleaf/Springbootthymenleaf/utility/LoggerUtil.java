package com.springthymenleaf.Springbootthymenleaf.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class LoggerUtil {

    private static ObjectMapper objMapper = new ObjectMapper();
    private LoggerUtil() {
    }
    public static Logger getLogger(String loggerName) {
        if (null == loggerName || loggerName.isEmpty()) {
            loggerName = "OtherLogger";
        }
        return LogManager.getLogger(loggerName);
    }

    public static Logger getLogger() {
        return getLogger("");
    }

    public static void log(Logger logger, Level level, Object object) {
        if (null == logger || null == object) {
            return;
        }
        try {
            String logData = objMapper.writeValueAsString(object);
            log(logger, level, logData);
            logData = null;
        } catch (Exception e) {
        }
    }

    public static void log(Logger logger, Level level, Object object, Map<String, String> mdcMap) {
        if (null == logger || null == object) {
            return;
        }
        try {
            String logData = objMapper.writeValueAsString(object);
            log(logger, level, logData);
            logData = null;
        } catch (Exception e) {
        }
    }
}
