package com.violet.ocpc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.violet.ocpc.web.facade.request.BaseRequest;

/**
 * TODO To describe the functionality of this method
 *
 * @author HYJ
 */
public class BaseController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    
    public static String SUCCESS = "000000";
    public static String EMPTY_PARAM = "000001";
    public static String SUCCESS_MSG = "success";
    public static String QUERY_ERROR = "200005";
    public static String SAVE_ERROR = "200006";
    public static String Error = "999999";
    public static String VALIDATE_ERROR = "200001";
    public static String Error_MSG = "出错了！请稍后重试！";
    
    public void printRequest(BaseRequest baseReq) throws Exception
    {
    	LOGGER.info(JSONObject.toJSONString(baseReq));
    }

}
