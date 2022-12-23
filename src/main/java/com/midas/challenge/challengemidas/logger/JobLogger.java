package com.midas.challenge.challengemidas.logger;

import com.midas.challenge.challengemidas.logger.exceptions.ExceptionSetting;
import com.midas.challenge.challengemidas.logger.exceptions.NotFoundExceptionSetting;
import com.midas.challenge.challengemidas.logger.handler.FileLogger;
import com.midas.challenge.challengemidas.logger.handler.DBLogger;
import com.midas.challenge.challengemidas.logger.handler.ConsoleLogger;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class JobLogger implements DataLogger {
    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    private static Map dbParams;
    private static Logger logger;

    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                     boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
        logger = Logger.getLogger("MyLog");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        dbParams = dbParamsMap;
    }

    @Override
    public boolean isInfoEnabled() {
        return logMessage;
    }

    @Override
    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    @Override
    public boolean isWarnEnabled() {
        return logWarning;
    }

    @Override
    public void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    @Override
    public boolean isErrorEnabled() {
        return logError;
    }

    @Override
    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    @Override
    public void logMessage(String messageText, Boolean message, Boolean warning, Boolean error)
            throws NotFoundExceptionSetting, ExceptionSetting, SQLException, IOException {
        if (messageText == null || messageText.trim().isEmpty()) {
            return;
        }

        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new NotFoundExceptionSetting("Invalid configuration");
        }
        if ((!isErrorEnabled() && !isInfoEnabled() && !isWarnEnabled()) || (!message && !warning && !error)) {
            throw new ExceptionSetting("Error or Warning or Message must be specified");
        }

        int t = 0;
        if (isInfoEnabled()) {
            t = com.midas.challenge.challengemidas.logger.domain.Level.INFO.getValue();
        }

        if (isErrorEnabled()) {
            t = com.midas.challenge.challengemidas.logger.domain.Level.ERROR.getValue();
        }

        if (isWarnEnabled()) {
            t = com.midas.challenge.challengemidas.logger.domain.Level.WARN.getValue();
        }

        StringBuilder  l = new StringBuilder();

        if (error && logError) {
            l.append("error ").append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date())).append(messageText);
        }

        if (warning && logWarning) {
            l.append("warning ").append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date())).append(messageText);
        }

        if (message && logMessage) {
            l.append("message ").append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date())).append(messageText);
        }

        if(logToFile) {
            logger.addHandler(FileLogger.getFileHandler(dbParams));
            info(messageText);
        }

        if(logToConsole) {
            logger.addHandler(ConsoleLogger.getConsoleHandler());
            info(messageText);
        }

        if(logToDatabase) {
            DBLogger.executeUpdate(dbParams,
                    "insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
        }

    }
}
