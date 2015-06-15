package jobs;

import java.util.ArrayList;
import java.util.ListIterator;

import carte.Elements;

public abstract class Monstre extends Personnage implements Elements {
    private int experienceDonnee;

    public int Vision;
    
    public Monstre(String nom, int vie, int force, int agilite,
    		int constitution, int maxPa, int experienceDonnee) 
    throws PersonnageCarException{
		super(nom, vie, force, agilite, constitution, maxPa);
    	if(vie <=0 || force <0 || agilite< 0 || constitution < 0 || maxPa <0){
    		throw new PersonnageCarException("Vous instanciez un monstre avec des statistiques négatifs.");
    	}
    	this.setdForce(new Degree(force));
    	this.setdAgilite(new Degree(agilite));
    	this.setEsquive(new Degree(agilite));
    	this.setdConstitution(new Degree(constitution));
    	this.setMaxPa(maxPa) ;this.setPa(maxPa);
    	this.remiseDegree();
        this.experienceDonnee = experienceDonnee;
    }
    
    public Monstre(Monstre choisi) {
        super(choisi);
        this.experienceDonnee = choisi.getExperienceDonnee();
        this.setMaxVie(choisi.getMaxVie());
        this.setVie(choisi.getVie());
        this.setdForce(new Degree(choisi.getForce()));
    	this.setdAgilite(new Degree(choisi.getAgilite()));
    	this.setEsquive(new Degree(choisi.getAgilite()));
    	this.setdConstitution(new Degree(choisi.getConstitution()));
    	this.setMaxPa(choisi.getMaxPa()) ;this.setPa(choisi.getPa());
    	this.remiseDegree();
    }

    public Monstre() {
		// TODO Auto-generated constructor stub
	}


    public int getExperienceDonnee() {
        return experienceDonnee;
    }

    public void setExperienceDonnee(int experienceDonnee) {
        this.experienceDonnee = experienceDonnee;
    }
    
	@Override
	public void remiseDegree() {
		this.setdForce(new Degree(this.getForce()));
		this.setdConstitution(new Degree(this.getConstitution()));
		this.setdAgilite(new Degree(this.getAgilite()));
		this.setDegats(this.getdForce());
		this.setAttaque(this.getdAgilite());
		this.setEsquive(this.getdAgilite());
		this.setDefense(this.getdConstitution());		
		System.out.println(this.stringStats());
	}
	
	public String toString(){
		return "M";
	}
	public String stringStats(){
		return this.getNom() + " VIE:"+this.getMaxVie() + " FOR: "+this.getdForce()+
				" AGI:" + this.getdAgilite() + " CONS: " + this.getdConstitution();
	}

	
	public static void afficheCollectionMonstre(ArrayList <Monstre> colMonstre){
				int indice =0;

				for (Monstre m : colMonstre){
					System.out.println("Monstre " + indice + " de la collection => " + m.affichageCaracteristique());
					indice++;
				}
				System.out.println("\n");
	}
	
	public static void afficheCollectionMonstreAttaque(ArrayList <Monstre> colMonstre){
				int indice =0;

				for (Monstre m : colMonstre){
					System.out.println((indice+1) +" - " + m.affichageCaracteristique());
					indice++;
				}
				System.out.println("\n");
	}
	public static ArrayList<Monstre> ajoutMonstreType() throws PersonnageCarException{
		ArrayList <Monstre> bomboCollec = new ArrayList<Monstre>();
		Monstre bomboFeu =  new Bombo("Bombo de Feu", 100, 100, 12, 12, 8, 8);
		Monstre grandBomboFeu = new Bombo("Grand Bombo de Feu", 100, 100, 18, 12, 14, 8);
		bomboCollec.add(bomboFeu);
		bomboCollec.add(grandBomboFeu);
		Monstre bomboGeant= new Bombo("Bombo Geant", 1000, 400, 40,20,20,15);
		bomboCollec.add(grandBomboFeu);

		return bomboCollec;
	}
	
	public static void main(String args[]){
		try{
			Monstre.ajoutMonstreType();
		}catch(PersonnageCarException e){}
		
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Monstre)) {
			return false;
		}
		Monstre other = (Monstre) obj;
		if (Vision != other.Vision) {
			return false;
		}
		if (experienceDonnee != other.experienceDonnee) {
			return false;
		}
		return true;
	}
	public abstract int getVision();
	
	public abstract int getPorteeAttaque();
	
	public abstract void attaquer(Heros heros);
	

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return  getNom() + "\nVIE:" + getVie() +
        		" FOR: " + getForce() + " AGI: " + getAgilite() +  " CONS: " + getConstitution()
        		+"\nPA:" + getPa() + "/"+getMaxPa();
	}
}