package pt.upt.ia.hillclimbing;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OitoRainhas {

	private int[] pos;

	public OitoRainhas() {
		pos = new int[8];
		for (int i = 0; i < 8; i++)
			pos[i] = (int) (Math.random() * 8);
	}

	public OitoRainhas(int[] pos) {
		this.pos = pos;
	}

	private int[] getPos() {
		return pos;
	}

	protected int h() {
		int cnt = 0;
		for (int l = 0; l < 6; l++) {
			for (int ls = l + 1; ls < 8; ls++) {
				if (pos[ls] == pos[l]) {
					cnt++;
					// System.out.println("! " + l + " (" + pos[l] + ") " + ls +
					// " (" + pos[ls] + ")");
				} else {
					if (Math.abs(pos[ls] - pos[l]) == Math.abs(ls - l)) {
						cnt++;
						// System.out.println("/ " + l + " (" + pos[l] + ") " +
						// ls + " (" + pos[ls] + ")");
					}
				}
			}
		}
		return cnt;
	}

	private ArrayList<OitoRainhas> getSuc() {
		ArrayList<OitoRainhas> suc = new ArrayList<>();
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				if (c != pos[l]) {
					int[] np = copia(pos);
					np[l] = c;
					OitoRainhas novo = new OitoRainhas(np);
					suc.add(novo);
				}
			}
		}
		return suc;
	}

	private int[] copia(int[] p) {
		// int[] np = new int[8];
		// for (int i = 0; i < 8; i++)
		// np[i] = p[i];
		// return np;
		return p.clone();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				buf.append(" " + (pos[l] == c ? ("" + l) : "."));
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	private static OitoRainhas pesquisa(OitoRainhas or) {
		OitoRainhas ant = null;
		while (ant == null || ant != or) {
			ant = or;
			for (OitoRainhas s : or.getSuc()) {
				if (s.h() < or.h()) {
					or = s;
				}
			}
		}
		System.out.println("\n"+or+"["+or.h()+"]");
		if (or.h() == 0) {
			System.out.println("FIM...");
		}
		return or;
	}

	public static void main(String[] args) {
		OitoRainhas or = new OitoRainhas();
		System.out.println(or + "\n" + or.h() + "\n");
//		for (OitoRainhas suc : or.getSuc()) {
//			System.out.println(suc + "\n" + suc.h() + "\n");
//		}
		// System.out.println(suc + "\n" + suc.h() + "\n");
		 OitoRainhas res = pesquisa(or);
		 System.out.println(res + "\n" + res.h() + "\n");
	}
}
