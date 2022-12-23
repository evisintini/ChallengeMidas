package com.midas.challenge.challengemidas.logger;

import java.io.IOException;
import java.sql.SQLException;

import com.midas.challenge.challengemidas.logger.exceptions.ExceptionSetting;
import com.midas.challenge.challengemidas.logger.exceptions.NotFoundExceptionSetting;

public interface DataLogger {
    boolean isInfoEnabled();

    void info(String message);

    boolean isWarnEnabled();

    void warn(String message);

    boolean isErrorEnabled();

    void error(String message);

    void logMessage(String messageText, Boolean message, Boolean warning, Boolean error)
            throws ExceptionSetting, NotFoundExceptionSetting, SQLException, IOException;

}
