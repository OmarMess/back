package com.cgi.repository;

import org.springframework.stereotype.Repository;

import com.cgi.model.Candidat;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

}
