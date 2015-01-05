package main.java.sample.server.json;

import java.util.HashMap;
import java.util.Map;

import org.okeydokey.framework.biz.impl.AbsBiz;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.exception.BizException;
import org.okeydokey.framework.utils.BaseUtil;
import org.okeydokey.framework.utils.StringUtil;

import com.google.gson.Gson;

public class HelloWorldJson extends AbsBiz {
	
	@Override
	public void before(IServletContext context) throws Exception {
		String inputMessage = context.getRequestASCIIString();
		
		if(StringUtil.isEmpty(inputMessage)){
			throw new BizException("ERROR", BaseUtil.getMessage("ERROR"));
		}
	}
	
	@Override
	public void execute(IServletContext context) throws Exception {
		try {
			String inputMessage = context.getRequestASCIIString();
			
			Map<String, Object> jsonmap = new Gson().fromJson(inputMessage, Map.class);
			
			//request mapping
			String value1 = (String) jsonmap.get("XYZ1");
			String value2 = (String) jsonmap.get("XYZ2");
			String value3 = (String) jsonmap.get("XYZ3");
			
			bizlog.info(value1 + value2 + value3);
			
			//result setting
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("MESSAGE", "HELLOWORLD");
			
			context.setResponseASCIIString(new Gson().toJson(resultMap));
			
		} catch (Exception e) {
			throw new BizException("ERROR", BaseUtil.getMessage("ERROR"), e);
		}
	}

	@Override
	public void after(IServletContext context) throws Exception {
		
		//if exception thrown 
		if(context.isBizExceptionThrown()){
			//result setting
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("MESSAGE", BaseUtil.getMessage("ERROR"));
			context.setResponseASCIIString(new Gson().toJson(resultMap));
		}

	}

}
