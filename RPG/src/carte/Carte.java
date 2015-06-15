package carte;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import jobs.Heros;
import jobs.Monstre;
import jobs.Personnage;
import jobs.Type;
import jobs.PersonnageCarException;
import Objets.Classe.Arme.CreationArme;
import Objets.Classe.Arme.ExceptionArme;
import Objets.Interface.Objet;

public class Carte implements Serializable{
	
	//Attrubuts
	private int nbLig;
	private int nbCol;
	private Elements [][] grille;
	private Elements s = new PtsCarte(".");
	private Elements mur = new PtsCarte("#");
	private ArrayList<Monstre> listeMonstre; 
	private ArrayList<? extends Objet> listeObjet;
	//Constructeur
	public Carte(int n, int p){
		nbLig = n;
		nbCol = p;
		grille = new Elements [nbLig][nbCol];
		
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
				
				grille[i][j] = s;
				
			}
		}
	}
	//Accesseurs
	public Elements getCase(int l, int c){
		return grille[l-1][c-1];
	}
	
	public int getnbLig(){
		return nbLig;
	}
	
	public int getnbCol(){
		return nbCol;
	}
	public Elements[][] getGrille() {
		return grille;
	}
	//Methodes
	public String Afficher(){
		System.out.println();
		String s = "";
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
				if(grille[i][j]instanceof Monstre){
					if(((Personnage) grille[i][j]).getVie()<=0)
						grille[i][j] =  ((Type) grille[i][j]).objetLaisse();
				}
				s += " | " + grille[i][j];
				
			}
			s += "|\n";
		}

		return s;
	}
	
	public void placer(int l, int c, Elements t ){
		
		l = l-1;
		c = c-1;
		//on teste si on se trouve bien sur la grille 
		if (l<0 || c<0 || l>nbLig || c>nbCol){
			System.out.print("ERREUR de placement !");
			return;
		}
		if (grille[l][c] ==  s ){
			grille[l][c] =  t;
		}
		else {
			System.out.print("ERREUR, cet zone n'est pas s !");

		}
			
		
	}
	//Deplacer
	public void deplacerHaut(Elements el){
		try{
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
			
				if(grille[i][j].equals(el) && el instanceof Monstre){

					if(grille[i-1][j].equals(s)){
						grille[i][j] = s;
						grille[i-1][j]= el;
						return;
					}
				}
				else if(grille[i][j].equals(el)){//on cherche le joueur
					if(i-1 >= 0 && grille[i-1][j].equals(s)  ){//on teste la position
					//	tmp = grille[i][j]; //On copie le perso
						grille[i][j] = s;//on s la case 
						grille[i-1][j] = el;//on deplace le perso
					}
					else if(i-1 <= nbLig && grille[i-1][j] instanceof Objet && grille[i][j] instanceof Heros ){//on teste la position
							((Heros) grille[i][j]).ajoutObjet((Objet) grille[i-1][j]);
							grille[i-1][j] = el;
							grille[i][j] = s;
					}		
				}
			}		
		}
		}catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	
	}
	
	public void deplacerBas(Elements el){
		try{
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
			
				if(grille[i][j].equals(el) && el instanceof Monstre){

					if(grille[i+1][j].equals(s)){
						grille[i][j] = s;
						grille[i+1][j]= el;
						return;

					}
				}
				else if(grille[i][j].equals(el)){//on cherche le joueur
					if(i+1 <= nbLig && grille[i+1][j].equals(s)  ){//on teste la position
					//	tmp = grille[i][j]; //On copie le perso
						grille[i+1][j] = el;//on deplace le perso
						grille[i][j] = s;//on s la case 
						return;
					}
					
					else if(i+1 <= nbLig && grille[i+1][j] instanceof Objet && el instanceof Heros ){//on teste la position
							((Heros) el).ajoutObjet((Objet) grille[i+1][j]);
							grille[i+1][j] = el;//on deplace le perso
							grille[i][j] = s;//on s la case 
							return;
							}
					}		
				}
			}		
		}catch (ArrayIndexOutOfBoundsException e){
		}
	
	}
	
	public void deplacerGauche(Elements el){
		try{
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
				
				if(grille[i][j].equals(el) && el instanceof Monstre){
					if(grille[i][j-1].equals(s)){
						grille[i][j] = s;
						grille[i][j-1]= el;
						return;

					}
				}
				else if(grille[i][j].equals(el) && grille[i][j-1] instanceof Objet  && el instanceof Heros ){
					((Heros) el).ajoutObjet((Objet) grille[i][j-1]);
					//tmp = el;
					grille[i][j]= s;
					grille[i][j-1] = el;	
					return;
				}
				else if(grille[i][j].equals(el) && grille[i][j-1].equals(s) ){//on cherche le joueur
					if(i-1 <= nbCol){//on teste la position
				//		tmp = el; //On copie le perso
						grille[i][j] = s;//on s la case 
						grille[i][j-1] = el;//on deplace le perso

						return;
					}		
				}
				}
			}		
		}catch (ArrayIndexOutOfBoundsException e){
		}
	}
	
	public void deplacerDroite(Elements el){
		try{
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
				
				if(grille[i][j].equals(el) && el instanceof Monstre){
					if(grille[i][j+1] instanceof Heros){
						((Monstre) el).attaquer((Heros) grille[i][j+1]);
						return;
					}
					if(grille[i][j+1].equals(s)){
						grille[i][j] = s;
						grille[i][j+1]= el;
						return;

					}
				}
				else if(grille[i][j].equals(el) && grille[i][j+1] instanceof Objet  && el instanceof Heros ){
					((Heros) el).ajoutObjet((Objet) grille[i][j+1]);
					//tmp = el;
					grille[i][j]= s;
					grille[i][j+1] = el;	
					return;
				}
				else if(grille[i][j].equals(el) && grille[i][j+1].equals(s) ){//on cherche le joueur
					if(i+1 <= nbCol){//on teste la position
				//		tmp = el; //On copie le perso
						grille[i][j] = s;//on s la case 
						grille[i][j+1] = el;//on deplace le perso

						return;
					}		
				}
				}
			}		
		}catch (ArrayIndexOutOfBoundsException e){
		}
	}
	
	public void deplacement(Elements x){
		if( x instanceof Heros){
		System.out.println("Vers où voulez vous deplacer ?\n(1 = gauche, 2 = bas, 3 = droite, 5 = haut)");
		Scanner scan = new Scanner(System.in);
		int nb = 0;
		try{
		nb = scan.nextInt();
		}catch (InputMismatchException e){
			System.out.println("Rentrer un autre chiffre");
		}

		switch (nb){
		case 5: this.deplacerHaut(x);
		break;
		case 2: this.deplacerBas(x);

		break;
		case 1: this.deplacerGauche(x);

		break;
		case 3: this.deplacerDroite(x);
		break;
		default: System.out.println("mauvais chiffre");
		}
		((Heros) x).translaterPa(-3);

		}
		else if(x instanceof Monstre){
			Monstre mon = (Monstre)x;
			for(int i = 0; i < nbLig; i++){
				for(int j = 0; j<nbCol; j++){
					if(grille[i][j].equals(mon)){
							for(int k = 0; k <=  mon.getVision(); k++){
								try{
									if (grille[i+k][j] instanceof Heros){
										this.deplacerBas(x);
										return;

									}
									else if (grille[i-k][j] instanceof Heros){
										this.deplacerHaut(x);
										return;

									}
								
								}catch(ArrayIndexOutOfBoundsException e){}
								
							for(int l = 0; l<= mon.getVision(); l++){
								try{
									if (grille[i][j+l] instanceof Heros){
										this.deplacerDroite(x);

										return;

									}
							/*		else if(grille[i][j-l] instanceof Heros){
										this.deplacerGauche(x);
										System.out.println("gauche");

										return;

									}*/
								
								}catch(ArrayIndexOutOfBoundsException e){}
							}
						}	
						
						
					}
				}
			}
			Random deplacement = new Random();
			 int depla = deplacement.nextInt(4);
			switch (depla){
			case 0 :
				this.deplacerBas(x);
				return;
			case 1:
				this.deplacerHaut(x);
				return;
			case 2:
				this.deplacerDroite(x);
				return;
			case 3:
				this.deplacerGauche(x);
				return;
		}
		}
	}

	public int attaqueMonstre(Monstre mon){
		ArrayList <Heros> heros_a_attaquer = new ArrayList<Heros>();
			for(int i = 0; i < nbLig; i++){
				for(int j = 0; j<nbCol; j++){
					if(grille[i][j].equals(mon)){
							for(int k = 0; k <=  mon.getPorteeAttaque(); k++){
								try{
									if (grille[i+k][j] instanceof Heros){
										heros_a_attaquer.add((Heros) grille[i+k][j]);
		
	
									}
									else if (grille[i-k][j] instanceof Heros){
										heros_a_attaquer.add((Heros) grille[i-k][j]);

									}
								
								}catch(ArrayIndexOutOfBoundsException e){}
								
							for(int l = 0; l<=mon.getPorteeAttaque(); l++){
								try{
									if (grille[i][j+l] instanceof Heros){
										heros_a_attaquer.add((Heros) grille[i][j+l]);
									}
									else if(grille[i][j-l] instanceof Heros){
										heros_a_attaquer.add((Heros) grille[i][j-l]);
									}
								}catch(ArrayIndexOutOfBoundsException e){}
							}
						}
				}
			}
			}
		if(heros_a_attaquer.isEmpty()==false){
			Random herosAattaquer = new Random();
			int choixAttaque = herosAattaquer.nextInt(heros_a_attaquer.size());
			mon.attaquer(heros_a_attaquer.get(choixAttaque));
			return 1;
		}
		else
			return 0;

	}
	public ArrayList<Monstre> attaquer(Heros p, int numMain){
		ArrayList <Monstre> monstre_a_attaquer = new ArrayList<Monstre>();
		for(int i = 0; i < nbLig; i++){
			for(int j = 0; j<nbCol; j++){
				if(grille[i][j].equals(p)){
					switch(numMain){
					case 1:
						for(int k = 0; k <=  p.getMainDroite().getPortee(); k++){
							try{
								if (grille[i+k][j] instanceof Monstre && monstre_a_attaquer.contains(grille[i+k][j]) ==false){
									monstre_a_attaquer.add((Monstre) grille[i+k][j] );
								}
								else if(grille[i-k][j] instanceof Monstre && monstre_a_attaquer.contains(grille[i-k][j]) ==false){
									monstre_a_attaquer.add((Monstre) grille[i-k][j]);
								}
							
							}catch(ArrayIndexOutOfBoundsException e){}
							
						for(int l = 0; l<= p.getMainDroite().getPortee(); l++){
							try{
								if (grille[i][j+l] instanceof Monstre && monstre_a_attaquer.contains(grille[i][j+l]) == false){
									monstre_a_attaquer.add((Monstre) grille[i][j+l]);
								}
								else if(grille[i][j-l] instanceof Monstre && monstre_a_attaquer.contains(grille[i][j-l]) == false){
									monstre_a_attaquer.add((Monstre) grille[i][j-l]);
								}
							}catch(ArrayIndexOutOfBoundsException e){}
						}
					}	
					break;

					case 2:
						for(int k = 0; k <=  p.getMainGauche().getPortee(); k++){
							try{
								if (grille[i+k][j] instanceof Monstre && monstre_a_attaquer.contains(grille[i+k][j]) ==false){
									monstre_a_attaquer.add((Monstre) grille[i+k][j] );
								}
								else if(grille[i-k][j] instanceof Monstre && monstre_a_attaquer.contains(grille[i-k][j]) ==false){
									monstre_a_attaquer.add((Monstre) grille[i-k][j]);
								}
							}catch(ArrayIndexOutOfBoundsException e){}
							
						for(int l = 0; l<= p.getMainGauche().getPortee(); l++){
								try{
									if (grille[i][j+l] instanceof Monstre && monstre_a_attaquer.contains(grille[i][j+l]) == false){
										monstre_a_attaquer.add((Monstre) grille[i][j+l]);
									}
									else if(grille[i][j-l] instanceof Monstre && monstre_a_attaquer.contains(grille[i][j-l]) == false){
										monstre_a_attaquer.add((Monstre) grille[i][j-l]);
									}
							
								}catch(ArrayIndexOutOfBoundsException e){}
							}
						}
					break;	
					}
				}
			}
		}
		
		if(monstre_a_attaquer.isEmpty()==false){
			System.out.println("Le return a fonctionné!");
			return monstre_a_attaquer;

		/*	System.out.println("Quel monstre souhaitez-vous attaquer?" );
			Monstre.afficheCollectionMonstreAttaque(monstre_a_attaquer);
			Scanner scanner = new Scanner(System.in);
			int choix = 0;
			int f = -1;*/
		/*	
			while(f == -1 ){
				try{
					choix = scanner.nextInt();
	
					if(choix == 0){
						return null;
					}
				if(numMain == 1)
					p.getMainDroite().attaquer(p, monstre_a_attaquer.get(choix-1));		
				else
					p.getMainGauche().attaquer(p, monstre_a_attaquer.get(choix-1));
					f++;
				}catch(IndexOutOfBoundsException e){
					System.out.println("Veuillez saisir un chiffre entre 0(annuler l'attaque) et "+ monstre_a_attaquer.size());
					Monstre.afficheCollectionMonstreAttaque(monstre_a_attaquer);

				}
			}
			if(monstre_a_attaquer.get(choix-1).getVie()<0){
				for(int i = 0; i < nbLig; i++){
					for(int j = 0; j<nbCol; j++){
						if(grille[i][j].equals(monstre_a_attaquer.get(choix-1))){
							grille[i][j] = ((Type) monstre_a_attaquer.get(choix-1)).objetLaisse();
						}
					}
				}
			}
		}
		*/
//		p.translaterPa(-4);
	//	monstre_a_attaquer.clear();	
		}
		System.out.println("Le return a echoué");

		return monstre_a_attaquer;
	}

	
	public void placementAleatoire(){
		try {
			listeMonstre = Monstre.ajoutMonstreType();
		} catch (PersonnageCarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			listeObjet = CreationArme.creationEpeeLegeres();
		} catch (ExceptionArme e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random alea = new Random();
		int aleatoireX = alea.nextInt(this.nbLig);
		int aleatoireY = alea.nextInt(this.nbCol);
		int choix = alea.nextInt(3);
		int objet_tire = alea.nextInt(listeObjet.size());
		int monstre_tire = alea.nextInt(listeMonstre.size());
		if(this.grille[aleatoireX][aleatoireY].equals(s)){
			switch (choix){
			case 0: grille[aleatoireX][aleatoireY] =  listeMonstre.get(monstre_tire);
					break;
			case 1: grille[aleatoireX][aleatoireY] = listeObjet.get(objet_tire);
					break;
			case 2: grille[aleatoireX][aleatoireY] = mur;
					break;
			}
		}
		
	}
	
	public String toString() {
		return "Carte "+ nbLig + "*" + nbCol;
	}

}
