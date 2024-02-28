package com.cgi.model;

import java.sql.Blob;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	private String prenom;
	private SituationFam situationFam;
	private String nationalite;
	private String cin;
	private String numTel;
	private String adresse;
	private String email;
	private String experience;
	private float nbrAneeExp;
	private String comp_manag;
	private Niveau_Langue niv_franc;
	private Niveau_Langue niv_ang;
	@Lob
	private Blob cv;
	private Profil profil;
	@OneToMany
	private List<CompetenceTechnique> competencesTech;s
}
