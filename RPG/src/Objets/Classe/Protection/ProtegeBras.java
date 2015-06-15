package Objets.Classe.Protection;

import jobs.Degree;
import jobs.Heros;
import Objets.Interface.Armure;

public class ProtegeBras implements Armure{

	String nomProtegeBras;
	private Degree resistanceProtegeBras;
	private Degree poids;
	private String description;

	public ProtegeBras(String nomProtegeBras,Degree poids,
			Degree resistanceProtegeBras, String description) {
		this.nomProtegeBras = nomProtegeBras;
		this.poids = poids;
		this.resistanceProtegeBras = resistanceProtegeBras;
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
		return resistanceProtegeBras;
	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getNomObjet() {
		// TODO Auto-generated method stub
		return nomProtegeBras;
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return  nomProtegeBras + "\nResistance proteges-bras: " + resistanceProtegeBras + "; Poids des proteges-bras : " + poids
				+ "Emplacement : " + this.emplacementEquipement();
		}

	@Override
	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Avant-Bras";
	}

	@Override
	public void utiliser(Heros h) {
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
		h.setDefense(Degree.soustraction(h.getDefense(), this.getSolidite()));
		h.setEsquive(Degree.somme(h.getEsquive(),this.getEncombrement()));
		h.ajoutObjet(this);
		return this;
	}

	public String toString(){
		return "O";
	}
}
