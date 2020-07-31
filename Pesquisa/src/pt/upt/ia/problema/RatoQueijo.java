package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

public class RatoQueijo implements IEstado {
	public static final int DIM = 10;
	public static final int VAZIO = 0;
	public static final int OCUPADO = 1;
	public static final int RATO = 2;
	public static final int QUEIJO = 3;

	private int ratoL;
	private int ratoC;
	private static int queijoL;
	private static int queijoC;

	private static int tab[][];
	static {
		tab = new int[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				tab[i][j] = VAZIO;
			}
		}
		tab[0][5] = OCUPADO;
		tab[1][3] = OCUPADO;
		tab[1][5] = OCUPADO;
		tab[1][8] = OCUPADO;
		tab[2][3] = OCUPADO;
		tab[2][5] = OCUPADO;
		tab[2][7] = OCUPADO;
		tab[2][8] = OCUPADO;
		tab[3][2] = OCUPADO;
		tab[3][3] = OCUPADO;
		tab[3][8] = OCUPADO;
		tab[4][2] = OCUPADO;
		tab[4][6] = OCUPADO;
		tab[4][7] = OCUPADO;
		tab[4][8] = OCUPADO;
		tab[5][2] = OCUPADO;
		tab[5][4] = OCUPADO;
		tab[5][6] = OCUPADO;
		tab[5][8] = OCUPADO;
		tab[6][4] = OCUPADO;
		tab[6][6] = OCUPADO;
		tab[6][8] = OCUPADO;
		tab[7][1] = OCUPADO;
		tab[7][2] = OCUPADO;
		tab[7][3] = OCUPADO;
		tab[7][4] = OCUPADO;
		tab[7][6] = OCUPADO;
		tab[8][6] = OCUPADO;

		queijoL = 5;
		queijoC = 9;
//		tab[queijoL][queijoC] = QUEIJO;
		
	}

	public RatoQueijo(int ratoL, int ratoC) {
		this.ratoL = ratoL;
		this.ratoC = ratoC;
	}

	public RatoQueijo() {
		ratoL = 1;
		ratoC = 1;
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> lista = new ArrayList<IEstado>();
		lista.add(new RatoQueijo());
		return lista;
	}

	@Override
	public boolean goal() {
		return ratoL == queijoL && ratoC == queijoC;
	}

	@Override
	public int hashCode() {
		return ratoL * 10 + ratoC;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != RatoQueijo.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		RatoQueijo oo = (RatoQueijo) o;
		return oo.ratoL == ratoL && oo.ratoC == ratoC;
	}

	@Override
	public double h() {
		return Math.abs(ratoL - queijoL) + Math.abs(ratoC - queijoC);
	}

	@Override
	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		if (ratoL > 0 && tab[ratoL-1][ratoC] == VAZIO) {
			s.add( new Acao( new RatoQueijo(ratoL-1, ratoC),1));
		}
		if (ratoL < DIM-1 && tab[ratoL+1][ratoC] == VAZIO) {
			s.add( new Acao( new RatoQueijo(ratoL+1, ratoC),1));
		}
		if (ratoC > 0 && tab[ratoL][ratoC-1] == VAZIO) {
			s.add( new Acao( new RatoQueijo(ratoL, ratoC-1),1));
		}
		if (ratoC < DIM-1 && tab[ratoL][ratoC+1] == VAZIO) {
			s.add( new Acao( new RatoQueijo(ratoL, ratoC+1),1));
		}
		return s;

	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int l = 0; l < DIM; l++) {
			for (int c = 0; c < DIM; c++) {
				if (ratoL == l && ratoC == c) {
					sb.append(" R ");
				} else if (queijoL == l && queijoC == c) {
					sb.append(" Q ");
				} else if (tab[l][c] == VAZIO) {
					sb.append(" . ");
				} else if (tab[l][c] == OCUPADO) {
					sb.append(" # ");
				} else {
					sb.append(" ? ");
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
		RatoQueijo ps = new RatoQueijo();

		System.out.println("" + ps + "  " + ps.h());
		System.out.println("================");
		ArrayList<Acao> s = ps.suc();
		for (Acao ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h() + "\n----------------\n");
		}
	}

}
