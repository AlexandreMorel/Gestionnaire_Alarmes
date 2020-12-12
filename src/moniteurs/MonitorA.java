package moniteurs;
import java.util.ArrayList;

import alarmes.AlarmFireEvent;
import alarmes.AlarmGazEvent;
import alarmesListener.FireListener;
import alarmesListener.GazListener;

/**
 * Classe MonitorA implementant les interfaces GazListener et FireListener permettant de gerer les alarmes de type gaz et feu
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class MonitorA implements GazListener, FireListener {
	/*
	 * name : le nom du moniteur
	 */
	private String name;
	/*
	 * listAlarmGaz : la liste des alarmes de type gaz gerees par le moniteur
	 */
	private ArrayList<AlarmGazEvent> listAlarmGaz;
	/*
	 * listAlarmFire : la liste des alarmes de type feu gerees par le moniteur
	 */
	private ArrayList<AlarmFireEvent> listAlarmFire; 

	
	/** Creer un moniteur de type A avec un parametre
	 * 
	 * @param name : le nom du moniteur
	 */
	public MonitorA(String name) {
		this.name = name;
		this.listAlarmGaz = new ArrayList<AlarmGazEvent>();
		this.listAlarmFire = new ArrayList<AlarmFireEvent>();
	}
	
	
	/** Methode permettant d'ajouter une alarme de type feu a liste des alarmes gerees pas le moniteur
	 * 
	 * @param fe : une alarme de type feu
	 */
	public void receiveFireEvent(AlarmFireEvent fe) {
		this.listAlarmFire.add(fe);                  
	}

	
	/** Methode permettant d'ajouter une alarme de type gaz a liste des alarmes gerees pas le moniteur
	 * 
	 * @param ge : une alarme de type gaz
	 */
	public void receiveGazEvent(AlarmGazEvent ge) {
		this.listAlarmGaz.add(ge);
	}

	
	/**
	 * @return : le nom du capteur
	 */
	public String getName() {
		return name;
	}

	
	/** Permet de modifier le nom du moniteur
	 * 
	 * @param name : le nouveau nom du moniteur
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Methode permettant d'afficher dans la console les alarmes de type gaz gerees par le moniteur
	 */
	public void getListAlarmGaz() {
		System.out.println("Les évènements suivants ont été enregistrés : ");
		for(int i=0; i<listAlarmGaz.size(); i++) {
			System.out.println(listAlarmGaz.get(i).toString());
		}
	}
	
	
	/** Methode permettant de modifier la liste des alarmes de type gaz gerees par le moniteur
	 * 
	 * @param listAlarmGaz : la nouvelle liste des alarmes de type gaz
	 */
	public void setListAlarmGaz(ArrayList<AlarmGazEvent> listAlarmGaz) {
		this.listAlarmGaz = listAlarmGaz;
	}

	
	/**
	 * Methode permettant d'afficher dans la console les alarmes de type feu gerees par le moniteur
	 */
	public void getListAlarmFire() {
		System.out.println("Les évènements suivants ont été enregistrés : ");
		for(int i=0; i<listAlarmFire.size(); i++) {
			System.out.println(listAlarmFire.get(i).toString());
		}
	}

	
	/** Methode permettant de modifier la liste des alarmes de type feu gerees par le moniteur
	 * 
	 * @param listAlarmFire : la nouvelle liste des alarmes de type feu
	 */
	public void setListAlarmFire(ArrayList<AlarmFireEvent> listAlarmFire) {
		this.listAlarmFire = listAlarmFire;
	}

}
