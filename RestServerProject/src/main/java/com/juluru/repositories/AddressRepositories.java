package com.juluru.repositories;

import java.util.List;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.juluru.entities.Address;

public interface AddressRepositories extends JpaRepository<Address, Integer>{
	
	
	
	List<Address> findTop3ByState(String state);

	
}
