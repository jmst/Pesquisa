package pt.upt.ia.problema;
import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

public class PuzzleSeisSeis implements IEstado {
    private int hash = Integer.MAX_VALUE;
	
// prof 7
//	private int tab[][] = {{1,0,0,0,0,6},{2,3,0,0,4,5}};
	
// prof 9	
	private int tab[][] = {{1,4,0,5,0,6},{0,0,2,3,0,0}};

	public PuzzleSeisSeis(int[][] novo) {
		tab = new int[2][6];
		for (int i=0; i<2; i++)
			for (int j=0; j<6; j++) {
				tab[i][j] = novo[i][j];
			}
	}

	public PuzzleSeisSeis() {
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> lista = new ArrayList<IEstado>();
		lista.add(new PuzzleSeisSeis());
    	return lista;
    }

	@Override
	public boolean goal() {
		boolean res = true;
		for (int i=0; i<6; i++)
			if (tab[1][i] != i+1)
				res = false;
		return res;
	}

	@Override
	public double h() {
		double h = 0;
		for (int l=0; l<2; l++)
			for (int c =0; c<6; c++)
				if ( tab[l][c] > 0) {
					int dif = Math.abs(c+1-tab[l][c]);
					h += dif + 1 - l;
				}
		return h;
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
	public int hashCode() {
		int result = 0;
		for (int l=0; l<2; l++)
			for (int c=0; c<6; c++) {
				result = result * 7 + tab[l][c];
			}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != PuzzleSeisSeis.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		PuzzleSeisSeis oo = (PuzzleSeisSeis) o;
		for (int i=0; i<2; i++)
			for (int j=0; j<6; j++)
			if (tab[i][j] != oo.get(i,j))
				return false;
		return true;
	}

    public int get(int i, int j) {
    	return tab[i][j];
    }

	@Override
	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		for (int l=0; l<2; l++)
			for (int c =0; c<6; c++) {
				if ( tab[l][c] == 0) {
					if (l==1 && tab[1][c] == 0 && tab[0][c] > 0) {		// baixo
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[1][c] = novo[0][c];
						novo[0][c] = 0;
						PuzzleSeisSeis novoEstado = new PuzzleSeisSeis(novo);
						s.add( new Acao(novoEstado,1));
					}
					if (l==0 && tab[0][c] == 0 && tab[1][c] > 0) {		// cima
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[0][c] = novo[1][c];
						novo[1][c] = 0;
						PuzzleSeisSeis novoEstado = new PuzzleSeisSeis(novo);
						s.add( new Acao(novoEstado,1));
					}
					if (c>0 && tab[l][c] == 0 && tab[l][c-1] > 0) {		// esquerda
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[l][c] = novo[l][c-1];
						novo[l][c-1] = 0;
						PuzzleSeisSeis novoEstado = new PuzzleSeisSeis(novo);
						s.add( new Acao(novoEstado,1));
					}
					if (c<5 && tab[l][c] == 0 && tab[l][c+1] > 0) {		// direita
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[l][c] = novo[l][c+1];
						novo[l][c+1] = 0;
						PuzzleSeisSeis novoEstado = new PuzzleSeisSeis(novo);
						s.add( new Acao(novoEstado,1));
					}
				}
			}
		return s;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		for (int l=0; l<2; l++) {
			for (int c=0; c<6; c++)
				if (tab[l][c] > 0) {		// c � a coluna da rainha da linha l
					sb.append(" "+tab[l][c]);
				}
				else
					sb.append(" .");
			sb.append("\n");
		}
		return new String( sb);
	}

//	public static void main( String[] args) {
////	    int[][] in = {{1,0,0,0,0,6},{3,0,2,4,5,0}};
//	    int[][] in = {{1,0,0,0,0,6},{2,3,0,0,4,5}};
//	    PuzzleSeis ps = new PuzzleSeis( in);
//	    ArrayList<Ramo> s = ps.suc();
//	    for (Ramo ss : s) {
//	        System.out.println( ss.getEstado() + "  " + ss.getEstado().h());
//	   }
//    }

}
