package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import pt.upt.ia.problema.ND;

public class PesquisaSofrega {
	private Fronteira f;
	private HashSet<IEstado> fechados;
	private int contaNos;

	public PesquisaSofrega(ArrayList<IEstado> i) {
		fechados = new HashSet<IEstado>();
		f = new Fronteira(new Sofrega());
		for (IEstado e : i) {
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
			if (!fechados.contains(no.getEstado())) {
				ArrayList<No> suc = no.getSuc();
				fechados.add(no.getEstado());
				for (No nosuc : suc) {
					if (nosuc.getEstado().goal()) {
						return nosuc;
					}
					if (no.ciclo(nosuc)) {
						continue;
					}
					// j� est� na lista de n�s fechados?
					if (fechados.contains(nosuc.getEstado())) {
						continue;
					}
					// j� est� na lista fronteira?
					No copia = f.contemEstado(nosuc);
					if (copia == null) {
						f.junta(nosuc);
					}
				}
			}
			no = f.cabeca();
			// estatistica
			contaNos++;
			// if (contaNos % 10000 == 0) {
			// System.out.println(no);
			// System.out.println(" nos expandidos: " + String.format("%1$,10d",
			// contaNos) + " fronteira: "
			// + String.format("%1$,10d", f.getContagem()));
			// }
		}
		return null;
	}

	public static void main(String[] args) {
		// PesquisaSofrega p = new PesquisaSofrega(RatoQueijo.getIniciais());
//		PesquisaSofrega p = new PesquisaSofrega(PuzzleOito.getIniciais());
		// PesquisaSofrega p = new PesquisaSofrega(PuzzleSeis.getIniciais());
		// PesquisaSofrega p = new PesquisaSofrega(MissCan.getIniciais());
		 PesquisaSofrega p = new PesquisaSofrega(ND.getIniciais());
		// PesquisaSofrega p = new PesquisaSofrega(ND6.getIniciais());
		// PesquisaSofrega p = new PesquisaSofrega(Baldes49.getIniciais());

		Calendar c = Calendar.getInstance();
		long t = c.getTimeInMillis();
		System.out.println("#########################################################");
		No no = p.resolve();
		System.out.println("===========================");
		if (no != null) {
			no.escrevePais();
		} else {
			System.out.println("Sem solu��o");
		}
		System.out.println("        nos expandidos: " + String.format("%1$,10d", p.getContaNos()) + "    fronteira: "
				+ String.format("%1$,10d", p.getFronteira().getContagem()));
		System.out.println("~~~~~~~~ FIM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		c = Calendar.getInstance();
		System.out.println("Demorou: " + (c.getTimeInMillis() - t) + " ms");
	}

	private class Sofrega implements IAlgoritmo {
		public void insere(List<No> lista, No no) {
			for (int i = 0; i < lista.size(); i++) {
				No n = lista.get(i);
				if (n.h() >= no.h()) {
					lista.add(i, no);
					return;
				}
			}
			lista.add(no);
		}
	}

}
