package org.okeydokey.framework.servlet.stream.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.config.ConfigConstants;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;
import org.okeydokey.framework.utils.BaseUtil;
import org.okeydokey.framework.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Read ascii from http reqeust stream
 * Write ascii to http response stream
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class AsciiStream implements IStreamReader, IStreamWriter {

	/**
	 * biz.log
	 */
	private static Logger log = LoggerFactory.getLogger("biz");

	/**
	 * default reading, writing buffer size
	 */
	private static final int defalutBufferSize = 1024 * 4;

	/**
	 * default charset
	 */
	private static final String defalutCharSet = "utf-8";

	/**
	 * single AsciiStream instance
	 */
	private static AsciiStream instance = null;

	/**
	 * return single AsciiStream
	 * 
	 * @return ASCIIStream
	 */
	public synchronized static AsciiStream getInstance() {
		if (instance == null) {
			instance = new AsciiStream();
		}

		return instance;
	}

	@Override
	public void read(IServletContext context) {
		// dataformat in context
		String dataformat = context.getDataFormat();
		// ascii charset
		String charSet = getCharSetByFomrat(dataformat);
		// buffer size
		int iBsize = getBsizeByFomrat(dataformat);
		try {
			//set request string to context
			context.setRequestASCIIString(readHttpServletRequestData(context.getRequest(), charSet, iBsize));
			log.info("![INPUT STRING]![" + context.getServletId() + "]![" + context.getRequestASCIIString() + "]");
		} catch (IOException e) {
			log.error("Reading stream error : " + BaseUtil.toExceptionString(e));
			throw new RuntimeException("Converting stream error ", e);
		} 
	}

	@Override
	public void write(IServletContext context) {
		// dataformat in context
		String dataformat = context.getDataFormat();
		// ascii charset
		String charSet = getCharSetByFomrat(dataformat);
		// buffer size
		int iBsize = getBsizeByFomrat(dataformat);
		String responseString = null;
		try {
			//from biz
			responseString = context.getResponseASCIIString();
			if (StringUtil.isEmpty(responseString)) {
				throw new RuntimeException("Response String is empty");
			}
			//write to response stream
			writeHttpServletResponseData(context.getResponse(), responseString.getBytes(charSet), iBsize);
			log.info("![OUTPUT STRING]![" + context.getServletId() + "]![" + responseString + "]");
		} catch (IOException e) {
			log.error("Writing stream error : " + BaseUtil.toExceptionString(e));
			throw new RuntimeException("Writing stream error ", e);
		}
	}

	/**
	 * get string value from request http request stream.
	 * 
	 * @param request -> HttpServletRequest
	 * @param iBsize -> buffer size
	 * @return charSet -> charset
	 * @throws IOException
	 */
	private String readHttpServletRequestData(HttpServletRequest request, String charSet, int iBsize) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), charSet), iBsize);
			String line = null;
			StringBuffer reqeustStringBuffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				reqeustStringBuffer.append(line);
			}
			if (null == reqeustStringBuffer || reqeustStringBuffer.length() == 0) {
				throw new RuntimeException("Request String is empty");
			}
			return reqeustStringBuffer.toString();
		} catch (IOException e) {
			log.error("Reading stream error : " + BaseUtil.toExceptionString(e));
			throw new RuntimeException("Converting stream error ", e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * write to http response stream.
	 * 
	 * @param response -> HttpServletResponse
	 * @param responseByteArray -> byte array from biz 
	 * @param iBsize -> buffer size
	 * @throws IOException
	 */
	private void writeHttpServletResponseData(HttpServletResponse response, byte[] responseByteArray, int iBsize) throws IOException {
		if (responseByteArray != null) {
			BufferedOutputStream byteOutput = null;
			try {
				byteOutput = new BufferedOutputStream(response.getOutputStream(), iBsize);
				if (responseByteArray != null) {
					//set content size 
					response.setContentLength(responseByteArray.length);
					byteOutput.write(responseByteArray);
				}
				byteOutput.flush();
			} catch (Exception e) {
				throw new RuntimeException("Converting stream error ", e);
			} finally {
				try {
					if (byteOutput != null)
						byteOutput.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * get charset by dataformat
	 * 
	 * @param dataformat -> bytearray,json,xml,flat,soap....
	 * @return String -> charSet
	 */
	private String getCharSetByFomrat(String dataformat) {
		String charSet = defalutCharSet;
		if (StringUtil.equals(ConfigConstants.DATA_FORMAT_JSON, dataformat)) {
			charSet = BaseUtil.getOkeydokey("okeydokey.json.charSet");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_XML, dataformat)) {
			charSet = BaseUtil.getOkeydokey("okeydokey.xml.charSet");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_FLAT, dataformat)) {
			charSet = BaseUtil.getOkeydokey("okeydokey.flat.charSet");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_SOAP, dataformat)) {
			charSet = BaseUtil.getOkeydokey("okeydokey.soap.charSet");
		}
		return charSet;
	}

	/**
	 * get buffer size by dataformat
	 * 
	 * @param dataformat-> bytearray,json,xml,flat,soap....
	 * @return int -> buffer size
	 */
	private int getBsizeByFomrat(String dataformat) {
		int bsize = defalutBufferSize;
		if (StringUtil.equals(ConfigConstants.DATA_FORMAT_JSON, dataformat)) {
			bsize = BaseUtil.getIntOkeydokey("okeydokey.json.buffer.size");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_XML, dataformat)) {
			bsize = BaseUtil.getIntOkeydokey("okeydokey.xml.buffer.size");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_FLAT, dataformat)) {
			bsize = BaseUtil.getIntOkeydokey("okeydokey.flat.buffer.size");
		} else if (StringUtil.equals(ConfigConstants.DATA_FORMAT_SOAP, dataformat)) {
			bsize = BaseUtil.getIntOkeydokey("okeydokey.soap.buffer.size");
		}
		return bsize;
	}
}
