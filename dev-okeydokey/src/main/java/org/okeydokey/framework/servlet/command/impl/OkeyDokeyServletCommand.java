package org.okeydokey.framework.servlet.command.impl;

import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.servlet.command.IServletCommand;
import org.okeydokey.framework.servlet.handler.IRequestHandler;
import org.okeydokey.framework.servlet.handler.impl.AsciiHandler;
import org.okeydokey.framework.servlet.handler.impl.ByteHandler;
import org.okeydokey.framework.servlet.handler.impl.WebHandler;
import org.okeydokey.framework.utils.StringUtil;

/**
 * <pre>
 * after bizid, dataformat setting are completed,
 * call each data format handler by dataformat
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class OkeyDokeyServletCommand implements IServletCommand {

	/* (non-Javadoc)
	 * @see org.okeydokey.framework.servlet.command.IServletCommand#execute(org.okeydokey.framework.context.IServletContext)
	 */
	public void execute(IServletContext context)   {

		// json,do,byte,soap,xml,webdav, ...
		String dataformat = context.getDataFormat();

		if (StringUtil.isEmpty(dataformat)) {
			throw new RuntimeException("dataformat is empty ");

		}
		// Process Handler
		IRequestHandler handler = getRequestHandlerByDataformat(dataformat);
		handler.process(context);

	}

	/**
	 * get IRequestHandler by dataformat
	 * 
	 * @param dataformat
	 * @return IRequestHandler
	 * @throws Exception 
	 */
	private IRequestHandler getRequestHandlerByDataformat(String dataformat) throws RuntimeException {
		
		if (StringUtil.equals(ConfigConstants.DATA_FORMAT_JSON, dataformat) ) {
			return (IRequestHandler) AsciiHandler.getInstance();
		}else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_XML, dataformat) ) {
			return (IRequestHandler) AsciiHandler.getInstance();
		}else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_FLAT, dataformat) ) {
			return (IRequestHandler) AsciiHandler.getInstance();
		}else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_SOAP, dataformat) ) {
			return (IRequestHandler) AsciiHandler.getInstance();
		}else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_WEB, dataformat)) {
			return (IRequestHandler) WebHandler.getInstance();
		}else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_BYTEARRAY, dataformat)) {
			return (IRequestHandler) ByteHandler.getInstance();
		}else{
			throw new RuntimeException("dataformat is wrong! : " + dataformat);
		}

	}

}
