package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

public class PuzzleCruz12 implements IEstado {
	private int tab[][];

	public PuzzleCruz12(int[][] novo) {
		tab = new int[7][7];
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				tab[i][j] = novo[i][j];
			}
	}

	public PuzzleCruz12() {
		tab = new int[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				tab[i][j] = -1;
			}
		}
		tab[0][3] = 0;
		tab[1][3] = 1;
		tab[2][3] = 0;
		tab[3][3] = 2;
		tab[4][3] = 0;
		tab[5][3] = 1;
		tab[6][3] = 0;
		tab[3][0] = 1;
		tab[3][1] = 2;
		tab[3][2] = 2;
		tab[3][4] = 2;
		tab[3][5] = 1;
		tab[3][6] = 0;
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> lista = new ArrayList<IEstado>();
		lista.add(new PuzzleCruz12());
		return lista;
	}

	@Override
	public boolean goal() {
		int [][] tg = new int[7][7];
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				tg[i][j] = -1;
		tg[0][3] = 2;
		tg[1][3] = 1;
		tg[2][3] = 0;
		tg[3][3] = 0;
		tg[4][3] = 0;
		tg[5][3] = 1;
		tg[6][3] = 2;
		tg[3][0] = 2;
		tg[3][1] = 1;
		tg[3][2] = 0;
		tg[3][3] = 0;
		tg[3][4] = 0;
		tg[3][5] = 1;
		tg[3][6] = 2;
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				if (tab[i][j] != tg[i][j])
					return false;
		return true;
	}

	private int[][] copia(int[][] tab) {
		int[][] novo = new int[7][7];
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				novo[i][j] = tab[i][j];
			}
		return novo;
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int c = 0; c < 7; c++) {
			result = result * 7 + tab[3][c];
		}
		for (int l = 0; l < 7; l++) {
			if (l != 3)
				result = result * 7 + tab[l][3];
		}
		return result;
	}

	private int get(int i, int j) {
		return tab[i][j];
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != PuzzleCruz12.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		PuzzleCruz12 oo = (PuzzleCruz12) o;
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++)
				if (tab[i][j] != oo.get(i, j))
					return false;
		return true;
	}
	
	@Override
	public double h() {
		double h = 0;
		for (int p = 0; p < 7; p++) {
			if (tab[3][p] > 0) {
				int dif = tab[3][p] == 2 ? 3- Math.abs(3-p) : Math.abs(2 - Math.abs(3-p));
				h += dif;
			}
			if (p != 3 && tab[p][3] > 0) {
				int dif = tab[p][3] == 2 ? 3- Math.abs(3-p) : Math.abs(2 - Math.abs(3-p));
				h += dif;
			}
		}
		return h;
	}

	@Override
	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		for (int p = 0; p < 7; p++) {
			if (tab[3][p] == 0) {
				if (p > 0 && tab[3][p - 1] > 0) {
					// da esquerda
					int[][] novo = copia(tab);
					novo[3][p] = novo[3][p - 1];
					novo[3][p - 1] = 0;
					PuzzleCruz12 novoEstado = new PuzzleCruz12(novo);
					s.add(new Acao(novoEstado, 1));
				}
				if (p < 6 && tab[3][p + 1] > 0) {
					// da direita
					int[][] novo = copia(tab);
					novo[3][p] = novo[3][p + 1];
					novo[3][p + 1] = 0;
					PuzzleCruz12 novoEstado = new PuzzleCruz12(novo);
					s.add(new Acao(novoEstado, 1));
				}
			}
			if (tab[p][3] == 0) {
				if (p > 0 && tab[p - 1][3] > 0) {
					// de cima
					int[][] novo = copia(tab);
					novo[p][3] = novo[p - 1][3];
					novo[p - 1][3] = 0;
					PuzzleCruz12 novoEstado = new PuzzleCruz12(novo);
					s.add(new Acao(novoEstado, 1));
				}
				if (p < 6 && tab[p + 1][3] > 0) {
					// de baixo
					int[][] novo = copia(tab);
					novo[p][3] = novo[p + 1][3];
					novo[p + 1][3] = 0;
					PuzzleCruz12 novoEstado = new PuzzleCruz12(novo);
					s.add(new Acao(novoEstado, 1));
				}
			}
		}
		return s;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int l = 0; l < 7; l++) {
			for (int c = 0; c < 7; c++) {
				if (tab[l][c] < 0) {
					sb.append("  ");
				} else if (tab[l][c] == 0) {
					sb.append(" -");
				} else {
					sb.append(" " + tab[l][c]);
				}
			}
			sb.append("\n");
		}
		return new String(sb);
	}

	public String repr() {
		return toString();
	}

	public static void main(String[] args) {
		PuzzleCruz12 ps = new PuzzleCruz12();
		System.out.println("" + ps + "  " + ps.h());
		System.out.println("================");
		ArrayList<Acao> s = ps.suc();
		for (Acao ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h() + "\n----------------\n");
		}
	}
}
