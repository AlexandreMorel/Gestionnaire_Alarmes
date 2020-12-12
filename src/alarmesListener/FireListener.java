package alarmesListener;
import java.util.EventListener;

import alarmes.AlarmFireEvent;

/**
 * Interface FireListener heritant de EventListener permettant d'ecouter les alarmes de type feu
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public interface FireListener extends EventListener {
	
	/** Methode permettant a un FireListener de s'inscrire aupres d'un objet AlarmFireEvent
	 * 
	 * @param fe : une alarme de type feu
	 */
	public void receiveFireEvent(AlarmFireEvent fe);
	
	/**
	 * @return : le nom du FireListener
	 */
	public String getName();

}
