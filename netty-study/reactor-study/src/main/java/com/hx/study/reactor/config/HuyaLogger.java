package com.hx.study.reactor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 *
 */
public class HuyaLogger {
   //告警通知级别
   public static final Marker FATAL_MARKER = MarkerFactory.getMarker("FATAL");

   private Logger log;
   private HuyaLogger(){

   }

   private HuyaLogger(Logger log){
      this.log = log;
   }



   public static HuyaLogger getHuyaLogger(Logger log ){
      return new HuyaLogger(log);
   }

   public static HuyaLogger getHuyaLogger(Class clz ){
      return new HuyaLogger(LoggerFactory.getLogger(clz));
   }

   public static HuyaLogger getLogger(Class clz ){
      return new HuyaLogger(LoggerFactory.getLogger(clz));
   }

   public String getName() {
      return log.getName();
   }

   public boolean isTraceEnabled() {
      return log.isTraceEnabled();
   }

   public void trace(String msg) {
      log.trace(msg);
   }

   public void trace(String format, Object arg) {
      log.trace(format, arg);
   }

   public void trace(String format, Object arg1, Object arg2) {
      log.trace(format, arg1, arg2);
   }

   public void trace(String format, Object... arguments) {
      log.trace(format, arguments);
   }

   public void trace(String msg, Throwable t) {
      log.trace(msg, t);
   }

   public boolean isTraceEnabled(Marker marker) {
      return log.isTraceEnabled(marker);
   }

   public void trace(Marker marker, String msg) {
      log.trace(marker, msg);
   }

   public void trace(Marker marker, String format, Object arg) {
      log.trace(marker, format, arg);
   }

   public void trace(Marker marker, String format, Object arg1, Object arg2) {
      log.trace(marker, format, arg1, arg2);
   }

   public void trace(Marker marker, String format, Object... argArray) {
      log.trace(marker, format, argArray);
   }

   public void trace(Marker marker, String msg, Throwable t) {
      log.trace(marker, msg, t);
   }

   public boolean isDebugEnabled() {
      return log.isDebugEnabled();
   }

   public void debug(String msg) {
      log.debug(msg);
   }

   public void debug(String format, Object arg) {
      log.debug(format, arg);
   }

   public void debug(String format, Object arg1, Object arg2) {
      log.debug(format, arg1, arg2);
   }

   public void debug(String format, Object... arguments) {
      log.debug(format, arguments);
   }

   public void debug(String msg, Throwable t) {
      log.debug(msg, t);
   }

   public boolean isDebugEnabled(Marker marker) {
      return log.isDebugEnabled(marker);
   }

   public void debug(Marker marker, String msg) {
      log.debug(marker, msg);
   }

   public void debug(Marker marker, String format, Object arg) {
      log.debug(marker, format, arg);
   }

   public void debug(Marker marker, String format, Object arg1, Object arg2) {
      log.debug(marker, format, arg1, arg2);
   }

   public void debug(Marker marker, String format, Object... arguments) {
      log.debug(marker, format, arguments);
   }

   public void debug(Marker marker, String msg, Throwable t) {
      log.debug(marker, msg, t);
   }

   public boolean isInfoEnabled() {
      return log.isInfoEnabled();
   }

   public void info(String msg) {
      log.info(msg);
   }

   public void info(String format, Object arg) {
      log.info(format, arg);
   }

   public void info(String format, Object arg1, Object arg2) {
      log.info(format, arg1, arg2);
   }

   public void info(String format, Object... arguments) {
      log.info(format, arguments);
   }

   public void info(String msg, Throwable t) {
      log.info(msg, t);
   }

   public boolean isInfoEnabled(Marker marker) {
      return log.isInfoEnabled(marker);
   }

   public void info(Marker marker, String msg) {
      log.info(marker, msg);
   }

   public void info(Marker marker, String format, Object arg) {
      log.info(marker, format, arg);
   }

   public void info(Marker marker, String format, Object arg1, Object arg2) {
      log.info(marker, format, arg1, arg2);
   }

   public void info(Marker marker, String format, Object... arguments) {
      log.info(marker, format, arguments);
   }

   public void info(Marker marker, String msg, Throwable t) {
      log.info(marker, msg, t);
   }

   public boolean isWarnEnabled() {
      return log.isWarnEnabled();
   }

   public void warn(String msg) {
      log.warn(msg);
   }

   public void warn(String format, Object arg) {
      log.warn(format, arg);
   }

   public void warn(String format, Object... arguments) {
      log.warn(format, arguments);
   }

   public void warn(String format, Object arg1, Object arg2) {
      log.warn(format, arg1, arg2);
   }

   public void warn(String msg, Throwable t) {
      log.warn(msg, t);
   }

   public boolean isWarnEnabled(Marker marker) {
      return log.isWarnEnabled(marker);
   }

   public void warn(Marker marker, String msg) {
      log.warn(marker, msg);
   }

   public void warn(Marker marker, String format, Object arg) {
      log.warn(marker, format, arg);
   }

   public void warn(Marker marker, String format, Object arg1, Object arg2) {
      log.warn(marker, format, arg1, arg2);
   }

   public void warn(Marker marker, String format, Object... arguments) {
      log.warn(marker, format, arguments);
   }

   public void warn(Marker marker, String msg, Throwable t) {
      log.warn(marker, msg, t);
   }

   public boolean isErrorEnabled() {
      return log.isErrorEnabled();
   }

   public void error(String msg) {
      log.error(msg);
   }

   public void error(String format, Object arg) {
      log.error(format, arg);
   }

   public void error(String format, Object arg1, Object arg2) {
      log.error(format, arg1, arg2);
   }

   public void error(String format, Object... arguments) {
      log.error(format, arguments);
   }

   public void error(String msg, Throwable t) {
      log.error(msg, t);
   }

   /**
    *  严重错误，将发送错误短信
    * @param msg
    */
   public void fatal(String msg) {
      log.error(FATAL_MARKER,msg);
   }

   /**
    * 严重错误，将发送错误短信
    * @param format
    * @param arg
    */
   public void fatal(String format, Object arg) {
      log.error(FATAL_MARKER,format, arg);
   }

   /**
    * 严重错误，将发送错误短信
    * @param format
    * @param arg1
    * @param arg2
    */
   public void fatal(String format, Object arg1, Object arg2) {
      log.error(FATAL_MARKER,format, arg1, arg2);
   }

   /**
    * 严重错误，将发送错误短信
    * @param format
    * @param arguments
    */
   public void fatal(String format, Object... arguments) {
      log.error(FATAL_MARKER,format, arguments);
   }

   /**
    * 严重错误，将发送错误短信
    * @param msg
    * @param t
    */
   public void fatal(String msg, Throwable t) {
      log.error(FATAL_MARKER,msg, t);
   }
}
