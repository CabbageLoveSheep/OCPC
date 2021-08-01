package com.violet.ocpc.web.common.dto;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * 响应DTO基类
 * 
 * @author huangfei
 */
public class BaseResponseDto implements Serializable
{
    private static final long serialVersionUID = 1631871063268967977L;
    protected static final String SUCCESS_RET_CODE = "000000";
    private static final String SUCCESS_RET_MSG = "SUCCESS";
    private static final String FAIL_RET_CODE = "999999";
    private static final String FAIL_RET_MSG = "SYSTEM ERROR";

    public static final BaseResponseDto SYSTEM_ERROR = new BaseResponseDto(FAIL_RET_CODE, FAIL_RET_MSG);
    public static final BaseResponseDto SUCCESS = new BaseResponseDto(SUCCESS_RET_CODE, SUCCESS_RET_MSG);

    /**
     * 响应代码 retCode = 999999, 表示系统异常 retCode = 000000, 正常情况下表示成功
     */
    private String retCode;

    /** 响应信息 */
    private String retMsg;
    
//    private JSONObject result;


    public BaseResponseDto()
    {
        this.retCode = SUCCESS_RET_CODE;
        this.retMsg = SUCCESS_RET_MSG;
    }


    public BaseResponseDto(String retCode, String retMsg)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }


    public String getRetCode()
    {
        return retCode;
    }


    public void setRetCode(String retCode)
    {
        this.retCode = retCode;
    }


    public String getRetMsg()
    {
        return retMsg;
    }


    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }


    @Override
    public String toString()
    {
        try
        {
            return BeanUtils.describe(this).toString();
        }
        catch (Exception exception)
        {
            return exception.getMessage();
        }
    }


    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }


    /**
     * 
     * 将对象中的所有 String类型的 属性值进行trim()处理
     * 
     */
    public void trimAllString() throws Exception
    {
        Method[] methods = this.getClass().getMethods();
        if (methods != null && methods.length > 0)
        {
            for (int i = 0; i < methods.length; i++)
            {
                Method method = methods[i];
                if (method.getName().startsWith("get"))
                {
                    Object resultObj = method.invoke(this, new Object[] {});
                    if (resultObj != null && resultObj instanceof String)
                    {
                        String resultStr = (String) resultObj;
                        String setMethodName = "set" + method.getName().substring(3, method.getName().length());

                        try
                        {
                            Method setMethod = this.getClass().getMethod(setMethodName, new Class[] { String.class });
                            setMethod.invoke(this, new Object[] { resultStr.trim() });
                        }
                        catch (NoSuchMethodException e)
                        {
                            // there is not setXXX method
                        }
                    }
                }
            }
        }
    }


    /**
     * 
     * 将所有值为 "" 的属性的值置为 null;
     * 
     */
    public void setAllEmptyStringToNull() throws Exception
    {
        Method[] methods = this.getClass().getMethods();
        if (methods != null && methods.length > 0)
        {
            for (int i = 0; i < methods.length; i++)
            {
                Method method = methods[i];
                if (method.getName().startsWith("get"))
                {
                    Object resultObj = method.invoke(this, new Object[] {});
                    if (resultObj instanceof String)
                    {
                        String result = (String) resultObj;
                        if ("".equals(result.trim()))
                        {
                            String setMethodName = "set" + method.getName().substring(3, method.getName().length());
                            try
                            {
                                Method setMethod = this.getClass().getMethod(setMethodName,
                                        new Class[] { String.class });
                                setMethod.invoke(this, new Object[] { null });
                            }
                            catch (NoSuchMethodException e)
                            {
                                // there is not setXXX method
                            }
                        }
                    }
                }
            }
        }
    }


    public boolean isSuccess()
    {
        return SUCCESS_RET_CODE.equals(this.retCode);
    }


//	public JSONObject getResult() {
//		return result;
//	}
//
//
//	public void setResult(JSONObject result) {
//		this.result = result;
//	}

}
