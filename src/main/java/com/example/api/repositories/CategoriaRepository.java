package com.example.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Query(value = "SELECT * FROM CATEGORIA WHERE NOME LIKE %:name%", nativeQuery = true)
	List<Categoria> findByNames(String name);

}
