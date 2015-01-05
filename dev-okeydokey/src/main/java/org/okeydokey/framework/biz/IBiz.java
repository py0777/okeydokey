package org.okeydokey.framework.biz;

import org.okeydokey.framework.context.IServletContext;

public interface IBiz {

	public abstract void execute(IServletContext context) throws Exception;

	public abstract void before(IServletContext context) throws Exception;

	public abstract void after(IServletContext context) throws Exception;

}
