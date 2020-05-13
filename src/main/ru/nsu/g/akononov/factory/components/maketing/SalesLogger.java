package ru.nsu.g.akononov.factory.components.maketing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class SalesLogger {

    private static Logger logger = Logger.getLogger(CarDealer.class.getName());

    public SalesLogger(boolean isLogging) {
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        logger.addHandler(consoleHandler);

        if (isLogging)
            setLoggerPreferences();
    }

    private void setLoggerPreferences() {
        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord record) {
                StringBuffer buf = new StringBuffer(1000);

                SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
                Date resultDate = new Date(record.getMillis());

                buf.append(date_format.format(resultDate));
                buf.append(" ");
                buf.append(formatMessage(record));
                buf.append("\n");

                return buf.toString();
            }
        };

        try {
            FileHandler handler = new FileHandler("salesLog.txt");
            handler.setFormatter(formatter);
            logger.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
