package turing;

/*
 * Author: Rubén Labrador Páez.
 * Email: alu0100309553@ull.edu.es
 * Tit: Grado Ingeniería Informática - Universidad de La Laguna
 * Course: 4 - Computación
 * Subject: Complejidad Computacional
 * Practice: 1
 * Class/Program: Autómata de pila
 * File: Reader.java
 * Description: Programa que simula el funcionamiento de un autómata de pila.
 * @author Rubén Labrador Páez
 * @version 1.0.0 13/10/2016
 **/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Clase para lectura de fichero con la definición del autómata

public class Reader {
	private FileReader fin = null;
	private BufferedReader bin = null;
	public ArrayList<ArrayList<String>> programa = new ArrayList<ArrayList<String>>();

	Reader(String file) {
		try {
			fin = new FileReader(file);
			bin = new BufferedReader(fin);
			String line = bin.readLine();
			while (line != null) {
				String[] token = line.split("\\s");
				ArrayList<String> sentencia = new ArrayList<String>();
				for (String st : token) {
					if (st.matches("#.*") | token.length == 0) {
						break;
					} else {
						sentencia.add(st);
					}
				}
				if (sentencia.size() >= 1) {
					programa.add(sentencia);
				}
				line = bin.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
