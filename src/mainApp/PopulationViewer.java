package mainApp;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
/**
 * Class: PopulationViewer
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * </br>Purpose: used to create a JFrame that allows an user to set parameters for a population, run evolution, and see data related to the fitness.
 * </br>Restrictions: None
 * </br> For example: PopulationViewer populationViewer = new PopulationViewer(); 
 */
public class PopulationViewer {
	private JFrame frame;
	private PopulationComponent pop = new PopulationComponent(new Population());
	private String status = "Stopped";
	private Timer timer;
	FittestOrganismViewer fittestOrganism = new FittestOrganismViewer();
	GenerationViewer generationViewer = new GenerationViewer();

	public void setUpViewer() {
		fittestOrganism.setUpViewer();
		generationViewer.setUpViewer();

		final int frameWidth = 1100;
		final int frameHeight = 500;

		this.frame = new JFrame();
		this.frame.setTitle("EvolutionViewer");
		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocation(25, 75);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());

		PopulationAdvanceListener advanceListener = new PopulationAdvanceListener(pop, fittestOrganism, this, generationViewer);
		Timer timer = new Timer(1, advanceListener);
		timer.stop();
		frame.add(pop);

		JLabel selectionLabel = new JLabel("Selection Method", SwingConstants.CENTER);
		String[] selectionMethods = { "Truncation", "Roulette Wheel", "Rank", "Rank Roulette", "Stable State" , "Learning Chance"};
		JComboBox selectionOptions = new JComboBox(selectionMethods);
		selectionOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pop.handleSetSelection((String) selectionOptions.getSelectedItem());
			}
		});

		JLabel fitnessLabel = new JLabel("Fitness Method", SwingConstants.CENTER);
		String[] fitnessMethods = { "Num. of 1s", "Target Organism", "Consec. num. of 1s" };
		JComboBox fitnessOptions = new JComboBox(fitnessMethods);
		selectionOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pop.handleSetFitness((String) fitnessOptions.getSelectedItem());
			}
		});

		JCheckBox crossoverCheckBox = new JCheckBox("Crossover?");
		crossoverCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pop.handleSetCrossover(!pop.handleGetCrossover());
			}
		});

		JLabel genSizeLabel = new JLabel("Gen. Size", SwingConstants.CENTER);
		JTextField genSizeText = new JTextField(pop.handleGetGenSize());
		genSizeText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int size = Integer.parseInt(genSizeText.getText());
				pop.handleSetGenSize(size);
			}
		});

		JLabel mutationRateLabel = new JLabel("Mutation Rate (N/pop)", SwingConstants.CENTER);
		JTextField mutationRateText = new JTextField(pop.handleGetMutationRate());
		mutationRateText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rate = Integer.parseInt(mutationRateText.getText());
				if (rate < 0 || rate > 100) {
				} else {
					pop.handleSetMutationRate(rate);
				}
			}
		});

		JLabel numGensLabel = new JLabel("# of Gens.", SwingConstants.CENTER);
		JTextField numGensText = new JTextField(pop.handleGetNumGens());
		numGensText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(numGensText.getText());
				pop.handleSetNumGens(num);
			}
		});

		JLabel genomeLengthLabel = new JLabel("Genome Length", SwingConstants.CENTER);
		JTextField genomeLengthText = new JTextField(pop.handleGetGenomeLength());
		genomeLengthText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int length = Integer.parseInt(genomeLengthText.getText());
				pop.handleSetGenomeLength(length);
			}
		});

		JLabel elitismLabel = new JLabel("Elitism %", SwingConstants.CENTER);
		JTextField elitismText = new JTextField(pop.handleGetElitism());
		elitismText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rate = Integer.parseInt(elitismText.getText());
				System.out.println("Elitism: " + rate);
				pop.handleSetElitism(rate);
			}
		});
		
		JLabel terminationLabel = new JLabel("Termination Fitness", SwingConstants.CENTER);
		JTextField terminationText = new JTextField(pop.handleGetTermination());
		terminationText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int condition = Integer.parseInt(terminationText.getText());
				pop.handleSetTermination(condition);
			}
		});

		JButton startButton = new JButton("Start Evoltuion");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (status.equals("Stopped")) {
					pop.createNewPopulation(Integer.parseInt(mutationRateText.getText()),
							Integer.parseInt(numGensText.getText()), Integer.parseInt(genSizeText.getText()),
							Integer.parseInt(genomeLengthText.getText()), Integer.parseInt(elitismText.getText()),
							(String) selectionOptions.getSelectedItem(), (String) fitnessOptions.getSelectedItem(),
							crossoverCheckBox.isSelected(), Integer.parseInt(terminationText.getText()));
					// System.out.println((String) fitnessOptions.getSelectedItem());
					pop.handleRunPopulationEvol();
					timer.restart();
					status = "Running";
					startButton.setText("Pause");
				} else if (status.equals("Paused")) {
					timer.start();
					status = "Running";
					startButton.setText("Pause");
				} else { // status should be "Running" here
					timer.stop();
					status = "Paused";
					startButton.setText("Start");
				}
			}
		});

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.restart();
				timer.stop();
				startButton.setText("Start");
				pop.createNewPopulation(Integer.parseInt(mutationRateText.getText()),
						Integer.parseInt(numGensText.getText()), Integer.parseInt(genSizeText.getText()),
						Integer.parseInt(genomeLengthText.getText()), Integer.parseInt(elitismText.getText()),
						(String) selectionOptions.getSelectedItem(), (String) fitnessOptions.getSelectedItem(),
						crossoverCheckBox.isSelected(), Integer.parseInt(terminationText.getText()));
				pop.repaint();
				status = "Stopped";
			}
		});

		frame.add(pop);

		JPanel panel = new JPanel(new GridLayout(2, 1));
		JPanel top = new JPanel(new GridLayout(1,9));
		top.add(selectionLabel);
		top.add(selectionOptions);
		top.add(fitnessLabel);
		top.add(fitnessOptions);
		top.add(crossoverCheckBox);
		top.add(terminationLabel);
		top.add(terminationText);
		top.add(startButton);
		top.add(resetButton);
		JPanel bottom = new JPanel(new GridLayout(1, 10));
		bottom.add(mutationRateLabel);
		bottom.add(mutationRateText);
		bottom.add(genSizeLabel);
		bottom.add(genSizeText);
		bottom.add(numGensLabel);
		bottom.add(numGensText);
		bottom.add(genomeLengthLabel);
		bottom.add(genomeLengthText);
		bottom.add(elitismLabel);
		bottom.add(elitismText);
		panel.add(top);
		panel.add(bottom);
		this.frame.add(panel, BorderLayout.SOUTH);
	}

	public void runApp() {
		this.frame.setVisible(true);
	}

	public void terminateRun() {
		try {
			pop.handleTerminate();
			timer.restart(); // doesn't throw null pointer at terminate
			timer.stop(); // doesn't throw it either
			status = "Stopped";
		} catch (NullPointerException e) {
		
		}

	}
	
	public String getStatus() {
		return this.status;
	}

}
