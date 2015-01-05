package org.okeydokey.framework.servlet.stream.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.servlet.stream.IStreamReader;
import org.okeydokey.framework.servlet.stream.IStreamWriter;
import org.okeydokey.framework.utils.BaseUtil;
import org.okeydokey.framework.utils.HexDumpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Read bytearray from http reqeust stream
 * Write bytearray to http response stream
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class ByteStream implements IStreamReader, IStreamWriter {

	/**
	 * biz.log
	 */
	private static Logger log = LoggerFactory.getLogger("biz");

	/**
	 * single ByteStream instance
	 */
	private static ByteStream instance = null;

	/**
	 * return single ByteStream
	 * 
	 * @return ByteStream
	 */
	public synchronized static ByteStream getInstance() {
		if (instance == null) {
			instance = new ByteStream();
		}

		return instance;
	}

	@Override
	public void read(IServletContext context) {
		// buffer size
		int iBsize = BaseUtil.getIntOkeydokey("okeydokey.byte.buffer.size");
		try {
			// read byte buffer from http reqeust stream
			ByteBuffer requstByteBuffer = readHttpServletRequestData(context.getRequest(), iBsize);
			if (null == requstByteBuffer || requstByteBuffer.capacity() == 0) {
				throw new RuntimeException("Request bytebuffer is empty");
			}
			// set byte buffer to context
			context.setReqeustBytebuffer(requstByteBuffer);
			log.info("![INPUT BYTE]![" + context.getServletId() + "]![" + HexDumpUtil.toHexDump(context.getReqeustBytebuffer().array()) + "]");
		} catch (IOException e) {
			log.error("Reading stream error : " + BaseUtil.toExceptionString(e));
			throw new RuntimeException("Stream Converting error ", e);
		}
	}

	@Override
	public void write(IServletContext context) {
		// buffer size
		int iBsize = BaseUtil.getIntOkeydokey("okeydokey.byte.buffer.size");
		try {
			// get response byte buffer from context
			ByteBuffer resopnseByteBuffer = context.getResponseBytebuffer();
			if (null == resopnseByteBuffer || resopnseByteBuffer.capacity() == 0) {
				throw new RuntimeException("Response bytebuffer is empty");
			}
			// write byte array to response http stream
			writeHttpServletResponseData(context.getResponse(), resopnseByteBuffer.array(), iBsize);
			log.info("![OUTPUT BYTE]![" + context.getServletId() + "]![" + HexDumpUtil.toHexDump(resopnseByteBuffer.array()) + "]");
		} catch (IOException e) {
			log.error("Writing Stream error : " + BaseUtil.toExceptionString(e));
			throw new RuntimeException("Stream Writing error ", e);
		}
	}

	/**
	 * write byte array to http response stream
	 * 
	 * @param response -> HttpServletResponse
	 * @param responseByteArray -> response byte array
	 * @param iBsize -> buffer size
	 * @throws IOException
	 */
	private void writeHttpServletResponseData(HttpServletResponse response, byte[] responseByteArray, int iBsize) throws IOException {
		if (responseByteArray != null) {
			BufferedOutputStream bufferOutput = null;
			try {
				bufferOutput = new BufferedOutputStream(response.getOutputStream(), iBsize);
				if (responseByteArray != null) {
					response.setContentLength(responseByteArray.length);
					bufferOutput.write(responseByteArray);
				}
				bufferOutput.flush();
			} catch (Exception e) {
				throw new RuntimeException("Converting stream error ", e);
			} finally {
				try {
					if (bufferOutput != null)
						bufferOutput.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * read ByteBuffer from http request stream
	 * 
	 * @param request -> HttpServletRequest
	 * @param iBsize -> buffer size
	 * @return ByteBuffer -> request ByteBuffer
	 * @throws IOException
	 */
	private ByteBuffer readHttpServletRequestData(HttpServletRequest request, int iBsize) throws IOException {
		InputStream input = null;
		ByteArrayOutputStream byteOutput = null;
		try {
			input = request.getInputStream();
			byte[] buffer = new byte[iBsize];
			byteOutput = new ByteArrayOutputStream(iBsize);
			int read;
			while (true) {
				read = input.read(buffer);
				if (read == -1)
					break;
				byteOutput.write(buffer, 0, read);
			}
			return ByteBuffer.wrap(byteOutput.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Converting stream error ", e);
		} finally {
			try {
				if (byteOutput != null)
					byteOutput.close();
			} catch (Exception e) {
			}
			try {
				if (input != null)
					input.close();
			} catch (Exception e) {
			}
		}

	}

}
