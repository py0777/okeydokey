package org.okeydokey.framework.service;

import java.io.IOException;
import java.util.ArrayList;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * service list class
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class ServiceList<E> extends ArrayList<Service> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4230246646938406745L;

	/**
	 * get Service from service list array
	 * if service is not in list, make and return instance
	 * @param context
	 * @return Service
	 */
	public Service getService(IServletContext context) {

		String bizId = context.getBizId();
		String className = context.getClassName();

		try {
			for (int i = 0; i < size(); i++) {
				Service service = (Service) get(i);
				if (service.getName().equals(bizId)) {
					return service;
				}
			}

			Service service = new Service(bizId, className);
			add(service);
			return service;

		} catch (IOException ioe) {
			return null;
		}
	}

}
