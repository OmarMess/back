package com.cgi.Service;

import com.cgi.model.CompetenceTechnique;
import com.cgi.model.Evaluation;
import com.cgi.model.Niveau;
import com.cgi.repository.EvaluationRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public byte[] generateEvaluationReport(Long evaluationId) throws JRException, IOException {

        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        InputStream reportStream = new ClassPathResource("reports/evaluation_report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("candidateNom", evaluation.getCandidateNom());
        parameters.put("candidatePrenom", evaluation.getCandidatePrenom());
        parameters.put("candidateEmail", evaluation.getCandidateEmail());
        parameters.put("evaluatorNom", evaluation.getEvaluatorNom());
        parameters.put("evaluatorPrenom", evaluation.getEvaluatorPrenom());
        parameters.put("evaluatorEmail", evaluation.getEvaluatorEmail());
        parameters.put("profil", evaluation.getProfil() != null ? evaluation.getProfil().name() : null);
        parameters.put("avis", evaluation.getAvis() != null ? evaluation.getAvis().name() : null);
        parameters.put("state", evaluation.getState() != null ? evaluation.getState().name() : null);
        parameters.put("niveauEtudes", evaluation.getNiveauEtudes() != null ? evaluation.getNiveauEtudes().name() : null);
        parameters.put("annee", evaluation.getAnnee());
        parameters.put("intitule", evaluation.getIntitule());
        parameters.put("specialite", evaluation.getSpecialite());
        parameters.put("experience", evaluation.getExperience());
        parameters.put("notes", evaluation.getNotes());

        StringBuilder competences = new StringBuilder();
        competences.append("Niveau\t\tDébutant\tA pratiqué\tAutonome\tConfirmé\tExpert\n\n");

       
        evaluation.getCompetences().forEach(competence -> {
            String technologie = competence.getTechnologie();
            Niveau niveau = competence.getNiveau();

            	
            competences.append(technologie);

        
            for (int i = 1; i <= 5; i++) {
                if (i == getNiveauValue(niveau)) {
                    competences.append(" \t\tX");
                } else {
                    competences.append(" \t\t-");
                }
            }
            competences.append("\n\n");
        });

        parameters.put("competences", competences.toString());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private int getNiveauValue(Niveau niveau) {
        switch (niveau) {
            case DEBUTANT:
                return 1;
            case PRATIQUE:
                return 2;
            case AUTONOME:
                return 3;
            case CONFIRME:
                return 4;
            case EXPERT:
                return 5;
            default:
                throw new IllegalArgumentException("Unknown Niveau: " + niveau);
        }
    }
}
