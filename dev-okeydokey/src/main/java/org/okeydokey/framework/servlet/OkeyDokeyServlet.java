package org.okeydokey.framework.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.context.impl.ApplicationContext;
import org.okeydokey.framework.context.impl.ServletContext;
import org.okeydokey.framework.servlet.command.IServletCommand;
import org.okeydokey.framework.servlet.command.impl.OkeyDokeyServletCommand;
import org.okeydokey.framework.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * OkeyDokey j2ee Framework Servlet
 * http:host:port/{contextpath}/{bizid}.{dataformat}
 * 
 * servlet path should be like {bizid}.{dataformat}
 * 
 * ex) http:localhost:8080/okeydokey/helloworld.json
 * 
 * contexpath  = /okeydokey
 * servletpath = /helloworld.json
 * bizid   = helloworld
 * dataformat  = json
 * 
 * contexpath is set by Web Application Server
 * 
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class OkeyDokeyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6689448930034989984L;

	/**
	 * http request log
	 * access.log
	 */
	private static Logger accesslog = LoggerFactory.getLogger("access");

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// check http request
			//checkRequest(request);
			
			// write HttpRequest log when debugging mode
			if(accesslog.isDebugEnabled()){
				accessLog(request);
			}
			// analyze HttpRequest and initialize context by HttpServletRequest and HttpServletResponse
			// ServletContext is instanced when each servlet request 
			IServletContext context = new ServletContext(request, response);

			// get bizId from ServletContext
			// bizId set by ApplicationContext
			String bizId = context.getBizId();

			// get className from ApplicationContext by bizid
			String className = ApplicationContext.getInstance().getServiceMap().get(bizId);
			if (StringUtil.isEmpty(className)) {
				throw new ServletException("Can't find class by bizId : " + bizId);
			}

			// set bizId in BizMap
			context.getBizMap().put(ConfigConstants.BIZID, bizId);

			// set className in ServletContext
			context.setClassName(className);

			// execute command
			IServletCommand command = new OkeyDokeyServletCommand();
			command.execute(context);

			// set response header
			response.setStatus(HttpServletResponse.SC_OK);

		} catch (Exception e) {
			// set response header
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
			throw new ServletException(e);
		}
	}

	/**
	 * check size and null
	 *  
	 * @param request -> HttpServletRequest
	 * @throws Exception
	 */
	private void checkRequest(HttpServletRequest request) throws Exception {
		if (request.getContentLength() < 1) {
			throw new Exception("content message length is < 0 : " + request.getContentLength());
		}
		if (request.getInputStream() == null) {
			throw new Exception("request is null");
		}
		
	}
	
	/**
	 * <pre>
	 * Write httpReqeust log(access.log)
	 * this log can overload
	 * so check out priority value in log4j.xml file
	 * but this method is useful when analyze httpReqeust stream
	 * </pre>
	 * 
	 * @param request
	 */
	private void accessLog(HttpServletRequest request) {
		// session id
		String sessionID = request.getSession().getId();
		if(StringUtil.isEmpty(sessionID)){
			sessionID ="NULLSESSION";
		}
		StringBuffer acess = new StringBuffer();
		acess.append("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		acess.append("\n[" + sessionID + "] " + "REQUEST URI : " + request.getRequestURI());
		acess.append("\n[" + sessionID + "] " + "CONTENT LENGTH : " + request.getContentLength());
		acess.append("\n[" + sessionID + "] " + "LOCAL ADDR : " + request.getLocalAddr());
		acess.append("\n[" + sessionID + "] " + "REMOTE ADDR : " + request.getRemoteAddr());
		acess.append("\n[" + sessionID + "] " + "SERVLET PATH : " + request.getServletPath());
		acess.append("\n[" + sessionID + "] " + "CHARACTER ENCODING : " + request.getCharacterEncoding());
		acess.append("\n[" + sessionID + "] " + "METHOD : " + request.getMethod());
		acess.append("\n[" + sessionID + "] " + "CONTENT TYPE : " + request.getContentType());
		acess.append("\n[" + sessionID + "] " + "CONTEXT PATH : " + request.getContextPath());
		
		// header
		Enumeration<?> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String s = (String) e.nextElement();
			acess.append("\n[" + sessionID + "] " + "HEADER : " + s + " " + request.getHeader(s));
		}
		
		// attribute
		e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			String s = (String) e.nextElement();
			acess.append("\n[" + sessionID + "] " + "ATTRIBUTE : " + s + " " + request.getAttribute(s));
		}
		
		// parameter
		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String s = (String) e.nextElement();
			acess.append("\n[" + sessionID + "] " + "PARAMETER : " + s + " " + request.getParameter(s));
		}
		acess.append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		accesslog.debug(acess.toString());
		//System.out.println(acess.toString());
	}

}
