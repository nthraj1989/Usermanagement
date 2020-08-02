package com.razz.pojo;

import java.util.Date;

import lombok.Data;

@Data
public class UserAccountDtl {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String PhoneNo;

	private Date DOB;

	private String sex;

	private String countryId;

	private String stateId;

	private String cityId;

	private String password;

	private String accountStatus;

}
