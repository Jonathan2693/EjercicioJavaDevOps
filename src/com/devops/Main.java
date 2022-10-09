package com.devops;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Cajero cajero = new Cajero(1000);
		boolean salir = false;
		Scanner entrada = new Scanner(System.in);
		
		do {
			cajero.mostrarMenu();
			try {
				salir = cajero.ejecutarOpcion(entrada.nextInt());
			}
			catch(InputMismatchException e) {
				entrada.next();
				System.out.println("La entrada de datos no es válida");
			}
			
		} while(salir==false);
		entrada.close();	
	}
}
