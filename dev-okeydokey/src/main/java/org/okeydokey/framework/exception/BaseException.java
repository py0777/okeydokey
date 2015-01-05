package org.okeydokey.framework.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8527438702375746578L;

	protected String messageId = null;

	protected String messageName = null;

	public BaseException(String messageId) {
		this(messageId, null, null);
	}

	public BaseException(String messageId, String messageName) {
		this(messageId, messageName, null);
	}

	public BaseException(String messageId, Throwable throwable) {
		this(messageId, null, throwable);
	}

	public BaseException(String messageId, String messageName,Throwable throwable) {
		super(messageId, throwable);
		this.messageId = messageId;
		this.messageName = messageName;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public Throwable getThrowable() {
		return super.getCause();
	}

}
