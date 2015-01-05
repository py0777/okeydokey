package org.okeydokey.framework.helper;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.okeydokey.framework.utils.BaseUtil;

/**
 * <pre>
 * make unique servlet id 
 * </pre>
 * 
 * @author johunsang@gmail.com
 * @version 1.0
 * @since 2015.01.01
 */
public class ServletIdHelper {
	
	/**
	 * single ServletIdHelper instance
	 */
	private static ServletIdHelper instance = null;
	
	/**
	 * delimeter
	 */
	private static String delimeter ="_";
	
	/**
	 * return ServletIdHelper instance
	 * 
	 * @return single ServletIdHelper
	 */
	public static ServletIdHelper getInstance(){
		if(instance == null){
			instance = new ServletIdHelper(99999, 99);
		}
		return instance;
	}
	
    /**
     * sequence max
     */
    private int servletSequenceMax = 99999;
    
    /**
     * sequence value
     */
    private int servletSequence = 0;

    public ServletIdHelper(int servletSequenceMax, int asyncSequenceMax){
        this.servletSequenceMax = servletSequenceMax;
    }
    
    public int getServletSequenceMax() {
        return this.servletSequenceMax;
    }

    public void setGlobalSequenceMax(int servletSequenceMax) {
        this.servletSequenceMax = servletSequenceMax;
    }


    synchronized private final int getServletSequence() {
        if(++servletSequence > servletSequenceMax){
            servletSequence = 0;
        }
        return servletSequence;
    }
    
    
    /**
     * make unique id
     * 
     * hostname_sessionId_bizid_yyyyMMdd_HHmmssSSS_sequence
     * 
     * @param id -> bizid
     * @param sessionId -> httpRequest session id
     * @return unique string id
     * @throws UnknownHostException
     */
    public String newServletId(String id, String sessionId) throws UnknownHostException {
    	String hostname = BaseUtil.getHostName();
    	if(hostname.length() > 13){
    		hostname =  hostname.substring(0, 13);
    	}
        int seq = getServletSequence();
        String yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder buff = new StringBuilder(50);
        buff.append(hostname);
        buff.append(delimeter);
        buff.append(sessionId);
        buff.append(delimeter);
        buff.append(id);
        buff.append(delimeter);
        buff.append(yyyyMMddHHmmssSSS.substring(0, 8)); 
        buff.append(delimeter);
        buff.append(yyyyMMddHHmmssSSS.substring(8));
        buff.append(delimeter);
        buff.append(lpadByte(String.valueOf(seq), '0', 5));
        return buff.toString();
    }
    
    public String lpadByte(String src,char padChar, int len) {
        byte[] bb = src==null ? new byte[0] : src.getBytes();
        
        if (bb.length >= len) {
            return new String(bb, 0, len); 
        }

        byte[] pad = new byte[len];
        int padLen = len - bb.length;
        System.arraycopy(bb, 0, pad, padLen, bb.length);

        for (int i=0; i<padLen; i++) {
            pad[i] = (byte)padChar;
        }
        return new String(pad);
    }

}
