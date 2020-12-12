package alarmes;
import java.util.GregorianCalendar;

/**
 * Classe AlarmFireEvent heritant de AlarmEvent qui definit les alarmes de type feu
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmFireEvent extends AlarmEvent {
	private static final long serialVersionUID = 1L;
	
	
	
	/** Creer une alarme de type feu avec les differents parametres
	 * 
	 * @param source : Object le capteur qui genere l'alarme
	 * @param date : GregorianCalendar la date a laquelle est cree l'alarme
	 * @param localisation : String l'endroit ou est declenchee l'alarme
	 * @param niveau : int le niveau d'importance de l'alarme
	 */
	public AlarmFireEvent(Object source, GregorianCalendar date, String localisation, int niveau) {
		super(source, date, localisation, niveau);
	}
	
	
	/**
	 * @see : voir methode toString() dans la classe mere AlarmEvent
	 */
	public String toString() {
		return "Alarme incendie : source=" + this.getSource() + ", date=" + this.getDate().getTime() + ", localisation=" + this.getLocalisation() + ", niveau=" + this.getNiveau() + "";
	}
	
	
	/**
	 * @see : voir methode getType() dans la classe mere
	 */
	public String getType() {
		return "Alarme incendie";
	}

}
