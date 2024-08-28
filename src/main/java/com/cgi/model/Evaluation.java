package com.cgi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String candidateNom;
    private String candidatePrenom;
    private String candidateEmail;

    private String evaluatorNom;
    private String evaluatorPrenom;
    private String evaluatorEmail;

    @Enumerated(EnumType.STRING)
    private NiveauEtudes niveauEtudes;

    private String annee;
    private String intitule;
    private String specialite;
    private String experience;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetenceTechnique> competences = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Profil profil;

    @Enumerated(EnumType.STRING)
    private Avis avis;

    private String notes;

    @Enumerated(EnumType.STRING)
    private State state;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateNom() {
		return candidateNom;
	}

	public void setCandidateNom(String candidateNom) {
		this.candidateNom = candidateNom;
	}

	public String getCandidatePrenom() {
		return candidatePrenom;
	}

	public void setCandidatePrenom(String candidatePrenom) {
		this.candidatePrenom = candidatePrenom;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getEvaluatorNom() {
		return evaluatorNom;
	}

	public void setEvaluatorNom(String evaluatorNom) {
		this.evaluatorNom = evaluatorNom;
	}

	public String getEvaluatorPrenom() {
		return evaluatorPrenom;
	}

	public void setEvaluatorPrenom(String evaluatorPrenom) {
		this.evaluatorPrenom = evaluatorPrenom;
	}

	public String getEvaluatorEmail() {
		return evaluatorEmail;
	}

	public void setEvaluatorEmail(String evaluatorEmail) {
		this.evaluatorEmail = evaluatorEmail;
	}

	public NiveauEtudes getNiveauEtudes() {
		return niveauEtudes;
	}

	public void setNiveauEtudes(NiveauEtudes niveauEtudes) {
		this.niveauEtudes = niveauEtudes;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public List<CompetenceTechnique> getCompetences() {
		return competences;
	}

	public void setCompetences(List<CompetenceTechnique> competences) {
		this.competences = competences;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Avis getAvis() {
		return avis;
	}

	public void setAvis(Avis avis) {
		this.avis = avis;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
