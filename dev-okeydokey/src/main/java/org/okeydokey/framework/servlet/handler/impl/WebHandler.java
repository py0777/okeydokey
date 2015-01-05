package org.okeydokey.framework.servlet.handler.impl;

import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.servlet.handler.IRequestHandler;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;
import org.okeydokey.framework.servlet.stream.impl.WebStream;

/**
 * <pre>
 * handling web mvc
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class WebHandler implements IRequestHandler {

	/**
	 * single WebHandler instance
	 */
	private static WebHandler instance;

	/**
	 * return single WebHandler
	 * 
	 * @return WebHandler
	 */
	public synchronized static WebHandler getInstance() {
		if (null == instance) {
			instance = new WebHandler();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.okeydokey.framework.servlet.handler.IRequestHandler#process(org.okeydokey.framework.context.IServletContext)
	 */
	@Override
	public void process(IServletContext context) {

		IStreamReader streamReader = null;
		IStreamWriter streamWriter = null;

		// reading stream
		streamReader = (IStreamReader) WebStream.getInstance();
		streamReader.read(context);

		// okeydokey execute
		Service service = ApplicationContext.getInstance().getServiceList().getService(context);
		service.execute(context);

		// writing stream
		streamWriter = (IStreamWriter) WebStream.getInstance();
		streamWriter.write(context);

	}
}
