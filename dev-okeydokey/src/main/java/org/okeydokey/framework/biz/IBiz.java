package org.okeydokey.framework.biz;

import org.okeydokey.framework.context.IServletContext;

/**
 * <pre>
 * interface biz class
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public interface IBiz {

	/**
	 * main transaction
	 * if exception occur during before method, this method does't execute
	 * 
	 * @param context
	 * @throws Exception
	 */
	public abstract void execute(IServletContext context) throws Exception;

	/**
	 * 
	 * @param context
	 * @throws Exception
	 */
	public abstract void before(IServletContext context) throws Exception;

	/**
	 * @param context
	 * @throws Exception
	 */
	public abstract void after(IServletContext context) throws Exception;

}
