package main.java.sample.server.bytearray;

import java.nio.ByteBuffer;

import org.okeydokey.framework.biz.impl.AbsBiz;
import org.okeydokey.framework.context.IServletContext;
import org.okeydokey.framework.exception.BizException;
import org.okeydokey.framework.utils.BaseUtil;

public class HelloWorldByte extends AbsBiz {

	@Override
	public void before(IServletContext context) throws Exception {
	}

	@Override
	public void execute(IServletContext context) throws Exception {
		try {
			ByteBuffer inMessageByte = context.getReqeustBytebuffer();

			inMessageByte.position(0);
			byte[] tempbyte = new byte[9];
			inMessageByte.get(tempbyte);
			String first = new String(tempbyte, "utf-8");
			bizlog.info("FIRST VALUE : " + first);

			inMessageByte.position(9);

			int second = inMessageByte.getInt();
			bizlog.info("SECOND VALUE : " + second);

			ByteBuffer outMessageByte = ByteBuffer.wrap("HELLOWORLD".getBytes("utf-8"));
			context.setResponseBytebuffer(outMessageByte);

		} catch (Exception e) {
			throw new BizException("ERROR", BaseUtil.getMessage("ERROR"), e);
		}
	}

	@Override
	public void after(IServletContext context) throws Exception {
		//if exception thrown 
		if(context.isBizExceptionThrown()){
			//result setting
			ByteBuffer outMessageByte = ByteBuffer.wrap("NO RESULT".getBytes("utf-8"));
			context.setResponseBytebuffer(outMessageByte);
		}
	}

}
