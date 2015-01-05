package org.okeydokey.framework.servlet.handler.impl;

import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.servlet.handler.IRequestHandler;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;
import org.okeydokey.framework.servlet.stream.impl.ByteStream;

/**
 * <pre>
 * handling bytearray 
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class ByteHandler implements IRequestHandler {

	/**
	 * single ByteHandler instance
	 */
	private static ByteHandler instance;

	/**
	 * return single ByteHandler
	 * 
	 * @return ByteHandler
	 */
	public synchronized static ByteHandler getInstance() {
		if (null == instance) {
			instance = new ByteHandler();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.okeydokey.framework.servlet.handler.IRequestHandler#process(org.okeydokey.framework.context.IServletContext)
	 */
	@Override
	public void process(IServletContext context)  {

		IStreamReader streamReader;
		IStreamWriter streamWriter;

		// reading stream
		streamReader = (IStreamReader) ByteStream.getInstance();
		streamReader.read(context);

		// okeydokey execute
		Service service = ApplicationContext.getInstance().getServiceList().getService(context);
		service.execute(context);

		// writing stream
		streamWriter = (IStreamWriter) ByteStream.getInstance();
		streamWriter.write(context);

	}

}
