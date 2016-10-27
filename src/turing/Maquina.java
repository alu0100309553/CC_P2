package turing;
/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 2
 * Class/Program: Máquina de Turing
 * File: Maquina.java
 * Description: Programa que simula el funcionamiento de una Máquina de Turing
 * @author Rubén Labrador Páez
 * @version 1.0.0 24/10/2016
 **/

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/*
 * Clase que representa a la Máquina de Turing, tiene un constructor que posee como parámetro un array con los datos de la máquina, 
 * y un método run, que ejecuta la máquina con la cadena o cadenas pasada
 */
public class Maquina {
	private Hashtable<String, Estado> estados = new Hashtable<String, Estado>();
	private Estado actual;
	private Cinta cinta;
	private Stack<Transcicion> trans = new Stack<Transcicion>(); //Pila que almacena posibles transiciones por si la máquina fuese no determinista valorar todas las opciones
	private Alfabeto alfabeto;
	private String blanco;
	private int pistas;

	//Constructor
	Maquina(ArrayList<ArrayList<String>> programa) {
		for (String est : programa.get(0)) {
			estados.put(est, new Estado(est));
		}
		alfabeto = new Alfabeto(programa.get(1));
		actual = estados.get(programa.get(3).get(0));
		blanco = programa.get(4).get(0);
		for (String est : programa.get(5)) {
			if (estados.containsKey(est)) {
				estados.get(est).setFinal();
			} else {
				System.err.println("El conjunto de estados finales contiene upn estado no existente: " + est);
				System.exit(1);
			}
		}
		pistas = Integer.parseInt(programa.get(6).get(0));
		cinta = new Cinta(programa.get(2), pistas, blanco);
		for (int i = 7; i < programa.size(); i++) {
			if (estados.containsKey(programa.get(i).get(0))) {
				estados.get(programa.get(i).get(0)).addFTran(programa.get(i), pistas);
			} else {
				System.err.println("Existe una función de transición que transita desde un estado no existente: "
						+ programa.get(i));
				System.exit(1);
			}
		}
	}

	//Método que ejecuta la máquina, recibe como parametros las cadenas de entrada y si se realiza traza o no, 
	//devuelve true a false si la cadena es aceptada o no.
	public boolean run(ArrayList<String> cadenas, boolean traza) {
		
		//Comprobar la cadena de entrada si corresponde con el alfabeto de entrada
		for (int i = 0; i < cadenas.size(); i++) {
			for (int j = (cadenas.get(i).length() - 1); j >= 0; j--) {
				if (!alfabeto.contiene("" + cadenas.get(i).charAt(j))) {
					System.err
					.println("Se ha intentado intruducir un simbolo no perteneciente al alfabeto de entrada");
					System.exit(1);
				} 
			}
		}

		cinta.setCinta(cadenas);
		boolean ejecutando = true;
		boolean aceptada = false;

		while (ejecutando) {
			ArrayList<FTrans> transPosActual = actual.explorar(cinta.read());
			if (traza) {
				String aux = "";
				for (FTrans t : transPosActual) {
					aux += t.getNext() + " ";
				}

				System.out.println(actual.getId() + " | " + cinta + aux);
			}
			for (FTrans tran : transPosActual) {
				trans.push(new Transcicion(new Cinta(cinta), tran));
			}
			if (transPosActual.isEmpty() && actual.esFinal()) {
				ejecutando = false;
				aceptada = true;
			} else if (transPosActual.isEmpty() && !actual.esFinal() && trans.isEmpty()) {
				ejecutando = false;
				aceptada = false;
			} else {
				Transcicion aux = trans.pop();
				if (estados.containsKey(aux.getFtran().getNext())) {
					actual = estados.get(aux.getFtran().getNext());
				} else {
					System.err
					.println("Se ha intentado transitar a un estado no existente: " + aux.getFtran().getNext());
					System.exit(1);
				}
				cinta = aux.getCinta();
				cinta.write(aux.getFtran().escritura());
				if (aux.getFtran().movimiento().equals("R")) {
					cinta.moveRight();
				} else if (aux.getFtran().movimiento().equals("L")) {
					cinta.moveLeft();
				} else if (aux.getFtran().movimiento().equals("S")){

				} else {
					System.err
					.println("Movimiento no permitido: " + aux.getFtran().movimiento());
					System.exit(1);
				}
			}
		}
		return aceptada;
	}

	public int getCintas() {
		return pistas;
	}
}
