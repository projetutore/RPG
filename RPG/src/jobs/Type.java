package jobs;

import java.io.Serializable;

import Objets.Interface.Objet;
import carte.Carte;

public interface Type extends Serializable {
    
	/**
	 * Donne le type du monstre.
	 * @return
	 */
	public String getNomType();
	
	
	/**
	 * Represente les actions que va effectuer un Type de Monstre sur la carte
	 * 
	 * @param c : carte où se trouve le monstre.
	 */
	public void action(Carte c);
	
	/**
	 * Un monstre tué laisse un objet sur la map.
	 * @return un objet contenu dans la liste des objets du monstre tué.
	 */
	public Objet objetLaisse();
	
}
