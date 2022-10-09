package com.devops;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Cajero cajero = new Cajero(1000);
		boolean salir = true;
		Scanner entrada = new Scanner(System.in);
		
		do {
			try {
				salir = cajero.validarPIN(entrada.nextLine());
			}
			catch (Exception e) {
				System.out.println("El formato del PIN no es válido");
				cajero.setContadorIntentos(cajero.getContadorIntentos() + 1);
			}
			
		} while(cajero.getContadorIntentos()!=3);
		
		while(salir==false){
			cajero.mostrarMenu();
			try {
				salir = cajero.ejecutarOpcion(entrada.nextInt());
			}
			catch(InputMismatchException e) {
				entrada.next();
				System.out.println("La entrada de datos no es válida");
			}
		} 
		entrada.close();	
	}
}
