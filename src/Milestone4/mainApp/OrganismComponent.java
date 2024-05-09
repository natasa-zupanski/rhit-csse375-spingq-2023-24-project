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
		this.organism = new Organism(100, FitnessStrategyFactory.getTypeFromString(""), RandomType.FAKE, "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
		this.frame = frame;
		this.setHeight = setHeight;
	}

	public void flipAllele(int x, int y, int height, int width) {
		this.organism.flipAlleleCoord(x, y, height, width);
		
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
