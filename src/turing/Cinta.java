/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 2
 * Class/Program: Máquina de Turing
 * File: Cinta.java
 * Description: Programa que simula el funcionamiento de una Máquina de Turing
 * @author Rubén Labrador Páez
 * @version 1.0.0 24/10/2016
 **/
package turing;
import java.util.ArrayList;
import java.util.Stack;

/*
 * Clase que representa a la cinta de la máquina,contiene tantos arrays de string como cintas tenga la máquina.
 * y un array de entreros con un puntero para cada cinta.
 * La cinta se ha construido sobre un array de tamaño fijo que se gestiona como si fuese un vector circular, pareciendo 
 * de esa manera que es infinito
 */
public class Cinta {
	private Alfabeto alfabeto;
	private ArrayList<ArrayList<String>> valores;
	private ArrayList<Integer> punteros = new ArrayList<Integer>();
	private int cintas;
	private String blanco;
	private int entradaMax = 0;
	private int punteroMin = 0;
	private final int TAM_CINTA = 1000;

	// Constructor normal
	Cinta(ArrayList<String> alp, int cintas, String blanco) {
		alfabeto = new Alfabeto(alp);
		this.cintas = cintas;
		this.blanco = blanco;
		valores = new ArrayList<ArrayList<String>>(cintas);
		for (int i = 0; i < cintas; i++) {
			valores.add(new ArrayList<String>(TAM_CINTA));
		}
		// inicializando las cintas a blanco
		for (int i = 0; i < TAM_CINTA; i++) {
			for (int j = 0; j < cintas; j++) {
				valores.get(j).add(blanco);
			}
		}
		for (int i = 0; i < cintas; i++) {
			punteros.add(0);
		}
	}

	// Constructor de copia
	Cinta(Cinta original) {
		valores = (ArrayList<ArrayList<String>>) original.valores.clone();
		alfabeto = new Alfabeto(original.alfabeto);
		punteros = (ArrayList<Integer>) original.punteros.clone();
		cintas = original.cintas;
		entradaMax = original.entradaMax;
		punteroMin = original.punteroMin;
	}

	public ArrayList<String> read() {
		ArrayList<String> aux = new ArrayList<String>();
		for (int i = 0; i < cintas; i++) {
			aux.add(valores.get(i).get(punterosNorm().get(i)));
		}
		return aux;
	}

	public void write(ArrayList<String> datos) {
		for (int i = 0; i < cintas; i++) {
			if (alfabeto.contiene(datos.get(i))) {
				valores.get(i).set(punterosNorm().get(i), datos.get(i));
			} else {
				System.err.println("Se ha intentado intruducir un simbolo no perteneciente al alfabeto de la cinta");
				System.exit(1);
			}
		}
	}

	public void moveRight(int cinta) {
		punteros.set(cinta, (punteros.get(cinta) - 1));
		if (punteroMin > punteros.get(cinta)){
			punteroMin = punteros.get(cinta);
		}
	}

	public void moveLeft(int cinta) {

		punteros.set(cinta, (punteros.get(cinta) + 1));
		if (entradaMax < punteros.get(cinta)){
			entradaMax = punteros.get(cinta);
		}

	}

	public void setCinta(ArrayList<String> cadenas) {
		for (int i = 0; i < cadenas.size(); i++) {
			int k = 0;
			for (int j = (cadenas.get(i).length() - 1); j >= 0; j--) {

				if (alfabeto.contiene("" + cadenas.get(i).charAt(j))) {
					valores.get(i).set(k, "" + cadenas.get(i).charAt(j));
				} else {
					System.err
					.println("Se ha intentado intruducir un simbolo no perteneciente al alfabeto de la cinta");
					System.exit(1);
				}
				k++;
			}
		}
		for (String aux : cadenas) {
			if (aux.length() > entradaMax) {
				entradaMax = aux.length();
			}
		}
		for (int i = 0; i < cintas ; i++){
			punteros.set(i, entradaMax-1);
		}

	}

	public String toString() {
		String aux = "";
		for (int i = 0; i < cintas; i++) {
			for (int j = entradaMax ; j >= punteroMin; j--) {
				if (pNorm(j) == punterosNorm().get(i)) {
					aux += "[" + valores.get(i).get(pNorm(j)) + "] ";
				} else {
					aux += valores.get(i).get(pNorm(j)) + " ";
				}
			}
			aux += " | ";
		}
		return aux;
	}

	private ArrayList <Integer> punterosNorm(){
		ArrayList <Integer> aux = new ArrayList <Integer>();
		for (int p : punteros){
			aux.add(((p%TAM_CINTA)+TAM_CINTA)%TAM_CINTA);
		}
		return aux;
	}

	private int pNorm(int n){
		return ((n%TAM_CINTA)+TAM_CINTA)%TAM_CINTA;
	}
}
