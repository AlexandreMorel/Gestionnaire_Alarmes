package jeuDeTest;
import gui.AlarmArchiving;
import gui.AlarmGenerator;
import gui.AlarmMonitoring;

/**
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class JeuDeTestGUI {
	
	public static void main(String [] args) {
		/*
		 * Creation d'une instance de la classe AlarmGenerator, interface de creation des alarmes
		 */
		AlarmGenerator ag = new AlarmGenerator();
		/*
		 * Creation d'une instance de la classe AlarmMonitoring, interface de gestion des alarmes
		 */
		AlarmMonitoring am = new AlarmMonitoring();
		/*
		 * Creation d'une instance de la classe AlarmArchiving, interface de gestion des alarmes archivees
		 */
		AlarmArchiving aa = new AlarmArchiving();
		/*
		 * On definit am comme interface de gestion des alarmes assignee a ag
		 */
		ag.addAlarmMonitoring(am);
		/*
		 * On definit aa comme interface de gestion des alarmes archivees assignee a am
		 */
		am.addAlarmArchiving(aa);
		
	}
	
}
