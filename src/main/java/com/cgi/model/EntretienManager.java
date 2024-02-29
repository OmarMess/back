package com.cgi.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class EntretienManager {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private LocalDate date_entretien_manag ;
		private LocalTime  heure_entretien_manag;
		private String lien_reunion;
		private String motif;
		private String motivation;
		private double salaire_actuel;
		private double pretention_salariale;
		private LocalDate duree_preavis;
		private String ville_preferee;
		private String points_forts;
		private String points_faibles;
		@Lob
		private Blob rapport_manager;
		private Statut statut_entretien;
		private Avis avis_manager;
		private String justif_avis;
		@ManyToOne
		private Candidat candidat; 
		@ManyToOne
		private UserApp manager;
}
