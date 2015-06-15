package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import Objets.Classe.Arme.Main;
import Objets.Classe.Protection.AvantBras;
import Objets.Classe.Protection.Bottes;
import Objets.Classe.Protection.Casque;
import Objets.Classe.Protection.Cote;
import Objets.Classe.Protection.Pieds;
import Objets.Classe.Protection.ProtegeBras;
import Objets.Classe.Protection.Tete;
import Objets.Classe.Protection.Torse;
import Objets.Interface.Arme;
import Objets.Interface.Armure;
import Objets.Interface.Objet;
import carte.Carte;
import carte.Elements;

/**
 * Heros est la classe representant le personnageJoueur
 * @version 1.0
 *
 */
public class Heros extends Personnage implements Elements {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Un heros possède une experience de type integer
     * 
     * @see Heros#getExperience()
     * @see Heros#setExperience(int)
     * @see Heros#gainExperience(int)
     */
    private int experience;

    /** Un heros possède un niveau integer
     * 
     * @see Heros#getNiveau()
     * @see Heros#setNiveau(int)
     * @see Heros#niveauSuperieur()
     */
    private int niveau;

    /**
     * Le "job" du heros. Non modifiable après la creation
     * 
     * Penser à regarder la documentation de la classe Jobs
     * 
     * @see Jobs
     * 
     * @see Heros#getJob()
     * @see Heros#setJob(Jobs)
     */
    private Job job;


    /**
     * Un personnage possède deux mains, chaque main contenant une arme
     *
     * @see Arme
     * 
     * @see Heros#getMainDroite()
     * @see Heros#getMainGauche()
     * @see Heros#setMainDroite(Arme)
     * @see Heros#setMainGauche(Arme)
     * @see Heros#equiper(Objet)
     * @see Heros#equipementArme(Arme)
     */
    
    public static final Arme DEFAULT_MAINDROITE = new Main("MainDroite"); 
    public static final Arme DEFAULT_MAINGAUCHE = new Main("Maingauche");
    
    private Arme mainDroite = DEFAULT_MAINDROITE;
    private Arme mainGauche = DEFAULT_MAINGAUCHE;
    
    /**
     * Un heros possède un torse, son torse par 
     * défaut n'ajoute aucun degré supplémentaire;
     * 
     * @see Armure
     * @see Torse
     * @see Cote
     * 
     * 
     *@see Heros#setTorse(Armure)
     *@see Heros#getTorse()
     *@see Heros#equiper(Objet)
     *@see Heros#equipementArmure(Armure)
     */
    public static final Armure DEFAULT_TORSE = new Torse();
    private Armure torse = DEFAULT_TORSE;
    
    /**
     * Un heros possède une tête qui par défault ne lui ajoute rien.
     * 
     * @see Armure
     * @see Tete
     * @see Casque
     * 
     * @see Heros#getTete()
     * @see Heros#setTete(Armure)
     * @see Heros#equiper(Objet)
     * @see Heros#equipementArmure(Armure)
     */
    public static final Armure DEFAULT_TETE = new Tete();
    private Armure tete = DEFAULT_TETE;
    
    /**
     * Un heros possède des avants_bras, par defaut ne lui ajoute rien.
     * 
     * @see Armure
     * @see AvantBras
     * @see ProtegeBras
     * 
     * @see Heros#getAvant_bras()
     * @see Heros#setAvant_bras(Armure)
     * @see Heros#equiper(Objet)
     * @see Heros#equipementArmure(Armure)
     */
    public static final Armure DEFAULT_AVANT_BRAS = new AvantBras();
    private Armure avant_bras = DEFAULT_AVANT_BRAS;
    
    /**
     * Un heros possède des pieds qui peuvent accueillir des bottes, 
     * par defaut lui ajoute aucun degré.
     * 
     * @see Armure
     * @see Pieds
     * @see Bottes
     * 
     * @see Heros#getPieds()
     */
    public static final Armure DEFAULT_PIEDS = new Pieds();
    private Armure pieds = DEFAULT_PIEDS;
    
    
    
    
    
    
    /**
     * Un personnage possède également un inventaire,
     * cette inventaire est un tableau d'objet de 20 emplacements.
     * 
     * @see Objet
     * @see Heros#getInventaire()
     * @see Heros#setInventaire(Objet[])
     * 
     */
    private Objet[] inventaire = new Objet[20];

