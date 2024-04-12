package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Class: OrganismComponent
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: used to draw the genotype of an organism
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: GenerationViewer generationViewer = new
 *         GenerationViewer();
 */
public class OrganismComponent extends JComponent {
	private Organism organism;
	private int height;

	/**
	 * ensures: initializes the component height to h
	 * 
	 * @param h used to initialize the height
	 *          <br>
	 *          requires: h>0
	 */
	public OrganismComponent(int h) {
		this.organism = new Organism(100, FitnessStrategyFactory.getTypeFromString(""), RandomType.FAKE);
		this.height = h;
	}

	public void flipAllele(int x, int y) {
		this.organism.flipAlleleCoord(x, y);
	}

	public void handleMutate(int rate) {
		this.organism.mutate(rate);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		this.organism.drawOn(g2d, this.height);
	}

	public void setOrganism(Organism o) {
		this.organism = o;
	}

	public String getOrganismGenotype() {
		return this.organism.getChromosome();
	}

}
