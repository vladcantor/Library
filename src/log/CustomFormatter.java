package log;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter{

	@Override
	public String format(LogRecord log) {
		
		return "["+log.getLoggerName()+"] "+new Date(log.getMillis())+" ["+log.getLevel()+"] "+log.getMessage()+System.getProperty("line.separator");
	}

}