    /**
     * Represente le nombre de points gagnees par niveau
     * 
     * @see FichePerso
     * @see Heros#monteeStat(int)
     * 
     */
    public static final int PTS_NIV = 3;
    
    public int ptsRestant;
    
    
    /**
     * Constructeur par defaut d'un Heros
     */
    public Heros() {
    	/**
    	 * Fait appel au constructeur par defaut de la classe Personnage
    	 */
        super();
        /**
         * Instancie un job avec le constructeur par defaut.
         */
        this.job = new Job();
        /**
         * Met l'experience du Heros a 0.
         */
        this.experience = 0;
        /**
         * Met le niveau du heros à 1
         */
        this.niveau = 1;


    }
  
    /**
     * Accesseur du job
     * 
     * @return le job du heros en cours
     */
    public Job getJob() {
        return job;
    }

    /**
     * Mutateur du job
     * 
     * @param job : job qui va remplacer celui en cours du héros.
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * Accesseur de l'experience
     * @return l'experience du heros en cours.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Augmente l'experience gagné par le heros lors d'un combat.
     * @param experienceGagnee : nombre de points d'experience gagné.
     */
    public void gainExperience(int experienceGagnee){
    	this.experience += experienceGagnee;
    	if (this.experience>=100 && this.niveau < 19){
    		this.niveauSuperieur();
    	}
    }

    /**
     * Accesseur du niveau de l'instance en cours.
     * @return : le niveau de l'instance en cours
     */
    public int getNiveau() {
        return niveau;
    }


    /**
     * acceseur de la MainDroite
     * @return l'arme contenu dans la mainDroite
     */
    public Arme getMainDroite() {
		return mainDroite;
	}

    /**
     * Modifie l'arme située dans la mainDroite
     * @param mainDroite: arme qui va remplacer l'ancienne arme.
     */
	public void setMainDroite(Arme mainDroite) {
		if(mainDroite.getNombreMain()==2){
			this.mainDroite = mainDroite; 
			this.mainGauche = mainDroite;
		}
		else if(mainDroite.getNombreMain()==2){
			this.mainDroite = mainDroite;
			this.mainGauche = DEFAULT_MAINGAUCHE;
		}
		else
			this.mainDroite = mainDroite;
	}

	/**
	 * Acceseur de l'arme située dans la mainGauche
	 * @return l'arme dans l'emplacement mainGauche
	 */
	public Arme getMainGauche() {
		return mainGauche;
	}

	/**
	 * Mutateur de la mainGauche.
	 * @param mainGauche: l'arme qui sera mise dans la mainGauche.
	 */
	public void setMainGauche(Arme mainGauche) {
			if(mainGauche.getNombreMain()==2){
			this.mainGauche = mainGauche; 
				this.mainDroite = mainGauche;
		}
		else if(mainDroite.getNombreMain()==2){
			this.mainGauche = mainGauche;
			this.mainDroite = DEFAULT_MAINDROITE;
		}
		else
			this.mainGauche = mainGauche;
	}

	/**
	 * Accesseur de l'armure équipée au torse
	 * @return : l'armure du torse
	 */
	public Armure getTorse() {
		return torse;
	}

	/**
	 * Mutateur de l'armure équipée au torse.
	 * @param torse : Armure qui remplacera la précedente armure.
	 */
	public void setTorse(Armure torse) {
		this.torse = torse;
	}

	/**
	 * Accesseur du casque équipé à la tete.
	 * @return : le casque équipé à la tete.
	 */
	public Armure getTete() {
		return tete;
	}

	/**
	 * Mutateur de la tete
	 * @param tete: Casque qui remplacera le casque précedemment équipé
	 */
	public void setTete(Armure tete) {
		this.tete = tete;
	}

	/**
	 * Acceseur des avants_bras
	 * @return : les proteges bras
	 */
	public Armure getAvant_bras() {
		return avant_bras;
	}

	/**
	 * Mutateur des avants bras
	 * @param avant_bras: remplace les avants bras precedents.
	 */
	public void setAvant_bras(Armure avant_bras) {
		this.avant_bras = avant_bras;
	}


	/**
	 * Accesseur de l'equipement aux pieds
	 * @return l'equipement au pieds.
	 */
	public Armure getPieds() {
		return pieds;
	}


