package pt.upt.ia.pesquisa;

public class Ramo {
	private IEstado estado;
	private double custo;
	private String descr;
	
	public Ramo( IEstado estado, double custo) {
		this.estado = estado;
		this.custo = custo;
		this.descr = "";
	}
	
	public Ramo( IEstado estado, double custo, String descr) {
		this.estado = estado;
		this.custo = custo;
		this.descr = descr;
	}
	
	public IEstado getEstado() {
		return estado;
	}
	
	public double getCusto() {
		return custo;
	}
	
	public String getDescr() {
		return descr;
	}
}
