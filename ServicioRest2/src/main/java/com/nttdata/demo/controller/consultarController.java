package com.nttdata.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.nttdata.demo.dao.*;
@RestController
@RequestMapping("/api/consultaVentas/")
public class consultarController {
	
	private Ventas venta = new Ventas();
	
	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getInfo(@PathVariable int id) {
    	System.out.println("####---> "+id);
    	System.err.println("####---> "+id);
    	String resultado = "{\"data\":[";
    	try {
    		ResultSet data  = venta.leerVentas(id);
			while(data.next()) {
				resultado += "{";
				resultado += "\"nombre\":\""+data.getString("primer_nombre")+"\",";
				resultado += "\"apellido\":\""+data.getString("primer_apellido")+"\",";
				resultado += "\"descripcion\":\""+data.getString("Descripción_Venta")+"\",";
				resultado += "\"valor_venta\":\""+data.getString("Valor_Venta")+"\"";
				resultado += "},";
			}
		} catch (SQLException e) {
			resultado += "{\"mensaje\":\""+e.getMessage()+"\"}";
		} catch(NullPointerException e) {
			resultado += "{\"mensaje\":\""+e.getMessage()+"\"}";
		}
    	resultado = resultado.substring(0, resultado.length()-1);
    	resultado += "]}";
        return new ResponseEntity<String>(resultado, HttpStatus.OK);
    }
	
	
}
