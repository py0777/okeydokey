package org.okeydokey.framework.servlet.handler.impl;

import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.servlet.handler.IRequestHandler;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;
import org.okeydokey.framework.servlet.stream.impl.AsciiStream;

/**
 * <pre>
 * handling ascii data format
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class AsciiHandler implements IRequestHandler {

	/**
	 * single JsonHandler instance
	 */
	private static AsciiHandler instance;

	/**
	 * return single JsonHandler
	 * 
	 * @return JsonHandler
	 */
	public synchronized static AsciiHandler getInstance() {
		if (null == instance) {
			instance = new AsciiHandler();
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
		streamReader = (IStreamReader) AsciiStream.getInstance();
		streamReader.read(context);

		// execute
		Service service = ApplicationContext.getInstance().getServiceList().getService(context);
		service.execute(context);

		// writing stream
		streamWriter = (IStreamWriter) AsciiStream.getInstance();
		streamWriter.write(context);

	}

}
