package mainApp;


/**
 * Class: MainApp
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 */
public class MainApp {
	/**
	 * ensures: sets up viewers
	 */
	private void runApp() {
		Views organismViewer = new OrganismViewer();
		organismViewer.setUpViewer();
		organismViewer.runApp();
		
		Views populationViewer = new PopulationViewer();
		populationViewer.setUpViewer();
		populationViewer.runApp();
	} // runApp

	/**
	 * ensures: runs the application
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();		
	} // main  

}