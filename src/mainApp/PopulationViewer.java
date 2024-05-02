package mainApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

/**
 * Class: PopulationViewer
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: used to create a JFrame that allows an user to set
 *         parameters for a population, run evolution, and see data related to
 *         the fitness.
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: PopulationViewer populationViewer = new
 *         PopulationViewer();
 */
public class PopulationViewer extends Views {

	private static final int MAX_GEN_SIZE = 10000;
	private static final int MAX_MUTATE_RATE = 100;
	private static final int MAX_NUM_GENS = 10000;
	private static final int MAX_LENGTH = 1000;

	protected enum Status {
		STOPPED,
		RUNNING,
		PAUSED
	}

	private PopulationComponent pop = new PopulationComponent(new Population(), this.frame);
	private Status status = Status.STOPPED;
	private Timer timer;

	FittestOrganismViewer fittestOrganism = new FittestOrganismViewer();
	OrganismViewer organismViewer = new OrganismViewer();
	GenerationViewer generationViewer = new GenerationViewer();

	public void setUpViewer() {
		super.setUpViewer();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		organismViewer.setUpViewer();
		organismViewer.runApp();
		fittestOrganism.setUpViewer();
		generationViewer.setUpViewer();

		final int frameWidth = 1500;
		final int frameHeight = 600;

		this.frame.setTitle("PopulationViewer");
		this.frame.setSize(frameWidth, frameHeight);
		this.frame.setLocation(25, 175);

		PopulationAdvanceListener advanceListener = new PopulationAdvanceListener(pop, fittestOrganism, this,
				generationViewer);
		Timer timer = new Timer(1, advanceListener);
		timer.stop();
		frame.add(pop);

		JLabel fitnessLabel = new JLabel("Fitness Method", SwingConstants.CENTER);
		String[] fitnessMethods = FitnessStrategyFactory.getStrings();// { "Num. of 1s", "Target Organism", "Consec.
																		// num. of 1s" };
		JComboBox<String> fitnessOptions = new JComboBox<String>(fitnessMethods);
		fitnessOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pop.handleSetFitness(
						FitnessStrategyFactory.getTypeFromString((String) fitnessOptions.getSelectedItem()));
			}
		});

		JLabel selectionLabel = new JLabel("Selection Method", SwingConstants.CENTER);
		selectionLabel.setToolTipText(
				"How to select which organisms from the previous generation contribute to the next generation.");
		String[] selectionMethods = SelectionStrategyFactory.getStrings();// { "Truncation", "Roulette Wheel", "Rank",
																			// "Rank Roulette", "Stable State" ,
																			// "Learning Chance"};
		/*
		 * WrappedLabel selectionTexts[] = new WrappedLabel[selectionMethods.length];
		 * for (int i = 0; i < selectionMethods.length; i++) {
		 * selectionTexts[i] = new WrappedLabel(selectionMethods[i]);
		 * selectionTexts[i].setToolTipText("WEEE");
		 * 
		 * }
		 */
		JComboBox<String> selectionOptions = new JComboBox<String>(selectionMethods);
		// JComboBox<JLabel> selectionOptions = new JComboBox<>(selectionTexts);
		// selectionOptions.getComponent(i) // for each and add the corresponding tool
		// tip
		/*
		 * for (int i = 0; i < selectionMethods.length; i++) {
		 * //Component comp = selectionOptions.getComponent(i);
		 * //comp.
		 * selectionOptions.
		 * }
		 */
		selectionOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionType type = SelectionStrategyFactory
						.getSelectionTypeFromString((String) selectionOptions.getSelectedItem());
				pop.handleSetSelection(type);
				if (type == SelectionType.LEARNINGCHANCE) {
					fitnessOptions.setEnabled(false);
				} else if (!fitnessOptions.isEnabled()) {
					fitnessOptions.setEnabled(true);
				}
			}
		});

		/*
		 * selectionOptions.addMouseListener(new MouseListener() {
		 * Component current;
		 * Timer timer;
		 * 
		 * public void mouseEntered(MouseEvent e) {
		 * Component next = selectionOptions.getComponentAt(e.getPoint());
		 * if (next == null) {
		 * // outside of the combobox
		 * } else if (current != next) {
		 * // we've changed what we're over
		 * startTimer();
		 * }
		 * }
		 * 
		 * private void startTimer() {
		 * timer = new Timer(500, new ActionListener() {
		 * public void actionPerformed(ActionEvent e) {
		 * selectionOptions.setToolTipText("WEEEE");
		 * }
		 * });
		 * timer.setRepeats(false);
		 * timer.start();
		 * }
		 * 
		 * public void mouseExited(MouseEvent e) {
		 * 
		 * }
		 * 
		 * public void mouseReleased(MouseEvent e) {
		 * 
		 * }
		 * 
		 * public void mouseClicked(MouseEvent e) {
		 * 
		 * }
		 * 
		 * public void mousePressed(MouseEvent e) {
		 * 
		 * }
		 * });
		 */

		JCheckBox crossoverCheckBox = new JCheckBox("Crossover?");
		crossoverCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pop.handleSetCrossover(!pop.handleGetCrossover());
			}
		});

		JLabel genSizeLabel = new JLabel("Gen. Size", SwingConstants.CENTER);
		JTextField genSizeText = new JTextField(pop.handleGetGenSize());
		CompoundInvalidInputListener genSizeListener = new CompoundInvalidInputListener(InputType.GENSIZE, genSizeText,
				0, MAX_GEN_SIZE, pop);
		genSizeText.addKeyListener(genSizeListener);
		genSizeText.addFocusListener(genSizeListener);

		JLabel mutationRateLabel = new JLabel("Mutation Rate (N/pop)", SwingConstants.CENTER);
		JTextField mutationRateText = new JTextField(pop.handleGetMutationRate());
		CompoundInvalidInputListener mutationRateListener = new CompoundInvalidInputListener(InputType.MUTATIONRATE,
				mutationRateText, -1, MAX_MUTATE_RATE, pop);
		mutationRateText.addKeyListener(mutationRateListener);
		mutationRateText.addFocusListener(mutationRateListener);

		/*
		 * mutationRateText.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * int rate = Integer.parseInt(mutationRateText.getText());
		 * if (rate < 0 || rate > MAX_MUTATE_RATE) {
		 * System.out.println("NOT YET IMPLEMENTED - NEG OR OVER MAX MUTATE RATE");
		 * } else {
		 * pop.handleSetMutationRate(rate);
		 * }
		 * }
		 * });
		 */

		JLabel numGensLabel = new JLabel("# of Gens.", SwingConstants.CENTER);
		JTextField numGensText = new JTextField(pop.handleGetNumGens());
		CompoundInvalidInputListener numGensListener = new CompoundInvalidInputListener(InputType.NUMGENS, numGensText,
				0, MAX_NUM_GENS, pop);
		numGensText.addKeyListener(numGensListener);
		numGensText.addFocusListener(numGensListener);

		/*
		 * numGensText.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * int num = Integer.parseInt(numGensText.getText());
		 * if (num > 0 && num <= MAX_NUM_GENS) {
		 * pop.handleSetNumGens(num);
		 * } else {
		 * System.out.println("NOT YET IMPLEMENTED - NON POS OR OVER MAX NUM OF GENS");
		 * }
		 * }
		 * });
		 */

		JLabel genomeLengthLabel = new JLabel("Genome Length", SwingConstants.CENTER);
		JTextField genomeLengthText = new JTextField(pop.handleGetGenomeLength());
		CompoundInvalidInputListener genomeLengthListener = new CompoundInvalidInputListener(InputType.GENOMELENGTH,
				genomeLengthText, 0, MAX_LENGTH, pop);
		genomeLengthText.addKeyListener(genomeLengthListener);
		genomeLengthText.addFocusListener(genomeLengthListener);

		/*
		 * genomeLengthText.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * int length = Integer.parseInt(genomeLengthText.getText());
		 * if (length > 0 && length <= MAX_LENGTH) {
		 * pop.handleSetGenomeLength(length);
		 * } else {
		 * System.out.println("NOT YET IMPLEMENTED - NON POS OR OVER MAX LENGTH");
		 * }
		 * }
		 * });
		 */

		JLabel elitismLabel = new JLabel("Elitism %", SwingConstants.CENTER);
		JTextField elitismText = new JTextField(pop.handleGetElitism());
		CompoundInvalidInputListener elistismListener = new CompoundInvalidInputListener(InputType.ELITISM, elitismText,
				-1, 100, pop);
		elitismText.addKeyListener(elistismListener);
		elitismText.addFocusListener(elistismListener);
		/*
		 * elitismText.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * int rate = Integer.parseInt(elitismText.getText());
		 * System.out.println("Elitism: " + rate);
		 * if (rate >= 0 && rate <= 100) {
		 * pop.handleSetElitism(rate);
		 * } else {
		 * System.out.println("NOT YET IMPLEMENTED - NEG OR OVER 100 PERCENT ELITISM");
		 * }
		 * }
		 * });
		 */

		JLabel terminationLabel = new JLabel("Termination Fitness", SwingConstants.CENTER);
		JTextField terminationText = new JTextField(pop.handleGetTermination());
		CompoundInvalidInputListener terminationListener = new CompoundInvalidInputListener(
				InputType.TERMINATIONFITNESS, terminationText, 0, Integer.parseInt(pop.handleGetGenomeLength()), pop);
		terminationText.addKeyListener(terminationListener);
		terminationText.addFocusListener(terminationListener);
		/*
		 * terminationText.addActionListener(new ActionListener() {
		 * 
		 * @Override
		 * public void actionPerformed(ActionEvent e) {
		 * int condition = Integer.parseInt(terminationText.getText());
		 * pop.handleSetTermination(condition);
		 * }
		 * });
		 */

		JButton startButton = new JButton("Start Evoltuion");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (status == Status.STOPPED) {
					String targetOranism = organismViewer.getTargetOrganism();
					/*
					 * pop.createNewPopulation(Integer.parseInt(mutationRateText.getText()),
					 * Integer.parseInt(numGensText.getText()),
					 * Integer.parseInt(genSizeText.getText()),
					 * Integer.parseInt(genomeLengthText.getText()),
					 * Integer.parseInt(elitismText.getText()),
					 * SelectionStrategyFactory
					 * .getSelectionTypeFromString((String) selectionOptions.getSelectedItem()),
					 * FitnessStrategyFactory.getTypeFromString((String)
					 * fitnessOptions.getSelectedItem()),
					 * crossoverCheckBox.isSelected(), Integer.parseInt(terminationText.getText()),
					 * targetOranism);
					 * // System.out.println((String) fitnessOptions.getSelectedItem());
					 */
					pop.createNewPopulation();
					pop.setTargetOrganism(targetOranism);
					pop.handleRunPopulationEvol();
					timer.restart();
					status = Status.RUNNING;
					startButton.setText("Pause");
				} else if (status == Status.PAUSED) {
					timer.start();
					status = Status.RUNNING;
					startButton.setText("Pause");
				} else { // status should be "Running" here
					timer.stop();
					status = Status.PAUSED;
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
				String targetOrganism = organismViewer.getTargetOrganism();
				/*
				 * pop.createNewPopulation(Integer.parseInt(mutationRateText.getText()),
				 * Integer.parseInt(numGensText.getText()),
				 * Integer.parseInt(genSizeText.getText()),
				 * Integer.parseInt(genomeLengthText.getText()),
				 * Integer.parseInt(elitismText.getText()),
				 * SelectionStrategyFactory
				 * .getSelectionTypeFromString((String) selectionOptions.getSelectedItem()),
				 * FitnessStrategyFactory.getTypeFromString((String)
				 * fitnessOptions.getSelectedItem()),
				 * crossoverCheckBox.isSelected(), Integer.parseInt(terminationText.getText()),
				 * targetOrganism);
				 */

				pop.createNewPopulation();
				pop.repaint();

				status = Status.STOPPED;
			}
		});

		JButton openFitnessButton = new JButton("Open Fittest Organism Viewer");
		openFitnessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fittestOrganism.runApp();
			}
		});

		JButton openGenerationButton = new JButton("Open Generation Viewer");
		openGenerationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generationViewer.runApp();
			}
		});

		JButton openOrganismButton = new JButton("Open Chromosome Viewer");
		openOrganismButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				organismViewer.runApp();
			}
		});

		JPanel buttonPanel = new JPanel();
		Dimension buttonPanelSize = new Dimension(100, 30);
		JLabel menuLabel = new JLabel("Views Manager:", SwingConstants.CENTER);
		buttonPanel.add(menuLabel);
		buttonPanel.add(openFitnessButton);
		buttonPanel.add(openGenerationButton);
		buttonPanel.add(openOrganismButton);
		buttonPanel.setPreferredSize(buttonPanelSize);
		this.frame.add(buttonPanel, BorderLayout.NORTH);

		frame.add(pop, BorderLayout.CENTER); // then add keys to the west

		JPanel keys = new JPanel(new GridLayout(5, 1));
		JLabel highest = new JLabel("Highest Fitness");
		JLabel average = new JLabel("Average Fitness");
		JLabel lowest = new JLabel("Lowest Fitness");
		JLabel variance = new JLabel("Variance in Fitness");
		Dimension keysPanelSize = new Dimension(150, 200);
		keys.add(highest);
		keys.add(average);
		keys.add(lowest);
		keys.add(variance);
		keys.setPreferredSize(keysPanelSize);

		// frame.add(keys, BorderLayout.EAST);

		JPanel panel = new JPanel(new GridLayout(2, 1));
		JPanel top = new JPanel(new GridLayout(1, 9));
		Dimension panelSize = new Dimension(100, 70);
		panel.setPreferredSize(panelSize);
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

	public void terminateRun() {
		try {
			pop.handleTerminate();
			timer.restart(); // doesn't throw null pointer at terminate
			timer.stop(); // doesn't throw it either
			status = Status.STOPPED;
		} catch (NullPointerException e) {

		}
	}
}
