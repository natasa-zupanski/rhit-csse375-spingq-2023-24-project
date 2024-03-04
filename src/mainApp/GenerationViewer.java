package mainApp;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Class: GenerationViewer
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * </br>Purpose: used to display the genotype of all of the organisms in a generation.
 * </br>Restrictions: None
 * </br> For example: GenerationViewer generationViewer = new GenerationViewer();
 */
public class GenerationViewer {
	private JFrame frame;
	private JPanel panel = new JPanel();
	private Generation gen = new Generation();
	private ArrayList<OrganismComponent> components= new ArrayList<OrganismComponent>();
	public GenerationViewer() {
		this.gen = new Generation();
	}
	
	/**
	 * ensures: initializes the generation to be displayed
	 * @param g: Generation being displayed
	 * <br>requires: g != null
	 */
	public GenerationViewer(Generation g) {
		this.gen = g;
	}
	
	public void setUpViewer() {
		
		final int frameWidth = 400;
		final int frameHeight = 400;

		this.frame = new JFrame();
		this.frame.setTitle("GenerationViewer");
		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocation(1125, 400);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel.setLayout(new GridLayout(gen.getRowNum(), gen.getColumnNum()));
		
		this.drawScreen();
		for(OrganismComponent o: components) {
			panel.add(o);
		}
		
		this.frame.add(this.panel);
		frame.setVisible(true);	
	}
	
	public void setGen(Generation g) {
		this.gen = g;
	}
	
	public void drawScreen() {
		this.frame.remove(panel);
		this.panel = new JPanel(new GridLayout(gen.getRowNum(), gen.getColumnNum()));
		components= new ArrayList<OrganismComponent>();
		for(int i = 0; i < gen.getOrganisms().length; i++) {
			OrganismComponent component = new OrganismComponent(30);
			component.setOrganism(gen.getOrganisms()[i]);
			components.add(component);
		}
		for(OrganismComponent o: components) {
			panel.add(o);
		}
		this.frame.add(this.panel);
		this.frame.revalidate();

	}
	
}
