package org.okeydokey.framework.context;

import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.data.impl.BizMap;

public interface IServletContext {

	public BizMap<String, Object> getBizMap();

	public void setBizMap(BizMap<String, Object> bizMap);

	public boolean isBizExceptionThrown();

	public void setBizExceptionThrown(boolean bizExceptionThrown);

	public Throwable getBizException();

	public void setBizException(Throwable bizException);

	public String getDataFormat();

	public void setDataFormat(String dataFormat);

	public String getClassName();

	public void setClassName(String className);

	public String getServletId();

	public void setServletId(String servletId) ;

	public String getBizId() ;

	public void setBizId(String bizId) ;

	public String getOriginalBizId() ;

	public void setOriginalBizId(String originalBizId);
	
	public HttpServletResponse getResponse();

	public HttpServletRequest getRequest();

	public void setRequest(HttpServletRequest request) ;

	public void setResponse(HttpServletResponse response) ;
	
	public ByteBuffer getReqeustBytebuffer();
	
	public void setReqeustBytebuffer(ByteBuffer reqeustBytebuffer) ;
	
	public ByteBuffer getResponseBytebuffer();

	public void setResponseBytebuffer(ByteBuffer responseBytebuffer);
	
	public String getRequestASCIIString();

	public void setRequestASCIIString(String requestASCIIString);

	public String getResponseASCIIString();

	public void setResponseASCIIString(String responseASCIIString);
}
