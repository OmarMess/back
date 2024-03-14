package com.cgi.repository;

import org.springframework.stereotype.Repository;
import com.cgi.model.EntretienTechnique;
import com.cgi.model.Statut;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EntretienTechniqueRepository extends JpaRepository<EntretienTechnique, Long>{
    List<EntretienTechnique> findBystatutEntretien(Statut statutEntretien);
}