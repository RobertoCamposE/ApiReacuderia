package com.apiRecauderia.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.dto.FruitsDTO;

import com.apiRecauderia.services.interfaces.IFruitsService;

@Service
public class CSVService {
	@Autowired
	IFruitsService repository;

	public void save(MultipartFile file) {
		try {
			List<FruitRequest> fruit = CSVHelper.csvToFruits(file.getInputStream());
			repository.saveAll(fruit);
		} catch (IOException e) {
			throw new RuntimeException("Error al almacenar la informacion CSV: " + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<FruitRequest> dto = new ArrayList<>();

		List<FruitsDTO> fruits = this.repository.findAll();
		for (FruitsDTO fruit : fruits) {

			FruitRequest fruitsFruitRequest = MHelpers.modelMapper().map(fruit, FruitRequest.class);
			dto.add(fruitsFruitRequest);
		}

		ByteArrayInputStream in = CSVHelper.fruitsToCSV(dto);
		return in;
	}

	public List<FruitRequest> getAllFruits() {
		List<FruitRequest> dto = new ArrayList<>();

		List<FruitsDTO> fruits = this.repository.findAll();
		for (FruitsDTO fruit : fruits) {

			FruitRequest fruitsFruitRequest = MHelpers.modelMapper().map(fruit, FruitRequest.class);
			dto.add(fruitsFruitRequest);
		}
		return dto;
	}
}