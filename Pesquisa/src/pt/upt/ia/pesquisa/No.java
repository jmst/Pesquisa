package pt.upt.ia.pesquisa;
import java.util.ArrayList;
import java.util.List;

public class No {
    private int profundidade;
    private IEstado estado;
    private double g;
    private double f;
    private No pai;
    private String oper;
    
    public No( IEstado estado, No pai, double custo)
    {
        this.estado = estado;
        this.pai = pai;
        if (pai == null) {
            g = custo;
            profundidade = 1;
        	f = g() + h();
        }
        else {
            g = pai.g() + custo;
            profundidade = pai.getProfundidade() + 1;
        	f = g() + h();
            if (pai.f() > f)
            	f = pai.f();
        }
        this.oper = "";
    }
    
    public No( IEstado estado, No pai, double custo, String oper)
    {
        this( estado, pai, custo);
        this.oper = oper;
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
    
    public IEstado getEstado() {
        return estado;
    }
    
    public ArrayList<No> getSuc() {
        ArrayList<No> suc = new ArrayList<No>();
        ArrayList<Acao> s = estado.suc();
        for (Acao r : s) {
            No n = new No( r.getEstado(), this, r.getCusto(), r.getDescr());
            suc.add( n);
        }
        return suc;
    }
    
    public boolean ciclo( No n) {
    	if (n.getEstado().equals(estado))
    		return true;
    	No p = pai;
    	while ( p != null) {
        	if (n.getEstado().equals( p.getEstado())) {
        		return true;
        	}
        	p = p.getPai();
    	}
    	return false;
    }
    
    public String toString() {
        return (oper!=null&&!oper.equals("")?("  "+oper):"")+ getEstado()+"   g= "+g()+"   h= "+h()+"   f= "+f()+"   prof= "+getProfundidade();
    }
    
    public String repr() {
        return "[" + getEstado()+"]";
    }

    public void escrevePais() {
        if (pai != null)
            pai.escrevePais();
        System.out.println( this);
    }
}
