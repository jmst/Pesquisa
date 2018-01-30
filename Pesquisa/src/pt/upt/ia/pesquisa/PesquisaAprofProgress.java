package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import pt.upt.ia.problema.Solitario;

public class PesquisaAprofProgress {
	private Fronteira f;
	private HashMap<Integer, No> fechados;
	private int contaNos;
	private int maxima;

	public PesquisaAprofProgress(int prof, ArrayList<Estado> i) {
		maxima = prof;
		fechados = new HashMap<Integer, No>();
		f = new Fronteira(new Profundidade());
		for (Estado e : i) {
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
			boolean salta = fechados.containsKey(no.getEstado().getKey());
//			if (fechados.containsKey(no.getEstado().getKey())) {
//				No n = fechados.get(no.getEstado().getKey());
//				if (n.getProfundidade() < no.getProfundidade())
//					salta = true;
//			}
			if (!salta) {
				ArrayList<No> suc = no.getSuc();
				fechados.put(no.getEstado().getKey(), no);
				for (No nosuc : suc) {
					if (nosuc.getEstado().goal()) {
						return nosuc;
					}
					if (no.ciclo(nosuc)) {
						continue;
					}
					No copia = f.contemEstado(nosuc);
					if (copia == null) {
						f.junta(nosuc);
					}
				}
			}
			no = f.cabeca();
			// estatistica
			contaNos++;
			if (contaNos % 10000 == 0) {
				System.out.println(no);
				System.out.println("        nos expandidos: " + String.format("%1$,10d", contaNos) + "    fronteira: "
						+ String.format("%1$,5d", f.getContagem()) + "      limite: " + maxima);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		PesquisaAprofProgress p = null;
		Calendar c = Calendar.getInstance();
		No no = null;
		int limite = 5;
		long t = c.getTimeInMillis();
		System.out.println("#########################################################");
		while (no == null) {
			limite++;
			p = new PesquisaAprofProgress(limite, Solitario.getIniciais());
//			p = new PesquisaAprofProgress(limite, PuzzleOito.getIniciais());
//			 p = new PesquisaAprofProgress( limite, PuzzleSeis.getIniciais());
			// p = new PesquisaAprofProgress( limite, MissCan.getIniciais());
			// p = new PesquisaAprofProgress( limite, ND.getIniciais());
//			 p = new PesquisaAprofProgress( limite, ND6.getIniciais());

			no = p.resolve();
		}
		System.out.println("===========================");
		if (no != null) {
			no.escrevePais();
		} else {
			System.out.println("Sem solução");
		}
		System.out.println("        nos expandidos: " + String.format("%1$,10d", p.getContaNos()) + "    fronteira: "
				+ String.format("%1$,10d", p.getFronteira().getContagem()) + "      limite: " + limite);
		System.out.println("~~~~~~~~ FIM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		c = Calendar.getInstance();
		System.out.println("Demorou: " + (c.getTimeInMillis() - t) + " ms");
	}

	private class Profundidade implements IAlgoritmo {
		public void insere(List<No> lista, No no) {
			if (no.getProfundidade() <= maxima)
				lista.add(0, no);
		}
	}

}
