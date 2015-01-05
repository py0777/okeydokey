package org.okeydokey.framework.servlet.stream;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * Read HttpRequest
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public interface IStreamReader {
	
	/**
	 * Read HttpRequest
	 * 
	 * @param IServletContext
	 * @throws Exception
	 */
	public void read(IServletContext context);
	
}
