package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class OrganismVisualization {
    private Color[] colors = { new Color(255, 8, 1), new Color(255, 95, 1), new Color(255, 166, 1),
        new Color(253, 235, 1), new Color(102, 253, 59), new Color(13, 252, 150), new Color(1, 203, 228),
        new Color(4, 122, 252), new Color(150, 54, 254), new Color(217, 67, 255) };
    
    private String chromosome;

    public OrganismVisualization(String chromosome)
    {
        this.chromosome = chromosome;
    }

    /**
	 * ensures: draws the organism on a 2D graphic, deciding how small or large to
	 * draw it to fit the given height. The organism is represented by its genetic
	 * code arranged in a grid, 1s being represented as colorful boxes, 0s being
	 * represented as black boxes, and ?s being represented as white boxes.
	 * Sometimes the index of the allele is shown in the corner of this box that
	 * represents it.
	 * 
	 * @param setHeight
	 * 
	 * @param g,        the graphics the organism is drawn on
	 * @param height,   the height given in which the drawn organism will fit
	 */
	public void drawOn(Graphics2D g, int setHeight, JFrame frame) {
		// calculate side size
		int width = 0;
		int height = setHeight;
		if (height == 0) {
			width = (int) frame.getSize().getWidth() - 10;
			height = (int) frame.getSize().getHeight() - 145;
		}
		int rows = (int) Math.sqrt(this.length());
		int cols = this.length() / rows;

		int boxSide = height / rows;
		int boxSideWidth = height / rows;
		if (width != 0) {
			boxSideWidth = width / cols;
		}

		// draw
		int[][] arr = this.toIntAr();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				// set color and determine color
				Color[] palette = this.getColorPalette(arr[row][col], col, row);
				g.setColor(palette[0]);

				// translate
				g.translate(col * boxSideWidth, row * boxSide);

				// draw box
				g.fillRect(0, 0, boxSideWidth, boxSide);

				// set index
				int index = rowColToIndex(row, col); // potentially -1
				String indexText = Integer.toString(index);// check that it gets it right...

				g.setColor(palette[1]);
				if (height > 30) {
					g.drawString(indexText, 5, boxSide - 5);
				}
				g.translate(-col * boxSideWidth, -row * boxSide);
			}
		}

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

    public void setChromosome(String chromosome)
    {
        this.chromosome = chromosome;
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
	 * ensures: gets the index of an allele in the genetic code based on the row and
	 * column given
	 * 
	 * @param row, the row the allele is in
	 * @param col, the column the allele is in
	 *             @return, the index of the allele in the chromosome
	 */
	int rowColToIndex(int row, int col) {
		int rows = (int) Math.floor(Math.sqrt(chromosome.length()));
		int cols = (int) Math.ceil(chromosome.length() / rows);
		return row * cols + col;
	}
    
}
