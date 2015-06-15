package Objets.Classe.Protection;

import jobs.Degree;
import jobs.Heros;
import Objets.Interface.Armure;

public class Bottes implements Armure {
	
	private String nomBottes;
	private Degree resistanceBottes;
	private Degree encombrement;
	private String description;
	
	public Bottes(String nomBottes, Degree resistanceBottes, Degree encombrement, String description){
		this.nomBottes = nomBottes;
		this.resistanceBottes = resistanceBottes;
		this.encombrement = encombrement;
		this.description = description;
	}

	@Override
	public Degree getEncombrement() {
		// TODO Auto-generated method stub
		return encombrement;
	}


	@Override
	public Degree getSolidite() {
		// TODO Auto-generated method stub
		return resistanceBottes;
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}


	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return nomBottes;
	}


	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Pieds";
	}


	@Override
	public void utiliser(Heros h) {
		h.equiper(this);
	}

	@Override
	public Armure equiper(Heros h) {
		// TODO Auto-generated method stub
		h.retirerObjet(this);
		return this;
	}


	@Override
	public Armure desequiper(Heros h) {
		// TODO Auto-generated method stub
		h.ajoutObjet(this);
		return this;
	}

	public String toString(){
		return "O";
	}
	
}
