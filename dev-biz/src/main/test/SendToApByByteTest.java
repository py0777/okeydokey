package main.test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.okeydokey.framework.utils.HexDumpUtil;

public class SendToApByByteTest {

	private static String Url = "http://localhost:8080/was-runtime/HELLOWORLDBYTE.bytearray";

	private byte[] makePostData() throws UnsupportedEncodingException, IOException{
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		DataOutputStream dout = new DataOutputStream(bout);
		// first
		dout.write("OKEYDOKEY".getBytes("utf-8"));
		// second
		dout.writeInt(100);

		dout.flush();
		
		System.out.println(HexDumpUtil.toHexDump(bout.toByteArray()));
		
		return bout.toByteArray();
	}
	
	public  void callServer(String url, int timeoutInSecond) throws Exception {
		
		HttpURLConnection conn = null;
		
		byte [] reqeustByte = makePostData();
		
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "ETC");
			conn.setRequestProperty("Connection", "close");
			conn.setConnectTimeout(timeoutInSecond * 1000);
			conn.setReadTimeout(timeoutInSecond * 1000);
			OutputStream out = conn.getOutputStream();
			out.write(reqeustByte, 0, reqeustByte.length);
			out.flush();

			int result = conn.getResponseCode();
			if (result != 200) {
				throw new IOException("Fail to call. code=[" + result + "].\n" + conn.getResponseMessage());
			}

			ByteArrayOutputStream bout = new ByteArrayOutputStream(Math.max(conn.getContentLength(), 100)); 
			byte[] buffer = new byte[64]; 
			InputStream resStream = conn.getInputStream();
			int readLen = -1;
			while ((readLen = resStream.read(buffer)) != -1) {
				bout.write(buffer, 0, readLen);
			}
			
			System.out.println(new String(bout.toString()));
			
		} finally {
			conn.disconnect();
		}

	}

	public static void main(String args[]) throws Exception {
		SendToApByByteTest tet = new SendToApByByteTest();
		tet.callServer(Url, 30);
	}

}
