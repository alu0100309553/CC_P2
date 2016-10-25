package turing;

/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 1
 * Class/Program: Autómata de pila
 * File: FTrans.java
 * Description: Programa que simula el funcionamiento de un autómata de pila.
 * @author Rubén Labrador Páez
 * @version 1.0.0 13/10/2016
 **/
import java.util.ArrayList;

// Clase en la que se definen funciones de transición
public class FTrans {
	private ArrayList<String> lee = new ArrayList<String>();
	private ArrayList<String> escribe = new ArrayList<String>();
	private String next;
	private String movimiento;
	private int pistas;

	FTrans(ArrayList<String> func, int pistas) {
		for (int i = 0; i < pistas; i++) {
			lee.add(func.get(i + 1));
		}
		next = func.get(pistas + 1);
		for (int i = 0; i < pistas; i++) {
			escribe.add(func.get(i + pistas + 2));
		}
		movimiento = func.get(2 * pistas + 2);
		this.pistas = pistas;
	}

	public ArrayList<String> lectura() {
		return lee;
	}

	public ArrayList<String> escritura() {
		return escribe;
	}

	public String getNext() {
		return next;
	}

	public String movimiento() {
		return movimiento;
	}

	public boolean transita(ArrayList<String> lectura) {
		boolean aux = true;
		for (int i = 0; i < pistas; i++) {
			if (!lectura.get(i).equals(lee.get(i))) {
				aux = false;
			}
		}
		return aux;
	}

}