	/**
	 * Mutateur de l'equipement concernant les pieds
	 * @param pieds : remplace le precedent equipement des pieds.
	 */
	public void setPieds(Armure pieds) {
		this.pieds = pieds;
	}



	public Objet[] getInventaire() {
		return inventaire;
	}

	/**
	 * La methode equiper, regarde l'emplacement de l'arme
	 * en fonction de celui-ci fait appel à une méthode équipement différente.
	 * 
	 * @param equipement
	 * 
	 * @see Arme#emplacementEquipement()
	 */
	public void equiper(Objet equipement){
		
		/**
		 * Dans le cas où objet est une instance d'arme
		 * On fait appel à equipementArme(Arme)
		 */
		if(equipement instanceof Arme){
			Arme arme_equip = (Arme) equipement;
			this.equipementArme(arme_equip);	
		}
			
		/**
		 * Dans le cas où  l'objet est une instance d'Armure
		 * On fait appelle à équipementArmure(Armure)
		 */
		
		else if(equipement instanceof Armure){
			Armure armure_equip = (Armure) equipement;
			this.equipementArmure(armure_equip);
		}
	}
	
	/**
	 * Cette fonction permet au personnage de s'équiper d'une arme.
	 * 
	 * @param arme_equip
	 * 
	 * @see Arme#equiper(Heros)
	 * 
	 */
	public void equipementArme(Arme arme_equip){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choixEquipement;
		/**
		 * Dans le cas ici ou l'arme que l'on souhaite équiper est une arme à deux mains.
		 */
		if(arme_equip.getNombreMain()==2){
			
			/**
			 * On verifie d'abord si une arme à deux mains est équipée
			 * (on peut remplacer mainDroite par mainGauche, cela revient au même)
			 */
			if(this.getMainDroite().getNombreMain() == 2){
				
				System.out.println("Etes-vous sur de vouloir remplacer : \n" + mainDroite.affichageCaracteristique()
						+ "\n1-Equiper "+arme_equip.affichageCaracteristique()+ "\n2-Annuler");
			}
			
			/**
			 * Dans le cas ou l'on serait équipé d'une ou plusieurs armes à 1 main.
			 */
			else{
				
			System.out.println("Etes-vous sur de vouloir remplacer : \n"+ mainDroite.affichageCaracteristique() + " et " 
		    + mainGauche.affichageCaracteristique()	+ " ?" + "\n1-Equiper "+arme_equip.affichageCaracteristique()+ "\n2-Annuler");
			}
			/**
			 * Choix équipement devient la réponse de l'utilisateur au programme:
			 * 1 = Oui et procéder à l'équipement
			 * 2 ou autres = annulation.
			 */
			choixEquipement = scanner.nextInt();
			while(choixEquipement!=1 && choixEquipement!=2){
				System.out.println("Choisissez entre 1 et 2");
				choixEquipement = scanner.nextInt();
			}
			
			/**
			 * Dans le cas de la réponse
			 */
			switch(choixEquipement){
		
				case 1: 
					/** 
					 * Si le personnageest équipé d'une arme à deux mains dans la mainDroite
					 * on fait appel à mainDroite.desequiper(Heros)
					 */
					if(this.getMainDroite().getNombreMain() == 2){
					mainDroite.desequiper(this);
					}
					/**
					 * Dans le cas contraire, on fait appel à mainDroite.desequiper(Heros,numMain)
					 */
					else{
						mainDroite.desequiper(this,1);
						mainGauche.desequiper(this,2);
					}
					
				
					
				/**
				 * On met dans la mainDroite(ou gauche) l'arme à équiper
				 */
				this.setMainDroite(arme_equip.equiper(this));
				break;
				case 2:
				return;
			}
		}
		
		
		/**
		 * Dans le cas ici où l'arme que l'on souhaite équiper est à une main.
		 */
		else{

		System.out.println("Dans quelle main souhaitez-vous équiper votre arme? \n1- Main Droite :" + mainDroite.affichageCaracteristique()
				+ "\n\n"+ "2- Main Gauche :" + mainGauche.affichageCaracteristique());
		/**
		 * On enregistre ensuite sa reponse dans une variable. 
		 */
		choixEquipement = scanner.nextInt();
		while(choixEquipement!=1 && choixEquipement!=2){
			System.out.println("Choisissez entre 1 et 2");
			scanner.nextInt();
		}
		
		/**
		 * Si la mainDroite(ou MainGauche) est une arme à deux mains
		 */
		
		if(mainDroite.getNombreMain()==2){
			/**
			 * On la desequipe
			 */
			mainDroite.desequiper(this);
			if(choixEquipement == 1){
				/**
				 * Puis selon la reponse de l'utilisateur au choix de la main
				 * on remplace la MainDroite(ou gauche) par l'homme qu'on souhaite équiper
				 * ici mainDroite.
				 */
				this.setMainDroite(arme_equip.equiper(this));
			}
			/**
			 * Ici mainGauche
			 */
			else{
				this.setMainGauche(arme_equip.equiper(this));
			}
		}

		/**
		 * Dans le cas où ce n'est pas le cas (arme à une main)
		 */
		else{
			
		
		switch(choixEquipement){
		/**
		 * Si choixEquipement = 1 on desequipe l'arme de la mainDroite et on la remplace par arme_equip
		 */
		case 1:
			mainDroite.desequiper(this,1);
			this.setMainDroite(arme_equip.equiper(this));
			break;
		/**
		 * Si choixEquipement = 2 on desequipe l'arme de la mainGauche et on la remplace par arme_equip.
		 */
		case 2:
	
			mainGauche.desequiper(this, 2);
			this.setMainGauche(arme_equip.equiper(this));
			break;
			}
		}

		}
	}
	
