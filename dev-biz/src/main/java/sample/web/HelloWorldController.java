package main.java.sample.web;


import org.okeydokey.framework.biz.impl.AbsBiz;
import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.exception.BizException;
import org.okeydokey.framework.utils.BaseUtil;

public class HelloWorldController extends AbsBiz {

	@Override
	public void before(IServletContext context) throws Exception {

	}

	@Override
	public void execute(IServletContext context) throws Exception {
		try {
			String fromJspValue = context.getBizMap().getString("ID");
			
			bizlog.debug("fromJspValue >> " + fromJspValue);
			
			context.getBizMap().put("KEY", fromJspValue);
			
			context.getBizMap().put(ConfigConstants.VIEW, "/view/Hello/HelloView.jsp");

		} catch (Exception e) {
			throw new BizException("ERROR", BaseUtil.getMessage("ERROR"), e);
		}
	}

	@Override
	public void after(IServletContext context) throws Exception {

	}

}
