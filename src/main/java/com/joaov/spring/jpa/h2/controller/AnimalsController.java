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

import com.joaov.spring.jpa.h2.model.Animals;
import com.joaov.spring.jpa.h2.repository.AnimalsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AnimalsController {
	final static Logger logger = Logger.getLogger(AnimalsController.class);

	@Autowired
	AnimalsRepository animalsRepository;

	@GetMapping("/animals")
	public ResponseEntity<List<Animals>> getAllAnimals() {
		try {
			List<Animals> animals = new ArrayList<Animals>();

			animalsRepository.findAll().forEach(animals::add);

			if (animals.isEmpty()) {
				logger.warn("No animals to display");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(animals, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/animals/{id}")
	public ResponseEntity<Animals> getAnimalsById(@PathVariable("id") long id) {
		Optional<Animals> animalData = animalsRepository.findById(id);

		if (animalData.isPresent()) {
			logger.warn("Couldn't find animal:  " + id);
			return new ResponseEntity<>(animalData.get(), HttpStatus.OK);
		} else {
			logger.info("Getting animal: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/animals")
	public ResponseEntity<Animals> createAnimals(@RequestBody List<Animals> animals) {
		try {
			for (Animals animal : animals) {
				if (animal.getTag() == null || animal.getTag() == "") {
					logger.error("Couldn't save files because the input is wrong.");
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			for (Animals _animals : animals) {
				_animals = animalsRepository.save(new Animals(_animals.getTag(), _animals.getFarm()));
			}
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/animals/{id}")
	public ResponseEntity<Animals> updateAnimals(@PathVariable("id") long id, @RequestBody Animals animal) {
		if (animal.getTag() == null || animal.getTag() == "") {
			logger.error("Couldn't update files because the input is wrong.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Optional<Animals> animalsData = animalsRepository.findById(id);

		if (animalsData.isPresent()) {
			Animals _animal = animalsData.get();
			_animal.setTag(animal.getTag());
			_animal.setFarm(animal.getFarm());
			logger.info("Updating animal: " + id);
			return new ResponseEntity<>(animalsRepository.save(_animal), HttpStatus.OK);
		} else {
			logger.warn("Could not find animal for the id: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/animals/{id}")
	public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable("id") long id) {
		try {
			animalsRepository.deleteById(id);
			logger.info("Deleting animal: " + id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/animals")
	public ResponseEntity<HttpStatus> deleteAllAnimals() {
		try {
			animalsRepository.deleteAll();
			logger.info("Deleting all animals");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("This is error : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
