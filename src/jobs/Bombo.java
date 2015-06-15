package jobs;

import java.util.ArrayList;
import java.util.Random;

import CalculEtCombat.Calcul;
import Objets.Classe.Arme.EpeeLegere;
import Objets.Classe.Arme.ExceptionArme;
import Objets.Classe.Arme.Lance;
import Objets.Classe.Protection.Casque;
import Objets.Classe.Protection.ProtegeBras;
import Objets.Interface.Arme;
import Objets.Interface.Armure;
import Objets.Interface.Objet;
import carte.Carte;

public class Bombo extends Monstre implements Type {

	public static int numeroBombo = 0;
	private int numero;
	
	public Bombo(String nom, int vie, int force, int agilite,
    		int constitution, int maxPa, int experienceDonnee) throws PersonnageCarException{
		super(nom+" "+numeroBombo, vie,force, agilite, constitution, maxPa, experienceDonnee);
		numero = numeroBombo;
		numeroBombo++;
	}

	public Bombo(Bombo x){
		super(x);
		numero = numeroBombo;
		numeroBombo++;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Bombo)) {
			return false;
		}
		Bombo other = (Bombo) obj;
		if (numero != other.numero) {
			return false;
		}
		return true;
	}
	public String getNomType() {
		// TODO Auto-generated method stub
		return "Bombo";
	}

		
	@Override
	public void action(Carte c) {
		Random actionAleatoire = new Random();
		int action = actionAleatoire.nextInt(3);
		
		if(c.attaqueMonstre(this) ==1){
			return;
		}
		switch(action){
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
		return 3;
	}
	@Override
	public int getPorteeAttaque() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public void attaquer(Heros heros) {
		
		double pourcent = (20*this.getMaxVie()) /100;
		if(this.getVie() <= pourcent){
			int explosion_m = this.getMaxVie()/4;
			heros.regainDeVie(-explosion_m);
			System.out.println(this.getNom() + " s'autodétruit. Il inflige " + explosion_m + " à " + heros.getNom());
			Calcul.calculDegats(heros, this);
			return;
		}
			Calcul.calculDegats(this, heros);
	}

	public static ArrayList<Objet> creationObjetBombo() throws ExceptionArme{
		ArrayList<Objet> listeObjetBombo = new ArrayList<Objet>();

		Arme hallebarde = new Lance("Hallebarde", new Degree(7), new Degree(5), "");
		Arme flamberge = new EpeeLegere("Flamberge", new Degree(7), new Degree(6), "Une épee légère aux attraits d'epee lourde");
		Armure casque_acier = new Casque("Casque d'acier", new Degree(4), new Degree(6), "Un casque d'acier qu'utilise les apprentis soldats");
		Armure gantelets_fer = new ProtegeBras("Gantelets de fer", new Degree(2), new Degree(4), "Des protèges bras faits de fer");
		listeObjetBombo.add(hallebarde);
		listeObjetBombo.add(flamberge);
		listeObjetBombo.add(casque_acier);
		listeObjetBombo.add(gantelets_fer);
		return listeObjetBombo;
	}
	
	@Override
	public Objet objetLaisse() {
		ArrayList<? extends Objet> objetBombo = null;
		try {
			objetBombo = Bombo.creationObjetBombo();
		} catch (ExceptionArme e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random objAlea = new Random();
		int hasardObj = objAlea.nextInt(objetBombo.size());
		
		return objetBombo.get(hasardObj);
	}
}
