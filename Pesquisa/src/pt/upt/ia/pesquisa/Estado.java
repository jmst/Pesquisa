package pt.upt.ia.pesquisa;
import java.util.ArrayList;

public abstract class Estado
{
    public abstract double h();
    public abstract boolean goal();
    public abstract ArrayList<Ramo> suc();
    public static ArrayList<Estado> getIniciais() {
    	return new ArrayList<Estado>();
    }
    
    public abstract boolean equals( Object p);
    
    public abstract int hashCode();
}
