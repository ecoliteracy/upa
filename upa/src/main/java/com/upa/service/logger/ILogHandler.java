package com.upa.service.logger;

public interface ILogHandler {
	/**
	 * Logs messages with level "DEBUG".
	 * @param msg message to log
	 * 
	 */
	void logMessage(Object msg);
	/**
	 * Logs messages with level "TRACE".
	 * @param msg message to log
	 * 
	 */
	void logTrace(Object msg);
	/**
	 * Logs messages with level "INFO".
	 * @param msg message to log
	 * 
	 */
	void logInfo(Object msg);
	/**
	 * Logs messages with level "WARN".
	 * @param msg message to log
	 * 
	 */
	void logWarn(Object msg);
	/**
	 * Logs messages with level "FATAL".
	 * @param msg message to log
	 * 
	 */
	void logFatal(Object msg);
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * 
	 */
	void logError(Object msg);
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * @param t error object to print error stack
	 * 
	 */
	void logError(Object msg, Throwable t);
}
