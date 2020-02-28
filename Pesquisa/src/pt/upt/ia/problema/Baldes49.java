package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.IEstado;
import pt.upt.ia.pesquisa.Ramo;

//
// dois baldes, de 9 e 4 litros
// medir 6 litros no primeiro balde
//
public class Baldes49 implements IEstado {
	private int b9;
	private int b4;

	public Baldes49(int b9, int b4) {
		this.b9 = b9;
		this.b4 = b4;
	}

	public double h() {
		return 0; // Math.abs(b9 - 6);
	}

	public ArrayList<Ramo> suc() {
		ArrayList<Ramo> s = new ArrayList<Ramo>();
		if (b9 > 0) {
			s.add(new Ramo(new Baldes49(0, b4), 1));
		}
		if (b4 > 0) {
			s.add(new Ramo(new Baldes49(b9, 0), 1));
		}
		if (b9 < 9) {
			s.add(new Ramo(new Baldes49(9, b4), 1));
		}
		if (b4 < 4) {
			s.add(new Ramo(new Baldes49(b9, 4), 1));
		}
		if (b9 < 9 && b4 > 0) {
			int falta = 9 - b9;
			if (falta >= b4) {
				s.add(new Ramo(new Baldes49(b9+b4, 0), 1));
			} else {
				s.add(new Ramo(new Baldes49(9, b4-falta), 1));
			}
		}
		if (b4 < 4 && b9 > 0) {
			int falta = 4 - b4;
			if (falta >= b9) {
				s.add(new Ramo(new Baldes49(0, b4+b9), 1));
			} else {
				s.add(new Ramo(new Baldes49(b9-falta, 4), 1));
			}
		}
		return s;
	}

	public boolean goal() {
		return b9 == 6;
	}

	public static ArrayList<IEstado> getIniciais() {
		ArrayList<IEstado> i = new ArrayList<IEstado>();
		i.add(new Baldes49(0, 0));
		return i;
	}

	public String toString() {
		String st = "" + b9 + "  " + b4;
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
		Baldes49 other = (Baldes49) obj;
		if (b9 != other.b9)
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
		int result = b9;
		result = (result * prime) + b4;
		return result;
	}

	public static void main(String[] args) {
		Baldes49 b = new Baldes49(0,0);
		ArrayList<Ramo> s = b.suc();
		System.out.println("-------------");
		System.out.println(b);
		System.out.println("");
		for (Ramo ss : s) {
			System.out.println(ss.getEstado() + "  " + ss.getEstado().h());
		}
	}

}
