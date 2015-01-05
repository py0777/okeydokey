package org.okeydokey.framework.servlet.stream;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * Write HttpResopnse
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public interface IStreamWriter {
	
	/**
	 * Write HttpResopnse
	 * 
	 * @param IServletContext
	 * @throws Exception
	 */
	public void write(IServletContext context);

}
