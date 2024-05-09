package com.nttdata.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nttdata.demo.controller.serviciosController;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class ServicioRest2ApplicationTests {
	
	private static final Long CODIGO_FACTORIAL1 = (long) 4;
	private static final Long CODIGO_FACTORIAL2 = (long) 9;
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
  
    @Autowired
    private MockMvc mockMvc;
      
    @Autowired
    private serviciosController miservicio;
    
    @Autowired
    protected WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }
    
	private static Long Factorial(Long n) {
	    Long factorial = (long) 1;
	    for (int i = 2; i <= n; ++i) {
	      factorial *= i;
	    }
	    return factorial;
	}
    /**
     * Metodo para pruebas servicio REST POST
     * @param documento y tipo documento
     * @return JSON
     * **/
    @Test
	void contextLoads() throws JsonProcessingException, Exception {
        String jsonExpected = "{\"titulo\":\"Hola a todos.\", \"mensaje\":\"Soy el Ing. Carlos Ruano, desarrollador de Back-End\",\"pd\":\"No se puede tener un gran software sin un gran equipo.\"}";
        
        mockMvc.perform(get("/api/hola"))
                .andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
        
	}
    
    @Test
    public void getInfo_ShouldReturnCorrectInfo() throws Exception {
    	Long data = Factorial(CODIGO_FACTORIAL1);
        String jsonExpected = "{\"numero\":\""+CODIGO_FACTORIAL1+"\", \"mensaje\":\"el valor del factorial es: "+data+"\"}";
 
        mockMvc.perform(get("/api/factorial/{id}", CODIGO_FACTORIAL1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
    }
    
    @Test
    public void getInfo_ShouldReturnCorrectFact() throws Exception {
    	Long data = Factorial(CODIGO_FACTORIAL2);
        String jsonExpected = "{\"numero\":\""+CODIGO_FACTORIAL2+"\", \"mensaje\":\"el valor del factorial es: "+data+"\"}";
 
        mockMvc.perform(get("/api/factorial/{id}", CODIGO_FACTORIAL2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
    }
    
    @Test
    public void testJunit1() {
    
      assertEquals("{\"numero\":\"4\", \"mensaje\":\"el valor del factorial es: 24\"}",miservicio.getInfo(CODIGO_FACTORIAL1));
    }
    
    @Test
    public void testJunit2() {
    
      assertEquals("{\"numero\":\"9\", \"mensaje\":\"el valor del factorial es: 362880\"}",miservicio.getInfo(CODIGO_FACTORIAL2));
    }

}