	/**
	 * Cette fonction permet de s'equiper d'une armure à l'emplacement Torse
	 * 
	 * @param armure_equip : armure que l'on souhaite Equiper
	 */
	
	public void equipementArmure(Armure armure_equip){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choixEquipement;
		
		/**
		 * En fonction de l'emplacement armure, on fait une comparaison differente
		 */
		switch(armure_equip.emplacementEquipement()){
		case "Torse":				
			System.out.println("Etes-vous sur de vouloir vous equiper de : \n"+ armure_equip.affichageCaracteristique() +
					" à la place de " + this.getTorse().affichageCaracteristique() + " ?\n1-Equiper\n2-Annuler");
			break;
		case "Pieds":
			System.out.println("Etes-vous sur de vouloir vous equiper de : \n"+ armure_equip.affichageCaracteristique() +
					" à la place de " + this.getPieds().affichageCaracteristique() + " ?\n1-Equiper\n2-Annuler");
			break;
		case "Avant-Bras":
			System.out.println("Etes-vous sur de vouloir vous equiper de : \n"+ armure_equip.affichageCaracteristique() +
					" à la place de " + this.getAvant_bras().affichageCaracteristique() + " ?\n1-Equiper\n2-Annuler");
			break;
		case "Tete":
			System.out.println("Etes-vous sur de vouloir vous equiper de : \n"+ armure_equip.affichageCaracteristique() +
					" à la place de " + this.getTete().affichageCaracteristique() + " ?\n1-Equiper\n2-Annuler");
			break;
		}
		
		choixEquipement = scanner.nextInt();
		while(choixEquipement!=1 && choixEquipement!=2){
			System.out.println("Choisissez entre 1 et 2");
			choixEquipement = scanner.nextInt();
			}
				
		switch(choixEquipement){
		/**
		 * Dans le cas où le Heros valide(rentre 1)
		 * On desequipe l'armure contenu dans emplacementEquipement egale à celui d'armure_equip
		 * puis on equipe armure_equip à l'emplacement désiré.
		 */
			case 1: 
				switch(armure_equip.emplacementEquipement()){
				case "Torse":				
					this.getTorse().desequiper(this);
					this.setTorse(armure_equip.equiper(this));
					break;
				case "Pieds":
					this.getPieds().desequiper(this);
					this.setPieds(armure_equip.equiper(this));
					break;
				case "Avant-Bras":
					this.getAvant_bras().desequiper(this);
					this.setAvant_bras(armure_equip.equiper(this));
					break;
				case "Tete":
					this.getTete().desequiper(this);
					this.setTete(armure_equip.equiper(this));
					break;
				}

				break;
		/**
		 * Dans le cas où on rentre 2, on return.			
		 */
			case 2:
				return;
		}
	}

