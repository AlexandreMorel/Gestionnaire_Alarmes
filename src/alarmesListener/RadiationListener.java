package alarmesListener;
import java.util.EventListener;

import alarmes.AlarmRadiationEvent;

/**
 * Interface RadiationListener heritant de EventListener permettant d'ecouter les alarmes de type radiation
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public interface RadiationListener extends EventListener {
	
	/** Methode permettant a un RadiationListener de s'inscrire aupres d'un objet AlarmRadiationEvent
	 * 
	 * @param re
	 */
	public void receiveRadiationEvent(AlarmRadiationEvent re);
	
	/**
	 * @return : le nom du FireListener
	 */
	public String getName();

}
