package Objets.Classe.Arme;

import java.util.ArrayList;

import Objets.Interface.Arme;
import jobs.Degree;

public class CreationArme {

	public static ArrayList<Arme> creationArme(){
		return null;
	}
	
	public static ArrayList<Arme> creationEpeeLegeres() throws ExceptionArme{
		ArrayList<Arme> listeEpeeLegere = new ArrayList<Arme>();
	
		Arme epeeAcier = new EpeeLegere("Epee d'acier", new Degree(2), new Degree(4), "Une épée d'acier basique");
		Arme rapiere = new EpeeLegere("Rapière", new Degree(4), new Degree(5), "L'arme du bretteur par excellence");
		Arme flamberge = new EpeeLegere("Flamberge", new Degree(7), new Degree(6), "Une épee légère aux attraits d'epee lourde");
		Arme vorpal_sword = new EpeeLegere("Epee Vorpale", new Degree(10), new Degree(4), "La lame de cette épée ne peut-être parée");
		
		listeEpeeLegere.add(epeeAcier);
		listeEpeeLegere.add(rapiere);
		listeEpeeLegere.add(flamberge);
		listeEpeeLegere.add(vorpal_sword);
		
		return listeEpeeLegere;
	}
	
	public static ArrayList<Arme> creationEpeeLourdes() throws ExceptionArme{
		ArrayList<Arme> listeEpeeLourde = new ArrayList<Arme>();
		
		Arme glaive = new EpeeLourde("Glaive", new Degree(6), new Degree(5), "Un glaive, son tranchant peut couper du bois en deux");
		Arme zweihander = new EpeeLourde("Zweihänder", new Degree(11), new Degree(4), "Les soldats sont amateurs de cette epee");
		Arme claymore = new EpeeLourde("Claymore", new Degree(14), new Degree(4), "Le tranchant de cette épée est reconnu de tous pour couper des chaines");
		
		listeEpeeLourde.add(glaive);
		listeEpeeLourde.add(zweihander);
		listeEpeeLourde.add(claymore);
		
		return listeEpeeLourde;
	}
		
	public static ArrayList<Arme> creationLances() throws ExceptionArme{
		ArrayList<Arme> listeLance = new ArrayList<Arme>();
		
		Arme pique = new Lance("Pique", new Degree(4), new Degree(3), "");
		Arme hallebarde = new Lance("Hallebarde", new Degree(7), new Degree(5), "");
		Arme trident = new Lance("Trident", new Degree(11), new Degree(5), "");
		
		listeLance.add(pique);
		listeLance.add(hallebarde);
		listeLance.add(trident);
		
		return listeLance; 
	}
	
	public static void afficherListeArme(ArrayList<Arme> a){
	
	int indice = 0;

	for (Arme arme : a){
		System.out.println("indice " + indice + ": " + arme.affichageCaracteristique() +"\n");
		indice++;
	}
}
	public static void main(String args[]) throws ExceptionArme{
		ArrayList<Arme> epeeLegere = CreationArme.creationLances();
		
		CreationArme.afficherListeArme(epeeLegere);
	}
}
