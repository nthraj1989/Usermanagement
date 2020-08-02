package com.razz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.razz.entity.UserManagementDtls;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagementDtls, Long> {
	
	List<UserManagementDtls> findByEmail(String email);

}
