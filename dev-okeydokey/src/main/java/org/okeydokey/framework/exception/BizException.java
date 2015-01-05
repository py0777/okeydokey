package org.okeydokey.framework.exception;

public class BizException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7108200963340172841L;

	public BizException(String messageId) {
        super(messageId);
    }

    public BizException(String messageId, String messageName) {
        super(messageId, messageName);
    }

    public BizException(String messageId, Throwable throwable) {
        super(messageId, throwable);
    }

    public BizException(String messageId, String messageName, Throwable throwable) {
        super(messageId, messageName, throwable);
    }
}
