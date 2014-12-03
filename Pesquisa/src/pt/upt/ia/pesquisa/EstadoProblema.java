package pt.upt.ia.pesquisa;
import java.util.ArrayList;

public abstract class EstadoProblema
{
    public abstract double h();
    public abstract boolean goal();
    public abstract ArrayList<Ramo> suc();
    public static ArrayList<EstadoProblema> getIniciais() {
    	return new ArrayList<EstadoProblema>();
    }
    
    public String getChave() {
        return "";
    }
    
    public int hashCode() {
    	return Integer.MAX_VALUE;
    }
    
    public boolean equals( Object p) {
    	return false;
    }
}
