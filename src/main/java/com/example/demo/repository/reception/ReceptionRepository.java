package com.example.demo.repository.reception;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reception;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception,Long> {
	
	List<Reception> findByNameLike(String name);
	
}
