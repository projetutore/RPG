package Objets.Classe.Arme;

import java.util.Random;

import CalculEtCombat.Calcul;
import Objets.Interface.Arme;
import jobs.Degree;
import jobs.Heros;
import jobs.Monstre;

public class HacheDeuxMains implements Arme {
	private String nomHacheDeuxMains; 
	private Degree impactHacheDeuxMains;
	private Degree maniabilite;
	private String description;
	private int portee = 1 ; 
	
	public String getNomObjet() {
		return nomHacheDeuxMains;
	}

	public String affichageCaracteristique() {
		return  nomHacheDeuxMains + "\nImpactArme: "+ impactHacheDeuxMains + "; Maniabilite: " + maniabilite +
				"\nPortee = " + portee + "; NombreMain = " + DEUXMAINS;
	}

	public String emplacementEquipement() {
		return "Main";
	}

	public Degree getImpactArme() {
		return impactHacheDeuxMains;
	}

	public Degree getManiabilite() {
		return maniabilite;
	}

	public Degree getImpactMagique() {
		return null;
	}

	public String getDescription() {
		return null;
	}

	public int getNombreMain() {
		return DEUXMAINS;
	}
	
	public int getPortee() {
		return portee;
	}

	@Override
	public Arme equiper(Heros h) {
		h.setDegats(Degree.somme(h.getDegats(), this.getImpactArme()));
		h.setAttaque(Degree.somme(h.getAttaque(), this.getManiabilite()));
		
		h.retirerObjet(this);
		return this;
	}

	@Override
	public Arme desequiper(Heros h) {
		h.setMainDroite(Heros.DEFAULT_MAINDROITE);
		h.setMainGauche(Heros.DEFAULT_MAINGAUCHE);
		h.setDegats(Degree.soustraction((h.getDegats()), this.getImpactArme()));
		h.setAttaque(Degree.soustraction((h.getAttaque()),  this.getManiabilite()));
		h.ajoutObjet(this);
		return this;
	}

	@Override
	public Arme desequiper(Heros h, int numMain) {
		return null;
	}

	@Override
	public void attaquer(Heros h, Monstre m) {
		int degats = Calcul.calculDegats(h, m);
		Random chancebriseDefense = new Random();
		int brisedefense = chancebriseDefense.nextInt(4);
		if(degats>0){
			if(brisedefense == 1){
				m.getDefense().setReste(m.getDefense().getReste()-4);
			}
		}
	}

	public void utiliser(Heros h) {
		h.equiper(this);;
	}


	public String toString(){
		return "O";
	}
}
