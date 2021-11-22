package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.Acao;
import pt.upt.ia.pesquisa.IEstado;

public class LightsOff implements IEstado {
	private static final int OFF = 0;
	private static final int ON = 1;
    private int tab[][];
    private int hash = Integer.MAX_VALUE;

    public LightsOff(int[][] novo) {
        tab = new int[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++) {
                tab[i][j] = novo[i][j];
            }
    }

    public LightsOff() {
        tab = new int[5][5];

// prof. 5
//        tab[0][0] = ON;
//        tab[0][1] = ON;
//        tab[0][2] = ON;
//        tab[0][3] = ON;
//        tab[0][4] = ON;
//        
//        tab[1][0] = ON;
//        tab[1][1] = OFF;
//        tab[1][2] = ON;
//        tab[1][3] = OFF;
//        tab[1][4] = OFF;
//        
//        tab[2][0] = OFF;
//        tab[2][1] = ON;
//        tab[2][2] = OFF;
//        tab[2][3] = OFF;
//        tab[2][4] = ON;
//        
//        tab[3][0] = OFF;
//        tab[3][1] = OFF;
//        tab[3][2] = ON;
//        tab[3][3] = ON;
//        tab[3][4] = ON;
//        
//        tab[4][0] = OFF;
//        tab[4][1] = OFF;
//        tab[4][2] = OFF;
//        tab[4][3] = ON;
//        tab[4][4] = ON;
        
// prof. 8
        tab[0][0] = OFF;
        tab[0][1] = ON;
        tab[0][2] = OFF;
        tab[0][3] = OFF;
        tab[0][4] = OFF;
        
        tab[1][0] = ON;
        tab[1][1] = ON;
        tab[1][2] = OFF;
        tab[1][3] = ON;
        tab[1][4] = ON;
        
        tab[2][0] = OFF;
        tab[2][1] = ON;
        tab[2][2] = OFF;
        tab[2][3] = ON;
        tab[2][4] = OFF;
        
        tab[3][0] = OFF;
        tab[3][1] = ON;
        tab[3][2] = ON;
        tab[3][3] = ON;
        tab[3][4] = ON;
        
        tab[4][0] = ON;
        tab[4][1] = ON;
        tab[4][2] = ON;
        tab[4][3] = ON;
        tab[4][4] = OFF;
        
        
// prof. 15
//        tab[0][0] = ON;
//        tab[0][1] = OFF;
//        tab[0][2] = ON;
//        tab[0][3] = OFF;
//        tab[0][4] = ON;
//        
//        tab[1][0] = ON;
//        tab[1][1] = ON;
//        tab[1][2] = OFF;
//        tab[1][3] = ON;
//        tab[1][4] = ON;
//        
//        tab[2][0] = OFF;
//        tab[2][1] = OFF;
//        tab[2][2] = ON;
//        tab[2][3] = OFF;
//        tab[2][4] = ON;
//        
//        tab[3][0] = OFF;
//        tab[3][1] = OFF;
//        tab[3][2] = ON;
//        tab[3][3] = OFF;
//        tab[3][4] = OFF;
//        
//        tab[4][0] = OFF;
//        tab[4][1] = OFF;
//        tab[4][2] = ON;
//        tab[4][3] = OFF;
//        tab[4][4] = ON;

    
    }

    public static ArrayList<IEstado> getIniciais() {
        ArrayList<IEstado> lista = new ArrayList<IEstado>();
        lista.add(new LightsOff());
        return lista;
    }

    @Override
    public boolean goal() {
    	for (int i = 0; i < 5; i++)
        	for (int j = 0; j < 5; j++)
        		if (tab[i][j] == ON)
        			return false;
        return true;
    }
        
    @Override
    public ArrayList<Acao> suc() {
        ArrayList<Acao> s = new ArrayList<Acao>();
        int[][] novo;
        for (int l=0; l<5; l++)
            for (int c =0; c<5; c++) {
                novo = copia( tab);
            	if (l > 0) {
            		novo[l-1][c] = 1 - novo[l-1][c];
            	}
		    	if (l < 4) {
		    		novo[l+1][c] = 1 - novo[l+1][c];
		    	}
            	if (c > 0) {
            		novo[l][c-1] = 1 - novo[l][c-1];
            	}
		    	if (c < 4) {
		    		novo[l][c+1] = 1 - novo[l][c+1];
		    	}
        		novo[l][c] = 1 - novo[l][c];
                LightsOff novoEstado = new LightsOff(novo);
                s.add( new Acao(novoEstado,1));
            }
        return s;
    }

    public int[][] copia( int[][] tab) {
        int[][] novo = new int[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++) {
                novo[i][j] = tab[i][j];
            }
        return novo;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        for (int l=0; l<5; l++) {
            for (int c=0; c<5; c++)
                if (tab[l][c] > 0) {        // c � a coluna da rainha da linha l
                    sb.append(" O");
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
        for (int i=0; i< 5 ; i++)
            for (int j=0; j<5; j++)
                tot += tab[i][j];
        return tot / 4;
    }
    
    public static void testa() {
        LightsOff p = new LightsOff();
        System.out.println("====================\n" + p);
        ArrayList<Acao> suc = p.suc();
        System.out.println("No. Sucessores: "+suc.size());
        for (Acao r : suc) {
            System.out.println( r.getEstado());
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
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != LightsOff.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		LightsOff oo = (LightsOff) o;
		for (int i=0; i<5; i++)
			for (int j=0; j<5; j++)
			if (tab[i][j] != oo.get(i,j))
				return false;
		return true;
	}

    public int get(int i, int j) {
    	return tab[i][j];
    }
    
	// hashCode, associa a cada objeto um n�mero inteiro que se deseja
	// distintivo
	@Override
	public int hashCode() {
		int result = 0;
		for (int l=0; l<5; l++)
			for (int c=0; c<5; c++) {
				result = result * 2 + tab[l][c];
			}
		return result;
	}

	public static void main(String[] args) {
		LightsOff lo = new LightsOff();
		System.out.println(lo);
		ArrayList<Acao> suc = lo.suc();
		for (Acao a : suc) {
			System.out.println(a.getEstado());
		}
		
	}
}
