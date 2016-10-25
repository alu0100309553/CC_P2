package turing;
/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 2
 * Class/Program: Máquina de Turing
 * File: Alfabeto.java
 * Description: Programa que simula el funcionamiento de una Máquina de Turing
 * @author Rubén Labrador Páez
 * @version 1.0.0 24/10/2016
 **/

import java.util.ArrayList;
/*
 * Clase empleada para guardar los alfabetos de la cinta y de entrada.
 */

public class Alfabeto {
	private ArrayList<String> alfabeto = new ArrayList<String>();

	//Constructor normal
	Alfabeto(ArrayList<String> entrada) {
		alfabeto = entrada;

	}

	//Constructor de copia
	Alfabeto(Alfabeto original) {
		alfabeto = (ArrayList<String>) original.alfabeto.clone();
	}

	// Método bool que devuelve true si el afabeto contiene el caracter que se le pasa por parámetro
	public boolean contiene(String caracter) {
		boolean aux = false;
		for (String a : alfabeto) {
			if (a.equals(caracter)) {
				aux = true;
			}
		}
		return aux;
	}
}
