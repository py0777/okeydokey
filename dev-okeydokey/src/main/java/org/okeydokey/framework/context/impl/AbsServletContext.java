package org.okeydokey.framework.context.impl;

import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.data.impl.BizMap;
/**
 * <pre>
 * abstract Servletcontext class
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public abstract class AbsServletContext implements IServletContext{

	/**
	 * default BizMap
	 */
	protected BizMap<String, Object> bizMap;

	/**
	 * whether exception occur where biz class
	 */
	protected boolean bizExceptionThrown;

	/**
	 * exception from biz class
	 */
	protected Throwable bizException;

	/**
	 * unique servlet id
	 */
	protected String servletId;

	/**
	 * biz id
	 */
	protected String bizId;

	/**
	 * biz id which called first
	 */
	protected String originalBizId;

	/**
	 * 
	 */
	protected String dataFormat;

	/**
	 * 
	 */
	protected String className;

	/**
	 * 
	 */
	protected HttpServletRequest request;

	/**
	 * 
	 */
	protected HttpServletResponse response;

	/**
	 * 
	 */
	protected ByteBuffer ReqeustBytebuffer;
	
	/**
	 * 
	 */
	protected ByteBuffer ResponseBytebuffer;
	
	/**
	 * 
	 */
	protected String RequestASCIIString;
	
	/**
	 * 
	 */
	protected String ResponseASCIIString;
	
	/**
	 * @throws Exception
	 */
	public AbsServletContext(){
		bizMap = new BizMap<String, Object>();
		bizExceptionThrown = false;
	}
   
	public BizMap<String, Object> getBizMap() {
		return bizMap;
	}

	public void setBizMap(BizMap<String, Object> bizMap) {
		this.bizMap = bizMap;
	}

	public boolean isBizExceptionThrown() {
		return bizExceptionThrown;
	}

	public void setBizExceptionThrown(boolean bizExceptionThrown) {
		this.bizExceptionThrown = bizExceptionThrown;
	}

	public Throwable getBizException() {
		return bizException;
	}

	public void setBizException(Throwable bizException) {
		this.bizException = bizException;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getServletId() {
		return servletId;
	}

	public void setServletId(String servletId) {
		this.servletId = servletId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getOriginalBizId() {
		return originalBizId;
	}

	public void setOriginalBizId(String originalBizId) {
		this.originalBizId = originalBizId;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ByteBuffer getReqeustBytebuffer() {
		return ReqeustBytebuffer;
	}

	public void setReqeustBytebuffer(ByteBuffer reqeustBytebuffer) {
		ReqeustBytebuffer = reqeustBytebuffer;
	}

	public ByteBuffer getResponseBytebuffer() {
		return ResponseBytebuffer;
	}

	public void setResponseBytebuffer(ByteBuffer responseBytebuffer) {
		ResponseBytebuffer = responseBytebuffer;
	}

	public String getRequestASCIIString() {
		return RequestASCIIString;
	}

	public void setRequestASCIIString(String requestASCIIString) {
		RequestASCIIString = requestASCIIString;
	}

	public String getResponseASCIIString() {
		return ResponseASCIIString;
	}

	public void setResponseASCIIString(String responseASCIIString) {
		ResponseASCIIString = responseASCIIString;
	}
}
