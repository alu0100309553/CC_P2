package turing;

/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 1
 * Class/Program: Autómata de pila
 * File: Cinta.java
 * Description: Programa que simula el funcionamiento de un autómata de pila.
 * @author Rubén Labrador Páez
 * @version 1.0.0 13/10/2016
 **/
import java.util.ArrayList;
import java.util.Stack;
/*
 * Clase que representa a la cinta del autómata, contiene un ArrayList de String
 * que guarda los caracteres de la cinta, un puntero que apunta a la posición de
 * y el alfabeto de la cinta *
 */
public class Cinta {
	private Alfabeto alphabeto;
	private ArrayList <ArrayList <String>> valores;
	private ArrayList <Integer> punteros  = new ArrayList<Integer>();
	private int cintas;
	private String blanco;
	private int entradaMax = 0;

	// Constructor normal
	Cinta(ArrayList<String> alp, int cintas, String blanco) {
		alphabeto = new Alfabeto (alp);
		this.cintas = cintas;
		this.blanco = blanco;
		valores = new ArrayList <ArrayList<String>>(cintas);
		for (int i = 0; i < cintas; i++){
			valores.add(new ArrayList <String>(500));
		}
		//inicializando las cintas a blanco
		for (int i = 0; i < 500; i++){
			for (int j = 0; j < cintas; j++){
				valores.get(j).add(blanco);
			}
		}
		for (int i = 0; i < cintas; i++){
			punteros.add(0);
		}
	}

	// Constructor de copia
	Cinta(Cinta original) {
		valores = (ArrayList<ArrayList<String>>) original.valores.clone();
		alphabeto = new Alfabeto(original.alphabeto);
		punteros =  (ArrayList<Integer>) original.punteros.clone();
		cintas = original.cintas;
		entradaMax = original.entradaMax;
	}

	public ArrayList <String> read() {
		ArrayList <String> aux = new ArrayList <String>();
		for (int i = 0 ; i < cintas ; i++){
			aux.add(valores.get(i).get(punteros.get(i)));
		}
		return aux;
	}
	public void write(ArrayList <String> datos) {
		for (int i = 0 ; i < cintas ; i++){
			valores.get(i).set(punteros.get(i), datos.get(i));
		}
	}
	public void moveRight(){
		for (int i = 0 ; i < cintas ; i++){
			punteros.set(i, (500+punteros.get(i)-1)%500);
		}
	}
	public void moveLeft(){
		for (int i = 0 ; i < cintas ; i++){
			punteros.set(i, (punteros.get(i)+1)%500);
		}
	}
	public void setCinta (ArrayList <String> cadenas){
		for (int i = 0; i < cadenas.size(); i++){
			int k = 0;
			for (int j = (cadenas.get(i).length() - 1); j >= 0; j --){
				valores.get(i).set(k, ""+cadenas.get(i).charAt(j));
				k++;
			}
		}
		for (String aux : cadenas){
			if (aux.length() > entradaMax){
				entradaMax = aux.length();
			}
		}

	}
	public String toString (){
		String aux = "";
		for (int i = 0; i < cintas; i++){
			for (int j = entradaMax+1; j >=0; j--){
				if (j == punteros.get(i)){
					aux += "["+valores.get(i).get(j)+"] ";
				} 
				else {
					aux += valores.get(i).get(j)+" ";
				}
			}
			aux += " | ";
		}
		return aux;
	}
}
