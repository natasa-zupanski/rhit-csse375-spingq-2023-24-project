package mainApp;

import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.JPanel;

/**
 * Class: FittestOrganismViewer
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: used to display the genotype of the current generation's
 *         fittest organism.
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: FittestOrganismViewer fittestOrganism = new
 *         FittestOrganismViewer();
 */
public class FittestOrganismViewer extends Views {
	private OrganismComponent component;
	private int genIndex = 0;
	private JLabel genNum;
	private JLabel fitnessText;
	private Organism organism;

	@Override
	public void setUpViewer() {
		super.setUpViewer();
		this.frame.setTitle("FittestOrganismViewer");
		this.frame.setSize(360, 420);
		this.frame.setLocation(1125, 25);
		component = new OrganismComponent(0, frame);

		this.frame.add(component, BorderLayout.CENTER);

		panel = new JPanel(new GridLayout(2, 1));

		JPanel top = new JPanel(new GridLayout(1, 6));
		top.setSize(200, 50);
		JPanel color = new JPanel(new GridLayout(1, 1));
		color.setBackground(Color.GREEN);
		top.add(color);
		JLabel colorLabel = new JLabel("1s");
		top.add(colorLabel);
		JPanel black = new JPanel(new GridLayout(1, 1));
		black.setBackground(Color.BLACK);
		top.add(black);
		JLabel blackLabel = new JLabel("0s");
		top.add(blackLabel);
		JPanel white = new JPanel(new GridLayout(1, 1));
		white.setBackground(Color.WHITE);
		top.add(white);
		JLabel whiteLabel = new JLabel("?s");
		top.add(whiteLabel);

		JPanel bottom = new JPanel(new GridLayout(1, 2));
		genNum = new JLabel("" + genIndex);
		genNum.setToolTipText("Number of the current generation.");
		fitnessText = new JLabel("");
		fitnessText.setToolTipText("Fitness of the fittest organism.");
		bottom.add(genNum);
		bottom.add(fitnessText);

		panel.add(top);
		panel.add(bottom);

		this.frame.add(panel, BorderLayout.SOUTH);

		this.runApp();
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
		genNum.setText("" + genIndex);
		fitnessText.setText("" + this.organism.fitness());
	}

}
