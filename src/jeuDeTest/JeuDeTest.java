package jeuDeTest;
import capteurs.CaptorFire;
import capteurs.CaptorGaz;
import capteurs.CaptorRadiation;
import moniteurs.MonitorA;
import moniteurs.MonitorB;

/**
 * @author : Alexandre Morel / Nicolas Michel
 *
 */
public class JeuDeTest {
	
	public static void main(String[] args) {
		
		CaptorGaz cg1 = new CaptorGaz("cG-205");
		
		CaptorFire cf1 = new CaptorFire("cF-207");
		
		CaptorRadiation cr1 = new CaptorRadiation("cR-205");
		
		MonitorA ma1 = new MonitorA("mA-207");
		
		MonitorB mb1 = new MonitorB("mB-205");
		
		cg1.addGazListener(ma1);
		
		cg1.addGazListener(mb1);
		
		cf1.addFireListener(ma1);
		
		cr1.addRadiationListener(mb1);
		
		cg1.generateGazEvent("Salle 205", 3, "Monoxyde de Carbone");
		
		cf1.generateFireEvent("Salle 207", 2);
		
		cr1.generateRadiationEvent("Salle 205", 1, 18);
		
		mb1.getListAlarmGaz();
		
		mb1.getListAlarmRadiation();
		
		cg1.showListeGazListener();
		
	}

}
