package mainApp;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Class: FittestOrganismViewer
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * </br>Purpose: used to display the genotype of the current generation's fittest organism.
 * </br>Restrictions: None
 * </br> For example: FittestOrganismViewer fittestOrganism = new FittestOrganismViewer();
 */
public class FittestOrganismViewer extends JComponent{
	private JFrame frame;
	private OrganismComponent component = new OrganismComponent(300);
	private int genIndex = 0;
	private JLabel genNum;
	private JLabel fitnessText;
	private Organism organism;
	
	public void setUpViewer() {
		this.frame = new JFrame();
		this.frame.setTitle("FittestOrganismViewer");
		this.frame.setSize(360, 360);
		this.frame.setLocation(1125, 25);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());
	
		this.frame.add(component, BorderLayout.CENTER);
		
		JPanel texts = new JPanel(); 
		
		
		genNum = new JLabel(""+genIndex);
		fitnessText = new JLabel("");
		texts.add(genNum);
		texts.add(fitnessText);
		
		this.frame.add(texts,BorderLayout.SOUTH);
	
		this.frame.setVisible(true);
	}
	
	public void updateOrganism(Organism o) {
		this.organism = o;
		this.component.setOrganism(this.organism);
		
	}
	
	public void setGenIndex(int i) {
		this.genIndex = i;
	}
	
	public void drawScreen() {
		component.repaint();
		genNum.setText(""+genIndex);
		fitnessText.setText(""+this.organism.fitness());
	}

}
