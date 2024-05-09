package com.nttdata.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class saludosController {

	@RequestMapping(value = "/api/hola", method = RequestMethod.GET)
	public ResponseEntity<String> mostrarpagAnterior() {
		String resultado = "{\"titulo\":\"Hola a todos.\", \"mensaje\":\"Soy el Ing. Carlos Ruano, desarrollador de Back-End\",\"pd\":\"No se puede tener un gran software sin un gran equipo.\"}";
		return new ResponseEntity<String>(resultado, HttpStatus.OK);
	}
}
