import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Usamos un unico scanner para toda la app
		Scanner teclado = new Scanner(System.in);
		// Array con enunciado y opciones
		String[] opciones = { "Elige una base de datos:", "SQLite Database", "HSQLDB Database", "Salir" };
		
		boolean salir = false;
		while (!salir) {
			switch (Menu.menu(opciones, teclado)) {
		        case 1:
		        	System.out.println("opcion uno");
		        	break;
		        case 2:
		        	System.out.println("opcion dos");
		        	break;
		        case 3:
		        	System.out.println("Hasta la proxima!");
		        	salir = !salir;
		        	break;
			}
		}

	}

}

