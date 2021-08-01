package com.violet.ocpc.web.service.impl;

import java.math.BigDecimal;

import com.violet.ocpc.web.dao.OidDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.violet.ocpc.web.service.OidService;

/**
 * @author HYJ
 *
 */
@Service("OidService")
public class OidServiceImpl implements OidService
{
	@Autowired
    OidDao oidDao;
	
	@Override
	public BigDecimal getOid() throws Exception
	{
		return oidDao.getOid();
	}

}
