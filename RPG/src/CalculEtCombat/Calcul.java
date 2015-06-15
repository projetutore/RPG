package CalculEtCombat;

import java.util.Random;

import jobs.Heros;
import jobs.Monstre;
import jobs.Personnage;
import Objets.Classe.Arme.Lance;

public class Calcul {

	/**
	 * La fonction qui calcule les degats prend en compte deux personnages.
	 * 
	 */
	    public static int calculDegats(Personnage p1, Personnage p2) {
	        // le personnage p1 est celui qui attaque, le p2 celui qui defend. 
	    	if(p2.getVie()<= 0){
	    		if (p2.getVie()<=0){
					return 0;
				}
	    	}
	    	
	    	Random lancer_de_des = new Random();
	    	Random xpenplus = new Random();
	    	int touche = 0;
	    	int esquive = 0;
	    	for(int i = 1; i<=p1.getAttaque().getDes(); i++){
	    		touche += (lancer_de_des.nextInt(8)+1)+(p1.getAttaque().getReste());
	    		}

	    	for(int j = 1; j<=p2.getEsquive().getDes(); j++){
	    			esquive += (lancer_de_des.nextInt(8)+1)+(p2.getEsquive().getReste());	
	    	}

	    	if(touche>=esquive){
	    		int degats = 0;
	    		int defense = 0;
	    		int degatsInfliges = 0;
	    		for(int i = 1; i<=p1.getDegats().getDes(); i++){
		    		degats += (lancer_de_des.nextInt(8)+1)+(p1.getDegats().getReste());
		    	}
		    			
		    	for(int j = 1; j<=p2.getDefense().getDes(); j++){
		    		if(p1 instanceof Heros){
		    			Heros h = (Heros) p1;
		    		/**
		    		 * Dans le cas où le personnage est un Heros équipé d'une Lance
		    		 * le résultat du lancer de dés de défense de p2 est /3 et le resultat du reste est également /3
		    		 */
		    			if(h.getMainDroite() instanceof Lance)
				    		defense += (lancer_de_des.nextInt(8)+1)/3+(p2.getEsquive().getReste());	
				    	else
				    		defense += (lancer_de_des.nextInt(8)+1)+(p2.getEsquive().getReste());
		     			}
		    		
		    		else{
		    			defense += (lancer_de_des.nextInt(8)+1)+(p2.getEsquive().getReste()); 
		    		}
		    	}

		
		    	degatsInfliges = degats-defense;	

	    		if(p1 instanceof Heros){
	    			Heros h = (Heros) p1;
	    			Monstre mon = (Monstre) p2;
	    			int xp = 0;
	    			if(h.getMainDroite().getClass().getSimpleName().equals( "EpeeLourde")){
	    				degatsInfliges *=2;
	    			}
	    			if(degatsInfliges<=0){
	    	            System.out.println(h.getNom() + " Tente d'attaquer " + mon.getNom() + ". Ce coup ne fait aucun dégât " + mon.getNom() + " n'a perdu aucun points de vie");
	    			}
	    			else{	    
	    				mon.regainDeVie(-degatsInfliges);
	    				System.out.println( h.getNom() + " inflige " + degatsInfliges+ " à " + mon.getNom());
	    				if (mon.getVie()<=0){
	    					System.out.println(mon.getNom() + " est mort et a fait tomber un objet");
	    					xp = mon.getExperienceDonnee();

	    				}
	    				else{
	    					System.out.println("\nIl lui reste à present " + mon.getVie());
	    					xp = mon.getExperienceDonnee()/5+xpenplus.nextInt(mon.getExperienceDonnee()/5);
	    					h.gainExperience(xp);
	    				}
	    			}
					h.gainExperience(xp);
	    			System.out.println(h.getNom() + " a gagné avec ce coup " + xp);
	    		}
	    		
	    		if(p1 instanceof Monstre)
		    	System.out.println("Un deux!");

	    		if(p1 instanceof Monstre){
	    			Monstre mon = (Monstre) p1;
	    			Heros h = (Heros) p2;
	    			if(degatsInfliges <= 0 ){
	    				System.out.println(h.getNom() +" s'est protégé de l'attaque de "+mon.getNom() + ". Cette attaque ne lui fait par conséquent aucun dégâts.");
	    			}
	    			else{
	    				h.regainDeVie(-degatsInfliges);
	    				System.out.println( mon.getNom() + " inflige un coup à "+ h.getNom() + ". La vie de " + h.getNom() + " est à présent de " + h.getVie());
	    			}
	    			
	    			if(h.getVie() <= 0){
	    				System.out.println("Olala, il semblerait que " + h.getNom() + " soit mort(e)");
	    			}
	    		}
	    		return degatsInfliges;
	    	}		
	    	
	    	else{
	    		System.out.println(p2.getNom() + " esquive l'attaque de "+p1.getNom());
	    	}
	    	return 0;
	    }
	    
}
