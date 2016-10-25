package turing;
/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 2
 * Class/Program: Máquina de Turing
 * File: Transicion.java
 * Description: Programa que simula el funcionamiento de una Máquina de Turing
 * @author Rubén Labrador Páez
 * @version 1.0.0 24/10/2016
 **/

// Clase empleada para almacenar posibles transiciones de la máquina,
// Contiene una función de transición, y el estado de la cinta en el instante en que se generó la transición.
public class Transcicion {
	private FTrans ftran = null;
	private Cinta cinta = null;
	
	Transcicion(Cinta c, FTrans n) {
		ftran = n;
		cinta = new Cinta(c);
	}

	public FTrans getFtran() {
		return ftran;
	}

	public Cinta getCinta() {
		return cinta;
	}
}
