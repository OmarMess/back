package com.cgi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.Service.CandidatService;
import com.cgi.model.Candidat;
import com.cgi.model.Profil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/interview/candidat")
public class CandidatController {
	
	@Autowired
    private CandidatService candidatService;

    @GetMapping("/profil/{profil}")
    public List<Candidat> findCandidatesByProfile(@PathVariable String profil) {
        return candidatService.findCandidatesByProfile(profil);
    }

}
