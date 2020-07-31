package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

public class PuzzleCruz implements IEstado {
	private int tab[][];

	public PuzzleCruz(int[][] novo) {
		tab = new int[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				tab[i][j] = novo[i][j];
			}
	}

	public PuzzleCruz() {
		tab = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tab[i][j] = -1;
			}
		}
		tab[0][2] = 0;
		tab[1][2] = 0;
		tab[2][2] = 3;
		tab[3][2] = 0;
		tab[4][2] = 1;
		tab[2][0] = 5;
		tab[2][1] = 2;
		tab[2][3] = 4;
		tab[2][4] = 0;
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> lista = new ArrayList<IEstado>();
		lista.add(new PuzzleCruz());
		return lista;
	}

	@Override
	public boolean goal() {
		boolean res = true;
		for (int i = 0; i < 5; i++)
			if (tab[2][i] != i + 1)
				res = false;
		return res;
	}

	private int[][] copia(int[][] tab) {
		int[][] novo = new int[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				novo[i][j] = tab[i][j];
			}
		return novo;
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int c = 0; c < 5; c++) {
			result = result * 10 + tab[2][c];
		}
		for (int l = 0; l < 5; l++) {
			if (l != 2)
				result = result * 10 + tab[l][2];
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
		if (o.getClass() != PuzzleCruz.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		PuzzleCruz oo = (PuzzleCruz) o;
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				if (tab[i][j] != oo.get(i, j))
					return false;
		return true;
	}

	@Override
	public double h() {
		double h = 0;
		for (int p = 0; p < 5; p++) {
			if (tab[2][p] > 0) {
				int dif = Math.abs(p + 1 - tab[2][p]);
				h += dif;
			}
			int peca = tab[p][2];
			if (p != 2 && peca > 0) {
				int dif = Math.abs(p - 2) + Math.abs(peca - 1 - 2);
				h += dif;
			}
		}
		return h;
	}

	@Override
	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		for (int p = 0; p < 5; p++) {
			if (tab[2][p] == 0) {
				if (p > 0 && tab[2][p - 1] > 0) {
					// da esquerda
					int[][] novo = copia(tab);
					novo[2][p] = novo[2][p - 1];
					novo[2][p - 1] = 0;
					PuzzleCruz novoEstado = new PuzzleCruz(novo);
					s.add(new Acao(novoEstado, 1));
				}
				if (p < 4 && tab[2][p + 1] > 0) {
					// da direita
					int[][] novo = copia(tab);
					novo[2][p] = novo[2][p + 1];
					novo[2][p + 1] = 0;
					PuzzleCruz novoEstado = new PuzzleCruz(novo);
					s.add(new Acao(novoEstado, 1));
				}
			}
			if (tab[p][2] == 0) {
				if (p > 0 && tab[p - 1][2] > 0) {
					// de cima
					int[][] novo = copia(tab);
					novo[p][2] = novo[p - 1][2];
					novo[p - 1][2] = 0;
					PuzzleCruz novoEstado = new PuzzleCruz(novo);
					s.add(new Acao(novoEstado, 1));
				}
				if (p < 4 && tab[p + 1][2] > 0) {
					// de baixo
					int[][] novo = copia(tab);
					novo[p][2] = novo[p + 1][2];
					novo[p + 1][2] = 0;
					PuzzleCruz novoEstado = new PuzzleCruz(novo);
					s.add(new Acao(novoEstado, 1));
				}
			}
		}
		return s;

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int l = 0; l < 5; l++) {
			for (int c = 0; c < 5; c++) {
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
		PuzzleCruz ps = new PuzzleCruz();
		System.out.println("" + ps + "  " + ps.h());
		System.out.println("================");
		ArrayList<Acao> s = ps.suc();
		for (Acao ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h() + "\n----------------\n");
		}
	}

}
