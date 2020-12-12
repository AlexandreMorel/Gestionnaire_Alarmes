package alarmes;
import java.util.GregorianCalendar;

/**
 * Classe AlarmGazEvent heritant de AlarmEvent qui definit les alarmes de type gaz
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmGazEvent extends AlarmEvent {
	private static final long serialVersionUID = 1L;
	/*
	 * typeGaz : le type de gaz detecte
	 */
	private String typeGaz;

	
	/** Creer une alarme de type gaz avec les differents parametres
	 * 
	 * @param Source : Object le capteur qui genere l'alarme
	 * @param date : GregorianCalendar la date a laquelle est cree l'alarme
	 * @param localisation : String l'endroit ou est declenchee l'alarme
	 * @param niveau : int le niveau d'importance de l'alarme
	 * @param typeGaz : String le type de gaz detecte
	 */
	public AlarmGazEvent(Object Source, GregorianCalendar date, String localisation, int niveau, String typeGaz) {
		super(Source, date, localisation, niveau);
		this.typeGaz = typeGaz;
	}

	
	/**
	 * @see : voir methode toString() dans la classe mere AlarmEvent
	 */
	public String toString() {
		return "Alarme gaz : source=" + this.getSource() + ", date=" + this.getDate().getTime() + ", localisation=" + this.getLocalisation() + ", niveau=" + this.getNiveau() + ", typeGaz=" + this.getTypeGaz() + "";
	}

	/**
	 * @return : le type de gaz detecte
	 */
	public String getTypeGaz() {
		return typeGaz;
	}

	
	/** Permet de modifier le type de gaz
	 * 
	 * @param typeGaz : String le nouveau type de gaz
	 */
	public void setTypeGaz(String typeGaz) {
		this.typeGaz = typeGaz;
	}

	/**
	 * @see : voir methode getType() dans la classe mere
	 */
	public String getType() {
		return "Alarme gaz";
	}
	
}
