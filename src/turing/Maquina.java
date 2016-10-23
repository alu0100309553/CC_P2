package turing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class Maquina {
	private Hashtable<String, Estado> estados = new Hashtable<String, Estado>();
	private Estado actual;
	private Cinta cinta;
	private Stack<Transcicion> trans = new Stack<Transcicion>();
	private Alfabeto alfabeto;
	private String blanco;
	private int cintas;

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
				System.err.println("El conjunto de estados finales contiene un estado no existente: " + est);
				System.exit(1);
			}
		}
		cintas = Integer.parseInt(programa.get(6).get(0));
		cinta = new Cinta(programa.get(2), cintas, blanco);
		for (int i = 7; i < programa.size(); i++) {
			if (estados.containsKey(programa.get(i).get(0))) {
				estados.get(programa.get(i).get(0)).addFTran(programa.get(i), cintas);
			} else {
				System.err.println("Existe una función de transición que transita desde un estado no existente: "
						+ programa.get(i));
				System.exit(1);
			}
		}
	}

	public boolean run(ArrayList<String> cadenas, boolean traza) {
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
				}
			}
		}
		return aceptada;
	}

	public int getCintas() {
		return cintas;
	}
}
