package pt.upt.ia.pesquisa;

import java.util.ArrayList;
import java.util.List;

public class Fronteira {
	private ArrayList<No> lista;
	private IAlgoritmo alg;

	public Fronteira(IAlgoritmo alg) {
		this.alg = alg;
		lista = new ArrayList<No>();
	}

	public void junta(No no) {
		alg.insere(lista, no);
	}

	public No cabeca() {
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista.remove(0);
		}
	}

	public No contemEstado(No n) {
		for (No fno : lista) {
			if (fno.getEstado().equals(n.getEstado()))
				return fno;
		}
		return null;
	}

	public void remove(No n) {
		lista.remove(n);
	}

	public int getContagem() {
		return lista.size();
	}

	public void imprime() {
		for (No n : lista) {
			System.out.print("  "+n.repr());
		}
		System.out.println();
	}

}
