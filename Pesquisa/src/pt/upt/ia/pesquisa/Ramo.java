package pt.upt.ia.pesquisa;

public class Ramo {
	private EstadoProblema estado;
	private double custo;
	
	public Ramo( EstadoProblema estado, double custo) {
		this.estado = estado;
		this.custo = custo;
	}
	
	public EstadoProblema getEstado() {
		return estado;
	}
	
	public double getCusto() {
		return custo;
	}
}
