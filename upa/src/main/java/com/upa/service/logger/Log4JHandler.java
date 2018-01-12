package com.upa.service.logger;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class Log4JHandler implements ILogHandler,Serializable{
	private Class<?> LOG;

	public Log4JHandler(Class<?> clazz){
		//this.LOG = Logger.getLogger(clazz);
		this.LOG = clazz;
	}
	/**
	 * Logs messages with level "DEBUG".
	 * @param msg message to log
	 * 
	 */
	public void logMessage(Object msg){
		//LOG.debug(msg);
		Logger.getLogger(LOG).debug(msg);
	}
	/**
	 * Logs messages with level "TRACE".
	 * @param msg message to log
	 * 
	 */
	public void logTrace(Object msg){
		//LOG.trace(msg);
		Logger.getLogger(LOG).trace(msg);
	}
	/**
	 * Logs messages with level "INFO".
	 * @param msg message to log
	 * 
	 */
	public void logInfo(Object msg){
		//LOG.info(msg);
		Logger.getLogger(LOG).info(msg);
	}
	/**
	 * Logs messages with level "WARN".
	 * @param msg message to log
	 * 
	 */
	public void logWarn(Object msg){
		//LOG.warn(msg);
		Logger.getLogger(LOG).warn(msg);
	}
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * 
	 */
	public void logError(Object msg){
		//LOG.error(msg);
		Logger.getLogger(LOG).error(msg);
	}
	/**
	 * Logs messages with level "FATAL".
	 * @param msg message to log
	 * 
	 */
    public void logFatal(Object msg){
    	//LOG.fatal(msg);
    	Logger.getLogger(LOG).fatal(msg);
    }
	/**
	 * Logs messages with level "ERROR".
	 * @param msg message to log
	 * @param t error object to print error stack
	 * 
	 */
    public void logError(Object msg, Throwable t){
    	//LOG.error(msg, t);
    	Logger.getLogger(LOG).error(msg, t);
    }
}
