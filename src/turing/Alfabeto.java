package turing;

import java.util.ArrayList;

public class Alfabeto {
	private ArrayList <String> alfabeto = new ArrayList <String>();
	Alfabeto (ArrayList <String> entrada){
		alfabeto = entrada;

	}
	Alfabeto (Alfabeto original){
		alfabeto = (ArrayList<String>)original.alfabeto.clone();
	}
	public boolean pertenece (String caracter){
		boolean aux = false;
		for (String a : alfabeto){
			if (a.equals(caracter)){
				aux = true;
			}
		}
		return aux;
	}
}

