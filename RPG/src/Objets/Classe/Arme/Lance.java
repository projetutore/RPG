package Objets.Classe.Arme;

import CalculEtCombat.Calcul;
import Objets.Interface.Arme;
import jobs.Degree;
import jobs.Heros;
import jobs.Monstre;

public class Lance implements Arme {

	private String nomLance; 
	private Degree impactLance;
	private Degree maniabilite;
	private String description;
	private int portee = 2 ; 
	private int nombreMain = UNEMAIN ;

	
	public Lance(String nomLance, Degree impactLance, Degree maniabilite, String description) throws ExceptionArme {
		this.nomLance = nomLance;
		this.impactLance = impactLance; 
		this.maniabilite = maniabilite;
		this.description = description;
	}

	@Override
	public String getNomObjet() {
		return nomLance;
	}

	@Override
	public Degree getImpactArme() {
		// TODO Auto-generated method stub
		return impactLance;
	}

	@Override
	public Degree getManiabilite() {
		// TODO Auto-generated method stub
		return maniabilite;
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
		return portee;
	}

	public String toString(){
		return "O";
	}
	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return  nomLance +"\nImpactArme: "+ impactLance + "; Maniabilite:"+ maniabilite +"\nPortee = " + portee
				+ "; NombreMain = " + nombreMain;
	}

	public String emplacementEquipement() {
		// TODO Auto-generated method stub
		return "Main";
	}

	@Override
	public Arme equiper(Heros h) {
		h.setDegats(Degree.somme(h.getDegats(), this.getImpactArme()));
		h.setAttaque(Degree.somme(h.getAttaque(), this.getManiabilite()));
		
		h.retirerObjet(this);
		return this;
	}

	@Override
	public Arme desequiper(Heros h, int numMain) {
		h.setMainDroite(Heros.DEFAULT_MAINDROITE);
		h.setMainGauche(Heros.DEFAULT_MAINGAUCHE);
		h.setDegats(Degree.soustraction((h.getDegats()), this.getImpactArme()));
		h.setAttaque(Degree.soustraction((h.getAttaque()),  this.getManiabilite()));
		h.ajoutObjet(this);
		return this;
	}

	@Override
	public Arme desequiper(Heros h) {
		return null;
		
	}

	@Override
	public void attaquer(Heros h, Monstre m) {
		Calcul.calculDegats(h, m);
	}

	public void utiliser(Heros h) {
		h.equiper(this);;
	}


	
}
