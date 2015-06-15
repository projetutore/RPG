package carte;

/**
 * Les PtsCartes sont les élements sur la carte, avec lesquels ne peuvent pas interargir les Personnages
 * ils s'agit donc des murs...
 * @version 1.0
 *
 */
public class PtsCarte implements Elements {


	/**
	 * Un PtsCarte possède juste un affichage(String) qui sera mis sur la carte
	 */
	private String affichage;
	
	
	/**
	 * De ce fait il s'instancie seulement avec un affichage(String)
	 * @param affichage
	 */
	public PtsCarte(String affichage){
		this.affichage = affichage;
	}
	

	/**
	 * Methode equals des pts carte
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PtsCarte)) {
			return false;
		}
		PtsCarte other = (PtsCarte) obj;
		if (affichage == null) {
			if (other.affichage != null) {
				return false;
			}
		} else if (!affichage.equals(other.affichage)) {
			return false;
		}
		return true;
	}

	/**
	 * Retourne l'affichage
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return affichage;
	}

	@Override
	public String affichageCaracteristique() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
