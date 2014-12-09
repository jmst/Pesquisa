package pt.upt.ia.problema;
import java.util.ArrayList;

import pt.upt.ia.pesquisa.EstadoProblema;
import pt.upt.ia.pesquisa.Ramo;


public class ND extends EstadoProblema {
    private int[] p;
    private int hash = Integer.MAX_VALUE;
    
    public ND() {
        p = null;
    }
    
    public ND( int[] p) {
        this.p = p;
    }
    
    public double h() {
        return 0;
    }

	@Override
	public int getKey() {
		if (hash != Integer.MAX_VALUE)
			return hash;
		int result = 0;
		for (int c=0; c<5; c++)
			result = result * 10 + p[c];
		hash = result;
		return result;
	}
    
    public ArrayList<Ramo> suc() {
        int[] np;
        ArrayList<Ramo> s = new ArrayList<Ramo>();
        for (int i=0; i<4; i++) {
            np = copia( p);
            if (np[i] == 1 && np[i+1] == 0) {
                np[i] = 0;
                np[i+1] = 1;
                s.add( new Ramo( new ND( np), 1));
            }
            np = copia( p);
            if (np[i] == 0 && np[i+1] == 2) {
                np[i] = 2;
                np[i+1] = 0;
                s.add( new Ramo( new ND( np), 1));
            }
        }
        for (int i=0; i<3; i++) {
            np = copia( p);
            if (np[i] == 1 && np[i+1] == 2 && np[i+2] == 0) {
                np[i] = 0;
                np[i+2] = 1;
                s.add( new Ramo( new ND( np), 1));
            }
            np = copia( p);
            if (np[i] == 0 && np[i+1] == 1 && np[i+2] == 2) {
                np[i] = 2;
                np[i+2] = 0;
                s.add( new Ramo( new ND( np), 1));
            }
        }
        return s;
    }

    public int[] copia( int[] p) {
        int[] np = new int[5];
        for (int i=0; i< 5; i++)
            np[i] = p[i];
        return np;
    }
        
    public boolean goal() {
        return p[0] == 2 && p[1] == 2 && p[3] == 1 && p[4] == 1;
    }
    
    public static ArrayList<EstadoProblema> getIniciais() {
        ArrayList<EstadoProblema> i = new ArrayList<EstadoProblema>();
        int[] p = {1,1,0,2,2};
        i.add( new ND( p));
        return i;
    }
    
    public String toString() {
        return "" + p[0]+ "  "+p[1]+ "  "+p[2]+ "  "+p[3]+ "  "+p[4];
    }

    public static void main( String[] args) {
        int[] e = {1,1,0,2,2};
        ND est = new ND(e);
        ArrayList<Ramo> s = est.suc();
        System.out.println("Inicial: " + est);
        ArrayList<Ramo> suc = est.suc();
        for (Ramo r : suc) {
            System.out.println(r.getEstado());
        }
        System.out.println("-------------");
    }
}
