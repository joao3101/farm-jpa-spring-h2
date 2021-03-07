package com.joaov.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaov.spring.jpa.h2.model.Farm;
import com.joaov.spring.jpa.h2.repository.FarmRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FarmsController {

	final static Logger logger = Logger.getLogger(AnimalsController.class);

	@Autowired
	FarmRepository farmRepository;

	@GetMapping("/farm")
	public ResponseEntity<List<Farm>> getAllFarms() {
		try {
			List<Farm> farm = new ArrayList<Farm>();

			farmRepository.findAll().forEach(farm::add);

			if (farm.isEmpty()) {
				logger.warn("No farms to display");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			logger.info("Getting all farms");
			return new ResponseEntity<>(farm, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("This is error: " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/farm/{id}")
	public ResponseEntity<Farm> getFarmById(@PathVariable("id") long id) {
		Optional<Farm> farmData = farmRepository.findById(id);

		if (farmData.isPresent()) {
			logger.info("Getting farm: " + id);
			return new ResponseEntity<>(farmData.get(), HttpStatus.OK);
		} else {
			logger.warn("Couldn't find farm: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/farm")
	public ResponseEntity<Farm> createFarm(@RequestBody List<Farm> farms) {
		try {
			// This check is necessary because there is a bug for the not null anotation
			// regarding H2
			for (Farm farm : farms) {
				if (farm.getName() == null || farm.getName() == "") {
					logger.error("Couldn't save files because the input is wrong.");
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			for (Farm farm : farms) {
				farm = farmRepository.save(new Farm(farm.getName()));
			}
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("This is error: " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/farm/{id}")
	public ResponseEntity<Farm> updateFarm(@PathVariable("id") long id, @RequestBody Farm farm) {
		if (farm.getName() == null || farm.getName() == "") {
			logger.error("Couldn't update files because the input is wrong.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Optional<Farm> farmData = farmRepository.findById(id);

		if (farmData.isPresent()) {
			Farm _farm = farmData.get();
			_farm.setName(farm.getName());
			logger.info("Updating farm: " + id);
			return new ResponseEntity<>(farmRepository.save(_farm), HttpStatus.OK);
		} else {
			logger.warn("Can't find any farm for the id: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/farm/{id}")
	public ResponseEntity<HttpStatus> deleteFarm(@PathVariable("id") long id) {
		try {
			farmRepository.deleteById(id);
			logger.info("Deleting farm: " + id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/farm")
	public ResponseEntity<HttpStatus> deleteAllFarms() {
		try {
			farmRepository.deleteAll();
			logger.info("Deleting all farms");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
