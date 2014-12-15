package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import pt.upt.ia.problema.ND6;

public class PesquisaLargura {
	private Fronteira f;
	private HashMap<Integer, EstadoProblema> fechados;
	private int contaNos;

	public PesquisaLargura(ArrayList<EstadoProblema> i) {
		fechados = new HashMap<Integer, EstadoProblema>();
		f = new Fronteira(new Largura());
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
			ArrayList<No> suc = no.getSuc();
			fechados.put(no.getEstado().getKey(), no.getEstado());
			for (No nosuc : suc) {
				if (nosuc.getEstado().goal()) {
					return nosuc;
				}
				if (fechados.containsKey(nosuc.getEstado().getKey())) {
					continue;
				}
				if (no.ciclo(nosuc)) {
					continue;
				}
				f.junta(nosuc);
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
//		PesquisaLargura p = new PesquisaLargura(PuzzleOito.getIniciais());
//		PesquisaLargura p = new PesquisaLargura(PuzzleSeis.getIniciais());
//		PesquisaLargura p = new PesquisaLargura(MissCan.getIniciais());
//		PesquisaLargura p = new PesquisaLargura(ND.getIniciais());
		PesquisaLargura p = new PesquisaLargura(ND6.getIniciais());

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

	private class Largura implements IAlgoritmo {
		public void insere(List<No> lista, No no) {
			lista.add(no);
		}
	}

}
