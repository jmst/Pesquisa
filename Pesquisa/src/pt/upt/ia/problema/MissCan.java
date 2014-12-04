package pt.upt.ia.problema;
import java.util.ArrayList;

import pt.upt.ia.pesquisa.EstadoProblema;
import pt.upt.ia.pesquisa.Ramo;


public class MissCan extends EstadoProblema {
    private int nMissE;
    private int nCanE;
    private boolean barcoE;
    
    public MissCan( int nme, int nce, boolean be) {
        nMissE = nme;
        nCanE = nce;
        barcoE = be;
    }
    
    public double h() {
    	int ne = nMissE+nCanE;
        return (ne == 0)?0:(ne>1)?(ne-1):1;
    }
    
    public ArrayList<Ramo> suc() {
        int nme;
        int nce;
        ArrayList<Ramo> s = new ArrayList<Ramo>();
        if (barcoE) {
            nme = nMissE - 2;
            nce = nCanE;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, false), 1));
            }
            nme = nMissE;
            nce = nCanE - 2;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, false), 1));
            }
            nme = nMissE - 1;
            nce = nCanE - 1;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, false), 1));
            }
            nme = nMissE - 1;
            nce = nCanE;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, false), 1));
            }
            nme = nMissE;
            nce = nCanE - 1;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, false), 1));
            }
        }
        else {
            nme = nMissE + 2;
            nce = nCanE;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, true), 1));
            }
            nme = nMissE;
            nce = nCanE + 2;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, true), 1));
            }
            nme = nMissE + 1;
            nce = nCanE + 1;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, true), 1));
            }
            nme = nMissE + 1;
            nce = nCanE;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, true), 1));
            }
            nme = nMissE;
            nce = nCanE + 1;
            if (valida( nme, nce)) {
                s.add( new Ramo( new MissCan( nme, nce, true), 1));
            }
        }
        return s;
    }

    public boolean goal() {
        return nMissE == 0 && nCanE == 0;
    }
    
    public static ArrayList<EstadoProblema> getIniciais() {
        ArrayList<EstadoProblema> i = new ArrayList<EstadoProblema>();
        i.add( new MissCan( 3, 3, true));
        return i;
    }
    
    public String toString() {
        String st = "" + nMissE+ "  "+nCanE;
        st += "  "+(barcoE ? "\\_____/" : "~~~~~~~");
        st += "~~~~~~~"+(barcoE ? "~~~~~~~" : "\\_____/");
        st += "  "+(3-nMissE)+"  "+(3-nCanE);
        return st;
    }
    
    public String getChave() {
    	return ""+nMissE+nCanE+(barcoE?1:0);
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (barcoE ? 1231 : 1237);
		result = prime * result + nCanE;
		result = prime * result + nMissE;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissCan other = (MissCan) obj;
		if (barcoE != other.barcoE)
			return false;
		if (nCanE != other.nCanE)
			return false;
		if (nMissE != other.nMissE)
			return false;
		return true;
	}
    
    private boolean valida( int nme, int nce) {
        if (nme < 0 || nme > 3 || nce < 0 || nce > 3)
            return false;
        if (nme == 0 || nme == 3)
            return true;
        if (nme == nce)
            return true;
        return false;
    }
 
    public static void main( String[] args) {
	    MissCan mc = new MissCan( 2,2,false);
	    ArrayList<Ramo> s = mc.suc();
	    System.out.println("-------------");
	    System.out.println(mc);
	    System.out.println("");
	    for (Ramo ss : s) {
	        System.out.println( ss.getEstado() + "  " + ss.getEstado().h());
	   }
    }

}
