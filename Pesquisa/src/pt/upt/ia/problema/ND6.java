package pt.upt.ia.problema;

import java.util.ArrayList;

import pt.upt.ia.pesquisa.Estado;
import pt.upt.ia.pesquisa.Ramo;

public class ND6 extends Estado {
	private int[] p;
	private int hash = Integer.MAX_VALUE;

	public ND6() {
		p = null;
	}

	public ND6(int[] p) {
		this.p = p;
	}

	public double h() {
		double vh = 0;
		for (int i = 0; i < 7; i++) {
			int v = p[i];
			switch (v) {
			case 0:
				vh += Math.abs(i - 3);
				break;
			case 1:
				vh += Math.abs(i - 6);
				break;
			case 2:
				vh += Math.abs(i - 5);
				break;
			case 3:
				vh += Math.abs(i - 4);
				break;
			case 4:
				vh += Math.abs(i - 2);
				break;
			case 5:
				vh += Math.abs(i - 1);
				break;
			case 6:
				vh += Math.abs(i - 0);
				break;
			}
		}
		return vh;
	}

//	@Override
//	public int getKey() {
//		if (hash != Integer.MAX_VALUE)
//			return hash;
//		hash = hashCode();
//		return hash;
//	}

	public ArrayList<Ramo> suc() {
		int[] np;
		ArrayList<Ramo> s = new ArrayList<Ramo>();
		for (int i = 0; i < 6; i++) {
			np = copia(p);
			if (np[i] > 0 && np[i + 1] == 0) {
				np[i + 1] = np[i];
				np[i] = 0;
				s.add(new Ramo(new ND6(np), 1));
			}
			np = copia(p);
			if (np[i] == 0 && np[i + 1] > 0) {
				np[i] = np[i + 1];
				np[i + 1] = 0;
				s.add(new Ramo(new ND6(np), 1));
			}
		}
		for (int i = 0; i < 5; i++) {
			np = copia(p);
			if (np[i] > 0 && np[i + 2] == 0) {
				np[i + 2] = np[i];
				np[i] = 0;
				s.add(new Ramo(new ND6(np), 1));
			}
			np = copia(p);
			if (np[i] == 0 && np[i + 2] > 0) {
				np[i] = np[i + 2];
				np[i + 2] = 0;
				s.add(new Ramo(new ND6(np), 1));
			}
		}
		return s;
	}

	public int[] copia(int[] p) {
		int[] np = new int[7];
		for (int i = 0; i < 7; i++)
			np[i] = p[i];
		return np;
	}

	public boolean goal() {
		return p[0] == 2 && p[1] == 2 && p[2] == 2 && p[3] == 0 && p[4] == 1 && p[5] == 1 && p[6] == 1;
	}

	public int get(int i) {
		return p[i];
	}

	// hashCode, associa a cada objeto um número inteiro que se deseja
	// distintivo
	@Override
	public int hashCode() {
		int result = 0;
		for (int c = 0; c < 7; c++)
			result = result * 10 + p[c];
		return result;
	}

	public boolean equals(Object o) {
		if (o == null) // null não é igual
			return false;
		if (o.getClass() != ND6.class) // classe diferente: não é igual
			return false;
		if (this == o) // tem a mesma referência de memória: é o mesmo objeto
			return true;
		ND6 oo = (ND6) o;
		for (int i = 0; i < 7; i++)
			if (p[i] != oo.get(i))
				return false;
		return true;
	}

	public static ArrayList<Estado> getIniciais() {
		ArrayList<Estado> i = new ArrayList<Estado>();
		int[] p = { 1, 1, 1, 0, 2, 2, 2 };
		i.add(new ND6(p));
		return i;
	}

	public String toString() {
		return "" + p[0] + "  " + p[1] + "  " + p[2] + "  " + p[3] + "  " + p[4] + "  " + p[5] + "  " + p[6];
	}

}
