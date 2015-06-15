package Objets.Classe.Protection;

import jobs.Degree;
import jobs.Heros;
import Objets.Interface.Arme;
import Objets.Interface.Armure;

public class Torse implements Armure{

	
	public Degree getEncombrement() {
		// TODO Auto-generated method stub
		return new Degree();
	}

	@Override
	public Degree getSolidite() {
		// TODO Auto-generated method stub
		return new Degree();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Quel torse, voici donc à quoi ont servi vos années d'entrainement!";
	}

	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return "Torse";
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return "Torse";
	}

	@Override
	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Torse";
	}

	@Override
	public Armure equiper(Heros h) {
		// TODO Auto-generated method stub
		h.setEsquive(h.getEsquive());
		h.setDefense(h.getDefense());
		return this;
	}

	@Override
	public Armure desequiper(Heros h) {
		h.setEsquive(Degree.somme(h.getEsquive(),this.getEncombrement()));
		h.setDefense(Degree.soustraction(h.getDefense(), this.getSolidite()));		
		
		return this;
	}

	@Override
	public void utiliser(Heros h) {
		// TODO Auto-generated method stub
		h.equiper(this);
	}


	public String toString(){
		return "O";
	}
}
