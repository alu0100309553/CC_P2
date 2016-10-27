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
	//private String movimiento;
	private ArrayList<String> movimiento  = new ArrayList<String>();
	private int cintas;

	FTrans(ArrayList<String> func, int cintas) {
		for (int i = 0; i < cintas; i++) {
			lee.add(func.get(i + 1));
		}
		next = func.get(cintas + 1);
		for (int i = 0; i < cintas; i++) {
			escribe.add(func.get(i + cintas + 2));
		}
		for (int i = 0; i < cintas; i++) {
			movimiento.add(func.get(2 * cintas + 2 + i));
		}
		this.cintas = cintas;
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

	public String movimiento(int cintaN) {
		return movimiento.get(cintaN);
	}

	public boolean transita(ArrayList<String> lectura) {
		boolean aux = true;
		for (int i = 0; i < cintas; i++) {
			if (!lectura.get(i).equals(lee.get(i))) {
				aux = false;
			}
		}
		return aux;
	}

}
