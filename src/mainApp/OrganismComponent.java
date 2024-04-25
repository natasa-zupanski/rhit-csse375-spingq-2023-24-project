package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
	private JFrame frame;
	private int setHeight;

	/**
	 * ensures: initializes the component height to h
	 * @param i 
	 * 
	 * @param h used to initialize the height
	 *          <br>
	 *          requires: h>0
	 */
	public OrganismComponent(int setHeight, JFrame frame) {
		this.organism = new Organism(100, FitnessStrategyFactory.getTypeFromString(""), RandomType.FAKE);
		this.frame = frame;
		this.setHeight = setHeight;
	}

	public void flipAllele(int x, int y, int height, int width) {
		int index = this.organism.getOrganismVisualization().flipAlleleCoord(x, y, height, width);
		if(index > 0)
		{
			this.organism.flipAllele(index);
		}
	}

	public void handleMutate(int rate) {
		this.organism.mutate(rate);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		this.organism.getOrganismVisualization().drawOn(g2d, this.setHeight, this.frame);
	}

	public void setOrganism(Organism o) {
		this.organism = o;
	}

	public String getOrganismGenotype() {
		return this.organism.getChromosome();
	}

}