	/**
	 * Phase de niveau supérieur (Experience >=100)
	 * 
	 * @see Heros#gainExperience(int)
	 */
    public void niveauSuperieur() {
    	@SuppressWarnings("unused")
		FichePerso base = null;
    	int gNiveau = 0;
                while(this.experience >=100 && this.niveau < 20){
                        this.experience -=100;
                        this.niveau++;
                        System.out.println(this.getNom() +" a pris en puissance, le niveau " + this.niveau + " a été atteint.");
                        this.repartitionPoints();
                        gNiveau++;
                        
                }
                ptsRestant += PTS_NIV*gNiveau;
                this.remiseDegree();
    }

    /**
     * Permet la création d'un personnageJoueur.
     * 
     * @return une instance de Heros qui sera le nouvel héros crée.
     * @since 1.0
     */
    public static Heros creationPersonnage() {
        System.out.println("Vous voici à l'heure de la création de votre personnage\n "+
                "Tout d'abord, comment vous appelerez vous?");
        Heros x = new Heros();
        while(x.getJob().getNomJob().equals("Classe")){
	        try{
	            @SuppressWarnings("resource")
	        Scanner creation = new Scanner(System.in);
	        x.setNom(creation.nextLine());
	        System.out.println("Je vois, c'est donc comme cela que vous avez décidé de vous appelez.\n" +
	                            "A présent, il est temps de décider de votre classe : \n"
	                            + "1 - Paladin \n"
	                            + "2 - Maraudeur \n"
	                            + "3 - Lance Rouge \n"
	                            + "4 - Bretteur \n"
	                            );
	        int choix = creation.nextInt();
	        x.setJob(x.getJob().choixJob(choix, x));
	        }catch(InputMismatchException e){
	        	e.printStackTrace();
	        }
        }
        x.remiseDegree();
        return x;
    }


    /**
     * Pour l'IHM, retourne un tableau d'entier contenu les stats.
     * @return
     */
    public int[] repartitionPoints(){
    	int f = this.getForce();
    	int c = this.getConstitution();
    	int a = this.getAgilite();
    	int[] stat = {f, c, a };
    	return stat;
    }
    
    /**
     * Pour l'IHM, augmente les stats d'un point.
     * @param stat
     */
    public void monteeStat(int stat){	
    	switch (stat){
    	case 0:
    		this.setForce(this.getForce()+1);
    		break;
    	case 1:
    		this.setConstitution(this.getConstitution()+1);
    		break;
    	case 2:
    		this.setAgilite(this.getAgilite()+1);
    		break;
    	}
    }
    
    /**
     * Pour l'IHM, baisse les stats d'un point.
     * @param stat
     */
    public void baisseStat(int stat){
    	switch(stat){
    	case 0:
    		this.setForce(this.getForce()-1);
    		break;
    	case 1:
    		this.setConstitution(this.getConstitution()-1);
    		break;
    	case 2:
    		this.setAgilite(this.getAgilite()-1);
    		break;
    	}
    }  

    /**
     * remiseDegree ou remiseStat
     * Ce qui sert à la gestion des Degrées.
     */
	public void remiseDegree() {
		this.setVie(this.getMaxVie());
		this.setdForce(new Degree(this.getForce()));
		this.setdConstitution(new Degree(this.getConstitution()));
		this.setdAgilite(new Degree(this.getAgilite()));
		this.setDegats(Degree.somme(this.getdForce(), this.getMainDroite().getImpactArme()));
		this.setAttaque(Degree.somme(this.getdAgilite(), this.getMainDroite().getManiabilite()));
		this.setEsquive(Degree.soustraction(this.getdAgilite(), this.torse.getEncombrement()));
		this.setDefense(Degree.somme(this.getdConstitution(), this.torse.getSolidite()));		
	}
	
	/**
	 * toString du Heros en cours, renvoi H (affichage sur la carte)
	 */
 	public String toString() {
 		return "H";
 	}

 	/**
 	 * Affichage des caractéristiques de l'instance en cours
 	 *  
 	 */
 	@Override
	public String affichageCaracteristique() {
		 return job + " " + super.affichageCaracteristique() + " niveau: " + niveau + "\n\nmainDroite : " +
				 mainDroite.affichageCaracteristique()+ "\n\nmainGauche=" + mainGauche.affichageCaracteristique()
 				+ "\n\nSTATS:degats=" + this.getDegats() + "; attaque="+this.getAttaque() + "; defense=" + this.getDefense()+"\n";
	}

 
 	
