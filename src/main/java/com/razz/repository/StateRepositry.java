package com.razz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razz.entity.State;

public interface StateRepositry extends JpaRepository<State, Integer> {

	List<State> findAllByCountryId(Integer countryId);

}
