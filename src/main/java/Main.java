import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {

		// Usamos un único scanner para toda la app
		Scanner teclado = new Scanner(System.in);
		// Array con enunciado y opciones
		String[] opciones = { "Elige una base de datos:", "SQLite Database", "HSQLDB Database", "Salir" };
		String tipo;
		File archivo;
		Connection con = null;
		boolean salir = false;
		
		while (!salir) {
			switch (Menu.menu(opciones, teclado)) {
			case 1:
				// SQLite
				tipo = "sqlite";
				archivo = new File("SQLite/sample.db");
				con = conectaBD(tipo, archivo);
				//creaTablas(con, "personas");
				//creaTablas(con, "estudiantes");
				infoBD(con);
				infoTablas(con);

				con.close();
				break;
			case 2:
				// HSQLDB
				tipo = "hsqldb";
				archivo = new File("HSQLBD/sample.bd");
				con = conectaBD(tipo, archivo);
				//creaTablas(con, "personas");
				//creaTablas(con, "estudiantes");
				infoBD(con);
				infoTablas(con);

				con.close();
				break;
			case 3:
				System.out.println("Hasta la próxima!");
				salir = true;
				break;

			default:
				System.out.println("Opción no válida, intenta de nuevo.");
			}
		}

		teclado.close();
	}

	
	
	
	
	
	
	
	
	
	
	public static Connection conectaBD(String tipoBD, File archivo) throws SQLException {
		// Verificamos tipo de BD
		if (tipoBD.equals("sqlite") || tipoBD.equals("hsqldb")) {
			// System.out.println("Verificación OK");
		} else {
			// System.out.println("Tipo de base de datos no soportado.");
			return null;
		}

		String StrJDBC = "jdbc:" + tipoBD + ":" + archivo;
		Connection con = DriverManager.getConnection(StrJDBC);

		// System.out.println("Conectado con exito a " + StrJDBC);
		return con;
	}

	
	
	public static int infoBD(Connection con) throws SQLException {

		String nombre = con.getMetaData().getDatabaseProductName();
		String driver = con.getMetaData().getDriverName();
		String url = con.getMetaData().getURL();
		String usuario = con.getMetaData().getUserName();

		System.out.println("----------------------------------");
		System.out.println("INFORMACIÓN DE LA BASE DE DATOS");
		System.out.println("----------------------------------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Driver: " + driver);
		System.out.println("URL: " + url);
		System.out.println("Usuario: " + usuario);
		System.out.println();

		return 0;
	}

	
	
	public static int infoTablas(Connection con) throws SQLException {
		// Obtener las tablas
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String[] types = { "TABLE" };
		
		// si HSQLDB schemaPattern = "PUBLIC"
		String tipoBD = con.getMetaData().getDatabaseProductName().toLowerCase();
		if (tipoBD.contains("hsql")) {
			schemaPattern = "PUBLIC";
		}
		
		int posibleNulo;
		String nullable;

		// mostrar info de las tablas
		System.out.println("----------------------------------");
		System.out.println("INFORMACIÓN DE TABLAS");
		System.out.println("----------------------------------");

		try (ResultSet tables = con.getMetaData().getTables(catalog, schemaPattern, tableNamePattern, types)) {
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				System.out.println("\nNombre de la Tabla: " + tableName + " Catalog: " + catalog + "; Schema: "
						+ schemaPattern + "; Type: " + types[0]);
				System.out.println("*** COLUMNAS de la TABLA " + tableName + " ***");

				ResultSet columns = con.getMetaData().getColumns(null, null, tableName, null);
				while (columns.next()) {
					String nombreColumna = columns.getString("COLUMN_NAME");
					String tipoColumna = columns.getString("TYPE_NAME");
					posibleNulo = columns.getInt("NULLABLE");
					if (posibleNulo == 0) { // 0 = no nulos
						nullable = "NO";
					} else if (posibleNulo == 1) {// 1 = admite nulos
						nullable = "YES";
					} else { // 2 = desconocido
						nullable = "UNKNOW";
					}

					// Imprimir información de la columna
					System.out.println("Nombre de Columna: " + nombreColumna + "; Tipo: " + tipoColumna
							+ "; IsNullable: " + nullable);
				}
			}
		}
		System.out.println();

		return 0;
	}

	
	
	public static void creaTablas(Connection con, String nombreTabla) {
		String dbType = "";

		// Determinar el tipo de base de datos
		try {
			DatabaseMetaData metaData = con.getMetaData();
			dbType = metaData.getDatabaseProductName().toLowerCase();
		} catch (SQLException e) {
			e.printStackTrace();
			return; // Salir si hay un error al obtener el tipo de base de datos
		}

		// Segun el tipo de BD (sqlite o hsqldb) la consulta es diferente
		String sql = "";
		if (dbType.contains("sqlite")) {
			sql = "CREATE TABLE IF NOT EXISTS " + nombreTabla + " (" + "id INTEGER PRIMARY KEY, "
					+ "name TEXT NOT NULL, " + "age INTEGER, " + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
		} else if (dbType.contains("hsql")) {
			sql = "CREATE TABLE IF NOT EXISTS " + nombreTabla + " (" + "id INTEGER PRIMARY KEY, "
					+ "name VARCHAR(255) NOT NULL, " + "age INTEGER, "
					+ "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
		} else {
			System.out.println("Tipo de base de datos no soportado: " + dbType);
			return;
		}

		// Ejecutar creación de tablas
		try (Statement statement = con.createStatement()) {
			statement.executeUpdate(sql);
			/*
			 * System.out.println("----------------------------------");
			 * System.out.println("CREACION DE TABLA");
			 * System.out.println("----------------------------------");
			 * System.out.println("Tabla " + nombreTabla + " creada (si no existia) en " +
			 * dbType); System.out.println();
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
