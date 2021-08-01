package com.violet.ocpc.web.common.exception;

/**
 * PlbizRest客户端异常。
 */
public class ApiException extends Exception
{

    private static final long serialVersionUID = 7443311314032180421L;
    private String errMsg;


    public ApiException()
    {
        super();
    }


    public ApiException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public ApiException(String message)
    {
        super(message);
    }


    public ApiException(Throwable cause)
    {
        super(cause);
    }


    public ApiException(String errCode, String errMsg)
    {
        super(errCode + ":" + errMsg);
        this.errMsg = errMsg;
    }


    public String getErrMsg()
    {
        return this.errMsg;
    }

}

