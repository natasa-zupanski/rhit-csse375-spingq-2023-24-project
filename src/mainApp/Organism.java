package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Class: Organism
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski <br>
 *         Purpose: to simulate the idea of an Organism, having genetic code, a
 *         graphic representation, a method that determines its fitness, methods
 *         that determine this fitness and change its genetic code, and methods
 *         to perform crossover. <br>
 *         Restrictions: Takes genetic code of 0s, 1s, and occasionally ?s.
 *         Other characters in the genetic code will not currently be supported
 *         for any functions of the overall simulator.
 */
public class Organism implements Comparable<Organism> {
	// mutable/changing fields
	private int constantFitness = -1;
	private String chromosome;
	private FitnessType fitnessType;
	private FitnessStrategy fitness = null;
	private int numGens = 0;

	private RandomInterface r = new WrappedRandom();

	// constant fields
	private final int HEIGHT = 300;
	private Color[] colors = { new Color(255, 8, 1), new Color(255, 95, 1), new Color(255, 166, 1),
			new Color(253, 235, 1), new Color(102, 253, 59), new Color(13, 252, 150), new Color(1, 203, 228),
			new Color(4, 122, 252), new Color(150, 54, 254), new Color(217, 67, 255) };

	/**
	 * ensures: constructs an organism given the length of its chromosome, the
	 * number of alleles each organism has in its genetic code, the fitness method,
	 * the name of the process that determines how fit an organism is based on its
	 * genetic code, and a boolean that determines whether or not there should be
	 * any ?s in the genetc code of the organism. This also overrides the fitmess
	 * method. The genetic code is randomly generated.
	 * 
	 * @param length,        the number of alleles in the genetic code of the
	 *                       organism
	 * @param fitnessMethod, the method that determines the fitness of an organism
	 *                       from its genetic code
	 * @param someUnsure,    if true, there may be ?s in the genetic code of the
	 *                       organism. If false, there will only be 0s and 1s in the
	 *                       genetic code.
	 */
	public Organism(int length, FitnessType type, boolean someUnsure, RandomType random) {
		this(length, type);
		if (someUnsure) {
			// Random r = new Random();
			char[] randomChromosome = new char[length];
			for (int index = 0; index < length; index++) {
				int chance = r.nextInt(4);
				if (chance == 1) {
					randomChromosome[index] = '1';
				}
				if (chance == 0) {
					randomChromosome[index] = '0';
				}
				if (chance == 2 || chance == 3) {
					randomChromosome[index] = '?';
				}
			}
			this.chromosome = String.valueOf(randomChromosome);
			r = RandomFactory.getRandomOfType(random);
			this.fitnessType = type;
		}
	}

	/**
	 * ensures: constructs an organism from the given genetic code as a String, with
	 * each character being an allele, and the given fitness method.
	 * 
	 * @param chromosome,    the genetic code of the organism
	 * @param fitnessMethod, the name of the process used to determine how fit an
	 *                       organism is based on their genetic code
	 */
	public Organism(String chromosome, FitnessType type, RandomType randomType) {
		this(chromosome);
		this.fitnessType = type;
		this.r = RandomFactory.getRandomOfType(randomType);
	}

