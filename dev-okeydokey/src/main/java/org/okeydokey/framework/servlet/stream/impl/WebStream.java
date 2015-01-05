package org.okeydokey.framework.servlet.stream.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.data.impl.BizMap;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;

/**
 * <pre>
 * Web MVC framework doesn't need steam read and steam write(cause http post request)
 * Because of pattern , implements interface(IStreamReader, IStreamWriter)
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class WebStream implements IStreamReader, IStreamWriter {

	/**
	 * single WebStream instance
	 */
	private static WebStream instance = null;

	/**
	 * @return single WebStream
	 */
	public synchronized static WebStream getInstance() {
		if (instance == null) {
			instance = new WebStream();
		}

		return instance;
	}

	/**
	 * make bizmap values from httpRequest parameters
	 * 
	 * @param context
	 * @return
	 */
	private BizMap<String, Object> serializeRequst(IServletContext context){
		HttpServletRequest request = context.getRequest();
		BizMap<String, Object> bizmap = context.getBizMap();
		
		Enumeration<?> param = request.getParameterNames();
		
		while (param.hasMoreElements()) {
			String key = (String) param.nextElement();
			bizmap.put(key, request.getParameter(key));
		}
		
		
		
		return bizmap;
	}
	
	@Override
	public void read(IServletContext context) {
		context.getRequest().setAttribute("BizMap", serializeRequst(context));
	}

	@Override
	public void write(IServletContext context) {
		// jsp forward
		if (null == context.getBizMap().get(ConfigConstants.VIEW)) {
			throw new RuntimeException("View is null");
		} else {
			// RequestDispatcher
			String forward_jsp_url = (String) context.getBizMap().get(ConfigConstants.VIEW);
			try {
				context.getRequest().getRequestDispatcher(forward_jsp_url).forward(context.getRequest(), context.getResponse());
			} catch (ServletException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
