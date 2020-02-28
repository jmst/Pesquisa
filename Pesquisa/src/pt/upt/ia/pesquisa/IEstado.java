package pt.upt.ia.pesquisa;
import java.util.ArrayList;

public interface IEstado
{
    double h();
    boolean goal();
    ArrayList<Ramo> suc();
    static ArrayList<IEstado> getIniciais() {
    	return new ArrayList<IEstado>();
    }
    
    boolean equals( Object p);
    int hashCode();
}
