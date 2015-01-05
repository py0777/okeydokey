package org.okeydokey.framework.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.okeydokey.framework.loader.BizIdLoader;
import org.okeydokey.framework.loader.PropertyLoader;

public final class BaseUtil {

	public static String getOsName() {
		return System.getProperty("os.name");
	}

	public static String getVmVersion() {
		return System.getProperty("java.version");
	}

	public static ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static String getHostName() {
		String returnValue = null;
		try {
			returnValue = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			returnValue = "";
		}
		return returnValue;
	}

	public static String getConfig(String key) {
		return PropertyLoader.getPropertyInstance().getConfig(key);
	}

	public static int getIntConfig(String key) {
		return PropertyLoader.getPropertyInstance().getIntConfig(key);
	}

	public static String getClassNameByBizId(String bizId) {
		return BizIdLoader.getPropertyInstance().getProperty(bizId);
	}

	public static String getCode(String code) {
		return PropertyLoader.getPropertyInstance().getCode(code);
	}

	public static String getMessage(String id) {
		return PropertyLoader.getPropertyInstance().getMessage(id);
	}
	
	public static String getOkeydokey(String id) {
		return PropertyLoader.getPropertyInstance().getOkeydokey(id);
	}
	
	public static int getIntOkeydokey(String id) {
		return PropertyLoader.getPropertyInstance().getIntOkeydokey(id);
	}
	
	public static String toExceptionString(Throwable th) {
		if (th == null)
			return "";
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		return sw.toString();
	}
}
