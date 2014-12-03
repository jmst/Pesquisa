package pt.upt.ia.problema;
import java.util.ArrayList;
import java.util.Arrays;

import pt.upt.ia.pesquisa.EstadoProblema;
import pt.upt.ia.pesquisa.Ramo;
public class PuzzleOito extends EstadoProblema {
    private int tab[][];
    private String chave = null;
    private int hash = Integer.MAX_VALUE;

    public PuzzleOito(int[][] novo) {
        tab = new int[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++) {
                tab[i][j] = novo[i][j];
            }
    }

    public PuzzleOito() {
        tab = new int[3][3];

// prof 6
//        tab[0][0] = 1;
//        tab[0][1] = 2;
//        tab[0][2] = 3;
//        tab[1][0] = 5;
//        tab[1][1] = 6;
//        tab[1][2] = 0;
//        tab[2][0] = 4;
//        tab[2][1] = 7;
//        tab[2][2] = 8;

// prof 19
//        tab[0][0] = 2;
//        tab[0][1] = 3;
//        tab[0][2] = 7;
//        tab[1][0] = 1;
//        tab[1][1] = 4;
//        tab[1][2] = 8;
//        tab[2][0] = 0;
//        tab[2][1] = 5;
//        tab[2][2] = 6;

// prof 21
//        tab[0][0] = 7;
//        tab[0][1] = 5;
//        tab[0][2] = 2;
//        tab[1][0] = 4;
//        tab[1][1] = 1;
//        tab[1][2] = 8;
//        tab[2][0] = 0;
//        tab[2][1] = 6;
//        tab[2][2] = 3;

// prof 24
        tab[0][0] = 2;
        tab[0][1] = 6;
        tab[0][2] = 7;
        tab[1][0] = 4;
        tab[1][1] = 1;
        tab[1][2] = 8;
        tab[2][0] = 5;
        tab[2][1] = 0;
        tab[2][2] = 3;

    }

    public static ArrayList<EstadoProblema> getIniciais() {
        int[][] tab = new int[3][3];
        ArrayList<EstadoProblema> lista = new ArrayList<EstadoProblema>();
        lista.add(new PuzzleOito());
        return lista;
    }

    @Override
    public boolean goal() {
        if (tab[0][0] != 1)
            return false;
        if (tab[0][1] != 2)
            return false;
        if (tab[0][2] != 3)
            return false;
        if (tab[1][0] != 4)
            return false;
        if (tab[1][1] != 5)
            return false;
        if (tab[1][2] != 6)
            return false;
        if (tab[2][0] != 7)
            return false;
        if (tab[2][1] != 8)
            return false;
        if (tab[2][2] != 0)
            return false;
        return true;
    }
        
    @Override
    public ArrayList<Ramo> suc() {
        ArrayList<Ramo> s = new ArrayList<Ramo>();
        int[][] novo;
        for (int l=0; l<3; l++)
            for (int c =0; c<3; c++) {
                if ( tab[l][c] == 0) {
                    if (l>0) {      // cima
                        novo = copia( tab);
                        novo[l][c] = tab[l-1][c];
                        novo[l-1][c] = 0;
                        PuzzleOito novoEstado = new PuzzleOito(novo);
                        s.add( new Ramo(novoEstado,1));
                    }
                    if (l<2) {      // baixo
                        novo = copia( tab);
                        novo[l][c] = tab[l+1][c];
                        novo[l+1][c] = 0;
                        PuzzleOito novoEstado = new PuzzleOito(novo);
                        s.add( new Ramo(novoEstado,1));
                    }
                    if (c>0) {      // esquerda
                        novo = copia( tab);
                        novo[l][c] = tab[l][c-1];
                        novo[l][c-1] = 0;
                        PuzzleOito novoEstado = new PuzzleOito(novo);
                        s.add( new Ramo(novoEstado,1));
                    }
                    if (c<2) {      // direita
                        novo = copia( tab);
                        novo[l][c] = tab[l][c+1];
                        novo[l][c+1] = 0;
                        PuzzleOito novoEstado = new PuzzleOito(novo);
                        s.add( new Ramo(novoEstado,1));
                    }
                }
            }
        return s;
    }

    public int[][] copia( int[][] tab) {
        int[][] novo = new int[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++) {
                novo[i][j] = tab[i][j];
            }
        return novo;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        for (int l=0; l<3; l++) {
            for (int c=0; c<3; c++)
                if (tab[l][c] > 0) {        // c � a coluna da rainha da linha l
                    sb.append(" "+tab[l][c]);
                }
                else
                    sb.append(" -");
            sb.append("\n");
        }
        return new String( sb);
    }

    @Override
    public double h() {
        double tot = 0;
        for (int i=0; i< 3 ; i++)
            for (int j=0; j<3; j++)
                tot += h( tab[i][j], i, j);
        return tot;
    }
    
    private double h(int n, int l, int c) {
        switch (n) {
            case 1:
                return l+c;
            case 2:
                return Math.abs(c-1) + l;
            case 3:
                return Math.abs(c-2) + l;
            case 4:
                return c + Math.abs(l-1);
            case 5:
                return Math.abs(c-1) + Math.abs(l-1);
            case 6:
                return Math.abs(c-2) + Math.abs(l-1);
            case 7:
                return c + Math.abs(l-2);
            case 8:
                return Math.abs(c-1) + Math.abs(l-2);
        }
        return 0;
    }
        
    public String getChave() {
        if (chave != null)
            return chave;
        String st = "";
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                st += tab[i][j];
        chave = st;
        return st;
    }
        
    public static void testa() {
        PuzzleOito p = new PuzzleOito();
        System.out.println("====================\n" + p);
        ArrayList<Ramo> suc = p.suc();
        System.out.println("No. Sucessores: "+suc.size());
        for (Ramo r : suc) {
            System.out.println( r.getEstado());
        }
    }

	@Override
	public int hashCode() {
		if (hash != Integer.MAX_VALUE)
			return hash;
		int result = 0;
		for (int l=0; l<3; l++)
			for (int c=0; c<3; c++) {
				result = result * 10 + tab[l][c];
			}
		hash = result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleOito other = (PuzzleOito) obj;
		if (!Arrays.deepEquals(tab, other.tab))
			return false;
		return true;
	}

}
