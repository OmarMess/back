package com.cgi.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntretienTechnique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private LocalDate date_entretien_tec ;
	private LocalTime  heure_entretien_tec;
	private String lien_reunion;
	private String motivation;
	private String commentaire ;
	private String conclusion_quest;
	@Enumerated(EnumType.STRING)
	private Avis avis_tec;
	private String justif_avis;
	@Lob
	private Blob rapportTech;
	@Enumerated(EnumType.STRING)
	private Statut statutEntretien;
	@ManyToOne
	private Candidat candidat; 
	@ManyToMany
	private List<UserApp> evaluateurs;

}
