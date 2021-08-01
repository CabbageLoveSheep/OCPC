package com.violet.ocpc.web.common.exception;

public class EmailVerifyCodeException extends Exception {

	private static final long serialVersionUID = 6111282581392077973L;

	private String errMsg;


    public EmailVerifyCodeException()
    {
        super();
    }


    public EmailVerifyCodeException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public EmailVerifyCodeException(String message)
    {
        super(message);
    }


    public EmailVerifyCodeException(Throwable cause)
    {
        super(cause);
    }


    public EmailVerifyCodeException(String errCode, String errMsg)
    {
        super(errCode + ":" + errMsg);
        this.errMsg = errMsg;
    }


    public String getErrMsg()
    {
        return this.errMsg;
    }
}
