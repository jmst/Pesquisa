package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.EstadoProblema;
import pt.upt.ia.pesquisa.Ramo;


public class ND6 extends EstadoProblema {
    private int[] p;
    private int hash = Integer.MAX_VALUE;
    
    public ND6() {
        p = null;
    }
    
    public ND6( int[] p) {
        this.p = p;
    }
    
    public double h() {
    	double vh = 0;
    	for (int i=0; i<7; i++) {
    		int v = p[i];
    		switch (v) {
    		case 0: vh += Math.abs(i-3);
    			break;
    		case 1: vh += Math.abs(i-6);
			break;
    		case 2: vh += Math.abs(i-5);
			break;
    		case 3: vh += Math.abs(i-4);
			break;
    		case 4: vh += Math.abs(i-2);
			break;
    		case 5: vh += Math.abs(i-1);
			break;
    		case 6: vh += Math.abs(i-0);
			break;
    		}
    	}
        return vh;
    }

	@Override
	public int getKey() {
		if (hash != Integer.MAX_VALUE)
			return hash;
		int result = 0;
		for (int c=0; c<7; c++)
			result = result * 10 + p[c];
		hash = result;
		return result;
	}
    
    public ArrayList<Ramo> suc() {
        int[] np;
        ArrayList<Ramo> s = new ArrayList<Ramo>();
        for (int i=0; i<6; i++) {
            np = copia( p);
            if (np[i] > 0 && np[i+1] == 0) {
                np[i+1] = np[i];
                np[i] = 0;
                s.add( new Ramo( new ND6( np), 1));
            }
            np = copia( p);
            if (np[i] == 0 && np[i+1] > 0) {
                np[i] = np[i+1];
                np[i+1] = 0;
                s.add( new Ramo( new ND6( np), 1));
            }
        }
        for (int i=0; i<5; i++) {
            np = copia( p);
            if (np[i] > 0 && np[i+2] == 0) {
                np[i+2] = np[i];
                np[i] = 0;
                s.add( new Ramo( new ND6( np), 1));
            }
            np = copia( p);
            if (np[i] == 0 && np[i+2] > 0) {
                np[i] = np[i+2];
                np[i+2] = 0;
                s.add( new Ramo( new ND6( np), 1));
            }
        }
        return s;
    }

    public int[] copia( int[] p) {
        int[] np = new int[7];
        for (int i=0; i< 7; i++)
            np[i] = p[i];
        return np;
    }
        
    public boolean goal() {
        return p[0] == 6 && p[1] == 5 && p[2] == 4 && p[3] == 0 && p[4] == 3 && p[5] == 2 && p[6] == 1;
    }
    
    public static ArrayList<EstadoProblema> getIniciais() {
        ArrayList<EstadoProblema> i = new ArrayList<EstadoProblema>();
        int[] p = {1,2,3,0,4,5,6};
        i.add( new ND6( p));
        return i;
    }
    
    public String toString() {
        return "" + p[0]+ "  "+p[1]+ "  "+p[2]+ "  "+p[3]+ "  "+p[4]+ "  "+p[5]+ "  "+p[6];
    }

 }
