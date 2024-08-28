package com.cgi.controller;

import com.cgi.model.Evaluation;
import com.cgi.model.Profil;
import com.cgi.model.State;
import com.cgi.repository.EvaluationRepository;

import net.sf.jasperreports.engine.JRException;

import com.cgi.Service.EvaluationService;
import com.cgi.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;
    
    @Autowired
    private EvaluationRepository evaluationRepository;
    
    @Autowired
    private ReportService reportService;


    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
        return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{scheduleId}")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation, @PathVariable Long scheduleId) {
        return evaluationService.saveEvaluation(evaluation, scheduleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id,@PathVariable Long scheduleId, @RequestBody Evaluation evaluation) {
        if (evaluationService.getEvaluationById(id).isPresent()) {
            evaluation.setId(id);
            return ResponseEntity.ok(evaluationService.saveEvaluation(evaluation,scheduleId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        if (evaluationService.getEvaluationById(id).isPresent()) {
            evaluationService.deleteEvaluation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @GetMapping("/evaluator/{email}")
    public List<Evaluation> getEvaluationsByEvaluatorEmail(@PathVariable String email) {
        return evaluationService.getEvaluationsByEvaluatorEmail(email);
    }
    
    
    @GetMapping("/{evaluationId}/report")
    public ResponseEntity<byte[]> getEvaluationReport(@PathVariable Long evaluationId) {
        try {
            byte[] reportBytes = reportService.generateEvaluationReport(evaluationId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "evaluation_report.pdf");
            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
        } catch (JRException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/count")
    public Map<String, Long> getProfileCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("FULLSTACK", evaluationService.countEvaluationsByProfil(Profil.FULLSTACK));
        counts.put("BACKEND", evaluationService.countEvaluationsByProfil(Profil.BACKEND));
        counts.put("FRONTEND", evaluationService.countEvaluationsByProfil(Profil.FRONTEND));
        counts.put("JAVA", evaluationService.countEvaluationsByProfil(Profil.JAVA));
        counts.put("PHP", evaluationService.countEvaluationsByProfil(Profil.PHP));
        return counts;
    }
    
    @GetMapping("/state-count")
    public Map<String, Long> getStateCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("SUCCESS", evaluationService.countEvaluationsByState(State.SUCCESS));
        counts.put("FAILED", evaluationService.countEvaluationsByState(State.FAILED));
        return counts;
    }
}
