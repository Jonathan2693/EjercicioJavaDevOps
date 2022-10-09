package com.devops;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cajero {

	private int saldo;
	private String historicoMovimientos;
	private final byte maxIntentos;
	private int contadorIntentos;
	private Scanner entrada;
	
	public Cajero(int saldoInicial) {
		this.saldo = saldoInicial;
		this.contadorIntentos = 0;
		this.maxIntentos = 3;
		this.entrada = new Scanner(System.in);
		this.historicoMovimientos = "";
		System.out.println("Bienvenido al Cajero Automático");
		System.out.println("Jonathan Quino");
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
				System.out.print("Ingrese la cantidad a retirar : ");
				try {
					retirarEfectivo(entrada.nextInt());
					ingresarRegistro("Se ha retidado dinero (opción 2)\n");
					break;
				}
				catch(InputMismatchException e) {
					entrada.next();
					System.out.println("La entrada de retiro no es valida");
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

	public byte getMaxIntentos() {
		return maxIntentos;
	}

	public Scanner getEntrada() {
		return entrada;
	}

	public void setEntrada(Scanner entrada) {
		this.entrada = entrada;
	}
	
}