 	/**
 	 * Ajoute un objet dans l'Inventaire
 	 * 
 	 * @param o : l'objet que l'on souhaite ajouter dans l'inventaire
 	 */
 	public String ajoutObjet(Objet o){
 		int i = 0;
 		/**
 		 * On cherche un emplacement libre dans l'Inventaire
 		 */
 		while( inventaire[i] != null){
 			i++;
 		}
 		
 		/**
 		 * Quand on le trouve, on place l'objet o à cette emplacement.
 		 */
 		if(inventaire[i]== null){
 			inventaire[i] = o;
 			System.out.println("Vous avez mis dans votre sac " + o.getNomObjet() + "\n");
 		}
 		/**
 		 * Si on le trouve pas (Inventaire remplir), message affiché.
 		 */
 		else{
 			System.out.println("Vous n'avez plus de place dans votre inventaire, jeter d'abord un objet avant mettre celui-ci dans votre sac!");
 		}
		return null;
 	}
 	
 	/**
 	 * Retire un objet qui est dans l'Inventaire
 	 * 
 	 * @param o : objet que l'on souhaite retirer du sac
 	 */
 	public void retirerObjet(Objet o){
 		int i = 0;
 		
 		/**
 		 * Tant que l'Emplacement i de l'inventaire ne correspond à l'objet que l'on cherche
 		 */
 		while( inventaire[i] == null || inventaire[i] != null){
 				try{
 					/**
 					 * Si on trouve l'objet
 					 */
 					if(inventaire[i].equals(o)){
 					
 				/**
 				 * On remplace l'emplacement où est contenu l'objet par un emplacement libre (null) 
 				 */
 				inventaire[i] = null;
 				return;
 					}
 				/** 
 				 * Dans le cas où on a rien trouvé du tout
 				 * on return.
 				 */
 	 		}catch(NullPointerException e ){
 	 			return;
 	 			
 	 		}

 			i++;
 		}
 		return;
 	}
 		
 	/**
 	 * Affiche l'Inventaire
 	 * @return 
 	 * 
 	 * @see Objet#utiliser(Heros)
 	 */
 	public String affichageInventaire(){
 		
 		/**
 		 * case_vide agit comme un incrementeur
 		 */
 		int case_vide = 0;
 		
 		String s = "";

 		/**
 		 * On parcours l'Inventaire.
 		 */
 		for(int i = 0 ; i<inventaire.length; i++){
 			try{

 	 			System.out.println(inventaire[i]);

 			/**
 			 * Si le nombre de case vide est égale à la taille de l'inventaire
 			 * cela veut dire que l'inventaire est vide, il n'y a donc rien à afficher.
 			 */
 			if(case_vide == inventaire.length){
				System.out.println("Votre inventaire est vide!");
 			}
 			
 			/**
 			 *Si l'emplacement i de l'inventaire est un Objet,
 			 *on print son nom, avec le numero correspondant à son emplacement dans l'Inventaire. 
 			 */
			if(this.inventaire[i] instanceof Objet){
					s +=  "Objet " + i +": "  + inventaire[i].getNomObjet() + "\n";	
			}
			
		/**
 		 * Si l'emplacement i est vide
 		 * on incremente case_vide de 1.
 		 */
		}catch(NullPointerException e){
			case_vide+=1;
		}
 		}
			return s;

 	}
 		
 		public String affichageBis(){
 			
 		/**
 		 * Le scanner qui va permettre de choisir l'objet que l'on souhaite utiliser
 		 */
 		Scanner choix = new Scanner(System.in);
 		System.out.println("Veuillez-saisir le n° de l'objet que vous souhaitez utiliser.");
 		int choixObjet = choix.nextInt();
 		while(inventaire[choixObjet] == null){
 			return;
 		}
 		
 		
 		System.out.println("Souhaitez-vous utilisez " + inventaire[choixObjet].getNomObjet() + "\nO - Oui\nN - Non");
 		
 		/**
 		 * Dans le cas où on valide l'utilisation de l'objet,
 		 */
 			String oui_non = choix.next();
 			switch (oui_non){
 			case "O": 
 				inventaire[choixObjet].utiliser(this);
 				break;
 			case "N":
 				return;
 		}
 	}
 	
