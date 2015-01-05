package org.okeydokey.framework.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * <pre>
 * bizid property Loader
 * 
 * biz id and class mapping property file loader
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public final class BizIdLoader {

	/**
	 * single instance
	 */
	private static BizIdLoader instance;
	
	/**
	 * Service properties
	 */
	private Properties props;
	
	/**
	 * Service property name
	 */
	private String propertyFile;

	/**
	 * Prohibit instance
	 */
	private BizIdLoader() {

	}

	/**
	 * Return single ServiceLoader instance
	 * @return ServiceLoader
	 */
	public synchronized static BizIdLoader getPropertyInstance() {
		if (instance == null) {
			instance = new BizIdLoader();
		}
		return instance;

	}

	/**
	 * Set service property files
	 * @param strDir
	 * @return boolean
	 */
	public boolean setPropertyFiles(String strDir) {
		props = new Properties();
		File file = new File(strDir);
		File[] subFiles = file.listFiles();

		if (subFiles.length == 0) {
			return false;
		}
		for (File profile : subFiles) {
			if (profile.canRead() && profile.isFile() && profile.getName().lastIndexOf(".properties") != 1) {
				System.out.println("---> Biz Id Property File : " + profile.getPath());
				InputStream in = null;
				try {
					in = new FileInputStream(profile);
					if (null != in) {
						props.load(in);
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}

		return true;

	}

	/**
	 * Return property file name
	 * @return String
	 */
	public String getPropertyFile() {
		return propertyFile;
	}

	/**
	 * Return property value
	 * 
	 * @param key
	 * @return String
	 */
	public String getValue(String key) {
		return getProperty(key);
	}

	/**
	 * Return value if key or value is null, return replace
	 * 
	 * @param key
	 * @param replace
	 * @return String
	 */
	public String getValue(String key, String replace) {
		if (key == null || key.equals(""))
			return replace;
		String retStr = getProperty(key);
		if (retStr.equals("")) {
			return replace;
		}
		return retStr;
	}

	/**
	 * Set value 
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		addProperty(key, value);
	}

	/**
	 * Remove value
	 * 
	 * @param key
	 */
	public void removeValue(String key) {

		if (key == null || key.equals(""))
			return;
		key = key.trim();
		props.remove(key);

	}

	/**
	 * Get property
	 * @param str
	 * @return String
	 */
	public String getProperty(String str) {

		if (str == null || str.equals(""))
			return "";
		return (props.getProperty(str) == null) ? "" : props.getProperty(str).trim();
	}

	/**
	 * Add property
	 * 
	 * @param key
	 * @param value
	 */
	public void addProperty(String key, String value) {

		if (key == null || key.equals("") || value == null)
			return;
		key = key.trim();
		value = value.trim();
		props.put(key, value);
	}

	/**
	 * Remove property
	 * @param key
	 */
	public void removeProperty(String key) {

		if (key == null || key.equals(""))
			return;
		key = key.trim();
		props.remove(key);

	}

	/**
	 * Return all property names
	 * @return String[]
	 */
	public String[] getPropertyNames() {
		String[] retArr = new String[props.size()];
		int i = 0;
		try {
			for (Enumeration e = props.propertyNames(); e.hasMoreElements(); i++) {
				retArr[i] = new String(((String) e.nextElement()).getBytes("8859_1"));
			}
		} catch (UnsupportedEncodingException uenc) {

			i = 0;
			for (Enumeration enc = props.propertyNames(); enc.hasMoreElements(); i++) {
				retArr[i] = (String) enc.nextElement();
			}
		}

		return retArr;
	}

}
