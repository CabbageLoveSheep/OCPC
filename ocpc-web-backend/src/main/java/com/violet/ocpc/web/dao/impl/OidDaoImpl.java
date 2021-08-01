package com.violet.ocpc.web.dao.impl;

import java.math.BigDecimal;

import com.violet.ocpc.web.dao.OidDao;
import com.violet.ocpc.web.dao.mapper.OidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HYJ
 *
 */
@Service("OidDao")
public class OidDaoImpl implements OidDao {

	@Autowired
	private OidMapper oidMapper;
	
	@Override
	public BigDecimal getOid() {
		return oidMapper.getOid();
	}

}
