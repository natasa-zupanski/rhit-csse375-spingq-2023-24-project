package mainApp;

public class OrganismUtilites {
    private String chromosome;

    public OrganismUtilites(String chromosome){
        this.chromosome = chromosome;
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

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }
}
