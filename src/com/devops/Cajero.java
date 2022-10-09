package com.devops;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cajero {

	private int saldo;
	private String historicoMovimientos;
	private int contadorIntentos;
	private Scanner entrada;
	private final String PIN = "1235";
	
	public Cajero(int saldoInicial) {
		this.saldo = saldoInicial;
		this.contadorIntentos = 0;
		this.entrada = new Scanner(System.in);
		this.historicoMovimientos = "";
		System.out.println("Bienvenido al Cajero Automático");
		System.out.print("Ingrese el PIN para utilizar la aplicación: ");
		
	}
	
	public boolean validarPIN(String pin) {
		if(!pin.equals(PIN)) {
			setContadorIntentos(getContadorIntentos()+1);
			System.out.println("El pin es incorrecto");
			System.out.println("Intentos : " +getContadorIntentos());
			if(getContadorIntentos()==3) System.out.println("Saliendo de la aplicación....");
			else System.out.print("Intentalo nuevamente: ");
			return true;
		}
		else {
			System.out.println("Bienvenido Jonathan Quino");
			contadorIntentos = 3;
			return false;
		}
	}
	
	public void mostrarMenu() {
		System.out.println("*************************************");
		System.out.println("1. Consultar Saldo");
		System.out.println("2. Retirar efectivo");
		System.out.println("3. Histórico de movimientos");
		System.out.println("4. Salir");
		System.out.println("Teclea el numero de la operación que requieres: ");
	}
	
	public boolean ejecutarOpcion(int opcion) {
		boolean salir = false;
		switch(opcion) {
			case 1:
				consultarSaldo();
				ingresarRegistro(1);
				System.out.println("Eliga una opcion para continuar");
				System.out.println("	1. Regresar al menu principal");
				System.out.println("	2. Salir");
				int eleccion;
				try {
					eleccion = entrada.nextInt();
					if (eleccion==1) break;
					else if (eleccion==2) {
						salir = true;
						break;
					}
					else System.out.println("Entrada de datos no válida");
				}
				catch(InputMismatchException e) {
					entrada.next();
					System.out.println("La entrada de datos no es válida");
				}
				break;
			case 2:
				if(!(getSaldo()==0)) {
					System.out.println("Seleccion una de las dos opciones");
					System.out.println("	1. Regresar al menu principal");
					System.out.println("	2. Salir");
					System.out.print("ó Ingrese la cantidad a retirar : ");
					try {
						eleccion = entrada.nextInt();
						if (eleccion==1) break;
						if (eleccion==2) {
							salir = true;
							break;
						}
						if(eleccion!=1 || eleccion!=2) {
								retirarEfectivo(eleccion);
								ingresarRegistro(2);
								if(getSaldo()==0) System.out.println("El saldo es 0, considere que no podrá retirar dinero aunque lo intente");
								break;
						}
					}
					catch(InputMismatchException e) {
						entrada.next();
						System.out.println("La entrada de datos no es válida");
					}
					break;
				}
				else {
					System.out.println("El saldo es 0, ya no puede retirarse dinero");
					break;
				}
			case 3:
				consultarHistorico();
				System.out.println("Seleccion una de las dos opciones");
				System.out.println("	1. Regresar al menu principal");
				System.out.println("	2. Salir");
				try {
					eleccion = entrada.nextInt();
					if (eleccion==1) break;
					else if (eleccion==2) {
						salir = true;
						break;
					}
					else System.out.println("Entrada de datos no válida");
				}
				catch(InputMismatchException e) {
					entrada.next();
					System.out.println("La entrada de datos no es válida");
				}
				break;
			case 4:
				System.out.println("Saliendo de la aplicación......");
				salir = true;
		}
		return salir;
	}
	

	private void consultarHistorico() {
		System.out.println("A continuación se muestra el registro de tus movimientos :");
		System.out.println(getHistoricoMovimientos());
		
	}

	private void ingresarRegistro(int accion) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");
		String fecha = dtf.format(LocalDateTime.now());
		String accionRegistro = accion==1 ? "Accion: Consulta de Saldo - ": "Accion: Retiro de dinero - ";
		String nuevoRegistro = fecha + " - " + accionRegistro + "Saldo Actual: "+ getSaldo() + " mxn\n";
		setHistoricoMovimientos(getHistoricoMovimientos() + nuevoRegistro);
		
	}

	public void consultarSaldo() {
		System.out.println("El saldo actual es : " + getSaldo() + " mxn");
	}
	
	public void retirarEfectivo(int saldoRetirar) {
		int retiro = saldo - saldoRetirar;
		if (retiro<0) {
			System.out.println("No tienes suficiente saldo para retirar");
			
		}
		else {
			System.out.println("El saldo se esta retirando");
			this.saldo = saldo - saldoRetirar;
			System.out.println("Se han retirado " + saldoRetirar + " mxn de tu cuenta");
		}
	}
	
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getHistoricoMovimientos() {
		return historicoMovimientos;
	}

	public void setHistoricoMovimientos(String historicoMovimientos) {
		this.historicoMovimientos = historicoMovimientos;
	}

	public int getContadorIntentos() {
		return contadorIntentos;
	}

	public void setContadorIntentos(int contadorIntentos) {
		this.contadorIntentos = contadorIntentos;
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}

	public String getPIN() {
		return PIN;
	}
	
}
