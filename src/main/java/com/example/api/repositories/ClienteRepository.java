package com.example.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query(value = "SELECT * FROM CLIENTE WHERE NOME LIKE %:name%", nativeQuery = true)
	List<Cliente> findByName(String name);
	
}
