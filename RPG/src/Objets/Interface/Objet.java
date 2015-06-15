package Objets.Interface;

import jobs.Heros;
import carte.Elements;

public interface Objet extends Elements{

	public String getNomObjet();
	public String toString();
	public String affichageCaracteristique();
	public void utiliser(Heros h);
}
