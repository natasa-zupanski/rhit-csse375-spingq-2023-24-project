package mainApp;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Class: GenerationViewer
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: used to display the genotype of all of the organisms in a
 *         generation.
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: GenerationViewer generationViewer = new
 *         GenerationViewer();
 */
public class GenerationViewer extends Views {
	// private Generation gen = new Generation();
	private Organism[] orgs = new Organism[100];
	private ArrayList<OrganismComponent> components = new ArrayList<OrganismComponent>();

	public GenerationViewer() {
		this.orgs = new Organism[100];
		for (int i = 0; i < 100; i++) {
			this.orgs[i] = new Organism(100, FitnessType.NUMONES);
		}
	}

	/**
	 * ensures: initializes the generation to be displayed
	 * 
	 * @param g: Generation being displayed
	 *           <br>
	 *           requires: g != null
	 */
	public GenerationViewer(Organism[] orgs) {
		this.orgs = orgs;
	}

	private int getRowNum() {
		return (int) Math.sqrt(orgs[0].length());
	}

	private int getColNum() {
		return (int) Math.sqrt(orgs[0].length());
	}

	public void setUpViewer() {
		super.setUpViewer();
		final int frameWidth = 400;
		final int frameHeight = 400;

		this.frame.setTitle("GenerationViewer");
		this.frame.setSize(frameWidth, frameHeight);

		this.panel.setLayout(new GridLayout(getRowNum(), getColNum()));

		this.drawScreen();
		for (OrganismComponent o : components) {
			panel.add(o);
		}

		this.frame.add(this.panel);
		frame.setVisible(true);
	}

	public void setGen(Organism[] orgs) {
		this.orgs = orgs;
	}

	public void drawScreen() {
		this.frame.remove(panel);
		this.panel = new JPanel(new GridLayout(getRowNum(), getColNum()));
		components = new ArrayList<OrganismComponent>();
		for (int i = 0; i < orgs.length; i++) {
			OrganismComponent component = new OrganismComponent(30);
			component.setOrganism(orgs[i]);
			components.add(component);
		}
		for (OrganismComponent o : components) {
			panel.add(o);
		}
		this.frame.add(this.panel);
		this.frame.revalidate();

	}

}
