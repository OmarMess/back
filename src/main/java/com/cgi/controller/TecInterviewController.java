package com.cgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.Service.TecInterviewService;
import com.cgi.model.EntretienTechnique;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/interview/tecinterview")
public class TecInterviewController {
    
    @Autowired
    private TecInterviewService tecInterviewService;

    @GetMapping("/status/{status}")
    public List<EntretienTechnique> findTecInterviewByStatus(@PathVariable String status) {
        return tecInterviewService.findTecInterviewByStatus(status);
    }
}
