package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Acao;

//
// dois baldes, de 9 e 4 litros
// medir 6 litros no primeiro balde
//
public class Baldes34 implements IEstado {
	private int b3;
	private int b4;

	public Baldes34(int b3, int b4) {
		this.b3 = b3;
		this.b4 = b4;
	}

	public double h() {
		return 0; // Math.abs(b4 - 2);
	}

	public ArrayList<Acao> suc() {
		ArrayList<Acao> s = new ArrayList<Acao>();
		if (b3 > 0) {
			s.add(new Acao(new Baldes34(0, b4), 1));
		}
		if (b4 > 0) {
			s.add(new Acao(new Baldes34(b3, 0), 1));
		}
		if (b3 < 3) {
			s.add(new Acao(new Baldes34(3, b4), 1));
		}
		if (b4 < 4) {
			s.add(new Acao(new Baldes34(b3, 4), 1));
		}
		if (b3 < 3 && b4 > 0) {
			int falta = 3 - b3;
			if (falta >= b4) {
				s.add(new Acao(new Baldes34(b3+b4, 0), 1));
			} else {
				s.add(new Acao(new Baldes34(3, b4-falta), 1));
			}
		}
		if (b4 < 4 && b3 > 0) {
			int falta = 4 - b4;
			if (falta >= b3) {
				s.add(new Acao(new Baldes34(0, b4+b3), 1));
			} else {
				s.add(new Acao(new Baldes34(b3-falta, 4), 1));
			}
		}
		return s;
	}

	public boolean goal() {
		return b4 == 2;
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> i = new ArrayList<IEstado>();
		i.add(new Baldes34(0, 0));
		return i;
	}

	public String toString() {
		String st = "" + b3 + "  " + b4;
		return st;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Baldes34 other = (Baldes34) obj;
		if (b3 != other.b3)
			return false;
		if (b4 != other.b4)
			return false;
		return true;
	}

	// hashCode, associa a cada objeto um n�mero inteiro que se deseja
	// distintivo
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = b3;
		result = (result * prime) + b4;
		return result;
	}

	public static void main(String[] args) {
		Baldes34 b = new Baldes34(0,0);
		ArrayList<Acao> s = b.suc();
		System.out.println("-------------");
		System.out.println(b);
		System.out.println("");
		for (Acao ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h());
		}
	}

}
