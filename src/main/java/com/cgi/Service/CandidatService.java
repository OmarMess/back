package com.cgi.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cgi.Exceptions.BusinessException;
import com.cgi.Exceptions.ErrorDto;
import com.cgi.model.Candidat;
import com.cgi.model.Profil;
import com.cgi.repository.CandidatRepository;

@Service
public class CandidatService {
	@Autowired
	private CandidatRepository candidatRepository;

	public List<Candidat> findCandidatesByProfile(String profile) {

		String[] profils = Arrays.stream(Profil.values()).map(Enum::name).toArray(String[]::new);

		if (!isValidProfil(profile, Profil.FRONTEND.toString(), Profil.BACKEND.toString(), Profil.FULLSTUCK.toString(),
				Profil.JAVA.toString(), Profil.PHP.toString())) {

			// TODO
			ErrorDto errorDto = new ErrorDto(400, HttpStatus.BAD_REQUEST,
					"Invalide enum: " + profile/* +valPossible */);
			throw new BusinessException(errorDto);
		}

		return candidatRepository.findByProfil(Profil.valueOf(profile));
	}

	private static boolean isValidProfil(String profil, String... values) {
		String regex = String.join("|", values);
		return profil.matches(regex);
	}

}
