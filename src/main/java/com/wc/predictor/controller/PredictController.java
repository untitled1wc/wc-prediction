package com.wc.predictor.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wc.predictor.entity.PlayerRank;
import com.wc.predictor.entity.Ranks;
import com.wc.predictor.service.ScheduleServiceImpl;

@RequestMapping("/predict")
@RestController
@CrossOrigin(origins = "*")//http://localhost:4200
public class PredictController {

	@Autowired
	ScheduleServiceImpl scheduleServiceImpl;
	
	@RequestMapping("/ranklist")
	public ResponseEntity<Ranks> getLeaderBoard(){
		Ranks r = new Ranks();
		PlayerRank rank = new PlayerRank("Alfred", 22.90, "alped88@gmail.com", "7736136299");
		PlayerRank rank1 = new PlayerRank("Anna", 14.20, "anna.paul1992c@gmail.com", "8281652514");
		ArrayList<PlayerRank> l = new ArrayList<PlayerRank>();
		l.add(rank);
		l.add(rank1);
		r.setPlayerRanks(l);
		ResponseEntity<Ranks> re = new ResponseEntity<Ranks>(r, HttpStatus.OK);
		return re;
	}
	
	@RequestMapping("/schedule")
	public ResponseEntity<String> getSchedule(){
		try {
			scheduleServiceImpl.getSchedule("/static/iccwc2019schedule.xlsx");
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseEntity<String> re = new ResponseEntity<String>("Good", HttpStatus.OK);
		return re;
	}
	
}
