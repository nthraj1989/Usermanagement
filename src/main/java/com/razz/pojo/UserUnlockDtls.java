package com.razz.pojo;

import lombok.Data;

@Data
public class UserUnlockDtls {

	private String email;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
}
