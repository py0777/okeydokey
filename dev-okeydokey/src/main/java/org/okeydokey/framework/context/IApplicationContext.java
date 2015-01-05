package org.okeydokey.framework.context;

import java.util.List;
import java.util.Map;

import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.service.ServiceList;

public interface IApplicationContext {

	public String getContextName();

	public void setContextName(String contextName);

	public String getContextRootPath();

	public void setContextRootPath(String contextRootPath);

	public ServiceList<Service> getServiceList();

	public void setServiceList(ServiceList<Service> serviceList);

	public Map<String, String> getServiceMap();

	public void setServiceMap(Map<String, String> serviceMap);

	public Map<String, Object> getConfigeMap();

	public void setConfigeMap(Map<String, Object> configeMap);

	public List<String> getBizClassPath();

	public void setBizClassPath(List<String> bizClassPath);

}
