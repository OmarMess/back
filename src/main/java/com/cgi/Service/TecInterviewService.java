package com.cgi.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cgi.Exceptions.BusinessException;
import com.cgi.Exceptions.ErrorDto;
import com.cgi.model.EntretienTechnique;
import com.cgi.model.Statut;
import com.cgi.repository.EntretienTechniqueRepository;

@Service
public class TecInterviewService {
    
    @Autowired
    private EntretienTechniqueRepository entretienTecRepo;

    public List<EntretienTechnique> findTecInterviewByStatus(String status) {
        String[] statuts = Arrays.stream(Statut.values()).map(Enum::name).toArray(String[]::new);

        if (!isValidStatus(status, Statut.Planned.toString(), Statut.Evaluated.toString(), Statut.Reported.toString(), Statut.Cancelled.toString())) {
            ErrorDto errorDto = new ErrorDto(400, HttpStatus.BAD_REQUEST,
                    "Invalid status: " + status);
            throw new BusinessException(errorDto);
        }
        return entretienTecRepo.findBystatutEntretien(Statut.valueOf(status));
    }

    private static boolean isValidStatus(String status, String...values) {
    	String regex = String.join("|", values);
		return status.matches(regex);
    }
}
