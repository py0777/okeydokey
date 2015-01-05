package org.okeydokey.framework.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.loader.BizIdLoader;
import org.okeydokey.framework.loader.PropertyLoader;
import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.service.ServiceList;
import org.okeydokey.framework.utils.BaseUtil;
import org.okeydokey.framework.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationListener implements ServletContextListener {

	/**
	 * okeydokey.log
	 */
	private static Logger log = LoggerFactory.getLogger("okeydokey");

	public void contextDestroyed(ServletContextEvent event) {

		StringBuffer logs = new StringBuffer();

		logs.append("\n#############################################################################");
		logs.append("\n#########################[OKEYDOKEY FRAMEWORK END!]##########################");
		logs.append("\n#############################################################################");

		write(logs.toString());

	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();

		StringBuffer logs = new StringBuffer();

		logs.append("\n#############################################################################");
		logs.append("\n#########################[OKEYDOKEY FRAMEWORK BEGIN]#########################");
		logs.append("\n#############################################################################");

		write(logs.toString());

		// 1.AppContext init
		initAppContext(ctx);

		// 2.Property init
		initProperty(ctx);

		// 3. bizId and Class Mapping
		initIdClassMapping(ctx);

	}

	private void initAppContext(ServletContext ctx) {
		System.out.println("#############################################################################");
		System.out.println("1. ApplicationContext init...");
		ApplicationContext.getInstance().setContextRootPath(ctx.getRealPath(""));
		ApplicationContext.getInstance().setContextName(ctx.getServletContextName());
		System.out.println("#############################################################################\n");
	}

	private void initProperty(ServletContext ctx) {
		System.out.println("#############################################################################");
		System.out.println("2. Property information loading...");

		String configPropertyFile = ctx.getRealPath("/WEB-INF/config/config.properties");
		String codePropertyFile = ctx.getRealPath("/WEB-INF/config/code.properties");
		String messagePropertyFile = ctx.getRealPath("/WEB-INF/config/message.properties");
		String okeydokeyPropertyFile = ctx.getRealPath("/WEB-INF/config/okeydokey.properties");

		System.out.println("---> Config File : " + configPropertyFile);
		System.out.println("---> Code File : " + codePropertyFile);
		System.out.println("---> Message File : " + messagePropertyFile);
		System.out.println("---> Okeydokey File : " + okeydokeyPropertyFile);

		// load Properties
		PropertyLoader.getPropertyInstance().setPropertyFile(configPropertyFile, codePropertyFile, messagePropertyFile, okeydokeyPropertyFile);
		System.out.println("#############################################################################\n");
	}

	private void initIdClassMapping(ServletContext ctx) {
		System.out.println("#############################################################################");
		// Set Id Property
		System.out.println("3. bizId and Class mapping from Property");

		String sIsMappingByProperty = BaseUtil.getOkeydokey("okeydokey.class.mapping.by.property");
		String sIsMappingByAnnotation = BaseUtil.getOkeydokey("okeydokey.class.mapping.by.annotation");

		boolean bIsMappingByProperty = false;
		boolean bIsMappingByAnnotation = false;

		if (!StringUtil.isEmpty(sIsMappingByProperty)) {
			bIsMappingByProperty = Boolean.parseBoolean(sIsMappingByProperty);
		}
		if (!StringUtil.isEmpty(sIsMappingByAnnotation)) {
			bIsMappingByAnnotation = Boolean.parseBoolean(sIsMappingByAnnotation);
		}
		// class and id mapping by property
		if (bIsMappingByProperty) {
			String idMapPropertiesStorepath = BaseUtil.getOkeydokey("okeydokey.bizid.path");

			if (StringUtil.isEmpty(idMapPropertiesStorepath)) {
				throw new RuntimeException("okeydokey.bizid.path is empty");
			}

			BizIdLoader.getPropertyInstance().setPropertyFiles(ctx.getRealPath(idMapPropertiesStorepath));

			// load Biz ID Mapping info
			ApplicationContext.getInstance().setServiceList(new ServiceList<Service>());
			String[] bizId = BizIdLoader.getPropertyInstance().getPropertyNames();
			Map<String, String> serviceMap = new HashMap<String, String>();
			for (String key : bizId) {
				String value = BizIdLoader.getPropertyInstance().getProperty(key);
				serviceMap.put(key, value);
				System.out.println("--->---> Biz Id : " + key + ", Class : " + value);
			}
			ApplicationContext.getInstance().setServiceMap(serviceMap);
		}
		System.out.println("#############################################################################\n");
	}

	private void write(String logs) {
		System.out.println(logs);
		log.info(logs);
	}

}