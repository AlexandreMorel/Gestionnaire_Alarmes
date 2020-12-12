package capteurs;
import java.util.ArrayList;

import alarmes.AlarmRadiationEvent;
import alarmesListener.RadiationListener;

/**
 * Classe qui definit les capteurs de type radiation
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class CaptorRadiation {
	/*
	 * name : le nom du capteur
	 */
	private String name;
	/*
	 * listeRadiationListener : la liste des ecouteurs du capteur 
	 */
	private ArrayList <RadiationListener> listeRadiationListener;
	
	
	/** Creer un capteur de type radiation avec un parametre
	 * 
	 * @param name : le nom du capteur
	 */
	public CaptorRadiation(String name) {
		this.name = name;
		this.listeRadiationListener = new ArrayList<>();
	}
	
	
	/** Methode qui ajoute un ecouteur a la liste des ecouteurs du capteur
	 * 
	 * @param rl : l'ecouteur a ajouter a la liste
	 */
	public void addRadiationListener(RadiationListener rl) {
		this.listeRadiationListener.add(rl);
	}
		
	
	/** Methode qui genere une instance de la classe AlarmRadiationEvent avec les differents parametres
	 * 
	 * @param localisation : le batiment dans lequel est genere l'alarme
	 * @param niveau : le niveau d'importance de l'alarme
	 * @param niveauRad : le niveau de radiation detecte
	 * @return : l'evenement genere, une instance de la classe AlarmRadiationEvent
	 */
	public AlarmRadiationEvent generateRadiationEvent(String localisation, int niveau, int niveauRad) {
		AlarmRadiationEvent re = new AlarmRadiationEvent(this, null, localisation, niveau, niveauRad);
		for (RadiationListener rl: listeRadiationListener) {
			rl.receiveRadiationEvent(re);
		}
		return re;
	}
	
	
	/**
	 * Methode qui affiche dans la console les ecouteurs du capteur
	 */
	public void showListeRadiationListener() {
		System.out.println("Ecouteurs de ce capteur :");
		for (int i=0; i<this.listeRadiationListener.size(); i++) {
			System.out.println(this.listeRadiationListener.get(i));
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
		return "CaptorRadiation [name=" + name + "]";
	}
	
}
