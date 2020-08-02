package com.razz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.razz.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
