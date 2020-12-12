package moniteurs;
import java.util.ArrayList;

import alarmes.AlarmGazEvent;
import alarmes.AlarmRadiationEvent;
import alarmesListener.GazListener;
import alarmesListener.RadiationListener;

/**
 * Classe MonitorB implementant les interfaces GazListener et RadiationListener permettant de gerer les alarmes de type gaz et radiation
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class MonitorB implements GazListener, RadiationListener {
	/*
	 * name : le nom du moniteur
	 */
	private String name;
	/*
	 * listAlarmGaz : la liste des alarmes de type gaz gerees par le moniteur
	 */
	ArrayList<AlarmGazEvent> listAlarmGaz; 
	/*
	 * listAlarmRadiation : la liste des alarmes de type radiation gerees par le moniteur
	 */
	ArrayList<AlarmRadiationEvent> listAlarmRadiation;
	
	
	/** Creer un moniteur de type B avec un parametre
	 * 
	 * @param name : le nom du moniteur
	 */
	public MonitorB(String name) {
		this.name = name;
		this.listAlarmGaz = new ArrayList<AlarmGazEvent>();
		this.listAlarmRadiation = new ArrayList<AlarmRadiationEvent>();
	}
	
	
	/** Methode permettant d'ajouter une alarme de type radiation a liste des alarmes gerees pas le moniteur
	 * 
	 * @param re : une alarme de type radiation
	 */
	public void receiveRadiationEvent(AlarmRadiationEvent re) {
		this.listAlarmRadiation.add(re);
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
	 * Methode permettant d'afficher dans la console les alarmes de type radiation gerees par le moniteur
	 */
	public void getListAlarmRadiation() {
		System.out.println("Les évènements suivants ont été enregistrés : ");
		for(int i=0; i<listAlarmRadiation.size(); i++) {
			System.out.println(listAlarmRadiation.get(i).toString());
		}
	}

	
	/** Methode permettant de modifier la liste des alarmes de type radiation gerees par le moniteur
	 * 
	 * @param listAlarmRadiation : la nouvelle liste des alarmes de type radiation
	 */
	public void setListAlarmRadiation(ArrayList<AlarmRadiationEvent> listAlarmRadiation) {
		this.listAlarmRadiation = listAlarmRadiation;
	}

}
