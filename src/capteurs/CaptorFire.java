package capteurs;
import java.util.ArrayList;

import alarmes.AlarmFireEvent;
import alarmesListener.FireListener;

/**
 * Classe qui definit les capteurs de type feu
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class CaptorFire {
	/*
	 * name : le nom du capteur
	 */
	private String name;
	/*
	 * listeFireListener : la liste des ecouteurs du capteur 
	 */
	private ArrayList <FireListener> listeFireListener;
	
	
	/** Creer un capteur de type feu avec un parametre
	 * 
	 * @param name : le nom du capteur
	 */
	public CaptorFire(String name) {
		this.name = name;
		this.listeFireListener = new ArrayList<>();
	}
	
	
	/** Methode qui ajoute un ecouteur a la liste des ecouteurs du capteur
	 * 
	 * @param fl : l'ecouteur a ajouter a la liste
	 */
	public void addFireListener(FireListener fl) {
		this.listeFireListener.add(fl);
	}
	
	
	/**
	 * Methode qui affiche dans la console les ecouteurs du capteur
	 */
	public void showListeFireListener() {
		System.out.println("Ecouteurs de ce capteur :");
		for (int i=0; i<this.listeFireListener.size(); i++) {
			System.out.println(this.listeFireListener.get(i));
		}
	}
	
	
	/** Methode qui genere une instance de la classe AlarmFireEvent avec les differents parametres
	 * 
	 * @param localisation : le batiment dans lequel est genere l'alarme
	 * @param niveau : le niveau d'importance de l'alarme
	 * @return : l'evenement genere, une instance de la classe AlarmFireEvent
	 */
	public AlarmFireEvent generateFireEvent(String localisation, int niveau) {
		AlarmFireEvent fe = new AlarmFireEvent(this, null, localisation, niveau);
		for(FireListener fl: listeFireListener) {
			fl.receiveFireEvent(fe); 
		}
		return fe;
	}

	
	/**
	 * @return : le nom du capteur
	 */
	public String getName() {
		return name;
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
		return "CaptorFire [name=" + name + "]";
	}
	
}