	/**
	 * ensures: contructs an organism's genetic code from a string, each character
	 * an allele.
	 * 
	 * @param chromosome, the string which the organisms genetic code will be set to
	 */
	public Organism(String chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 * ensures: constructs an organism from the length of its chromosome, the number
	 * of alleles in its randomly decided genetic code, and its fitness method, the
	 * process which decides how fit it is based on its genetic code.
	 * 
	 * @param length,        the number of alleles in the genetic code
	 * @param fitnessMethod, the process which decides how fit an organism is
	 */
	public Organism(int length, FitnessType type, RandomType randomType) {
		char[] randomChromosome = new char[length];
		r = RandomFactory.getRandomOfType(randomType);
		for (int i = 0; i < length; i++) {
			int chance = r.nextInt(2);
			if (chance == 1) {
				randomChromosome[i] = '0';
			} else {
				randomChromosome[i] = '1';
			}
		}
		this.fitnessType = type;
		this.chromosome = String.valueOf(randomChromosome);
	}

	public Organism(String chromosome2, FitnessType fitnessType2, RandomType randomType) {
		this(chromosome2, fitnessType2);
		r = RandomFactory.getRandomOfType(randomType);
	}

	/**
	 * ensures: counts and returns the number of 0s in the genetic code of the
	 * organism.
	 * 
	 * @return, the number of 0s in the genetic code of the organism.
	 */
	public int numOf0s() {
		int total = 0;
		for (char c : this.chromosome.toCharArray()) {
			if (c == '0') {
				total += 1;
			}
		}
		return total;
	}

	/**
	 * ensures: counts and returns the number of ?s in the genetic code of the
	 * organism.
	 * 
	 * @return, the number of ?s in the genetic code of the organism
	 */
	public int numOfQs() {
		int total = 0;
		for (char c : this.chromosome.toCharArray()) {
			if (c == '?') {
				total += 1;
			}
		}
		return total;
	}

	/**
	 * ensures: gets and returns the length of the chromosome of the organism, the
	 * number of alleles in its genetic code
	 * 
	 * @return, the length of the chromosme
	 */
	public int length() {
		return chromosome.length();
	}

	/**
	 * ensures: flips the value of an allele at a specific index, from 0 to 1 or 1
	 * to 0
	 * 
	 * @param index, the index at which to flip the allele
	 */
	private void flipAllele(int index) {
		char c = chromosome.toCharArray()[index];
		char[] result = chromosome.toCharArray();
		if (c == '0') {
			c = '1';
		} else if (c == '1') {
			c = '0';
		}
		result[index] = c;
		this.chromosome = String.valueOf(result);
	}

	/**
	 * ensures: transforsms the string of the chromosome of the organism into a 2D
	 * array of integers, which helps in the graphics process. 1s become 1s. 0s
	 * become 0s, and ?s become -1s.
	 * 
	 * @return, the chromosome as an array of 2D integers
	 */
	public int[][] toIntAr() {
		int length = this.length();
		int rows = (int) Math.floor(Math.sqrt(length)); // should round down, ex: 20 -- r: 4, c: 5; 21 -- r: 4, c: 6, if
														// 25 -- r: 5, c: 5
		int cols = (int) Math.ceil((double) this.length() / (double) rows); // should round up
		int[][] result = new int[rows][cols];
		char[] chromArr = chromosome.toCharArray();

		for (int index = 0; index < length; index++) {
			int row = this.indexToRowCol(index, rows, cols)[0]; // -- 0 --
			int col = this.indexToRowCol(index, rows, cols)[1];

			int num = -1;
			if (chromArr[index] == '0') {
				num = 0;
			}
			if (chromArr[index] == '1') {
				num = 1;
			}
			if (chromArr[index] == '?') {
				num = -1;
			}
			result[row][col] = num;
		}

		return result;
	}

	/**
	 * ensures: mutates the chromosome. This means there is a chance for each allele
	 * in the genetic code to flip. This chance is the given rate which is taken to
	 * be a percent.
	 * 
	 * @param rate, the chance as a percent of each allele to flip
	 */
	public void mutatePercent(int rate) {
		char[] arr = chromosome.toCharArray();
		// Random r = new Random();
		for (int index = 0; index < this.length(); index++) {
			int chance = r.nextInt(100);
			if (chance < rate) {
				// flipAllele(index);
				if (arr[index] == '0') {
					arr[index] = '1';
				} else if (arr[index] == '1') {
					arr[index] = '0';
				}
			}
		}
		this.chromosome = String.valueOf(arr);
	}

	/**
	 * ensures: mutates the organism, having a chance to flip each allele of the
	 * organism. On average, this method flips a number of alleles equal to the
	 * given rate.
	 * 
	 * @param rate, the number of alleles that flip on average during mutation, the
	 *              given rate of mutation
	 */
	public void mutate(int rate) {
		// Random r = new Random();
		for (int i = 0; i < this.length(); i++) {
			int chance = r.nextInt(this.length());
			if (chance < rate) {
				flipAllele(i);
			}
		}
	}

	/**
	 * ensures: gets and returns the chromosme of the organism, its genetic code
	 * 
	 * @return, the chromosome
	 */
	public String getChromosome() {
		return this.chromosome;
	}

	/**
	 * ensures: draws the organism on a 2D graphic, deciding how small or large to
	 * draw it to fit the given height. The organism is represented by its genetic
	 * code arranged in a grid, 1s being represented as colorful boxes, 0s being
	 * represented as black boxes, and ?s being represented as white boxes.
	 * Sometimes the index of the allele is shown in the corner of this box that
	 * represents it.
	 * 
	 * @param g,      the graphics the organism is drawn on
	 * @param height, the height given in which the drawn organism will fit
	 */
	public void drawOn(Graphics2D g, int height) {
		// calculate side size
		int rows = (int) Math.sqrt(this.length());
		int cols = this.length() / rows;

		int boxSide = height / rows;

		// draw
		int[][] arr = this.toIntAr();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				// set color and determine color
				Color[] palette = this.getColorPalette(arr[row][col], col, row);
				g.setColor(palette[0]);

				// translate
				g.translate(col * boxSide, row * boxSide);

				// draw box
				g.fillRect(0, 0, boxSide, boxSide);

				// set index
				int index = rowColToIndex(row, col); // potentially -1
				String indexText = Integer.toString(index);// check that it gets it right...

				g.setColor(palette[1]);
				if (height > 30) {
					g.drawString(indexText, 5, boxSide - 5);
				}
				g.translate(-col * boxSide, -row * boxSide);
			}
		}

	}

	/**
	 * ensures: takes the index of an allele in the chromosome and returns the row
	 * and column it should be in.
	 * 
	 * @param index, the index of the allele.
	 * @param cols,  the number of columns in a 2D representation of the chromosome.
	 *               @return, the row and column pair at which the allele can be
	 *               found in a 2D representation of the chromosome.
	 */
	private int[] indexToRowCol(int index, int rows, int cols) {
		int row = index / cols;
		int col = (index - (row * cols));
		return new int[] { row, col };

	}

	/**
	 * ensures: gets the index of an allele in the genetic code based on the row and
	 * column given
	 * 
	 * @param row, the row the allele is in
	 * @param col, the column the allele is in
	 *             @return, the index of the allele in the chromosome
	 */
	private int rowColToIndex(int row, int col) {
		int rows = (int) Math.floor(Math.sqrt(chromosome.length()));
		int cols = (int) Math.ceil(chromosome.length() / rows);
		return row * cols + col;
	}

	/**
	 * ensures: returns the color palette for an allele, the first color being the
	 * background color for that box and the second color being that of the text
	 * which displays the index of the allele in the chromosome.
	 * 
	 * @param i,   the value of the allele as an integer. 1 is 1. 0 is 0. ? is -1.
	 * @param col, the column the allele is found in
	 * @param row, the row the allele is found in
	 *             @return, the color pallete, the first color being the background
	 *             color, the second being the text color.
	 */
	private Color[] getColorPalette(int i, int col, int row) {
		if (i == -1) {
			return new Color[] { Color.WHITE, Color.BLACK };
		}
		if (i == 0) {
			return new Color[] { Color.BLACK, Color.WHITE };
		}
		if (i == 1) {
			return new Color[] { colors[Math.abs((row + col) / 2)], Color.BLACK };

		}
		return new Color[] {};
	}

	/**
	 * ensures: flips an allele at a given x,y coordinate, calculating first which
	 * row and column this allele is in and, from this, getting the index of that
	 * allele. The allele is then flipped at that index.
	 * 
	 * @param x, the x coordinate of the allele to flip
	 * @param y, the y coordinate of the allele to flip
	 */
	public void flipAlleleCoord(int x, int y) {
		int rows = (int) Math.sqrt(this.length());
		int boxSide = this.HEIGHT / rows;
		int cols = this.length() / rows;

		if (x < cols * boxSide && y < HEIGHT) {

			int row = y / boxSide;
			int col = x / boxSide;

			int index = this.rowColToIndex(row, col);
			this.flipAllele(index);
		}
	}

	/**
	 * ensures: calculates and returns the fitness of an organism based on the
	 * fitness method in use
	 * 
	 * @return, the fitness of the organism
	 */
	public int fitness() {
		fitness = FitnessStrategyFactory.getFitnessStrategyOfType(fitnessType, numGens, constantFitness);
		int num = fitness.getFitness(chromosome);
		this.constantFitness = num;
		return num;
	}

	/**
	 * ensures: calculates the number of ones in the organism's genetic code which
	 * is the fitness of the organism if the fitness method is Num. of 1s or the
	 * number of 1s in the genetic code.
	 * 
	 * @return, the number of ones in the chromosome of the organism, the fitness if
	 * the fitness method is Num. of 1s
	 */
	public int numOf1s() {
		int sum = 0;

		for (char c : this.chromosome.toCharArray()) {
			if (c == '1') {
				sum += 1;
			}
		}

		return sum;
	}

	/**
	 * ensures: sets the constant fitness of the organism to -1, effectively
	 * resetting it so it can be determined again.
	 * 
	 */
	public void resetConstantFitness() {
		this.constantFitness = -1;
	}

	/**
	 * 
	 * ensures: creates and returns a new organism that is the crossover of the
	 * genetic code of this organism and that of another, given organism. The point
	 * at which this returned organisms code stops being this organisms code and is
	 * the other organisms code is randomly determined. In other words, the
	 * crossover point is randomly determined.
	 * 
	 * @param other, the organism with which this organism will create the new
	 *               organisms genetic code
	 *               @return, the new organism, its genetic code a crossover between
	 *               this organism and other, the other organism
	 */
	public Organism newCrossover(Organism other) {
		if (other.length() == this.length()) {

			// Random r = new Random();
			int crossoverPoint = r.nextInt(this.length());
			int whichFirst = r.nextInt(2);

			String first = "";
			String second = "";

			if (whichFirst == 0) {
				first = this.chromosome;
				second = other.getChromosome();
			} else {
				first = other.getChromosome();
				second = this.chromosome;
			}

			String result = "";
			result += first.substring(0, crossoverPoint);
			result += second.substring(crossoverPoint);

			return new Organism(result, this.fitness.getFitnessType(), r.getType());
		} else {
			return null;
		}

	}

	/**
	 * 
	 * ensures: compares this organism to another organism, arg0, based on their
	 * fitness. Determines which organism has the greater and lesser fitness or if
	 * both have the same fitness.
	 * 
	 * @param arg0, the other organism to which this organism is compared
	 *              @return, positive if this organisms fitness is greater, negative
	 *              if this organisms fitness os lesser, and zero if both organisms
	 *              have the same fitness.
	 */
	@Override
	public int compareTo(Organism arg0) {
		Organism toCompare = (Organism) arg0;
		int to = 0;
		int score = 0;
		to = toCompare.fitness();
		score = this.fitness();
		if (score > to) {
			// return positive if greater
			return 1;
		}
		if (score == to) {
			return 0; // if equal
		}
		if (score < to) {
			return -1; // negative number if less than
		}

		return 0;
	}

	/**
	 *
	 * ensures: sets the fitness method of the organisms which is used to decide how
	 * to determine the fitness of the organism from its genetic code
	 * 
	 * @param method, the name of the fitness method to set
	 */
	public void setFitnessMethod(FitnessType method) {
		this.fitnessType = method;
	}

	public FitnessType getFitnessType() {
		return this.fitness.getFitnessType();
	}

	public void setNumGens(int numGens) {
		this.numGens = numGens;
	}

	public RandomType getRandomType() {
		return r.getType();
	}
}
