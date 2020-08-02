package com.razz.service;

import java.util.Map;

import com.razz.pojo.UserAccountDtl;
import com.razz.pojo.UserLoginDtls;
import com.razz.pojo.UserUnlockDtls;

public interface UserManagementService {

	UserAccountDtl save(UserAccountDtl user);
	
	Boolean checkDuplicateEmail(String emailId);

	Map<Integer, String> getAllCountry();

	Map<Integer, String> getStateByCountryId(Integer id);

	Map<Integer, String> getCityByStateId(Integer id);
	
	Boolean unlockAccount(UserUnlockDtls userunlockdetails);
	
	UserLoginDtls login(UserLoginDtls Userlogindtls);
	
	Boolean forgetPassword(String email);
}
