package com.nttdata.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.demo.models.InfoService;

@RestController
@RequestMapping("/api/factorial/")
public class serviciosController {

	@Autowired(required = false)
    private InfoService infoService;
 
	private static Long Factorial(Long n) {
	    Long factorial = (long) 1;
	    for (int i = 2; i <= n; ++i) {
	      factorial *= i;
	    }
	    return factorial;
	}

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getInfo(@PathVariable Long id) {
    	Long data = Factorial(id);
        return "{\"numero\":\""+id+"\", \"mensaje\":\"el valor del factorial es: "+data+"\"}";
    }
    
}
