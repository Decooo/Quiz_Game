package com.jakub.quizgame.controller;

import com.jakub.quizgame.dto.RankingDTO;
import com.jakub.quizgame.repository.RankingRepository;
import com.jakub.quizgame.services.RankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController {

	private RankingService rankingService;

	public RankingController(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	public void setRankingService(RankingService rankingService) {
		this.rankingService = rankingService;
	}

	@GetMapping(value = "/get")
	public ResponseEntity<Collection<RankingDTO>> ranking(){
		return new ResponseEntity<>(rankingService.getRanking(), HttpStatus.OK);
	}


}
