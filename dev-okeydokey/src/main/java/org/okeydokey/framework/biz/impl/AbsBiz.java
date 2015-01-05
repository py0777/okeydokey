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
 *
 */
public abstract class AbsBiz implements IBiz {

	public static Logger bizlog;

	public AbsBiz() {
		bizlog = LoggerFactory.getLogger("biz");
	}

	/**
	 * 
	 * 
	 * @param bizmap
	 * @param context
	 * @param bizId
	 * @throws Exception
	 */
	protected void callBiz(IServletContext context, String bizId) throws Exception {
		
		// Get and set class
		String className = ApplicationContext.getInstance().getServiceMap().get(bizId);
		context.setClassName(className);

		// Set original bizId
		if(StringUtil.isEmpty(context.getOriginalBizId())){
			context.setOriginalBizId(context.getBizId());
		}
		
		// Set bizId
		context.setBizId(bizId);
		context.getBizMap().put(ConfigConstants.BIZID, bizId);

		// Execute Service start
		Service service = ApplicationContext.getInstance().getServiceList().getService(context);
		service.execute(context);
	}
}
