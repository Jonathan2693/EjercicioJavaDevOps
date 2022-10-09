package com.devops;

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
				ingresarRegistro("Se ha consultado saldo (opción 1)\n");
				break;
			case 2:
				if(!(getSaldo()==0)) {
					System.out.print("Ingrese la cantidad a retirar : ");
					try {
						retirarEfectivo(entrada.nextInt());
						ingresarRegistro("Se ha retidado dinero (opción 2)\n");
						if(getSaldo()==0) System.out.println("El saldo es 0, considere que no podrá retirar dinero aunque lo intente");
						break;
					}
					catch(InputMismatchException e) {
						entrada.next();
						System.out.println("La entrada de retiro no es valida");
						break;
					}
				}
				else {
					System.out.println("El saldo es 0, ya no puede retirarse dinero");
					break;
				}
			case 3:
				consultarHistorico();
				ingresarRegistro("Se ha consultado el histórico (opción 2)\n");
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

	private void ingresarRegistro(String registro) {
		setHistoricoMovimientos(getHistoricoMovimientos() + registro);
		
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
