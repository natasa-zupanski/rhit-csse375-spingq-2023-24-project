package tests;

import static org.junit.Assert.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.junit.Test;

import mainApp.*;

public class ViewTest {
    @Test
	public void testFittestOrganism () {
        FittestOrganismViewer view = new FittestOrganismViewer();
        view.setUpViewer();
        assertEquals(JFrame.DISPOSE_ON_CLOSE, view.getFrame().getDefaultCloseOperation());
        assertTrue(view.getFrame().getLayout() instanceof BorderLayout);
	}

    @Test
	public void testGeneration () {
        GenerationViewer view = new GenerationViewer();
        view.setUpViewer();
        assertEquals(JFrame.DISPOSE_ON_CLOSE, view.getFrame().getDefaultCloseOperation());
        assertTrue(view.getFrame().getLayout() instanceof BorderLayout);
	}

    @Test
	public void testPopulation () {
        PopulationViewer view = new PopulationViewer();
        view.setUpViewer();
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getFrame().getDefaultCloseOperation());
        assertTrue(view.getFrame().getLayout() instanceof BorderLayout);
	}

    @Test
	public void testOrganism () {
        OrganismViewer view = new OrganismViewer();
        view.setUpViewer();
        assertEquals(JFrame.DISPOSE_ON_CLOSE, view.getFrame().getDefaultCloseOperation());
        assertTrue(view.getFrame().getLayout() instanceof BorderLayout);
	}
}
