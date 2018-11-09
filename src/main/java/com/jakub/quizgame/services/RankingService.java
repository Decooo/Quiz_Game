package com.jakub.quizgame.services;

import com.jakub.quizgame.dto.RankingDTO;
import com.jakub.quizgame.repository.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class RankingService {

	private RankingRepository rankingRepository;

	public RankingService(RankingRepository rankingRepository) {
		this.rankingRepository = rankingRepository;
	}


	public Collection<RankingDTO> getRanking() {
		return rankingRepository.findAll().stream()
				.map(r -> new ModelMapper().map(r, RankingDTO.class))
				.sorted(Comparator.comparing(RankingDTO::getResult).reversed()	)
				.collect(Collectors.toList());
	}
}
