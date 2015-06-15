package Objets.Interface;

import jobs.Heros;
import jobs.Monstre;

public interface Arme extends Offensif{

	public Arme equiper(Heros h);
	public Arme desequiper(Heros h);
	public Arme desequiper(Heros h, int numMain);
	public void attaquer(Heros h, Monstre m);
}
