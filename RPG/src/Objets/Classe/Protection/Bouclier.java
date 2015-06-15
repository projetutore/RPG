package Objets.Classe.Protection;

import Objets.Interface.Arme;
import Objets.Interface.Defensif;
import jobs.Degree;
import jobs.Heros;
import jobs.Monstre;

public class Bouclier implements Arme, Defensif{

	String nomBouclier;
	private Degree impactBouclier;
	private Degree maniabiliteBouclier;
	private Degree resistanceBouclier;
	private Degree encombrement;
	private String description;
	
	public Bouclier(String nomBouclier, Degree impactBouclier,
			Degree maniabiliteBouclier, Degree resistanceBouclier,
			Degree encombrement) {
		this.nomBouclier = nomBouclier;
		this.impactBouclier = impactBouclier;
		this.maniabiliteBouclier = maniabiliteBouclier;
		this.resistanceBouclier = resistanceBouclier;
		this.encombrement = encombrement;
	}

	private int nombreMain = UNEMAIN;
	private int portee = 1;
	public final static int PORTEE_BOUCLIER= 1;

	

	public String toString(){
		return "O";
	}

	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return nomBouclier;
	}

	@Override
	public Degree getImpactArme() {
		// TODO Auto-generated method stub
		return impactBouclier;
	}

	@Override
	public Degree getManiabilite() {
		// TODO Auto-generated method stub
		return maniabiliteBouclier;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public int getNombreMain() {
		// TODO Auto-generated method stub
		return nombreMain;
	}

	@Override
	public int getPortee() {
		// TODO Auto-generated method stub
		return PORTEE_BOUCLIER;
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return  nomBouclier +"\nImpactArme: "+ impactBouclier + "; Maniabilite:"+ maniabiliteBouclier + 
				"\nResistanceBouclier: " + resistanceBouclier + "; Poids du bouclier : " + encombrement
				+ "\nPortee = " + portee + "; NombreMain = " + nombreMain;
	}

	@Override
	public Arme equiper(Heros h) {
		// TODO Auto-generated method stub
		h.setDegats(Degree.somme(h.getDegats(), this.getImpactArme()));
		h.setAttaque(Degree.somme(h.getAttaque(), this.getManiabilite()));
		h.setEsquive(Degree.soustraction(h.getEsquive(),this.getEncombrement()));
		h.setDefense(Degree.somme(h.getDefense(), this.getSolidite()));
		h.retirerObjet(this);
		return this;
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
		h.setEsquive(Degree.somme(h.getEsquive(),this.getEncombrement()));
		h.setDefense(Degree.soustraction(h.getDefense(), this.getSolidite()));

		h.ajoutObjet(this);
		return this;

	}

	@Override
	public Arme desequiper(Heros h){
		return null;
	}

	@Override
	public Degree getEncombrement() {
		return encombrement;
	}

	@Override
	public Degree getSolidite() {
		// TODO Auto-generated method stub
		return resistanceBouclier;
	}


	@Override
	public void utiliser(Heros h) {
		h.equiper(this);
		
	}

	@Override
	public void attaquer(Heros h, Monstre m) {
		// TODO Auto-generated method stub
		
	}

	
}
