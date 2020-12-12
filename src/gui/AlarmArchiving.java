package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import alarmes.AlarmEvent;

/**
 * Classe AlarmArchiving, interface graphique permettant de consulter les alarmes archivees et d'en avoir des statistiques
 * 
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class AlarmArchiving extends JFrame implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Box sousContainer = Box.createVerticalBox();
	
	private JPanel container = new JPanel();
	private JPanel panAlarm = new JPanel();
	private JPanel panBouton = new JPanel();
	
	private JLabel labelAlarm = new JLabel("Alarmes traitées :");
	/*
	 * Liste des alarmes
	 */
	private JList<String> listAllAlarm = new JList<String>();
	/*
	 * Contient les alarmes sous la forme d'instance des differentes classes d'alarmes
	 */
	private ArrayList<AlarmEvent> archivedAlarm = new ArrayList<AlarmEvent>();
	/*
	 * Composant qui contient les alarmes affichees dans la liste listAllAlarm, ex: Alarme gaz
	 */
	private DefaultListModel<String> alarmListEvent = new DefaultListModel<String>();
	
	private JButton boutonDetails = new JButton("Détails");
	/*
	 * Bouton qui permet d'afficher des statistiques sur les alarmes archivees
	 */
	private JButton boutonStats = new JButton("Statistiques");

	/*
	 * Creer l'interface graphique des archives 
	 */
	public AlarmArchiving () {
		
		this.setTitle("Interface des archives");
	    this.setSize(400, 175);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLocation(483, 78);
	    
		/*
		 * Creation de la liste contenant les alarmes archivees
		 */
		listAllAlarm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAllAlarm.setLayoutOrientation(JList.VERTICAL);
		listAllAlarm.addListSelectionListener(this);
		listAllAlarm.setModel(alarmListEvent);
		
		/*
		 * Creation de l'element deroulant pour la liste des alarmes archivees
		 */
		JScrollPane scroller = new JScrollPane(listAllAlarm);
		scroller.setPreferredSize(new Dimension(120, 70));
		
	    panAlarm.add(labelAlarm);
	    panAlarm.add(scroller);
	    sousContainer.add(panAlarm);
	    
	    panBouton.setLayout(new FlowLayout());
	    boutonDetails.setEnabled(false);	//Au depart aucune alarme n'est selectionnee dans la liste, le bouton est desactive
	    boutonDetails.addActionListener(this);
	    panBouton.add(boutonDetails);	
	    boutonStats.addActionListener(this);
	    panBouton.add(boutonStats);
	    
	    sousContainer.add(panBouton);
	    
	    container.add(sousContainer, BorderLayout.CENTER);
	    
	    this.setContentPane(container);
	    this.setVisible(true);
		
	}
	
	/*
	 *  Methode definissant le comportement de l'application lors d'un changement de choix dans la liste des alarmes archivees
	 */
	public void valueChanged(ListSelectionEvent arg0) {
		
		/*
		 * Aucune selection dans la liste des alarmes, le bouton est inactif
		 */
		if (listAllAlarm.getSelectedIndex()==-1||alarmListEvent.size()==0) {
			this.boutonDetails.setEnabled(false);
		
	    } else {
	    	this.boutonDetails.setEnabled(true);
	    }
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
			/*
			 * ... On affiche les details de l'alarme dans une fenetre de dialogue
			 */
			int getIndexAlarm = listAllAlarm.getSelectedIndex();
			JOptionPane.showMessageDialog(this, archivedAlarm.get(getIndexAlarm).toString(), "Informations sur l'alarme", JOptionPane.INFORMATION_MESSAGE);
		} else {
			/*
			 * Si l'action est un clic sur le bouton "Archiver"...	
			 */
			if (this.archivedAlarm.size()==0)
				JOptionPane.showMessageDialog(this, "Pour faire des stats, il faut des données !", "Aucune alarme archivée", JOptionPane.WARNING_MESSAGE);
			else {
				/*
				 * On cree une fenetre un graphique dans une nouvelle fenetre
				 */
				StatisticalChart demo = new StatisticalChart("Statistiques des alarmes");
		        demo.pack();
		        RefineryUtilities.centerFrameOnScreen(demo);
		        demo.setVisible(true);
			}
		}
	}
	
	/**
	 * Methode permettant d'ajouter une alarme a la liste des alarmes archivees
	 * 
	 * @param ae : l'alarme a ajouter
	 */
	public void addAlarm(AlarmEvent ae) {
		this.archivedAlarm.add(ae);
	}
	
	/**
	 * Methode qui ajoute le type d'alarme a la liste des alarmes archivees et reconstruit la fenetre
	 * 
	 * @param infoAlarm : le type d'alarme sous forme de String
	 */
	public void addAlarmInList(String infoAlarm) {
		sousContainer.removeAll();
		alarmListEvent.addElement(infoAlarm);
		listAllAlarm.setModel(alarmListEvent);
	    sousContainer.add(panAlarm);
	    panBouton.add(boutonDetails);
	    panBouton.add(boutonStats);
	    sousContainer.add(panBouton);
	    container.updateUI(); 
	}
	
	/**
	 * Classe interne qui permet de creer le graphique de repartition des alarmes archivees
	 */
	private class StatisticalChart extends JFrame {
		
		private static final long serialVersionUID = 1L;
		
		public StatisticalChart(String title) {
			super(title);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			JPanel chartPanel = createDemoPanel();
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        setContentPane(chartPanel);
		}

	    /**
	     * Creer le dataset contenant la repartition des alarmes dans les batiments selon leur type et leur nombre
	     *
	     * @return : le dataset.
	     */
	    public CategoryDataset createDataset() {
	        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
	        
	        ArrayList<Integer> countedAlarm = countAlarm(AlarmArchiving.this.archivedAlarm);
	      
	        categoryDataset.addValue(countedAlarm.get(0), "Feu", "Bat A");
	        categoryDataset.addValue(countedAlarm.get(1), "Gaz", "Bat A");
	        categoryDataset.addValue(countedAlarm.get(2), "Radiation", "Bat A");
	        categoryDataset.addValue(countedAlarm.get(3), "Feu", "Bat B");
	        categoryDataset.addValue(countedAlarm.get(4), "Gaz", "Bat B");
	        categoryDataset.addValue(countedAlarm.get(5), "Radiation", "Bat B");
	        categoryDataset.addValue(countedAlarm.get(6), "Feu", "Bat C");
	        categoryDataset.addValue(countedAlarm.get(7), "Gaz", "Bat C");
	        categoryDataset.addValue(countedAlarm.get(8), "Radiation", "Bat C");

	        return categoryDataset;
	    }

	    /**
	     * Creer le graphique
	     *
	     * @param dataset : le dataset 
	     *
	     * @return : le graphique
	     */
	    private JFreeChart createChart(CategoryDataset dataset) {
	    	
	    	int nbA = (int) dataset.getValue("Feu", "Bat A") + (int) dataset.getValue("Gaz", "Bat A") + (int) dataset.getValue("Radiation", "Bat A");
	    	int nbB = (int) dataset.getValue("Feu", "Bat B") + (int) dataset.getValue("Gaz", "Bat B") + (int) dataset.getValue("Radiation", "Bat B");
	    	int nbC = (int) dataset.getValue("Feu", "Bat C") + (int) dataset.getValue("Gaz", "Bat C") + (int) dataset.getValue("Radiation", "Bat C");
	    	
	        JFreeChart chart = ChartFactory.createStackedBarChart("Répartition des alarmes archivées", null, null, dataset, PlotOrientation.VERTICAL, false, true, false);

	        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	        MyStackedBarRenderer renderer = new MyStackedBarRenderer();
	        plot.setRenderer(renderer);

	        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
	        renderer.setPositiveItemLabelPositionFallback(position);
	        renderer.setNegativeItemLabelPositionFallback(position);

	        StandardCategoryItemLabelGenerator scilg = new StandardCategoryItemLabelGenerator("{0}", NumberFormat.getInstance());
	        renderer.setBaseItemLabelGenerator(scilg);
	        renderer.setBaseItemLabelsVisible(true);

	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setUpperBound(Math.max(nbA, Math.max(nbB, nbC)));
	        ChartUtilities.applyCurrentTheme(chart);
	        return chart;
	    }

	    /**
	     * Creer un JPanel pour le graphique
	     *
	     * @return : le JPanel
	     */
	    public JPanel createDemoPanel() {
	        JFreeChart chart = createChart(createDataset());
	        return new ChartPanel(chart);
	    }
	    
	    /**
	     * Methode qui compte le nombre d'alarmes de chaque type en fonction de la zone geographique
	     * 
	     * @param ae : un ArrayList qui contient des instances des differentes classes d'alarmes
	     * 
	     * @return ai : un ArrayList de integer qui contient les compteurs de type d'alarmes par zone geo
	     */
	    public ArrayList<Integer> countAlarm(ArrayList<AlarmEvent> ae) {
	    	int gazA = 0;
	    	int feuA = 0;
	    	int radA = 0;
	    	int gazB = 0;
	    	int feuB = 0;
	    	int radB = 0;
	    	int gazC = 0;
	    	int feuC = 0;
	    	int radC = 0;
	    	
	    	for (int i=0; i<ae.size(); i++) {
	    		if (ae.get(i).getLocalisation().equals("A")) {
	    			if (ae.get(i).getType().equals("Alarme incendie"))
	    				feuA++;
	    			else if (ae.get(i).getType().equals("Alarme gaz"))
	    				gazA++;
	    			else
	    				radA++;
	    		} else if (ae.get(i).getLocalisation().equals("B")) {
	    			if (ae.get(i).getType().equals("Alarme incendie"))
	    				feuB++;
	    			else if (ae.get(i).getType().equals("Alarme gaz"))
	    				gazB++;
	    			else
	    				radB++;
	    		} else {
	    			if (ae.get(i).getType().equals("Alarme incendie"))
	    				feuC++;
	    			else if (ae.get(i).getType().equals("Alarme gaz"))
	    				gazC++;
	    			else
	    				radC++;
	    		}
	    	}
	    		
	    	ArrayList<Integer> ai = new ArrayList<Integer>();
	    	ai.add(feuA);
	    	ai.add(gazA);
	    	ai.add(radA);
	    	ai.add(feuB);
	    	ai.add(gazB);
	    	ai.add(radB);
	    	ai.add(feuC);
	    	ai.add(gazC);
	    	ai.add(radC);
	    	
	    	return	ai;
	    	
	    }
	}
	
	private static class MyStackedBarRenderer extends StackedBarRenderer {

		private static final long serialVersionUID = 1L;

		int oldColumn = -99;

        int count = 0;

        Paint[] list = DefaultDrawingSupplier.DEFAULT_PAINT_SEQUENCE;

        /**
         * Methode qui retourne la peinture de l'item
         *
         * @param row : l'index de la ligne
         * 
         * @param column : l'index de la colonne
         *
         * @return : la peinture
         */
        public Paint getItemPaint(int row, int column) {

            if (this.oldColumn != column) {
                this.count = 0;
                this.oldColumn = column;
            }
            else {
                this.count++;
            }
            return this.list[this.count];
        }
    }
}

