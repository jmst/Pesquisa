package pt.upt.ia.pesquisa;

public class Ramo {
	private Estado estado;
	private double custo;
	private String descr;
	
	public Ramo( Estado estado, double custo) {
		this.estado = estado;
		this.custo = custo;
		this.descr = "";
	}
	
	public Ramo( Estado estado, double custo, String descr) {
		this.estado = estado;
		this.custo = custo;
		this.descr = descr;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public double getCusto() {
		return custo;
	}
	
	public String getDescr() {
		return descr;
	}
}
