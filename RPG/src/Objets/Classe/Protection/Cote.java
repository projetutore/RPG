package Objets.Classe.Protection;

import jobs.Degree;
import jobs.Heros;
import Objets.Interface.Armure;

public class Cote implements Armure{

	String nomCote;
	private Degree resistanceCote;
	private Degree poids;
	private String description;
	
	
	public Cote(String nomCote, Degree resistanceCote,
		 Degree poids, String description) {
		this.nomCote = nomCote;
		this.resistanceCote = resistanceCote;
		this.poids = poids;
		this.description = description;
	}

	@Override
	public Degree getEncombrement() {
		// TODO Auto-generated method stub
		return poids;
	}

	@Override
	public Degree getSolidite() {
		// TODO Auto-generated method stub
		return resistanceCote;
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return nomCote;
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return  nomCote + "\nResistance Côte: " + resistanceCote + "; Poids de la côte : " + poids
				+ "\nEmplacement : " + this.emplacementEquipement();
		}

	@Override
	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Torse";
	}

	@Override
	public Armure equiper(Heros h) {
		h.setDefense(Degree.somme(h.getDefense(), this.getSolidite()));
		h.setEsquive(Degree.soustraction(h.getEsquive(),this.getEncombrement()));

		h.retirerObjet(this);
		return this;

	}

	@Override
	public Armure desequiper(Heros h) {
		h.setDefense(Degree.soustraction(h.getDefense(), this.getSolidite()));
		h.setEsquive(Degree.somme(h.getEsquive(),this.getEncombrement()));
		h.ajoutObjet(this);
		return this;

	}

	@Override
	public void utiliser(Heros h) {
		h.equiper(this);

	}

	public String toString(){
		return "O";
	}
}
