package Objets.Classe.Protection;

import jobs.Degree;
import jobs.Heros;
import Objets.Interface.Armure;

public class Casque implements Armure{

	private String nomCasque;
	private Degree poids;
	private Degree resistanceCasque;
	private String description;

	public Casque(String nomCasque, Degree poids, Degree resistanceCasque,
			String description) {
		super();
		this.nomCasque = nomCasque;
		this.poids = poids;
		this.resistanceCasque = resistanceCasque;
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
		return resistanceCasque;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return nomCasque;
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Tete";
	}

	@Override
	public void utiliser(Heros h) {
		// TODO Auto-generated method stub
		h.equiper(this);
	}

	@Override
	public Armure equiper(Heros h) {
		// TODO Auto-generated method stub
		h.setDefense(Degree.somme(h.getDefense(), this.getSolidite()));
		h.setEsquive(Degree.soustraction(h.getEsquive(),this.getEncombrement()));
		h.retirerObjet(this);
		return this;

	}

	@Override
	public Armure desequiper(Heros h) {
		// TODO Auto-generated method stub
		h.setDefense(Degree.soustraction(h.getDefense(), this.getSolidite()));
		h.setEsquive(Degree.somme(h.getEsquive(),this.getEncombrement()));
		h.ajoutObjet(this);
		return this;

	}

	public String toString(){
		return "O";
	}
}
