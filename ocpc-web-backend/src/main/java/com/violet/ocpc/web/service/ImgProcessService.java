package com.violet.ocpc.web.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author HYJ
 *
 */
public interface ImgProcessService {
	Map<Integer, List<BigDecimal>> grayImage(int status, String imgSourcePath, String outPath) 
			throws Exception;
	
	int grayImageNoRtn(int status, String imgSourcePath, String outPath) throws Exception;
}
