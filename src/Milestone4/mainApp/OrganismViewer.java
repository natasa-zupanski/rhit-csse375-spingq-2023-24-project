package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class: OrganismComponent
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: Used to view/load/save files representing an organism's
 *         genotype
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: OrganismViewer organismViewer = new OrganismViewer();
 */
public class OrganismViewer extends Views {

	private String targetOrganism = "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110";

	@Override
	public void setUpViewer() {
		super.setUpViewer();
		final int frameWidth = 360;
		final int frameHeight = 470;

		this.frame.setTitle("OrganismViewer");
		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocation(1100, 100);

		JLabel fileTitle = new JLabel("");
		this.frame.add(fileTitle, BorderLayout.NORTH);

		panel = new JPanel(new GridLayout(2, 1));

		OrganismComponent component = new OrganismComponent(0, frame);
		this.frame.add(component, BorderLayout.CENTER);

		class ClickListener implements MouseListener {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();

				component.flipAllele(x - 10, y - 40, (int) frame.getSize().getHeight() - 145,
						(int) frame.getSize().getWidth() - 10);

				frame.repaint();
				targetOrganism = component.getOrganismGenotype();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		}

		frame.addMouseListener(new ClickListener());

		String rateString = "1";

		JLabel mutationRateLabel = new JLabel("M Rate: _/N", SwingConstants.CENTER);

		JTextField mutationRateText = new JTextField(rateString);
		mutationRateText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton mutate = new JButton("Mutate");
		mutate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(mutationRateText.getText());
				int rate = Integer.parseInt(mutationRateText.getText());
				component.handleMutate(rate);
				frame.repaint();
				targetOrganism = component.getOrganismGenotype();
			}
		});

		JButton load = new JButton("Load");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame fileChooserFrame = new JFrame();
				fileChooserFrame.setTitle("File Chooser");
				fileChooserFrame.setSize(500, 400);
				fileChooserFrame.setLocation(600, 400);
				fileChooserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fileChooserFrame.setLayout(new BorderLayout());
				JFileChooser fileChooser = new JFileChooser("OrganismFiles/");
				fileChooser.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e2) {
						String genotype = "";
						File file = fileChooser.getSelectedFile();
						Scanner s;
						try {
							s = new Scanner(file);
							while (s.hasNext()) {
								try {
									genotype += s.next();
								} catch (InputMismatchException e) {
									String nonNumber = s.next();
									System.err.println("Non-number " + nonNumber + " found.  Ignoring.");
								}
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						targetOrganism = genotype;
						component.setOrganism(
								new Organism(genotype, FitnessStrategyFactory.getTypeFromString("Target Organism"),
										RandomType.FAKE, genotype, 0, 100));
						fileTitle.setText(file.getName());
						frame.repaint();
						fileChooserFrame.setVisible(false);
					}
				});
				fileChooserFrame.add(fileChooser);
				fileChooserFrame.setVisible(true);
			}
		});

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String genotype = component.getOrganismGenotype();

				JFrame fileChooserFrame = new JFrame();
				fileChooserFrame.setTitle("File Chooser");
				fileChooserFrame.setSize(500, 400);
				fileChooserFrame.setLocation(600, 400);
				fileChooserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fileChooserFrame.setLayout(new BorderLayout());

				JFileChooser fileChooser = new JFileChooser("OrganismFiles/");
				fileChooser.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e2) {
						File file = fileChooser.getSelectedFile();
						try {
							FileWriter myWriter = new FileWriter(file);
							myWriter.write(genotype);
							myWriter.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						fileChooserFrame.setVisible(false);
					}
				});
				fileChooserFrame.add(fileChooser);
				fileChooserFrame.setVisible(true);
			}
		});

		JPanel top = new JPanel(new GridLayout(1, 6));
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
		top.setMaximumSize(new Dimension(frameWidth, 10));

		panel.add(top);

		JPanel bottom = new JPanel(new GridLayout(2, 3));
		bottom.add(mutate);
		bottom.add(mutationRateLabel);
		bottom.add(mutationRateText);
		bottom.add(load);
		bottom.add(save);
		panel.add(bottom);

		// panel.setMaximumSize(new Dimension(frameWidth, 100));

		this.frame.add(panel, BorderLayout.SOUTH);

	}

	public String getTargetOrganism() {
		return this.targetOrganism;
	}

}
