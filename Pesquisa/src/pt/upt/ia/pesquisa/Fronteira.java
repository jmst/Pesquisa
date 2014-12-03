package pt.upt.ia.pesquisa;
import java.util.ArrayList;
import java.util.List;

public class Fronteira {
    private ArrayList<No> lista;
    private IAlgoritmo alg;
    
    public Fronteira( IAlgoritmo alg)
    {
        this.alg = alg;
        lista = new ArrayList<No>();
    }
    
    public void junta( No no) {
        alg.insere( lista, no);
    }
    
    public No cabeca() {
        if (lista.isEmpty()) {
            return null;
        }
        else {
            return lista.remove( 0);
        }
    }
    
    public int getContagem() {
        return lista.size();
    }
    
    public void imprime() {
        System.out.println("\n..................");
        for (No n : lista) {
            System.out.println(n);
        }
    }
    

}
