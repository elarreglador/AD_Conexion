import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                	archivo = new File("SQLite/my.db");
                	con = conectaBD(tipo, archivo);
                	con.close();
                    break;
                case 2:
                    // HSQLDB
                	tipo = "hsqldb";
                	archivo = new File("HSQLBD/my.bd");
                	con = conectaBD(tipo, archivo);
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
        
        teclado.close();  // Cerrar el scanner para liberar recursos
    }

    public static Connection conectaBD(String tipoBD, File archivo) throws SQLException {
        // Verificamos tipo de BD
        if (tipoBD.equals("sqlite") || tipoBD.equals("hsqldb")) {
            System.out.println("Verificación OK");
        } else {
            System.out.println("Tipo de base de datos no soportado.");
            return null;
        }
        
        String StrJDBC = "jdbc:" + tipoBD + ":" + archivo;
        Connection con = DriverManager.getConnection(StrJDBC);
        
        System.out.println("Conectado con exito a " + StrJDBC);
        return con;
        
    }
}
