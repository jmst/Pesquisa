package pt.upt.ia.problema;
import java.util.ArrayList;

import pt.upt.ia.pesquisa.EstadoProblema;
import pt.upt.ia.pesquisa.Ramo;
public class PuzzleSeis extends EstadoProblema {
	
// prof 7
//	private int tab[][] = {{1,0,0,0,0,6},{2,3,0,0,4,5}};
	
// prof 9
	private int tab[][] = {{1,4,0,0,0,6},{2,3,0,0,0,5}};
	
// prof 13	
//	private int tab[][] = {{1,4,0,5,3,6},{0,0,0,2,0,0}};

	private int hash = Integer.MAX_VALUE;

	public PuzzleSeis(int[][] novo) {
		tab = new int[2][6];
		for (int i=0; i<2; i++)
			for (int j=0; j<6; j++) {
				tab[i][j] = novo[i][j];
			}
	}

	public PuzzleSeis() {
	}

	public static ArrayList<EstadoProblema> getIniciais() {
		ArrayList<EstadoProblema> lista = new ArrayList<EstadoProblema>();
		lista.add(new PuzzleSeis());
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

	@Override
	public int getKey() {
		if (hash != Integer.MAX_VALUE)
			return hash;
		int result = 0;
		for (int l=0; l<2; l++)
			for (int c=0; c<6; c++) {
				result = result * 7 + tab[l][c];
			}
		hash = result;
		return result;
	}

	@Override
	public ArrayList<Ramo> suc() {
		ArrayList<Ramo> s = new ArrayList<Ramo>();
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
						PuzzleSeis novoEstado = new PuzzleSeis(novo);
						s.add( new Ramo(novoEstado,1));
					}
					if (l==0 && tab[0][c] == 0 && tab[1][c] > 0) {		// cima
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[0][c] = novo[1][c];
						novo[1][c] = 0;
						PuzzleSeis novoEstado = new PuzzleSeis(novo);
						s.add( new Ramo(novoEstado,1));
					}
					if (c>0 && tab[l][c] == 0 && tab[l][c-1] > 0) {		// esquerda
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[l][c] = novo[l][c-1];
						novo[l][c-1] = 0;
						PuzzleSeis novoEstado = new PuzzleSeis(novo);
						s.add( new Ramo(novoEstado,1));
					}
					if (c<5 && tab[l][c] == 0 && tab[l][c+1] > 0) {		// direita
						int[][] novo = new int[2][6];
						for (int i=0; i<2; i++)
							for (int j=0; j<6; j++) {
								novo[i][j] = tab[i][j];
							}
						novo[l][c] = novo[l][c+1];
						novo[l][c+1] = 0;
						PuzzleSeis novoEstado = new PuzzleSeis(novo);
						s.add( new Ramo(novoEstado,1));
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
