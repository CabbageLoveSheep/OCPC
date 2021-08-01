package com.violet.ocpc.web.facade.response;

import com.violet.ocpc.web.common.dto.BaseResponseDto;
import com.violet.ocpc.web.facade.response.vo.UserVO;

public class LoginResponse extends BaseResponseDto {
	
	private static final long serialVersionUID = -7563889102208477027L;

	private String valid;
	
	private String loginId;
	
	private String userOid;
	
	private UserVO userProfile;

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserOid() {
		return userOid;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public UserVO getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserVO userProfile) {
		this.userProfile = userProfile;
	}
	
}