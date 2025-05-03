package iut.gon.tp3;

public class GrilleModel {

	String[][] tableau = new String[3][3];
	
	public GrilleModel() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tableau[i][j] = String.format("%.%",i,j);
			}
		}	
	}
	
	public String getCase (int lg, int col) {
		return tableau[lg][col];
	}
	
	public void setCase (int lg, int col, String texte) {
		tableau[lg][col] = texte;
	}
}

