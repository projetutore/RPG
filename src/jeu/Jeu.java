package jeu;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import jobs.Bombo;
import jobs.Degree;
import jobs.Heros;
import jobs.Monstre;
import jobs.PersonnageCarException;
import Objets.Classe.Arme.Arc;
import Objets.Classe.Arme.EpeeLourde;
import Objets.Classe.Arme.ExceptionArme;
import Objets.Classe.Arme.Gungnir;
import carte.Carte;
import carte.Elements;
import carte.PtsCarte;

public class Jeu implements Serializable {
	
	private Carte carte;
	private Heros personnageJoueur;
	


	
	public Carte selectionCarte(){
		
		carte = new Carte(20,20);
		personnageJoueur = Heros.creationPersonnage();
		//carte.Afficher();
		carte.placer(1, 1, personnageJoueur);
			
		ArrayList<Monstre>listeMonstre = null;
		try {
		listeMonstre = Monstre.ajoutMonstreType();
		} catch (PersonnageCarException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 Arc artemis = null;
		    EpeeLourde claymore = null;
		    Gungnir gungnir = null;
		    try {
				 artemis = new Arc("Hankyu", new Degree(4), new Degree(6), 3, "");
				 claymore = new EpeeLourde("Claymore", new Degree(14), new Degree(4), "" );
				 gungnir = new Gungnir();
			} catch (ExceptionArme e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    personnageJoueur.equiper(claymore);
		    personnageJoueur.equiper(claymore);

		carte.placer(4, 2, new Bombo((Bombo) listeMonstre.get(0)));		carte.placer(6, 6, new Bombo((Bombo) listeMonstre.get(1)));		carte.placer(5, 2, new Bombo((Bombo) listeMonstre.get(0)));
		
		
		carte.placer(1, 5, claymore);	carte.placer(1, 4, new PtsCarte("#"));	carte.placer(1, 6, new PtsCarte("#"));
		
		carte.placer(2, 5, new Bombo((Bombo) listeMonstre.get(0)));	
		
		carte.placer(9, 4, new Bombo((Bombo) listeMonstre.get(0)));	carte.placer(9, 3, artemis);
		
//		carte.placer(9, 2, listeMonstre.get(0));	carte.placer(7, 10, listeMonstre.get(0));

		carte.placer(5, 10, listeMonstre.get(0));	carte.placer(6, 9, new Bombo((Bombo) listeMonstre.get(0)));	carte.placer(7, 10, new Bombo((Bombo) listeMonstre.get(0)));

		//carte.deplacement();	
	//    System.out.println(carte.Afficher());
	   
	    carte.placer(6,10, gungnir); 
		//  personnageJoueur.choix(carte);
		
	    
		return carte;
		
	}
	
	
	public Jeu(){
		carte = this.selectionCarte();
	}
	
	@Override
	public String toString() {
		return "Jeu :\n" + carte + "\npersonnageJoueur=" + personnageJoueur.affichageCaracteristique();
				
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Heros getPersonnageJoueur() {
		return personnageJoueur;
	}
	public void setPersonnageJoueur(Heros personnageJoueur) {
		this.personnageJoueur = personnageJoueur;
	}
	public static void sauvegardeJeu(Jeu jeu){
		FileOutputStream outputFile = null;
		try {
			outputFile = new FileOutputStream("sauvegarde0.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//int i = 0;
		
		ObjectOutputStream outputWriter;
		
		try{
		/*	for(i =0; outputFile.equals("sauvegarde"+i+".txt");  i++){
				outputFile = new FileOutputStream("sauvegarde"+i+".txt");
			}*/
//			outputFile = new FileOutputStream("sauvegarde"+i+".txt");

			outputWriter = new ObjectOutputStream(outputFile);
			
			outputWriter.writeObject(jeu);
			
			outputWriter.close(); outputFile.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
	
	public static Jeu chargementJeu(){
		FileInputStream inputfile = null;
		try {
			inputfile = new FileInputStream("sauvegarde0.txt");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectInputStream inputReader = null;
		Jeu j =null;
		try{
			for (int i = 0; i< 99; i++){
				inputReader = new ObjectInputStream(inputfile);

				
				//if(((Object) inputfile).exists)){			
					//System.out.println(inputfile);	
				
					j=(Jeu) inputReader.readObject(); // on lit le caractère et on note son code dans i
					
					//System.out.print(j.toString()+"\n");
				
				
					}
					
		//			inputfile = new FileInputStream("sauvegarde"+i+".txt");
			//	}
				
			inputReader.close();
		}catch(FileNotFoundException e){
			try {
				inputReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch(EOFException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
	public static void main(String args[]){
		Jeu x= new Jeu();
		Jeu.sauvegardeJeu(x);
		x.getPersonnageJoueur().choix(x.getCarte());
		//Jeu y = Jeu.chargementJeu();
	  
	}
}
