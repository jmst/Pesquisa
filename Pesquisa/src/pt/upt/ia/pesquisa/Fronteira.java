package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Fronteira {
	private ArrayList<No> lista;
	private Set<IEstado> estados;
	private IAlgoritmo alg;
	private static final boolean USAR_SET_ESTADOS = true;

	public Fronteira(IAlgoritmo alg) {
		this.alg = alg;
		lista = new ArrayList<No>();
		if (USAR_SET_ESTADOS) {
			estados = new HashSet<IEstado>();
		}
	}

	public void junta(No no) {
		if (USAR_SET_ESTADOS) {
			if (!estados.contains(no.getEstado())) {
				alg.insere(lista, no);
				estados.add(no.getEstado());
			}
		} else {
			alg.insere(lista, no);
		}
	}

	public No cabeca() {
		if (lista.isEmpty()) {
			return null;
		} else {
			No c = lista.remove(0);
			if (USAR_SET_ESTADOS) {
				estados.remove(c.getEstado());
			}
			return c;
		}
	}

	public No contemEstado(No n) {
		if (USAR_SET_ESTADOS) {
			if (estados.contains(n.getEstado())) {
				for (No fno : lista) {
					if (fno.getEstado().equals(n.getEstado()))
						return fno;
				}
			}
		} else {
			for (No fno : lista) {
				if (fno.getEstado().equals(n.getEstado()))
					return fno;
			}
		}
		return null;
	}

	public void remove(No n) {
		lista.remove(n);
		if (USAR_SET_ESTADOS) {
			estados.remove(n.getEstado());
		}
	}

	public int getContagem() {
		return lista.size();
	}

	public void imprime() {
		for (No n : lista) {
			System.out.print("  " + n.repr());
		}
		System.out.println();
	}

}