 	/**
 	 * Choix à faire sur la carte
 	 * 
 	 * @param carte : carte sur laquelle le Heros se déplace
 	 */
	public void choix(Carte carte){
		int i = 0;
	/**
	 * Boucle infini.
	 */
		while(i!=-1){
		System.out.println("Que souhaitez-vous faire ?\n(1 = deplacer, 2 = attaquer, 3 = voir l'inventaire)\nIl vous reste "+this.getPa()+"Pa/"+this.getMaxPa()+"Pa");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int nb = 0;
		try{
		nb = scan.nextInt();
		}catch (InputMismatchException e){
			System.out.println("Rentrer un autre chiffre");
		}

		
		switch (nb){
		
		case 1: 
			if(this.getPa()<3){
				System.out.println("Vous n'avez pas assez de PA pour vous déplacer.");
				break;
			}
			carte.deplacement(this);
			break;
		case 2:
			if(this.getPa()<4){
				System.out.println("Vous n'avez pas assez de PA pour attaquer.");
				break;
			}
		
			if(mainDroite.getNombreMain()==2){
				System.out.println("Votre arme : "+ this.getMainDroite().affichageCaracteristique()
						+ "\nValider vous votre attaque? \n1 - Attaquer \n2 - Annuler");
				int choixAction = 0;
				
				choixAction = scan.nextInt();
				
				switch(choixAction){
				case 1 :
					carte.attaquer(this, 1);
					break;
				}
			}
			else{
				
		
			int choixMain = 0;
			System.out.println("Avec quelle main souhaitez vous attaquer?\n\n1 - "+ this.getMainDroite().affichageCaracteristique()+"\n\n2 - "+ this.getMainGauche().affichageCaracteristique());
			
			choixMain = scan.nextInt();

			switch(choixMain){
			case 1:
				carte.attaquer(this, 1);
				break;
			case 2:
				carte.attaquer(this, 2);
				break;
				}
			}
			break;
		case 3: 
			this.affichageInventaire();
			break;
		}
	
		for(int k = 0; k < carte.getnbLig(); k++){
			for(int j = 0; j<carte.getnbCol(); j++){
				if(carte.getGrille()[k][j] instanceof Monstre){
					((Type)carte.getGrille()[k][j]).action(carte);
				}
			}
		}
		System.out.println(carte.Afficher());
		}

	}
	public static void afficheCollectionHeros(ArrayList <Heros> colHeros){
		int indice =0;

		for (Heros h : colHeros){
			System.out.println("Monstre " + indice + " de la collection => " + h.affichageCaracteristique());
			indice++;
		}
		System.out.println("\n");
	}



	@Override
	/**
	 * Fonction equals, compare l'instance actuelle avec un object
	 * renvoi true si les deux instances sont semblables.
	 * Faulse si les deux instances ne sont pas les mêmes
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Heros)) {
			return false;
		}
		Heros other = (Heros) obj;
		if (avant_bras == null) {
			if (other.avant_bras != null) {
				return false;
			}
		} else if (!avant_bras.equals(other.avant_bras)) {
			return false;
		}
		if (experience != other.experience) {
			return false;
		}
		if (!Arrays.equals(inventaire, other.inventaire)) {
			return false;
		}
		if (job == null) {
			if (other.job != null) {
				return false;
			}
		} else if (!job.equals(other.job)) {
			return false;
		}
		if (mainDroite == null) {
			if (other.mainDroite != null) {
				return false;
			}
		} else if (!mainDroite.equals(other.mainDroite)) {
			return false;
		}
		if (mainGauche == null) {
			if (other.mainGauche != null) {
				return false;
			}
		} else if (!mainGauche.equals(other.mainGauche)) {
			return false;
		}
		if (niveau != other.niveau) {
			return false;
		}
		if (pieds == null) {
			if (other.pieds != null) {
				return false;
			}
		} else if (!pieds.equals(other.pieds)) {
			return false;
		}
		if (tete == null) {
			if (other.tete != null) {
				return false;
			}
		} else if (!tete.equals(other.tete)) {
			return false;
		}
		if (torse == null) {
			if (other.torse != null) {
				return false;
			}
		} else if (!torse.equals(other.torse)) {
			return false;
		}
		return true;
	}
}
