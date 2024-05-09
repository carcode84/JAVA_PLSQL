package com.nttdata.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conexion {
	private Connection conexion; //Variable de conexión
    static String bd = "sistema"; //Nombre de la Base de Datos
    static String user = "dragon"; //Usuario del Gestor será root si no lo cambiaron
    static String password = "046984"; //Contraseña del Gestor si tiene
    static String server = "jdbc:mariadb://localhost:3306/" + bd; //"jdbc:mysql://localhost/" + bd; //Sentencia de Conexión

    /** Construtor(es) **/
    public Conexion() { }
  
    /** Métodos **/
    public void establecerConexion() {
    	
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		//conexion = DriverManager.getConnection(server, user, password);
    		conexion = DriverManager.getConnection(server, user, password);
    	} catch(Exception e) {
    		System.out.println(" Imposible realizar conexion con la Base de Datos ");
    		conexion = null;
    	}
    }
 
    public Connection getConexion() {
    	return conexion;
    }
 
    public void cerrar(ResultSet rs) {
    	if(rs != null) {
    		try {
    			rs.close();
    		} catch(Exception e) {
    			System.out.print(" No es posible cerrar la conexión ");
    		}
    	}
    }
 
    public void cerrar(java.sql.Statement stmt) {
    	if(stmt != null) {
    		try {
    			stmt.close();
    		} catch(Exception e) { }
    	}
    }
 
    public void destruir() {
    	if(conexion != null) {
    		try{
    			conexion.close();
    		} catch(Exception e){ }
    	}  
    }
}
