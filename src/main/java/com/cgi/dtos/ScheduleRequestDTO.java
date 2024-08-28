package com.cgi.dtos;

import java.sql.Date;

public class ScheduleRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private Long evaluatorId;
    private Date date;
    private String meetinglink;

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(Long evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public String getMeetinglink() {
		return meetinglink;
	}

	public void setMeetinglink(String meetinglink) {
		this.meetinglink = meetinglink;
	}
}
