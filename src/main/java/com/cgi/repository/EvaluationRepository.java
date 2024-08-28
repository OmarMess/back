package com.cgi.repository;

import com.cgi.model.Evaluation;
import com.cgi.model.Profil;
import com.cgi.model.State;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	List<Evaluation> findByEvaluatorEmail(String evaluatorEmail);
	long countByProfil(Profil profil);
	long countByState(State state);
}
