package com.nttdata.demo.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nttdata.demo.dao.Conexion;

public class Ventas {
    // ATRIBUTOS
    // Objeto de la clase conexión
    private Conexion objConexion = new Conexion();
    private int idVenta = 0;
    private int idEmpleado = 0;
    private String descripcionVenta = null;
    private String valorVenta = null;
    // atributos para la conexión
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private ResultSet rs = null;
    
    // CONSTRUCTORES
    public Ventas() {} //Constructor sin argumentos

    public Ventas(int idVenta, int idEmpleado, String descripcionVenta, String valorVenta) { //Constructor con argumentos
       this.idVenta = idVenta;
       this.idEmpleado = idEmpleado;
       this.descripcionVenta = descripcionVenta;
       this.valorVenta = valorVenta;
    }
    
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDescripcionVenta() {
		return descripcionVenta;
	}
	public void setDescripcionVenta(String descripcionVenta) {
		this.descripcionVenta = descripcionVenta;
	}
	public String getValorVenta() {
		return valorVenta;
	}
	public void setValorVenta(String valorVenta) {
		this.valorVenta = valorVenta;
	}
    
	   // MÉTODOS DEFINIDOS POR EL USUARIO
    public String crearVenta() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call crearVenta(?,?,?,?)}");
          cstmt.setInt(1, idVenta); // campo idVenta
          cstmt.setInt(2, idEmpleado); // campo IdEmpleado
          cstmt.setString(3, descripcionVenta); // campo Descripcion venta
          cstmt.setString(4, valorVenta); // campo valor Venta
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



	public String editarVentas() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call editarVenta(?,?,?,?)}");
          cstmt.setInt(1, idVenta); // campo idVenta
          cstmt.setInt(2, idEmpleado); // campo IdEmpleado
          cstmt.setString(3, descripcionVenta); // campo Descripcion venta
          cstmt.setString(4, valorVenta); // campo valor Venta
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
   
    public String borrarVentas() {
       String mensaje = "";
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call borrarVenta(?)}");
          cstmt.setInt(1, idVenta); // campo idVenta
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
   
    public ResultSet leerVentas(int id) {
       try {
          //Se carga el driver de conexión
          objConexion.establecerConexion();
          cstmt = objConexion.getConexion().prepareCall("{call buscarVenta(?)}");
          cstmt.setInt(1, id); // campo idVentas
          // Ejecutamos el procedimiento almacenado
          return cstmt.executeQuery();
       } catch (Exception error) {
          error.printStackTrace();
       }
       return null;
    }    
    
}
