package org.okeydokey.framework.biz.impl;


import org.okeydokey.framework.biz.IBiz;
import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.service.Service;
import org.okeydokey.framework.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * abstract biz class
 * all biz class should extend this class
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public abstract class AbsBiz implements IBiz {

	/**
	 * biz.log
	 */
	public static Logger bizlog;

	/**
	 * constructor
	 */
	public AbsBiz() {
		bizlog = LoggerFactory.getLogger("biz");
	}

	/**
	 * call biz class by bizId
	 * 
	 * @param context -> ServletContext
	 * @param bizId -> bizId
	 * @throws Exception
	 */
	protected void callBiz(IServletContext context, String bizId) throws Exception {
		
		// get and set class
		String className = ApplicationContext.getInstance().getServiceMap().get(bizId);
		context.setClassName(className);

		// set original bizId
		if(StringUtil.isEmpty(context.getOriginalBizId())){
			context.setOriginalBizId(context.getBizId());
		}
		
		// set bizId
		context.setBizId(bizId);
		context.getBizMap().put(ConfigConstants.BIZID, bizId);

		// execute Service start
		Service service = ApplicationContext.getInstance().getServiceList().getService(context);
		service.execute(context);
	}
}
