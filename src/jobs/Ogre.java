package jobs;

import java.util.Random;

import CalculEtCombat.Calcul;
import Objets.Interface.Objet;
import carte.Carte;

public class Ogre extends Monstre implements Type{

	public Ogre(String nom, int vie, int force, int agilite,
    		int constitution, int maxPa, int experienceDonnee) throws PersonnageCarException{
		super(nom, vie, force, agilite, constitution, maxPa, experienceDonnee);

	}

	@Override
	public String getNomType() {
		// TODO Auto-generated method stub
		return "Ogre";
	}

	@Override
	public void action(Carte c) {
		Random actionAleatoire = new Random();
		int actionOgre = actionAleatoire.nextInt(2);
		if(c.attaqueMonstre(this) ==1){
			return;
		}
		
		switch(actionOgre){
		case 0:
			c.deplacement(this);
			break;
		default:
			return;
		}
	}

	@Override
	public int getVision() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getPorteeAttaque() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void attaquer(Heros heros) {
		// TODO Auto-generated method stub
		Calcul.calculDegats(this, heros);

	}

	@Override
	public Objet objetLaisse() {
		// TODO Auto-generated method stub
		return null;
	}

}
