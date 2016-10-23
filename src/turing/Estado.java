package turing;

/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 1
 * Class/Program: Autómata de pila
 * File: Estados.java
 * Description: Programa que simula el funcionamiento de un autómata de pila.
 * @author Rubén Labrador Páez
 * @version 1.0.0 13/10/2016
 **/
import java.util.ArrayList;

public class Estado {
	// Array de funciones de transición para el estado
	private ArrayList<FTrans> ftrans = new ArrayList<FTrans>();
	private boolean esFinal = false;
	private String id;

	// Constructor
	Estado(String identificador) {
		id = identificador;
	}

	public void setFinal() {
		esFinal = true;
	}

	public void addFTran(ArrayList<String> func, int cintas) {
		ftrans.add(new FTrans(func, cintas));
	}

	public ArrayList<FTrans> getFTrans() {
		return ftrans;
	}

	public boolean esFinal() {
		return esFinal;
	}

	// Método para explorar transiciones posibles, recibe el caracter en la pila
	// y la cinta
	public ArrayList<FTrans> explorar(ArrayList<String> lectura) {
		ArrayList<FTrans> aux = new ArrayList<FTrans>();
		for (FTrans func : ftrans) {
			if (func.transita(lectura)) {
				aux.add(func);
			}
		}
		return aux;
	}

	public String getId() {
		return id;
	}
}
