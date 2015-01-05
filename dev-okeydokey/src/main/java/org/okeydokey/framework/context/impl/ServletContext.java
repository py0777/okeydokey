package org.okeydokey.framework.context.impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.helper.ServletIdHelper;

public class ServletContext extends AbsServletContext {

	public ServletContext(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super();
		
		// Set httpRequest, httpResponse in ServletContext
		this.request = request;
		this.response = response;

		// Get bizId and dataformat
		obtainBizidDataformat();
		
		// Set Servlet Id
		this.servletId = ServletIdHelper.getInstance().newServletId(this.bizId, request.getSession().getId());

	}

	private void obtainBizidDataformat() throws Exception {
		String pathInfo = this.request.getServletPath();
		final int delimIndex = pathInfo.indexOf(".");
		if (delimIndex != -1) {
			this.bizId = pathInfo.substring(1, delimIndex);
			this.dataFormat = pathInfo.substring(delimIndex + 1);
		} else {
			throw new ServletException("Servlet mapping Error!! Can't obtain bizid and dataFormat");
		}
	}


}
