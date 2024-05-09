package com.nttdata.demo.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nttdata.demo.dao.Conexion;

public class Empleado {
    // ATRIBUTOS
    // Objeto de la clase conexión
    private Conexion objConexion = new Conexion();
    private int idEmpleado = 0;
    private String primerNombre = null;
    private String segundoNombre = null;
    private String primerApellido = null;
    private String segundoApellido = null; 
    private int edad = 0;
    private int numIdentificacion = 0;
    
    // atributos para la conexión
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private ResultSet rs = null;
   
    // CONSTRUCTORES
    public Empleado() {} //Constructor sin argumentos

    public Empleado(int idEmpleado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, int edad, int numIdentificacion) { //Constructor con argumentos
       this.idEmpleado = idEmpleado;
       this.primerNombre = primerNombre;
       this.segundoNombre = segundoNombre;
       this.primerApellido = primerApellido;
       this.segundoApellido = segundoApellido;
       this.edad = edad;
       this.numIdentificacion = numIdentificacion;
    }
   
    // MÉTODOS GETTER Y SETTER
    public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(int numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}
   
    // MÉTODOS DEFINIDOS POR EL USUARIO
    public String crearEmpleado() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call crearEmpleado(?,?,?,?,?,?,?)}");
          cstmt.setInt(1, idEmpleado); // campo idEmpleado
          cstmt.setString(2, primerNombre); // campo primerNombre
          cstmt.setString(3, segundoNombre); // campo segundoNombre
          cstmt.setString(4, primerApellido); // campo primerApellido
          cstmt.setString(5, segundoApellido); // campo segundoApellido
          cstmt.setInt(6, edad); // campo edad
          cstmt.setInt(7, numIdentificacion); // campo Identificacion
          // Ejecutamos el procedimiento almacenado
          if(cstmt.executeUpdate() == 1) {
             mensaje = "La persona se creó con éxito.";
          } else {
             mensaje = "Error al crear la persona.";
          }
          objConexion.destruir();
       } catch (Exception error) {
          error.printStackTrace();
       }
       return mensaje;
    }



	public String editarEmpleado() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call editarEmpleado(?,?,?,?,?,?,?)}");
          cstmt.setInt(1, idEmpleado); // campo idEmpleado
          cstmt.setString(2, primerNombre); // campo primerNombre
          cstmt.setString(3, segundoNombre); // campo segundoNombre
          cstmt.setString(4, primerApellido); // campo primerApellido
          cstmt.setString(5, segundoApellido); // campo segundoApellido
          cstmt.setInt(6, edad); // campo edad
          cstmt.setInt(7, numIdentificacion); // campo Identificacion
          // Ejecutamos el procedimiento almacenado
          if(cstmt.executeUpdate() == 1) {
             mensaje = "La persona se editó con éxito.";
          } else {
             mensaje = "Error al editar la persona.";
          }
          objConexion.destruir();
       } catch (Exception error) {
          error.printStackTrace();
       }
       return mensaje;
    }
   
    public String borrarEmpleado() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call borrarEmpleado(?)}");
          cstmt.setInt(1, idEmpleado); // campo idEmpleado
          // Ejecutamos el procedimiento almacenado
          if(cstmt.executeUpdate() == 1) {
             mensaje = "La persona se borró con éxito.";
          } else {
             mensaje = "Error al borrar la persona.";
          }
          objConexion.destruir();
       } catch (Exception error) {
          error.printStackTrace();
       }
       return mensaje;
    }
   
    public ResultSet leerEmpleado(int id) {
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call buscarEmpleado(?)}");
          cstmt.setInt(1, id); // campo idEmpleado
          // Ejecutamos el procedimiento almacenado
          return cstmt.executeQuery();
       } catch (Exception error) {
          error.printStackTrace();
       }
       return null;
    }
}
