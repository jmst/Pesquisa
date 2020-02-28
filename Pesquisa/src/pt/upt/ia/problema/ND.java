package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Ramo;


public class ND implements IEstado {
    private int[] p;
    private int hash = Integer.MAX_VALUE;
    
    public ND() {
        p = null;
    }
    
    public ND( int[] p) {
        this.p = p;
    }
    
    public double h() {
    	int h=0;
        int[] pf = {2,2,0,1,1};
        for (int i=0; i<5; i++) {
        	if (p[i] != pf[i])
        		h++;
        }
        return h;
    }

//	@Override
//	public int getKey() {
//		if (hash != Integer.MAX_VALUE)
//			return hash;
//		hash = hashCode();
//		return hash;
//	}
    
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
    
    public static ArrayList<IEstado> getIniciais() {
        ArrayList<IEstado> i = new ArrayList<IEstado>();
        int[] p = {1,1,0,2,2};
        i.add( new ND( p));
        return i;
    }

    public int get(int i) {
    	return p[i];
    }
    
	public boolean equals( Object o) {
		if (o == null) // null n�o � igual
			return false;
		if (o.getClass() != ND.class) // classe diferente: n�o � igual
			return false;
		if (this == o) // tem a mesma refer�ncia de mem�ria: � o mesmo objeto
			return true;
		ND oo = (ND) o;
		for (int i=0; i<5; i++)
			if (p[i] != oo.get(i))
				return false;
		return true;
	}

	// hashCode, associa a cada objeto um n�mero inteiro que se deseja
	// distintivo
	@Override
	public int hashCode() {
		int result = 0;
		for (int i=0; i<5; i++)
			result = result*10+p[i];
		return result;
	}
    
    public String toString() {
        return "" + p[0]+ " "+p[1]+ " "+p[2]+ " "+p[3]+ " "+p[4];
    }

    public static void main( String[] args) {
        int[] e = {1,0,1,2,2};
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
