package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import alarmes.AlarmEvent;

/**
 * Classe AlarmMonitoring, interface graphique permettant de gerer les alarmes declenchees
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmMonitoring extends JFrame implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Box sousContainer = Box.createVerticalBox();
	
	private JPanel container = new JPanel();
	private JPanel panAlarm = new JPanel();
	private JPanel panBouton = new JPanel();
	
	private JLabel labelAlarm = new JLabel("Alarmes non-traitées :");
	
	/*
	 * La liste principale des alarmes
	 */
	private JList<String> listAllAlarm = new JList<String>();
	
	/*
	 * ArrayList qui contient les alarmes sous la forme d'instance des differentes classes d'alarmes
	 */
	private ArrayList<AlarmEvent> registeredAlarm = new ArrayList<AlarmEvent>();
	
	/*
	 * ArrayList qui contient : "Non-vu" si l'alarme n'a pas été consultée, "Vu" sinon
	 */
	private ArrayList<String> consulted = new ArrayList<String>();
	
	/*
	 * Composant qui contient les alarmes affichees dans la liste listAllAlarm, ex: Alarme gaz
	 */
	private DefaultListModel<String> alarmListEvent = new DefaultListModel<String>();
	
	private JButton boutonDetails = new JButton("Détails");
	private JButton boutonArchiver = new JButton("Archiver");
	
	/*
	 * Un attribut de la classe AlarmArchiving, pour garder une trace des alarmes "supprimees"
	 */
	private AlarmArchiving archive;
	
	/*
	 * Creer l'interface graphique de gestion des alarmes
	 */
	public AlarmMonitoring() {
		
		this.setTitle("Interface de gestion des alarmes");
	    this.setSize(400, 180);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocation(483, 475);
	    
		/*
		 * Creation de la liste contenant les alarmes
		 */
		listAllAlarm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAllAlarm.setLayoutOrientation(JList.VERTICAL);
		listAllAlarm.addListSelectionListener(this);
		listAllAlarm.setModel(alarmListEvent);
		
		/*
		 * Creation de l'element deroulant pour la liste des alarmes
		 */
		JScrollPane scroller = new JScrollPane(listAllAlarm);
		scroller.setPreferredSize(new Dimension(120, 70));
		
	    panAlarm.add(labelAlarm);
	    panAlarm.add(scroller);
	    sousContainer.add(panAlarm);
	    
	    panBouton.setLayout(new FlowLayout());
	    boutonDetails.setEnabled(false);		//Au depart aucune alarme n'est selectionnee dans la liste, le bouton est desactive
	    boutonDetails.addActionListener(this);
	    boutonArchiver.setEnabled(false);		//Aucune alarme consultee, bouton inactif
	    boutonArchiver.addActionListener(this);
	    panBouton.add(boutonDetails);
	    panBouton.add(boutonArchiver);
	    sousContainer.add(panBouton);
	    
	    container.add(sousContainer, BorderLayout.CENTER);
	    
	    this.setContentPane(container);
	    this.setVisible(true);
	}

	/**
	 * Methode permettant de gerer l'appui sur les boutons
	 * 
	 * @param ae : l'action a traiter
	 */
	public void actionPerformed(ActionEvent ae) {
		/*
		 * Si l'action est un clic sur le bouton "Details"...
		 */
		if (ae.getActionCommand().toString() == "Détails") {
			int getIndexAlarm = listAllAlarm.getSelectedIndex();
			/*
			 * ... On affiche les details de l'alarme dans une fenetre de dialogue
			 */
			JOptionPane.showMessageDialog(this, registeredAlarm.get(getIndexAlarm).toString(), "Informations sur l'alarme", JOptionPane.INFORMATION_MESSAGE);
			this.boutonArchiver.setEnabled(true); //Activation du bouton
			this.consulted.set(getIndexAlarm, "Vu"); //On passe le statut de l'alarme sur "Vu" commme elle a ete consultee
		/*
		 * Si l'action est un clic sur le bouton "Archiver"...	
		 */
		} else if(ae.getActionCommand().toString() == "Archiver") {
			int rep = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment archiver cette alarme ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION) {
				int getIndexAlarm = listAllAlarm.getSelectedIndex();
				/*
				 * ... On ajoute l'alarme a la liste des alarmes archivees de la fenetre des archives ...
				 */
				this.archive.addAlarm(registeredAlarm.get(getIndexAlarm));
				this.archive.addAlarmInList(registeredAlarm.get(getIndexAlarm).getType());
				this.removeAlarmInList(getIndexAlarm);
				/*
				 * ... Et on supprime l'alarme de la liste des alarmes non-traitees
				 */
				this.removeAlarm(getIndexAlarm);
				this.removeConsulted(getIndexAlarm);
			}
		}
	}
	
	/*
	 * Methode definissant le comportement de l'application lors d'un changement de choix dans la liste des alarmes
	 */
	public void valueChanged(ListSelectionEvent e) {
		
		/*
		 * Aucune selection dans la liste des alarmes, les boutons sont inactifs
		 */
		if (listAllAlarm.getSelectedIndex()==-1||alarmListEvent.size()==0) {
			this.boutonArchiver.setEnabled(false);
			this.boutonDetails.setEnabled(false);
		
	    } else {
	    	this.boutonDetails.setEnabled(true);
	    	/*
	    	 * Le bouton "Archiver" est actif ssi l'alarme selectionnee a ete consultee
	    	 */
	    	if(this.consulted.get(listAllAlarm.getSelectedIndex()).contentEquals("Non-vu")) { 
	    		this.boutonArchiver.setEnabled(false);
	    	} else { 
	    		this.boutonArchiver.setEnabled(true); 
	    	}
	    }
	}
	
	/**
	 * Methode permettant d'ajouter une alarme a la liste des alarmes enregistrees
	 * 
	 * @param ae : l'alarme a ajouter
	 */
	public void addAlarm(AlarmEvent ae) {
		this.registeredAlarm.add(ae);
	}
	
	/**
	 * Methode qui ajoute le type d'alarme a la liste des alarmes non-traitees et reconstruit la fenetre
	 * 
	 * @param infoAlarm : le type d'alarme sous forme de String
	 */
	public void addAlarmInList(String infoAlarm) {
		JOptionPane.showMessageDialog(this, infoAlarm, "Nouvelle Alarme déclenchée", JOptionPane.WARNING_MESSAGE);
		sousContainer.removeAll();
		alarmListEvent.addElement(infoAlarm);
		listAllAlarm.setModel(alarmListEvent);
	    sousContainer.add(panAlarm);
	    panBouton.add(boutonDetails);
	    panBouton.add(boutonArchiver);
	    sousContainer.add(panBouton);
	    container.updateUI(); 
	}
	
	/**
	 * Methode permettant de supprimer une alarme de la liste des alarmes enregistrees
	 * 
	 * @param index : l'index de l'alarme a supprimer
	 */
	public void removeAlarm(int index) {
		this.registeredAlarm.remove(index);
	}
	
	/**
	 * Methode qui supprime le type d'alarme de la liste des alarmes non-traitees et reconstruit la fenetre
	 * 
	 * @param index : l'index de l'alarme a supprimer
	 */
	public void removeAlarmInList(int index) {
		sousContainer.removeAll();
		alarmListEvent.removeElementAt(index);
		listAllAlarm.setModel(alarmListEvent);
	    sousContainer.add(panAlarm);
	    panBouton.add(boutonDetails);
	    panBouton.add(boutonArchiver);
	    sousContainer.add(panBouton);
	    container.updateUI();
	}
	
	/**
	 * Methode qui ajoute le statut de l'alarme a la liste consulted
	 * 
	 * @param status : "Vu" ou "Non-vu"
	 */
	public void addConsulted(String status) { 
		this.consulted.add(status);
	}
	
	/**
	 * Methode qui supprime le statut de l'alarme de la liste consulted
	 * 
	 * @param index : l'index du statut a supprimer
	 */
	public void removeConsulted(int index) {
		this.consulted.remove(index);
	}
	
	/**
	 * Methode permettant d'ajouter une interface d'archivage des alarmes a notre interface de gestion des alarmes
	 * 
	 * @param archive : l'interface qui va gerer les alarmes archivees par cette fenetre
	 */
	public void addAlarmArchiving(AlarmArchiving archive) {
		this.archive = archive;
	}

}
