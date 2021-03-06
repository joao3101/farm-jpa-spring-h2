package com.joaov.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaov.spring.jpa.h2.model.Farm;
import com.joaov.spring.jpa.h2.repository.FarmRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FarmsController {

	@Autowired
	FarmRepository farmRepository;

	@GetMapping("/farm")
	public ResponseEntity<List<Farm>> getAllFarms() {
		try {
			List<Farm> farm = new ArrayList<Farm>();

			farmRepository.findAll().forEach(farm::add);

			if (farm.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(farm, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/farm/{id}")
	public ResponseEntity<Farm> getFarmById(@PathVariable("id") long id) {
		Optional<Farm> farmData = farmRepository.findById(id);

		if (farmData.isPresent()) {
			return new ResponseEntity<>(farmData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/farm")
	public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
		try {
			Farm _farm = farmRepository.save(new Farm(farm.getName()));
			return new ResponseEntity<>(_farm, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/farm/{id}")
	public ResponseEntity<Farm> updateFarm(@PathVariable("id") long id, @RequestBody Farm farm) {
		Optional<Farm> farmData = farmRepository.findById(id);

		if (farmData.isPresent()) {
			Farm _farm = farmData.get();
			_farm.setName(farm.getName());
			return new ResponseEntity<>(farmRepository.save(_farm), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/farm/{id}")
	public ResponseEntity<HttpStatus> deleteFarm(@PathVariable("id") long id) {
		try {
			farmRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/farm")
	public ResponseEntity<HttpStatus> deleteAllFarms() {
		try {
			farmRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
