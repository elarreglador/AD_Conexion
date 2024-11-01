
public class Main {
/*
ACCESO A DATOS
ACTIVIDAD 3.1 - CONEXIÓN
	Implementa un programa que se pueda conectar a cada una de las bases de datos propuestas.
Recuerda incluir las bases de datos en la carpeta del proyecto y referenciarlas con paths relativos.

Cada una utiliza un protocolo diferente (SQLite o HSQLDB). El usuario seleccionará una base de
datos en un menú como el siguiente:

Elige una base de datos:
1. SQLite Database
2. HSQLDB Database
3. Salir

Una vez seleccionada la bese de datos, el programa deberá conectarse a esta I mostrar
información. Después el menu debe ser mostrado otra vez.

La información a mostrar debe ser:

● Información sobre la base de datos:
Nombre
Driver
URL
Usuario

● Información sobre las tablas
Nombre de tabla
Catalog
Schema
Type

● Información de las columnas de cada tabla
Nombre de columna
Type
Is Nullable

Esta debería ser la salida si se selecciona SQLite:

----------------------------------
INFORMACIÓN DE LA BASE DE DATOS
----------------------------------
Nombre: SQLite
Driver: SQLite JDBC
URL: jdbc:sqlite:D:\Sources\DataAccess\DBs\sqlite\act2.1
Usuario: null

----------------------------------
INFORMACIÓN DE TABLAS
----------------------------------
Nombre de la Tabla: departments; Catalog: null; Schema: null; Type: TABLE
*** COLUMNAS de la TABLA departments ***
Nombre de columna: dept_num; Type: INT; IsNullable: NO
Nombre de columna: name; Type: VARCHAR(20); IsNullable: YES
Nombre de columna: office; Type: VARCHAR(20); IsNullable: YES
----------------------------------
Nombre de la Tabla: teachers; Catalog: null; Schema: null; Type: TABLE
*** COLUMNAS de la TABLA teachers ***
Nombre de columna: id; Type: INT; IsNullable: YES
Nombre de columna: name; Type: VARCHAR(15); IsNullable: YES
Nombre de columna: surname; Type: VARCHAR(40); IsNullable: YES
Nombre de columna: email; Type: VARCHAR(50); IsNullable: YES
Nombre de columna: start_date; Type: DATE; IsNullable: YES
Nombre de columna: dept_num; Type: INT; IsNullable: YES
----------------------------------

Cuando conectes con SQLite , considera todas las tablas.
Cuando conectes con HSQLDB , considera unicamente las tablas con schema = “PUBLIC”.

Recuerda:
● Es importante reutilizar código.
● Es importante que la salida sea lo mas similar posible a la mostrada en el enunciado.

 */
	public static void main(String[] args) {
		

	}

}
