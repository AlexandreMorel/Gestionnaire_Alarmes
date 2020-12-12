package alarmesListener;
import java.util.EventListener;

import alarmes.AlarmGazEvent;

/**
 * Interface GazListener heritant de EventListener permettant d'ecouter les alarmes de type gaz
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public interface GazListener extends EventListener {
	
	/** Methode permettant a un GazListener de s'inscrire aupres d'un objet AlarmGazEvent
	 * 
	 * @param ge : une alarme de type gaz 
	 */
	public void receiveGazEvent(AlarmGazEvent ge);
	
	/**
	 * @return : le nom du GazListener
	 */
	public String getName();

}
