package org.okeydokey.framework.context.impl;

import java.util.List;
import java.util.Map;

import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.service.ServiceList;

public class ApplicationContext {

	private static ApplicationContext instance = null;

	private String contextRootPath;

	private String contextName;

	private List<String> bizClassPath;
	
	private ServiceList<Service> ServiceList;

	private Map<String, String> serviceMap;

	private Map<String, Object> configeMap;
	
	private ApplicationContext() {
		
	}

	public synchronized static ApplicationContext getInstance() {
		if (instance == null) {
			instance = new ApplicationContext();
		}
		return instance;
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public String getContextRootPath() {
		return contextRootPath;
	}

	public void setContextRootPath(String contextRootPath) {
		this.contextRootPath = contextRootPath;
	}

	public ServiceList<Service> getServiceList() {
		return ServiceList;
	}

	public void setServiceList(ServiceList<Service> serviceList) {
		ServiceList = serviceList;
	}

	public Map<String, String> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(Map<String, String> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public Map<String, Object> getConfigeMap() {
		return configeMap;
	}

	public void setConfigeMap(Map<String, Object> configeMap) {
		this.configeMap = configeMap;
	}

	public List<String> getBizClassPath() {
		return bizClassPath;
	}

	public void setBizClassPath(List<String> bizClassPath) {
		this.bizClassPath = bizClassPath;
	}

	
}
