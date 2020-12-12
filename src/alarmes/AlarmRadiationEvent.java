package alarmes;
import java.util.GregorianCalendar;

/**
 * Classe AlarmRadiationEvent heritant de AlarmEvent qui definit les alarmes de type radiation
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmRadiationEvent extends AlarmEvent {
	private static final long serialVersionUID = 1L;
	/*
	 * niveauRad : le niveau de radiation detecte
	 */
	private int niveauRad;
	
	
	/** Creer une alarme de type radiation avec les differents parametres 
	 * 
	 * @param Source : Object le capteur qui genere l'alarme
	 * @param date : GregorianCalendar la date a laquelle est cree l'alarme
	 * @param localisation : String l'endroit ou est declenchee l'alarme
	 * @param niveau : int le niveau d'importance de l'alarme
	 * @param niveauRad : int le niveau de radiation detecte
	 */
	public AlarmRadiationEvent(Object source, GregorianCalendar date, String localisation, int niveau, int niveauRad) {
		super(source, date, localisation, niveau);
		this.niveauRad = niveauRad;
	}

	
	/**
	 * @see : voir methode toString() dans la classe mere AlarmEvent
	 */
	public String toString() {
		return "Alarme radiation : source=" + this.getSource() + ", date=" + this.getDate().getTime() + ", localisation=" + this.getLocalisation() + ", niveau=" + this.getNiveau() + ", niveauRad=" + this.getNiveauRad() + "";
	}
	
	
	/**
	 * @return : le niveau de radiation detecte
	 */
	public int getNiveauRad() {
		return niveauRad;
	}

	
	/** Permet de modifier le niveau de radiation
	 * 
	 * @param niveauRad : int le nouveau niveau de radiation
	 */
	public void setNiveauRad(int niveauRad) {
		this.niveauRad = niveauRad;
	}
	
	
	/**
	 * @see : voir methode getType() dans la classe mere
	 */
	public String getType() {
		return "Alarme radiation";
	}

}
