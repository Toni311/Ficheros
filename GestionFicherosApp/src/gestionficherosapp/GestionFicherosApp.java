package gestionficherosapp;

import gestionficheros.MainGUI;

public class GestionFicherosApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionFicherosImp getFicherosImp = new GestionFicherosImp();
		new MainGUI(getFicherosImp).setVisible(true);
	}

}
