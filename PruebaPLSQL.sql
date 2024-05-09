-- CREACION DE TABLAS Y PROCEDURE PARA PRUEBA TECNICA JAVA PL-SQL
/* base de Datos mariadb
   nombre de la database: sistema
   Terminal de mariadb
   CREATE USER dragon IDENTIFIED BY '046984';
   CREATE DATABASE sistema;
   GRANT ALL PRIVILEGES ON sistema * TO dragon;
   FLUSH PRIVILEGES;
   **/

CREATE TABLE `empleado` (
   `ID_Empleado` INTEGER NOT NULL,
   `primer_nombre` varchar(200) NOT NULL,
   `segundo_nombre` varchar(200) DEFAULT NULL,
   `primer_apellido` varchar(200) NOT NULL,
   `segundo_apellido` varchar(200) DEFAULT NULL,
   `edad` INTEGER(2) NOT NULL,
   `num_identificacion` INTEGER(12) NOT NULL,
   PRIMARY KEY (`ID_Empleado`)
);

CREATE TABLE `ventas` (
   `ID_Venta` INTEGER NOT NULL,
   `ID_Empleado` INTEGER NOT NULL,
   `Descripción_Venta` varchar(200) NOT NULL,
   `Valor_Venta` varchar(200) NOT NULL,
   PRIMARY KEY (`ID_Venta`)
);

CREATE SEQUENCE SEQ_VENTAS START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_EMPLOYEE START WITH 1 INCREMENT BY 1;

ALTER TABLE sistema.ventas ADD CONSTRAINT ventas_empleado_FK FOREIGN KEY (ID_Empleado) REFERENCES sistema.empleado(ID_Empleado);


INSERT INTO sistema.empleado(ID_Empleado, primer_nombre, segundo_nombre, 
	primer_apellido, segundo_apellido, edad, num_identificacion)
    VALUES (NEXT VALUE FOR SEQ_EMPLOYEE, 'Juan', 'Pepe', 'Perez', 'Maldonado', 23, 102345007);
   
INSERT INTO sistema.empleado(ID_Empleado, primer_nombre, segundo_nombre, 
	primer_apellido, segundo_apellido, edad, num_identificacion)
    VALUES (NEXT VALUE FOR SEQ_EMPLOYEE, 'Maria', 'Fernandez', 'Mendez', 'Maldonado', 25, 1223453455); 
   

INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 1, '2 paquetes de comapan', '6900');    
INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 1, '1 paquetes de mortadela', '9500');   
INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 1, '1 paquetes de queso tajado', '24600');  
INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 2, '1 salsa de tomate', '12500');
INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 2, '1 paquetes de papas', '4600');
INSERT INTO sistema.ventas(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta)
    VALUES (NEXT VALUE FOR SEQ_VENTAS, 2, '1 litro de gaseosa', '6000');   
   
SELECT * FROM sistema.empleado;
SELECT * FROM sistema.ventas;


-- PARA CREAR LA VENTA
DROP PROCEDURE IF EXISTS sistema.crearVenta;
CREATE PROCEDURE sistema.crearVenta(IN XID_Empleado INTEGER, 
	IN XDescripción_Venta VARCHAR(200), 
	IN XValor_Venta VARCHAR(200))
BEGIN
	INSERT INTO persona(ID_Venta, ID_Empleado, Descripción_Venta, Valor_Venta) 
	VALUES(NEXT VALUE FOR SEQ_VENTAS, XID_Empleado, XDescripción_Venta, XValor_Venta);
END;

-- PARA EDITAR LA VENTA
DROP PROCEDURE IF EXISTS sistema.editarVenta;
CREATE PROCEDURE sistema.editarVenta(IN xID_Venta INTEGER, 
	IN XID_Empleado INTEGER, 
	IN XDescripción_Venta VARCHAR(200), 
	IN XValor_Venta VARCHAR(200))
BEGIN
   	UPDATE ventas SET ID_Empleado=XID_Empleado, 
	Descripción_Venta=XDescripción_Venta, Valor_Venta=XValor_Venta
	WHERE ID_Venta = xID_Venta;
END;

 
-- PARA BORRAR LA VENTA
DROP PROCEDURE IF EXISTS sistema.borrarVenta;
CREATE PROCEDURE sistema.borrarVenta(IN xID_Venta INTEGER)
BEGIN
   DELETE FROM ventas WHERE ID_Venta = xID_Venta;
END;
 
-- PARA BUSCAR LA VENTA
DROP PROCEDURE IF EXISTS sistema.buscarVenta;
CREATE PROCEDURE sistema.buscarVenta(IN id INTEGER)
BEGIN
   	SELECT * FROM ventas as A 
   	JOIN empleado as B
   	ON A.ID_Empleado = B.ID_Empleado
	WHERE A.ID_Empleado = id;
END;

-- PARA CREAR EL EMPLEADO
DROP PROCEDURE IF EXISTS sistema.crearEmpleado;
CREATE PROCEDURE sistema.crearEmpleado(IN xprimer_nombre VARCHAR(200), 
	IN xsegundo_nombre VARCHAR(200), 
	IN xprimer_apellido VARCHAR(200), 
	IN xsegundo_apellido VARCHAR(200), 	
	IN xedad INTEGER,
	IN xnum_identificacion INTEGER)
BEGIN
	INSERT INTO empleado(ID_Empleado, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, edad, num_identificacion) 
	VALUES(NEXT VALUE FOR SEQ_EMPLOYEE, xprimer_nombre, xsegundo_nombre, xprimer_apellido, xsegundo_apellido, xedad, xnum_identificacion);
END;

-- PARA EDITAR EL EMPLEADO
DROP PROCEDURE IF EXISTS sistema.editarEmpleado;
CREATE PROCEDURE sistema.editarEmpleado(IN XID_Empleado INTEGER, 
	IN xprimer_nombre VARCHAR(200), 
	IN xsegundo_nombre VARCHAR(200), 
	IN xprimer_apellido VARCHAR(200), 
	IN xsegundo_apellido VARCHAR(200), 	
	IN xedad INTEGER,
	IN xnum_identificacion INTEGER)
BEGIN
   	UPDATE empleado  SET primer_nombre=xprimer_nombre, 
   	segundo_nombre=xsegundo_nombre, primer_apellido=xprimer_apellido, 
   	segundo_apellido=xsegundo_apellido,
	edad=xedad, num_identificacion=xnum_identificacion
	WHERE ID_Empleado = xID_Empleado;
END;

 
-- PARA BORRAR EL EMPLEADO
DROP PROCEDURE IF EXISTS sistema.borrarEmpleado;
CREATE PROCEDURE sistema.borrarEmpleado(IN xID_Empleado INTEGER)
BEGIN
   DELETE FROM empleado WHERE ID_Empleado = xID_Empleado;
END;
 
-- PARA BUSCAR EL EMPLEADO
DROP PROCEDURE IF EXISTS sistema.buscarEmpleado;
CREATE PROCEDURE sistema.buscarEmpleado(IN id INTEGER)
BEGIN
   	SELECT * FROM empleado
	WHERE ID_Empleado = id;
END;