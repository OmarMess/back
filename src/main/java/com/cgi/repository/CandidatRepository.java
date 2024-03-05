package com.cgi.repository;

import org.springframework.stereotype.Repository;

import com.cgi.model.Candidat;
import com.cgi.model.Profil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	
	 List<Candidat> findByProfil(Profil profil);

}
