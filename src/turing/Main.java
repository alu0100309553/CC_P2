package turing;

/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 1
 * Class/Program: Autómata de pila
 * File: Main.java
 * Description: Programa que simula el funcionamiento de un autómata de pila.
 * @author Rubén Labrador Páez
 * @version 1.0.0 13/10/2016
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		boolean aceptada = false;
		boolean traza = false;
		Reader rd = new Reader(args[0]);
		Maquina mt = new Maquina(rd.programa);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("¿Realizar traza (S/N)?");
		String command = br.readLine();
		if (command.equals("S")) {
			traza = true;
		} else {
			traza = false;
		}
		ArrayList<String> cadenas = new ArrayList<String>();
		for (int i = 0; i < mt.getCintas(); i++) {
			System.out.println("Indique la cadena en la pista " + i + ":");
			cadenas.add(br.readLine());
		}

		aceptada = mt.run(cadenas, traza);

		if (aceptada) {
			System.out.println("La cadena es aceptada");
		} else {
			System.out.println("La cadena no es aceptada");
		}
	}
}
