package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.Estado;
import pt.upt.ia.pesquisa.Ramo;

public class Solitario extends Estado {
	private int tab[][];
	private int hash = Integer.MAX_VALUE;
	private static final int DIM = 6;

	public Solitario(int[][] novo) {
		tab = new int[DIM][DIM];
		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++) {
				tab[i][j] = novo[i][j];
			}
	}

	public Solitario() {
		tab = new int[DIM][DIM];

		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++) {
				tab[i][j] = 1;
			}
		int med = DIM / 2;
		tab[med][med] = 0;
	}

	public static ArrayList<Estado> getIniciais() {
		ArrayList<Estado> lista = new ArrayList<Estado>();
		lista.add(new Solitario());
		return lista;
	}

	@Override
	public boolean goal() {
		int c = 0;
		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++) {
				c += tab[i][j];
				if (c > 1)
					return false;
			}
		return true;
	}

	@Override
	public ArrayList<Ramo> suc() {
		ArrayList<Ramo> s = new ArrayList<Ramo>();
		int[][] novo;
		for (int l = 0; l < DIM; l++) {
			for (int c = 0; c < DIM - 2; c++) {
				if (tab[l][c] == 1 && tab[l][c + 1] == 1 && tab[l][c + 2] == 0) {
					novo = copia(tab);
					novo[l][c] = 0;
					novo[l][c + 1] = 0;
					novo[l][c + 2] = 1;
					Solitario novoEstado = new Solitario(novo);
					s.add(new Ramo(novoEstado, 1));
				}
				if (tab[l][c] == 0 && tab[l][c + 1] == 1 && tab[l][c + 2] == 1) {
					novo = copia(tab);
					novo[l][c] = 1;
					novo[l][c + 1] = 0;
					novo[l][c + 2] = 0;
					Solitario novoEstado = new Solitario(novo);
					s.add(new Ramo(novoEstado, 1));
				}
			}
		}
		for (int l = 0; l < DIM - 2; l++) {
			for (int c = 0; c < DIM; c++) {
				if (tab[l][c] == 1 && tab[l + 1][c] == 1 && tab[l + 2][c] == 0) {
					novo = copia(tab);
					novo[l][c] = 0;
					novo[l + 1][c] = 0;
					novo[l + 2][c] = 1;
					Solitario novoEstado = new Solitario(novo);
					s.add(new Ramo(novoEstado, 1));
				}
				if (tab[l][c] == 0 && tab[l + 1][c] == 1 && tab[l + 2][c] == 1) {
					novo = copia(tab);
					novo[l][c] = 1;
					novo[l + 1][c] = 0;
					novo[l + 2][c] = 0;
					Solitario novoEstado = new Solitario(novo);
					s.add(new Ramo(novoEstado, 1));
				}
			}
		}
		// for (int l = 0; l < DIM; l++) {
		// for (int c = 0; c < DIM-1; c++) {
		// if (tab[l][c] == 1 && tab[l][c + 1] == 0) {
		// novo = copia(tab);
		// novo[l][c] = 0;
		// novo[l][c + 1] = 1;
		// Solitario novoEstado = new Solitario(novo);
		// s.add(new Ramo(novoEstado, 1));
		// }
		// if (tab[l][c] == 0 && tab[l][c + 1] == 1) {
		// novo = copia(tab);
		// novo[l][c] = 1;
		// novo[l][c + 1] = 0;
		// Solitario novoEstado = new Solitario(novo);
		// s.add(new Ramo(novoEstado, 1));
		// }
		// }
		// }
		// for (int l = 0; l < DIM-1; l++) {
		// for (int c = 0; c < DIM; c++) {
		// if (tab[l][c] == 1 && tab[l + 1][c] == 0) {
		// novo = copia(tab);
		// novo[l][c] = 0;
		// novo[l + 1][c] = 1;
		// Solitario novoEstado = new Solitario(novo);
		// s.add(new Ramo(novoEstado, 1));
		// }
		// if (tab[l][c] == 0 && tab[l + 1][c] == 1) {
		// novo = copia(tab);
		// novo[l][c] = 1;
		// novo[l + 1][c] = 0;
		// Solitario novoEstado = new Solitario(novo);
		// s.add(new Ramo(novoEstado, 1));
		// }
		// }
		// }
		return s;
	}

	public int[][] copia(int[][] tab) {
		int[][] novo = new int[DIM][DIM];
		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++) {
				novo[i][j] = tab[i][j];
			}
		return novo;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int l = 0; l < DIM; l++) {
			for (int c = 0; c < DIM; c++)
				if (tab[l][c] > 0) {
					sb.append(" " + tab[l][c]);
				} else
					sb.append(" -");
			sb.append("\n");
		}
		return new String(sb+" hashcode: "+hashCode()+"\n");
	}

	@Override
	public double h() {
		double tot = 0;
		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++)
				tot += tab[i][j];
		return tot;
	}

	public static void testa() {
		Solitario p = new Solitario();
		System.out.println("====================\n" + p);
		ArrayList<Ramo> suc = p.suc();
		System.out.println("No. Sucessores: " + suc.size());
		for (Ramo r : suc) {
			System.out.println(r.getEstado());
		}
	}
//
//	@Override
//	public int getKey() {
//		if (hash != Integer.MAX_VALUE)
//			return hash;
//		hash = hashCode();
//		return hash;
//	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null não é igual
			return false;
		if (o.getClass() != Solitario.class) // classe diferente: não é igual
			return false;
		if (this == o) // tem a mesma referência de memória: é o mesmo objeto
			return true;
		Solitario oo = (Solitario) o;
		for (int i = 0; i < DIM; i++)
			for (int j = 0; j < DIM; j++)
				if (tab[i][j] != oo.get(i, j))
					return false;
		return true;
	}

	public int get(int i, int j) {
		return tab[i][j];
	}

	// hashCode, associa a cada objeto um número inteiro que se deseja
	// distintivo
	@Override
	public int hashCode() {
		int result = 0;
		for (int l = 0; l < DIM; l++)
			for (int c = 0; c < DIM; c++) {
				result = result * 2 + tab[l][c];
			}
		return result;
	}

	public static void main(String[] args) {
		int[][] t = new int[DIM][DIM];
		for (int i=0; i<DIM; i++) {
			for (int j=0; j<DIM; j++) {
				t[i][j] = 1;
			}
		}
		t[1][3] = 0;
		t[1][2] = 0;
		t[2][3] = 0;
		t[2][2] = 0;
		Solitario s = new Solitario(t);
		ArrayList<Ramo> suc = s.suc();
		for (Ramo seg : suc) {
			System.out.println("" + seg.getEstado());
		}
	}
}
