package com.apiRecauderia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.apiRecauderia.dto.FruitFound;
import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.dto.FruitsDTO;
import com.apiRecauderia.services.interfaces.IFruitsService;
import com.apiRecauderia.utilities.CSVHelper;
import com.apiRecauderia.utilities.CSVService;
import com.apiRecauderia.utilities.exceptions.ApiUnprocessableEntity;
import com.apiRecauderia.utilities.exceptions.ResponseMessage;
import com.apiRecauderia.validator.FruitValidator;

@CrossOrigin("*")
@RestController
@RequestMapping("/recauderia")
public class ApiController {
	@Autowired
	private IFruitsService fruitsService;
	@Autowired
	private FruitValidator fruitValidator;

	@GetMapping("/test")
	public ResponseEntity<Object> test() {
		return ResponseEntity.status(HttpStatus.OK).body("La conexion es correcta ");
	}

	@GetMapping(value = "/obtenerFrutas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> obtenerFrutas() {

		return ResponseEntity.status(HttpStatus.OK).body(this.fruitsService.findAll());
	}

	@Autowired
	CSVService fileService;

	@PostMapping("/agregarFruta")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);

				message = "El archivo se cargo en la BD exitosamente: " + file.getOriginalFilename();

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "El archivo no pudo cargarse en la BD: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Pofavor carga solo archivos CSV";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@PostMapping(value = "/obtenerFruta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> ObtenerFruta(@RequestBody FruitFound clave) {
		FruitsDTO fruit = this.fruitsService.findByClave(clave.getClave());
		System.out.println();
		if (fruit == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(fruit);
	}

	@PostMapping(value = "/agregaFruta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> agregarFruta(@RequestBody FruitRequest request) throws ApiUnprocessableEntity {
		this.fruitValidator.validator(request);
		this.fruitsService.save(request);
		return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);

	}

	@PutMapping(value = "/actualizarFruta/{clave}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizarFruta(@RequestBody FruitRequest request, @PathVariable String clave)
			 {
		this.fruitsService.update(request, clave);
			return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
		

	}

}
