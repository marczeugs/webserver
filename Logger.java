import java.time.LocalTime;

public class Logger {
	private static LogLevel logLevel = LogLevel.DEBUG;

	public static void log(Object o) {
		if(logLevel == LogLevel.DEBUG) System.out.println(LocalTime.now() + " || " + o);
	}
	
	public static void err(Object o) {
		if(logLevel == LogLevel.DEBUG || logLevel == LogLevel.ERROR) System.out.println(LocalTime.now() + " || " + o);
	}
	
	public static void setLogLevel(LogLevel l) {
		logLevel = l;
	}

	public static LogLevel getLogLevel() {
		return logLevel;
	}
	
	public enum LogLevel {
		DEBUG,
		ERROR,
		NONE;
	}
}