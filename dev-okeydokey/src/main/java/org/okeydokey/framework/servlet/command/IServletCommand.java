package org.okeydokey.framework.servlet.command;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * servlet command interface
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public interface IServletCommand {

	/**
	 * <pre>
	 * Execute servlet Command
	 * </pre>
	 * 
	 * @param IServletContext
	 * @throws Exception
	 */
	public void execute(IServletContext context) ;

	
}
