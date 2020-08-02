package com.razz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "user_account", uniqueConstraints = @UniqueConstraint(columnNames = { "user_email", "phone_number" }))
public class UserManagementDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "user_email")
	private String email;

	@Column(name = "phone_number")
	private String PhoneNo;

	private Date DOB;

	private String sex;

	@Column(name = "country_id")
	private String countryId;

	@Column(name = "state_id")
	private String stateId;

	@Column(name = "city_id")
	private String cityId;

	@Column(name = "user_pwd")
	private String password;

	@Column(name = "acc_status")
	private String accountStatus;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="created_date", updatable=false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="updated_date",insertable=false )
	private Date updatedDate;
	

}
