package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.upt.ia.problema.Grafo;

// Pesquisa Custo Uniforme em grafo
public class PesquisaCustoUniforme {

		private Fronteira f;
		private Set<EstadoProblema> fechados;
		private int contaNos;

		public PesquisaCustoUniforme(ArrayList<EstadoProblema> i) {
			fechados = new HashSet<EstadoProblema>();
			f = new Fronteira(new CustoUniforme());
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
			while (no != null) {
				if (no.getEstado().goal()) {
					return no;
				}
				ArrayList<No> suc = no.getSuc();
				fechados.add(no.getEstado());
				for (No nosuc : suc) {
					if (fechados.contains(nosuc.getEstado())) {
						continue;
					}
					No copia = f.contemEstado( nosuc);
					if (copia == null) {
						f.junta(nosuc);
						continue;
					}
					if (copia.g() > nosuc.g()) {
						f.remove( copia);
						f.junta( nosuc);
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
			 PesquisaCustoUniforme p = new PesquisaCustoUniforme(Grafo.getIniciais());

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

		private class CustoUniforme implements IAlgoritmo {
			public void insere(List<No> lista, No no) {
				for (int i = 0; i < lista.size(); i++) {
					No n = lista.get(i);
					if (n.g() >= no.g()) {
						lista.add(i, no);
						return;
					}
				}
				lista.add(no);
			}
		}

	}
