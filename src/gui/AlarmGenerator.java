package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alarmes.AlarmFireEvent;
import alarmes.AlarmGazEvent;
import alarmes.AlarmRadiationEvent;
import capteurs.CaptorFire;
import capteurs.CaptorGaz;
import capteurs.CaptorRadiation;
import moniteurs.MonitorA;
import moniteurs.MonitorB;

/**
 * Classe AlarmGenerator, interface graphique permettant de générer les alarmes
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmGenerator extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	/*
	 * Composant graphique qui contiendra tous les autres composants, disposition verticale des elements
	 */
	private Box sousContainer = Box.createVerticalBox();
	/*
	 * JPanel qui servira de ContentPane
	 */
	private JPanel container = new JPanel();
	/*
	 * JPanel reserve aux composants graphique lies au choix du batiment
	 */
	private JPanel batiment = new JPanel();
	/*
	 * JPanel reserve aux composants graphique lies au choix du type d'alarme
	 */
	private JPanel typeAlarm = new JPanel();
	/*
	 * JPanel reserve aux composants graphique lies au choix du niveau d'importance de l'alarme
	 */
	private JPanel niveau = new JPanel();
	/*
	 * JPanel reserve aux composants graphique lies au choix du type de gaz et du niveau de radiation
	 */
	private JPanel spe = new JPanel();
	/*
	 * JPanel reserve aux boutons
	 */
	private JPanel bouton = new JPanel();
	/*
	 * JLabel pour annoncer le choix du type d'alarme
	 */
	private JLabel labelType = new JLabel("Type d'alarme :");
	/*
	 * JLabel pour annoncer le choix du niveau d'importance
	 */
	private JLabel labelNiveau = new JLabel("Niveau d'importance :");
	/*
	 * JLabel pour annoncer le choix du batiment
	 */
	private JLabel labelBatiment = new JLabel("Choix du Bâtiment :");
	/*
	 * JLabel pour annoncer le choix des informations speciales
	 */
	private JLabel labelSpe = new JLabel();
	/*
	 * Vecteur contenant les differents type d'alarme
	 */
	String[] tab1 = {"Feu", "Gaz", "Radiation"};
	/*
	 * Liste graphique contenant les differents type d'alarme
	 */
	private JComboBox<String> comboType = new JComboBox<String>(tab1);
	/*
	 * Vecteur contenant les differents niveau d'importance des alarmes
	 */
	String[] tab2 = {"1", "2", "3"};
	/*
	 * Liste graphique contenant les differents niveau d'importance des alarmes
	 */
	private JComboBox<String> comboNiveau = new JComboBox<String>(tab2);
	/*
	 * Vecteur contenant les differents batiment disponible
	 */
	String[] tab3 = {"A", "B", "C"};
	/*
	 * Liste graphique contenant les differents batiments
	 */
	private JComboBox<String> comboBatiment = new JComboBox<String>(tab3);
	/*
	 * Champ de texte pour annoncer le choix des informations speciales
	 */
	private JTextField jtfSpe = new JTextField("");
	/*
	 * Bouton pour generer l'alarme avec les parametres choisis
	 */
	private JButton button = new JButton("Créer alarme");
	/*
	 * Un attribut de la classe AlarmMonitoring pour permettre la gestion des alarmes generees
	 */
	private AlarmMonitoring monitor;
	
	/**
	 * Creer l'interface graphique de generation des alarmes 
	 */
	public AlarmGenerator() {
		this.setTitle("Interface de création d'alarme");
	    this.setSize(400, 220);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    /*
	     * Les composants lies au choix du batiment
	     */
	    comboBatiment.setPreferredSize(new Dimension(100, 20));
	    comboBatiment.addActionListener(this);
	    batiment.add(labelBatiment);
	    batiment.add(comboBatiment);
	    sousContainer.add(batiment);
	    
	    /*
	     * Les composants lies au type d'alarme
	     */
	    comboType.setPreferredSize(new Dimension(100, 20));
	    comboType.addActionListener(this);
	    typeAlarm.add(labelType);
	    typeAlarm.add(comboType);
	    sousContainer.add(typeAlarm);
		
	    /*
	     * Les composants lies au niveau d'importance
	     */
	    comboNiveau.setPreferredSize(new Dimension(100, 20));
	    niveau.add(labelNiveau);
	    niveau.add(comboNiveau);
	    sousContainer.add(niveau);
	    
	    /*
	     * Les boutons
	     */
	    button.addActionListener(this);
	    bouton.add(button);
	    sousContainer.add(bouton);
	    
	    container.add(sousContainer, BorderLayout.CENTER);
	    
	    /*
	     * On definit container comme le ContentPane de notre fenetre
	     */
	    this.setContentPane(container);
	    this.setVisible(true);
	}

	
	/**
	 * Methode permettant de gerer les choix de l'utilisateur et l'appui sur le bouton
	 * 
	 * @param ae : l'action a traiter
	 */
	public void actionPerformed(ActionEvent ae) {
		/*
		 * Si l'action concerne un choix dans une Combobox
		 */
		if (ae.getActionCommand().toString() == "comboBoxChanged") { 
	    	String getType = (String)comboType.getSelectedItem();		
		
			if (getType.equals("Gaz")) {
				/*
				 * Ici on va enlever tous les composants et ajouter en plus le champ de texte pour le type de gaz
				 */
				sousContainer.removeAll();
				labelSpe.setText("Type de gaz :");
				Font police = new Font("Arial", Font.BOLD, 14);
			    jtfSpe.setFont(police);
			    jtfSpe.setPreferredSize(new Dimension(100, 20));
			    jtfSpe.setForeground(Color.BLACK);
			    spe.add(labelSpe);
			    spe.add(jtfSpe);
			    sousContainer.add(batiment);
			    sousContainer.add(typeAlarm);
			    sousContainer.add(niveau);
			    sousContainer.add(spe);
			    sousContainer.add(bouton);
				container.updateUI();
				
			} else if (getType.equals("Radiation")) {
				/*
				 * On en fait de meme pour le champ de texte pour le niveau de radiation
				 */
				sousContainer.removeAll();
				labelSpe.setText("Niveau de radiation :");
				Font police = new Font("Arial", Font.BOLD, 14);
			    jtfSpe.setFont(police);
			    jtfSpe.setPreferredSize(new Dimension(100, 20));
			    jtfSpe.setForeground(Color.BLACK);
				spe.add(labelSpe);
				spe.add(jtfSpe);
				sousContainer.add(batiment);
				sousContainer.add(typeAlarm);
				sousContainer.add(niveau);
				sousContainer.add(spe);
				sousContainer.add(bouton);
				container.updateUI();
				
			} else if (getType.equals("Feu")) {
				/*
				 * On reconstruit aussi notre fenetre lorsqu'on repasse au choix de type alarme feu pour enlever le champ de texte alors inutile
				 */
				sousContainer.removeAll();
				sousContainer.add(batiment);
				sousContainer.add(typeAlarm);
				sousContainer.add(niveau);
				sousContainer.add(bouton);
				container.updateUI();
			}
		} else {
			/*
			 * si l'action est un clic sur le bouton "Creer alarme"
			 */
			String getType = (String)comboType.getSelectedItem();
			/*
			 * On recupere les informations dans les differents composants graphique et on creer l'alarme correspondante
			 */
			if (getType.equals("Gaz")) {
					
				if (jtfSpe.getText().length()==0) {
					JOptionPane.showMessageDialog(this, "Veuillez choisir un type de Gaz", "Champ de texte vide", JOptionPane.ERROR_MESSAGE);
						
				} else {
					String getSpe = (String)jtfSpe.getText();
					CaptorGaz cg = new CaptorGaz((String)comboBatiment.getSelectedItem());
					MonitorB mb = new MonitorB((String)comboBatiment.getSelectedItem());
					cg.addGazListener(mb);
					AlarmGazEvent ge = cg.generateGazEvent((String)comboBatiment.getSelectedItem(), (Integer)comboNiveau.getSelectedIndex()+1, getSpe);
					
					/*
					 * On ajoute l'alarme a la liste des alarmes non-traitees de l'instance de la classe AlarmMonitoring donne en attribut
					 */
					monitor.addAlarm(ge);
					monitor.addAlarmInList(ge.getType());
					/*
					 * On definit le statut de l'alarme comme non-consultees
					 */
					monitor.addConsulted("Non-vu");
				}
				
			} else if (getType.equals("Radiation")) {
					
				if (isInteger(jtfSpe.getText())==false) {
					JOptionPane.showMessageDialog(this, "Niveau de radiation doit être un nombre entier", "Valeur erronée", JOptionPane.ERROR_MESSAGE);
					
				} else if (Integer.parseInt(jtfSpe.getText()) < 1 || Integer.parseInt(jtfSpe.getText()) > 100) {
					JOptionPane.showMessageDialog(this, "Niveau de Radiation doit être compris dans l'intervalle [1,100]", "Valeur erronée", JOptionPane.ERROR_MESSAGE);
					
				} else {
					CaptorRadiation cr = new CaptorRadiation((String)comboBatiment.getSelectedItem());
					MonitorB mb = new MonitorB((String)comboBatiment.getSelectedItem());
					cr.addRadiationListener(mb);
					AlarmRadiationEvent re = cr.generateRadiationEvent((String)comboBatiment.getSelectedItem(), (Integer)comboNiveau.getSelectedIndex()+1, Integer.parseInt(jtfSpe.getText()));
					monitor.addAlarm(re);
					monitor.addAlarmInList(re.getType());
					monitor.addConsulted("Non-vu");
				}
				
			} else {
				
				CaptorFire cf = new CaptorFire((String)comboBatiment.getSelectedItem());
				MonitorA ma = new MonitorA((String)comboBatiment.getSelectedItem());
				cf.addFireListener(ma);
				AlarmFireEvent fe = cf.generateFireEvent((String)comboBatiment.getSelectedItem(), (Integer)comboNiveau.getSelectedIndex()+1);
				monitor.addAlarm(fe);
				monitor.addAlarmInList(fe.getType());
				monitor.addConsulted("Non-vu");
			}
				
		}

	}
	
	
	/**
	 * Methode permettant d'ajouter une interface de gestion des alarmes a notre interface de creation des alarmes
	 * 
	 * @param monitor : le moniteur qui va gerer les alarmes creees par cette fenetre
	 */
	public void addAlarmMonitoring(AlarmMonitoring monitor) {
		this.monitor = monitor;
	}
	
	
	/**
	 * Methode qui verifie si le String en parametre est un nombre entier
	 * 
	 * @param chaine : la chaine de caractere a analyser
	 * 
	 * @return : true si chaine est un entier, faux sinon
	 */
	public boolean isInteger(String chaine) {
		try {
			Integer.parseInt(chaine);	
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
}


