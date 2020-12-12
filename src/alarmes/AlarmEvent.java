package alarmes;
import java.util.EventObject;
import java.util.GregorianCalendar;

/**
 * Classe abstraite AlarmEvent heritant de EventObject servant de base aux différents types d'alarmes
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public abstract class AlarmEvent extends EventObject {
	private static final long serialVersionUID = 1L;
    /* 
     * date : date à laquelle l'alarme a ete declenchee
     */
	private GregorianCalendar date;
	/*
	 * localisation : le batiment concerne par l'alarme declenchee
	 */
	private String localisation;
	/*
	 * niveau : le niveau d'importance de l'alarme declenchee
	 */
	private int niveau;
	
	
	
	/** Creer une alarme avec les differents parametres 
	 * 
	 * @param source : Object le capteur qui genere l'alarme
	 * @param date : GregorianCalendar la date a laquelle est cree l'alarme
	 * @param localisation : String l'endroit ou est declenchee l'alarme
	 * @param niveau : int le niveau d'importance de l'alarme
	 */
	public AlarmEvent(Object source, GregorianCalendar date, String localisation, int niveau) {
		super(source);
		this.date = new GregorianCalendar();
		this.localisation = localisation;
		this.niveau = niveau;
	}
	
	
	/**
	 * Methode qui retourne les informations relatives a l'alarme
	 */
	public abstract String toString();
	
	
	/**
	 * Methode qui retourne le type de l'alarme
	 */
	public abstract String getType();
	
	
	/**
	 * @return : la date de creation de l'alarme
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	
	/** Permet de modifier la date de l'alarme
	 * 
	 * @param date : GregorianCalendar la nouvelle date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	
	/** 
	 * @return : la localisation de l'alarme
	 */
	public String getLocalisation() {
		return localisation;
	}

	
	/** Permet de modifier la localisation de l'alarme
	 * 
	 * @param localisation : String la nouvelle localisation
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	
	/** 
	 * @return : le niveau d'importance de l'alarme
	 */
	public int getNiveau() {
		return niveau;
	}

	
	/** Permet de modifier le niveau d'importance de l'alarme
	 * 
	 * @param niveau : int le nouveau niveau d'importance
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}
