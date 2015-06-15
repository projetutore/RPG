package carte;

import java.io.Serializable;

public interface Elements extends Serializable {
	
	/**
	 * Represente l'affichage d'un Elements sur la carte
	 * @return
	 */
	public String toString();

	/**
	 * Represente les caracteristiques d'un Elements
	 * @return : toute les caracteristiques de l'Elements.
	 */
	public String affichageCaracteristique();
}
