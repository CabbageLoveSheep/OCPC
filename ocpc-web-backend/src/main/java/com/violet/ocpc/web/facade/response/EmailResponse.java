package com.violet.ocpc.web.facade.response;

import com.violet.ocpc.web.common.dto.BaseResponseDto;

public class EmailResponse extends BaseResponseDto {
	private String isExist;

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
	
}
