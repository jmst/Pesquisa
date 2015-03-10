package pt.upt.ia.pesquisa;
import java.util.ArrayList;
import java.util.List;

public class No {
    private int profundidade;
    private EstadoProblema estado;
    private double g;
    private double f;
    private No pai;
    
    public No( EstadoProblema estado, No pai, double custo)
    {
        this.estado = estado;
        this.pai = pai;
        if (pai == null) {
            g = custo;
            profundidade = 0;
        	f = g() + h();
        }
        else {
            g = pai.g() + custo;
            profundidade = pai.getProfundidade() + 1;
        	f = g() + h();
            if (pai.f() > f)
            	f = pai.f();
        }
    }
    
    public int getProfundidade() {
        return profundidade;
    }
    
    public double g() {
        return g;
    }
    
    public double h() {
        return estado.h();
    }
    
    public double f() {
        return f;
    }
    
    public No getPai() {
        return pai;
    }
    
    public EstadoProblema getEstado() {
        return estado;
    }
    
    public ArrayList<No> getSuc() {
        ArrayList<No> suc = new ArrayList<No>();
        ArrayList<Ramo> s = estado.suc();
        for (Ramo r : s) {
            No n = new No( r.getEstado(), this, r.getCusto());
            suc.add( n);
        }
        return suc;
    }
    
    public boolean ciclo( No n) {
    	if (n.getEstado().equals(estado))
    		return true;
    	No p = pai;
    	while ( p != null) {
        	if (n.getEstado().getKey() == p.getEstado().getKey()) {
        		return true;
        	}
        	p = p.getPai();
    	}
    	return false;
    }
    
    public String toString() {
        return "" + getEstado()+"   g= "+g()+"   h= "+h()+"   f= "+f()+"   prof= "+getProfundidade();
    }

    public void escrevePais() {
        if (pai != null)
            pai.escrevePais();
        System.out.println( this);
    }
}
