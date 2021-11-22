package pt.upt.ia.problema;
import java.util.ArrayList;

import pt.upt.ia.pesquisa.Acao;

/**
 * Interface que descreve um estado de um problema de pesquisa
 * 
 * @author jt
 * @version 2.1
 */

public interface IEstado
{
	/*
	 * função heurística
	 * retorna o valor de h associado ao estado
	 */
    double h();
    /*
     * retorna true se o estado for final (um objetivo)
     */
    boolean goal();
    /*
     * retorna uma coleção com os estados sucessores, e o custo de transição de cada um
     */
    ArrayList<Acao> suc();
    /*
     * retorna uma coleção com o(s) estado(s) inicial(is)
     */
    static ArrayList<IEstado> getIniciais() {
    	return new ArrayList<IEstado>();
    }
    /*
     * retorna true se este estado for igual ao objeto p
     */
    boolean equals( Object p);
    /*
     * retorna um inteiro com o valor do hash code deste estado
     */
    int hashCode();
}
