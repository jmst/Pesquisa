package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

public class PuzzleSeis implements IEstado {
	private int hash = Integer.MAX_VALUE;
	private static final int NPECAS = 9;

	private int tab[] = { 1, 3, 6, 2, 7, 4, 5, 9, 8 };

	public PuzzleSeis(int[] novo) {
		tab = new int[NPECAS];
		for (int j = 0; j < NPECAS; j++) {
			tab[j] = novo[j];
		}
	}

	public PuzzleSeis() {
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> lista = new ArrayList<IEstado>();
		lista.add(new PuzzleSeis());
		return lista;
	}

	@Override
	public boolean goal() {
		for (int i = 0; i < NPECAS-1; i++)
			if (tab[i] > tab[i + 1])
				return false;
		return true;
	}

//	@Override
//	public double h() {
//		int h = 0;
//		for (int c = 0; c < NPECAS-1; c++)
//			if (tab[c] > tab[c + 1]) {
//				h++;
//			}
//		return h;
//	}

	@Override
	public double h() {
		int h = 0;
		for (int c = 0; c < NPECAS-1; c++)
			if (tab[c] > tab[c + 1]) {
				h++;
			}
		return Math.floor(h / 2.0 + 0.6);
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int c = 0; c < NPECAS; c++) {
			result = result * 7 + tab[c];
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != PuzzleSeis.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		PuzzleSeis oo = (PuzzleSeis) o;
		for (int j = 0; j < NPECAS; j++)
			if (tab[j] != oo.get(j))
				return false;
		return true;
	}

	public int get(int j) {
		return tab[j];
	}

	@Override
	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		for (int i = 0; i < NPECAS-1; i++) {
			for (int j = i + 1; j < NPECAS; j++) {
				int[] novo = new int[NPECAS];
				for (int k = 0; k < NPECAS; k++) {
					novo[k] = tab[k];
				}
				int temp = novo[i];
				novo[i] = novo[j];
				novo[j] = temp;
				PuzzleSeis novoEstado = new PuzzleSeis(novo);
				s.add(new Acao(novoEstado, 1));
			}
		}
		return s;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int c = 0; c < NPECAS; c++) {
			sb.append(" " + tab[c]);
		}
		sb.append("\n");
		return new String(sb);
	}

	public static void main(String[] args) {
		PuzzleSeis ps = new PuzzleSeis();
		ArrayList<Acao> s = ps.suc();
		for (Acao ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h());
		}
	}

}
