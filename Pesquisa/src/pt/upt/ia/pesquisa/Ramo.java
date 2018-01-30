package pt.upt.ia.pesquisa;

public class Ramo {
	private Estado estado;
	private double custo;
	
	public Ramo( Estado estado, double custo) {
		this.estado = estado;
		this.custo = custo;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public double getCusto() {
		return custo;
	}
}
