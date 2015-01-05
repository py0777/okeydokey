package org.okeydokey.framework.service;

import java.io.IOException;

import org.okeydokey.framework.biz.impl.AbsBiz;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.exception.BizException;
import org.okeydokey.framework.utils.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * service class
 * execute biz class
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class Service {

	private String name;
	private String className;

	static Logger errorlog = LoggerFactory.getLogger("error");
	static Logger bizlog = LoggerFactory.getLogger("biz");

	public Service(String bizId, String className) throws IOException {
		setName(bizId);
		setClassName(className);
	}

	public void execute(IServletContext context) {

		context.setBizExceptionThrown(false);
		context.setBizException(null);
		Object object = null;

		try {
			object = Class.forName(className).newInstance();
			if (object instanceof AbsBiz) {
				// before start
				infoLog("![BEFORE]![" + context.getServletId() + "]");
				((AbsBiz) object).before(context);
				// execute !!
				infoLog("![EXECUTE]![" + context.getServletId() + "]");
				((AbsBiz) object).execute(context);
			} else {
				errorLog("Specified class is not Biz Class : " + object.getClass().getName());
			}
		} catch (BizException be) {
			context.setBizExceptionThrown(true);
			context.setBizException(be);
			errorLog("![BIZ EXCEPTION]![" + context.getServletId() + "]\n" + BaseUtil.toExceptionString(be));
		} catch (InstantiationException e) {
			errorLog("![INSTANTIAION EXCEPTION]![" + context.getServletId() + "]\n" + BaseUtil.toExceptionString(e));
		} catch (IllegalAccessException e) {
			errorLog("![ILLEGAL ACCESS EXCEPTION]![" + context.getServletId() + "]\n" + BaseUtil.toExceptionString(e));
		} catch (ClassNotFoundException e) {
			errorLog("![CLASS NOT FOUND EXCEPTION]![" + context.getServletId() + "]\n" + BaseUtil.toExceptionString(e));
		} catch (Exception e) {
			errorLog("![EXCEPTION]![" + context.getServletId() + "]\n" + BaseUtil.toExceptionString(e));
		} finally {
			try {
				// after start
				infoLog("![AFTER]![" + context.getServletId() + "]");
				((AbsBiz) object).after(context);
			} catch (Exception e) {
				// errorLog("!error!" + context.getServletId() +"\n"+
				// BaseUtil.toExceptionString(e));
			}
		}

	}

	private void errorLog(String message) {
		errorlog.error(message);
		bizlog.error(message);
	}

	private void infoLog(String message) {
		bizlog.info(message);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
