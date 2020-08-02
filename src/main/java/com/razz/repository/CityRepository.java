package com.razz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razz.entity.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	List<City> findAllByStateId(Integer stateId);

}
