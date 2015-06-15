package jobs;

import java.io.Serializable;

public class Job implements Serializable{
    private String nomJob;


    public Job() {
        this.nomJob ="Classe";
    }

    public Job(String nomJob) {
        this.nomJob = nomJob;
    }

    public String getNomJob() {
        return nomJob;
    }

    public void setNomJob(String nomJob) {
        this.nomJob = nomJob;
    }


    /*public Arcane getArcaneDeJob(int i) {
        return new Arcane(this.ArcaneDeJob[i]);
    }*/

    public String toString() {
        return nomJob;
    }

    public Job choixJob(int i, Heros h){
    	Job job = new Job();
    	switch (i){
    	case 1:
            h.setMaxVie(120);
            h.setForce(8);
            h.setAgilite(2);
            h.setConstitution(13);
            h.setMaxPa(10);   
            h.setPa(10);

            job.setNomJob("Paladin");
    		break;
    	case 2:
    		h.setMaxVie(150);
            h.setForce(16);
            h.setAgilite(14);
            h.setConstitution(10);
            h.setMaxPa(10);   
            h.setPa(1000);

            job.setNomJob("Maraudeur");
    		break;
    	case 3:
    		h.setMaxVie(100);
            h.setForce(13);
            h.setAgilite(6);
            h.setConstitution(8);
            h.setMaxPa(10);   
            h.setPa(10);

            job.setNomJob("LanceRouge");
    		break;
    	case 4:
			h.setMaxVie(110);
			h.setForce(13);
			h.setAgilite(14);
			h.setConstitution(7);
			h.setMaxPa(3);
    	    job.setNomJob("Bretteur");
    		break;
    	}
    	
    	return job;
    }
    
    public boolean equals(Object job){
    	if(this.nomJob.equals(((Job) job).nomJob))
    			return true;
    		return false;
     }

}
