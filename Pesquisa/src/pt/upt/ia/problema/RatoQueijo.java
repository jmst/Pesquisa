package pt.upt.ia.problema;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import pt.upt.ia.pesquisa.Estado;
import pt.upt.ia.pesquisa.No;
import pt.upt.ia.pesquisa.Ramo;

public class RatoQueijo extends Estado {
	private int hash = Integer.MAX_VALUE;
	private int posL;
	private int posC;
	private int posQL = 7;
	private int posQC = 7;
	private Label mensagem;
	private Label[][] lp;

	public RatoQueijo() {
		this(0, 0);
	}

	public RatoQueijo(int posL, int posC) {
		this.posL = posL;
		this.posC = posC;
	}

	public static ArrayList<Estado> getIniciais() {
		ArrayList<Estado> lista = new ArrayList<>();
		lista.add(new RatoQueijo());
		return lista;
	}

	public boolean goal() {
		return (posL == posQL && posC == posQC);
	}

	public double h() {
		return Math.abs(posL - posQL) + Math.abs(posC - posQC);
	}

	public ArrayList<Ramo> suc() {
		ArrayList<Ramo> s = new ArrayList<>();
		for (int l = posL - 1; l <= posL + 1; l++) {
			for (int c = posC - 1; c <= posC + 1; c++) {
				if (l != posL || c != posC) {
					if (valida(l, c)) {
						s.add(new Ramo(new RatoQueijo(l, c), 1));
					}
				}
			}

		}
		return s;
	}

	private boolean valida(int l, int c) {
		if (l < 0 || c < 0 || l > 7 || c > 7)
			return false;
		if (l == 0 && c == 3)
			return false;
		if (l == 2 && c == 3)
			return false;
		if (l == 3 && c == 3)
			return false;
		if (l == 3 && c == 4)
			return false;
		if (l == 3 && c == 5)
			return false;
		if (l == 5 && c == 6)
			return false;
		if (l == 6 && c == 6)
			return false;
		if (l == 7 && c == 6)
			return false;
		return true;
	}

	@Override
	public String getChave() {
		return "" + posL + ":" + posC;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for (int l = 0; l < 8; l++) {
			for (int c = 0; c < 8; c++) {
				sb.append(" " + ((l == posL && c == posC) ? "R"
						: (l == posQL && c == posQC) ? "Q" : valida(l, c) ? "." : "#"));
			}
			sb.append("\n");
		}
		return new String(sb);
	}

	public Node getNode() {
		GridPane grid = new GridPane();
		grid.setPrefWidth(1000);
		lp = new Label[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				lp[i][j] = new Label("  ");
				lp[i][j].setFont(new Font("Lucida Console", 10));
				grid.add(lp[i][j], j + 2, i + 2);
			}
		}
		mensagem = new Label("");
		grid.add(mensagem, 1, 10, 12, 1);
		grid.add(new Label("        "), 10, 0);
		return grid;
	};

	public void displayNode(No no) {
		Platform.runLater(() -> {
			for (int l = 0; l < 8; l++)
				for (int c = 0; c < 8; c++) {
					if (l == posL && c == posC) {
					lp[l][c].setText(" R ");
					} else if (l == posQL && c == posQC) {
						lp[l][c].setText(" Q ");
					} else if (!valida(l, c)) {
						lp[l][c].setText(" # ");
					} else {
						lp[l][c].setText("   ");
					}
				}
		});
	};

	public void setMensagem(String st) {
		mensagem.setText(st);
	}

	@Override
	public int getKey() {
		if (hash != Integer.MAX_VALUE)
			return hash;
		hash = hashCode();
		return hash;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result * 7 + posL;
		result = result * 7 + posC;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) // null não é igual
			return false;
		if (o.getClass() != RatoQueijo.class) // classe diferente: não é igual
			return false;
		if (this == o) // tem a mesma referência de memória: é o mesmo objeto
			return true;
		RatoQueijo oo = (RatoQueijo) o;
		return posL == oo.posL && posC == oo.posC;
	}

}
