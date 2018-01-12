package com.upa.service.logger;

import java.io.Serializable;

public class LoggerServiceImpl implements ILoggerService,Serializable{

	private final ILogHandler logHandler;

	public LoggerServiceImpl(Class<?> clazz){
		this.logHandler = new Log4JHandler(clazz);
	}
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * 
	 */
	 public void logError(Object msg) {
		logHandler.logError(msg);
	}
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * @param t error object to print error stack
	 * 
	 */
	public void logError(Object msg, Throwable t) {
		logHandler.logError(msg, t);
	}
	/**
	 * Logs messages with level "INFO".
	 * @param msg message to log
	 * 
	 */
	public void logInfo(Object msg) {
		logHandler.logInfo(msg);

	}
	/**
	 * Logs messages with level "DEBUG".
	 * @param msg message to log
	 * 
	 */
	public void logMessage(Object msg) {
		logHandler.logMessage(msg);
	}
	/**
	 * Logs messages with level "TRACE".
	 * @param msg message to log
	 * 
	 */
	public void logTrace(Object msg) {
		logHandler.logTrace(msg);
	}
	/**
	 * Logs messages with level "WARN".
	 * @param msg message to log
	 * 
	 */
	public void logWarn(Object msg) {
		logHandler.logWarn(msg);
	}
	/**
	 * Logs messages with level "FATAL".
	 * @param msg message to log
	 * 
	 */
	public void logFatal(Object msg) {
		logHandler.logFatal(msg);
	}
}
