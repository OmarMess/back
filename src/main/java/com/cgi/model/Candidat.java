package com.cgi.model;

import java.sql.Blob;
import java.util.List;
import com.cgi.model.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	private Niveau_Langue niv_franc;
	@Enumerated(EnumType.STRING)
	private Niveau_Langue niv_ang;
	@Lob
	private Blob cv;
	@Enumerated(EnumType.STRING)
	private Profil profil;
	@OneToMany
	private List<CompetenceTechnique> competencesTech;
	@OneToMany
	private List<Formation> formations;
	@OneToMany
	private List<Certificat> certificats;
	@OneToMany
	private List<EntretienManager> entretiens_manag;
	@OneToMany
	private List<EntretienTechnique> entretiens_tec;
}
