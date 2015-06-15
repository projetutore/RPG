package Objets.Classe.Arme;

import CalculEtCombat.Calcul;
import Objets.Interface.Arme;
import jobs.Degree;
import jobs.Heros;
import jobs.Monstre;

public class Main implements Arme {

	private String nomMain;

	
	public Main(String nomMain){
		this.nomMain = nomMain;
	}
	public String getNomObjet() {
		return nomMain;
	}

	@Override
	public Degree getImpactArme() {
		// TODO Auto-generated method stub
		return new Degree();
	}

	@Override
	public Degree getManiabilite() {
		// TODO Auto-generated method stub
		return new Degree();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Votre main, quoi de plus?";
	}

	@Override
	public int getNombreMain() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getPortee() {
		// TODO Auto-generated method stub
		return 1;
	}

	public String toString(){
		return nomMain +" " +this.getDescription();
	}


	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return nomMain;
	}
	

	@Override
	public Arme equiper(Heros h) {
		h.setDegats(h.getDegats());
		h.setAttaque(h.getAttaque());
		return this;

	}
	@Override
	public Arme desequiper(Heros h) {
		return null;
	}
	
	@Override
	public Arme desequiper(Heros h, int numMain) {
		switch(numMain){
		case 1: 
			h.setMainDroite(Heros.DEFAULT_MAINDROITE);
			break;
		case 2:
			h.setMainGauche(Heros.DEFAULT_MAINGAUCHE);
			break;
		}
		h.setDegats(Degree.soustraction((h.getDegats()), this.getImpactArme()));
		h.setAttaque(Degree.soustraction((h.getAttaque()),  this.getManiabilite()));
		return this;
	}
	@Override
	public void attaquer(Heros h, Monstre m) {
		Calcul.calculDegats(h, m);
	}
	
	public void utiliser(Heros h) {
		h.equiper(this);;
	}

}
