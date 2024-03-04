package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class: PopulationAdvanceListener
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * </br>Purpose: Used to handle updates to the various viewers as the timer increases
 * </br>Restrictions: None
 * </br> For example: PopulationAdvanceListener advanceListener = new PopulationAdvanceListener(population, fittestOrganism, populationViewer, generationViewer);
 */ 
public class PopulationAdvanceListener implements ActionListener {
	private PopulationComponent component;
	private FittestOrganismViewer organismViewer;
	private PopulationViewer populationViewer;
	private GenerationViewer generationViewer;

	/**
	 * ensures: initializes the population component to c, the fittest organism viewer to o, the population viewer to v, and the generation viewer to g
	 * @param c used to initialize the population component
	 * <br>requires: c != null
	 * @param o used to initialize the fittest organism viewer
	 * <br>requires: o != null
	 * @param v used to initialize the population viewer
	 * <br>requires: v != null
	 * @param g used to initialize the generation viewer
	 * <br>requires: g != null
	 */
	public PopulationAdvanceListener(PopulationComponent c, FittestOrganismViewer o, PopulationViewer v,
			GenerationViewer g) {
		this.component = c;
		this.organismViewer = o;
		this.populationViewer = v;
		this.generationViewer = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.component.handleGetGensSoFar() < Integer.parseInt(this.component.handleGetNumGens())) {
			if (component.handleGetFittest().fitness() < Integer.parseInt(component.handleGetTermination())) {
				advanceOneTick();
			} else {
				populationViewer.terminateRun();
				System.out.println("Terminated run");
			}
		}
	}

	public void advanceOneTick() {
		this.component.updateState();
		this.component.drawScreen();
		this.organismViewer.updateOrganism(component.handleGetFittest());
		this.organismViewer.setGenIndex(component.handleGetGensSoFar() - 1);
		this.organismViewer.drawScreen();
		this.generationViewer.setGen(component.handleGetLatestGen());
		this.generationViewer.drawScreen();
	}

}
