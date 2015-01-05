package org.okeydokey.framework.servlet.handler;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * Request Handler Interface
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public interface IRequestHandler {

	/**
	 * process handler
	 * 
	 * 1. Read input stream
	 * 2. Execute biz class
	 * 3. Write output stream
	 * 
	 * @param AbsContext
	 * @throws Exception
	 */
	void process(IServletContext context);

}
