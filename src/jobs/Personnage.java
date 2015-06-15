package jobs;

public abstract class Personnage {
    private String nom;

    private int vie, maxVie, force, agilite, constitution, pa, maxPa;

    private Degree dForce;
    private Degree dConstitution;
    private Degree dAgilite;
    private Degree attaque;
    private Degree degats;
    private Degree esquive;
    private Degree defense;


    public Personnage(){
    	this.nom = "Inconnu";
    }


    public Personnage(String nom, int vie, int force, int agilite,
    		int constitution, int pa) {
		this.nom = nom;
		this.vie = this.maxVie = vie;
		this.force = force;
		this.agilite = agilite;
		this.constitution = constitution;
		this.pa = pa = this.maxPa;
        this.dForce = new Degree(this.force);
        this.dConstitution = new Degree(this.constitution);
        this.dAgilite = new Degree(this.agilite);
        }

    public Personnage(Personnage choisi) {
        this.nom = new String(choisi.getNom());
        this.vie = this.maxVie = choisi.getVie();
        this.force= choisi.getForce();
        this.agilite = choisi.getAgilite();
        this.constitution = choisi.getConstitution();
        this.pa =  this.maxPa = choisi.getPa() ;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
    	this.vie = vie;
    }

    public int getMaxVie() {
        return maxVie;
    }

    public void regainDeVie(int regain){
    	this.vie +=regain;
    	if(this.vie > maxVie){
    		vie = maxVie;
    	}
    }
    public void setMaxVie(int maxVie) {
        this.maxVie = maxVie;
    }

   
    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }


 

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }
    
    public void translaterPa(int pa){
    	this.pa += pa;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Personnage)) {
			return false;
		}
		Personnage other = (Personnage) obj;
		if (agilite != other.agilite) {
			return false;
		}
		if (attaque == null) {
			if (other.attaque != null) {
				return false;
			}
		} else if (!attaque.equals(other.attaque)) {
			return false;
		}
		if (constitution != other.constitution) {
			return false;
		}
		if (dAgilite == null) {
			if (other.dAgilite != null) {
				return false;
			}
		} else if (!dAgilite.equals(other.dAgilite)) {
			return false;
		}
		if (dConstitution == null) {
			if (other.dConstitution != null) {
				return false;
			}
		} else if (!dConstitution.equals(other.dConstitution)) {
			return false;
		}
		if (dForce == null) {
			if (other.dForce != null) {
				return false;
			}
		} else if (!dForce.equals(other.dForce)) {
			return false;
		}
		if (defense == null) {
			if (other.defense != null) {
				return false;
			}
		} else if (!defense.equals(other.defense)) {
			return false;
		}
		if (degats == null) {
			if (other.degats != null) {
				return false;
			}
		} else if (!degats.equals(other.degats)) {
			return false;
		}
		if (esquive == null) {
			if (other.esquive != null) {
				return false;
			}
		} else if (!esquive.equals(other.esquive)) {
			return false;
		}
		if (force != other.force) {
			return false;
		}
		if (maxPa != other.maxPa) {
			return false;
		}
		if (maxVie != other.maxVie) {
			return false;
		}
		if (nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!nom.equals(other.nom)) {
			return false;
		}
		if (pa != other.pa) {
			return false;
		}
		if (vie != other.vie) {
			return false;
		}
		return true;
	}


	public int getMaxPa() {
        return maxPa;
    }

    public void setMaxPa(int maxPa) {
        this.maxPa = maxPa;
    }

    public Degree getdForce() {
		return dForce;
	}

	public void setdForce(Degree dForce) {
		this.dForce = dForce;
	}


	public Degree getdConstitution() {
		return dConstitution;
	}

	public void setdConstitution(Degree dConstitution) {
		this.dConstitution = dConstitution;
	}

	public Degree getdAgilite() {
		return dAgilite;
	}

	public void setdAgilite(Degree dAgilite) {
		this.dAgilite = dAgilite;
	}

	public Degree getAttaque() {
		return attaque;
	}

	public void setAttaque(Degree attaque) {
		this.attaque = attaque;
	}

	public Degree getDegats() {
		return degats;
	}

	public void setDegats(Degree degats) {
		this.degats = degats;
	}

	public Degree getEsquive() {
		return esquive;
	}


	public void setEsquive(Degree esquive) {
		this.esquive = esquive;
	}


	public Degree getDefense() {
		return defense;
	}


	public void setDefense(Degree defense) {
		this.defense = defense;
	}
	

	public abstract void remiseDegree();

    public String affichageCaracteristique() {
        return  nom + "\nVIE:" + maxVie + " FOR: " + force
                + " AGI: " + agilite +  " CONS: " + constitution;

    }
    
    public String toStringCaracteristique(){
    	return "FOR : " + force + "\nAGI : " + agilite +  " CONS : " + constitution;
    }
    
 	public static void main(String[] args) {
        Heros paris = new Heros();
    	System.out.println(paris);
    }
}
