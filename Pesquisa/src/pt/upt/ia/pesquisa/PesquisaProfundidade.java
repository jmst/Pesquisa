package pt.upt.ia.pesquisa;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import pt.upt.ia.problema.ND6;

public class PesquisaProfundidade {
	private Fronteira f;
	private HashMap<Integer, EstadoProblema> fechados;
	private int contaNos;

	public PesquisaProfundidade(ArrayList<EstadoProblema> i) {
		fechados = new HashMap<Integer, EstadoProblema>();
		f = new Fronteira(new Profundidade());
		for (EstadoProblema e : i) {
			f.junta(new No(e, null, 0));
		}
		contaNos = 0;
	}

	public Fronteira getFronteira() {
		return f;
	}

	public int getContaNos() {
		return contaNos;
	}

	public No resolve() {
		//
		No no = f.cabeca();
		while (no != null && !no.getEstado().goal()) {
			boolean salta = false;
			if (fechados.containsKey(no.getEstado().getKey())) {
				salta = true;
			}
			if (! salta) {
				ArrayList<No> suc = no.getSuc();
				fechados.put(no.getEstado().getKey(), no.getEstado());
				for (No nosuc : suc) {
					if (nosuc.getEstado().goal()) {
						return nosuc;
					}
					if (no.ciclo(nosuc)) {
						continue;
					}
					f.junta(nosuc);
				}
			}
			no = f.cabeca();
			// estatistica
			contaNos++;
			if (contaNos % 10000 == 0) {
				System.out.println(no);
				System.out.println("        nos expandidos: " + String.format("%1$,10d", contaNos) + "    fronteira: "
						+ String.format("%1$,10d", f.getContagem()));
			}
		}
		return null;
	}

	public static void main(String[] args) {
//		PesquisaProfundidade p = new PesquisaProfundidade(PuzzleOito.getIniciais());
//		PesquisaProfundidade p = new PesquisaProfundidade(PuzzleSeis.getIniciais());
//		PesquisaProfundidade p = new PesquisaProfundidade(MissCan.getIniciais());
//		PesquisaProfundidade p = new PesquisaProfundidade(ND.getIniciais());
		PesquisaProfundidade p = new PesquisaProfundidade(ND6.getIniciais());

		Calendar c = Calendar.getInstance();
		long t = c.getTimeInMillis();
		System.out.println("#########################################################");
		No no = p.resolve();
		System.out.println("===========================");
		if (no != null) {
			no.escrevePais();
		} else {
			System.out.println("Sem solução");
		}
		System.out.println("        nos expandidos: " + String.format("%1$,10d", p.getContaNos()) + "    fronteira: "
				+ String.format("%1$,10d", p.getFronteira().getContagem()));
		System.out.println("~~~~~~~~ FIM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		c = Calendar.getInstance();
		System.out.println("Demorou: " + (c.getTimeInMillis() - t) + " ms");
	}

	private class Profundidade implements IAlgoritmo {
		public void insere(List<No> lista, No no) {
			lista.add(0,no);
		}
	}

}
