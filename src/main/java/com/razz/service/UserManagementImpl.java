package com.razz.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razz.entity.City;
import com.razz.entity.Country;
import com.razz.entity.State;
import com.razz.entity.UserManagementDtls;
import com.razz.pojo.UserAccountDtl;
import com.razz.pojo.UserLoginDtls;
import com.razz.pojo.UserUnlockDtls;
import com.razz.repository.CityRepository;
import com.razz.repository.CountryRepository;
import com.razz.repository.StateRepositry;
import com.razz.repository.UserManagementRepository;
import com.razz.utils.EmailUtility;
import com.razz.utils.SendPasswordThroughEmail;
import com.razz.utils.TempPasswordUtils;

@Service
public class UserManagementImpl implements UserManagementService {

	@Autowired
	CountryRepository countryrepository;

	@Autowired
	StateRepositry staterepository;

	@Autowired
	CityRepository cityrepository;

	@Autowired
	UserManagementRepository usermanagementrepo;

	@Autowired
	EmailUtility emailutility;
	
	@Autowired
	SendPasswordThroughEmail spte;

	@Override
	public UserAccountDtl save(UserAccountDtl user) {
		user.setAccountStatus("locked");
		user.setPassword(TempPasswordUtils.generateTempPassword(6));
		UserManagementDtls entity = new UserManagementDtls();
		BeanUtils.copyProperties(user, entity);
		UserManagementDtls saved = usermanagementrepo.save(entity);
		UserAccountDtl saveduser = new UserAccountDtl();
		BeanUtils.copyProperties(saved, saveduser);
		boolean isEmailSent = false;
		if (saved != null) {
			isEmailSent = emailutility.sendEmail(saveduser);
		}
		if (!isEmailSent) {
			usermanagementrepo.deleteById(saveduser.getId());
			return null;
		}
		return saveduser;

	}

	@Override
	public Map<Integer, String> getAllCountry() {
		Map<Integer, String> countrymap = new LinkedHashMap<>();
		List<Country> countries = countryrepository.findAll();
		countries.forEach(country -> {
			countrymap.put(country.getId(), country.getName());
		});
		return countrymap;

	}

	@Override
	public Map<Integer, String> getStateByCountryId(Integer countryId) {
		Map<Integer, String> statemap = new LinkedHashMap<>();

		List<State> states = staterepository.findAllByCountryId(countryId);
		// System.out.println(states.toString());
		states.forEach(state -> {
			statemap.put(state.getId(), state.getName());
		});

		return statemap;
	}

	@Override
	public Map<Integer, String> getCityByStateId(Integer stateId) {
		Map<Integer, String> citiesmap = new LinkedHashMap<>();
		List<City> cities = cityrepository.findAllByStateId(stateId);
		cities.forEach(city -> {
			citiesmap.put(city.getId(), city.getName());
		});
		return citiesmap;
	}

	@Override
	public Boolean checkDuplicateEmail(String email) {
		List<UserManagementDtls> listofemail = usermanagementrepo.findByEmail(email);
		System.out.println(listofemail);
		return listofemail.isEmpty();
	}

	@Override
	public Boolean unlockAccount(UserUnlockDtls userunlockdetails) {
		List<UserManagementDtls> user = usermanagementrepo.findByEmail(userunlockdetails.getEmail());
		UserManagementDtls saveduser = new UserManagementDtls();
		try {
			saveduser = user.get(0);
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
		if (saveduser.getPassword().equals(userunlockdetails.getTempPassword())) {
			saveduser.setPassword(userunlockdetails.getNewPassword());
			saveduser.setAccountStatus("UNLOCKED");
			UserManagementDtls usermanagementdetails = new UserManagementDtls();
			BeanUtils.copyProperties(saveduser, usermanagementdetails);
			UserManagementDtls accountupdated = usermanagementrepo.save(usermanagementdetails);
			return accountupdated != null;
		}

		return false;

	}

	@Override
	public UserLoginDtls login(UserLoginDtls userlogindtls) {
		List<UserManagementDtls> userdetail = usermanagementrepo.findByEmail(userlogindtls.getEmail());
		if (userdetail != null) {
			UserManagementDtls userManagementDtls = userdetail.get(0);
			if (userManagementDtls.getPassword().equals(userlogindtls.getPassword())
					&& userManagementDtls.getAccountStatus().equals("UNLOCKED")) {

				return userlogindtls;
			}
			return null;
		} else {
			return null;
		}

	}

	@Override
	public Boolean forgetPassword(String email) {
		List<UserManagementDtls> savedemail = usermanagementrepo.findByEmail(email);
		if (savedemail != null) {
			UserManagementDtls userdetails = savedemail.get(0);
			UserAccountDtl saveduser = new UserAccountDtl();
			BeanUtils.copyProperties(userdetails, saveduser);
			return spte.sendEmail(saveduser);
		}
		return false;
	}

}
