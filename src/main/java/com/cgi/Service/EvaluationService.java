package com.cgi.Service;

import com.cgi.model.CompetenceTechnique;
import com.cgi.model.Evaluation;
import com.cgi.model.Profil;
import com.cgi.model.State;
import com.cgi.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;
    
    @Autowired
    private ScheduleService scheduleService;


    public Optional<Evaluation> getEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation, Long scheduleId) {
        for (CompetenceTechnique competence : evaluation.getCompetences()) {
            competence.setEvaluation(evaluation);
        }

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        // mark the schedule as evaluated
        scheduleService.updateEvaluatedStatus(scheduleId, true);

        return savedEvaluation;
    }


    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }
    
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public List<Evaluation> getEvaluationsByEvaluatorEmail(String evaluatorEmail) {
        return evaluationRepository.findByEvaluatorEmail(evaluatorEmail);
    }
    
    public long countEvaluationsByProfil(Profil profil) {
        return evaluationRepository.countByProfil(profil);
    }
    
    public long countEvaluationsByState(State state) {
        return evaluationRepository.countByState(state);
    }
}
