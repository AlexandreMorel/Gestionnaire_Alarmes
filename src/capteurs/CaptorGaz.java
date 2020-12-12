package capteurs;
import java.util.ArrayList;

import alarmes.AlarmGazEvent;
import alarmesListener.GazListener;

/**
 * Classe qui definit les capteurs de type gaz
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class CaptorGaz {
	/*
	 * name : le nom du capteur
	 */
	private String name;
	/*
	 * listeGazListener : la liste des ecouteurs du capteur 
	 */
	private ArrayList <GazListener> listeGazListener;
	
	
	/** Creer un capteur de type gaz avec un parametre
	 * 
	 * @param name : le nom du capteur
	 */
	public CaptorGaz(String name) {
		this.name = name;
		this.listeGazListener = new ArrayList<>();
	}
	
	
	/** Methode qui ajoute un ecouteur a la liste des ecouteurs du capteur
	 * 
	 * @param gl : l'ecouteur a ajouter a la liste
	 */
	public void addGazListener(GazListener gl) {
		this.listeGazListener.add(gl);
	}
	
	
	/** Methode qui genere une instance de la classe AlarmGazEvent avec les differents parametres
	 * 
	 * @param localisation : le batiment dans lequel est genere l'alarme
	 * @param niveau : le niveau d'importance de l'alarme
	 * @param typeGaz : le type de gaz detecte
	 * @return : l'evenement genere, une instance de la classe AlarmGazEvent
	 */
	public AlarmGazEvent generateGazEvent(String localisation, int niveau, String typeGaz) {
		AlarmGazEvent ge = new AlarmGazEvent(this, null, localisation, niveau, typeGaz);
		for (GazListener gl: listeGazListener) {
			gl.receiveGazEvent(ge);
		}
		return ge;
	}
	
	
	/**
	 * Methode qui affiche dans la console les ecouteurs du capteur
	 */
	public void showListeGazListener() {
		System.out.println("Ecouteurs de ce capteur :");
		for (int i=0; i<this.listeGazListener.size(); i++) {
			System.out.println(this.listeGazListener.get(i).getName());
		}
	}

	
	/**
	 * @return : le nom du capteur
	 */
	public String getName() {
		return this.name;
	}

	
	/** Permet de modifier le nom du capteur
	 * 
	 * @param name : le nouveau nom du capteur
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Methode qui affiche les informations relatives au capteur
	 */
	public String toString() {
		return "CaptorGaz [name=" + name + "]";
	}
	
}
