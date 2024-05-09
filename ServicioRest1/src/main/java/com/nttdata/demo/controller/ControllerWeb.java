package com.nttdata.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.demo.models.ModeloJson;



@Controller
//@RestController
public class ControllerWeb {

    /**
     * Metodo consulta información del usuario REST POST
     * @param documento y tipo documento (ModeloJson)
     * @return JSON
     * **/
	@PostMapping("/consultar/usuario")
	public ResponseEntity<String> consultarUsuarioPost(@RequestBody ModeloJson input) {
		String resultado = "";
		try {
			int numero = Integer.parseInt(input.getDocumento());
			if(input.getDocumento().equals("23445322") && numero == 23445322 && input.getTipoDocument().equals("c")) {
				resultado = "{ \"mensaje\":\"El usuario si esta registrado.\", \"users\":[{\"firstname\":\"Pepe\",\"secondname\":\"Anderson\",\"lastname\":\"Perez\",\"sendlastname\":\"Gutierrez\",\"telefono\":\"5550102\",\"dirección\":\"Av siempre vida 123\",\"ciudad\":\"Springfield\"}]}";
				return new ResponseEntity<String>(resultado, HttpStatus.OK);
			} else if( !input.getTipoDocument().equals("c") && !input.getTipoDocument().equals("p")){
				resultado = "{ \"mensaje\":\"Ingreso un tipo de documento no valido.\"}";
				return new ResponseEntity<String>(resultado, HttpStatus.BAD_REQUEST);				
			}else {
				resultado = "{ \"mensaje\":\"El usuario no se encuentra.\"}";
				return new ResponseEntity<String>(resultado, HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			resultado = "{ \"mensaje\":\"El documento no es númerico.\"}";
			return new ResponseEntity<String>(resultado, HttpStatus.BAD_REQUEST);			
		}

	}
	
    /**
     * Metodo para pruebas servicio REST GET
     * @param sin parametros de entrada
     * @return JSON
     * **/
	@GetMapping("/consultar/usuario")
	public String consultarUsuarioGet() {
		/*String resultado = "{\"mensaje\":\"No hay datos de entrada para consultar.\"}";
		return new ResponseEntity<String>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);*/
		return "error";
	}
	
    /**
     * Metodo para pruebas servicio REST GET
     * @param sin parametros de entrada
     * @return JSON
     * **/
	@RequestMapping(value = "/consultar/empleado", method = RequestMethod.GET)
	public ResponseEntity<String> mostrarpagAnterior() {
		String resultado = "{\"mensaje\":\"No hay datos de entrada para consultar.\"}";
		return new ResponseEntity<String>(resultado, HttpStatus.OK);
	}
}
