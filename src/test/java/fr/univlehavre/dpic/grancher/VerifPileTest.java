package fr.univlehavre.dpic.grancher;
import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import fr.univlehavre.dpic.grancher.PlateauTest;
import fr.univlehavre.dpic.grancher.VerifPile;

public class VerifPileTest 
{
	VerifPile verif;
	
	@Before
	public void setup()
	{
		Plateau usine = new Plateau();
		Collections.sort(usine.getListePiles());
		verif = new VerifPile(usine);
	}
	
	@Test
	public void estFinListeTest()
	{
		boolean resultat1 = verif.estFinListe(8);
		boolean resultat2 = verif.estFinListe(7);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
	
	@Test
	public void estRevenuDepartTest()
	{
		boolean resultat = verif.estRevenuDepart(6, 5);
		assertTrue(resultat);
		
		boolean resultat2 = verif.estRevenuDepart(4, 7);
		assertFalse(resultat2);
	}
	
	@Test
	public void neContientPasEspionTest()
	{
		boolean resultat1 = verif.neContientPasEspion(5);
		boolean resultat2 = verif.neContientPasEspion(1);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
	
	@Test
	public void pileInvalideTest()
	{
		boolean resultat1 = verif.pileInvalide(-2);
		boolean resultat2 = verif.pileInvalide(9);
		boolean resultat3 = verif.pileInvalide(3);
		
		assertTrue(resultat1);
		assertTrue(resultat2);
		assertFalse(resultat3);
	}
	
	@Test
	public void pileValideTest()
	{
		boolean resultat1 = verif.pileValide(-2);
		boolean resultat2 = verif.pileValide(9);
		boolean resultat3 = verif.pileValide(3);
		
		assertFalse(resultat1);
		assertFalse(resultat2);
		assertTrue(resultat3);
	}

	@Test
	public void peutRevenirDebutListeTest()
	{
		boolean resultat1 = verif.peutRevenirDebutListe(2,8);
		boolean resultat2 = verif.peutRevenirDebutListe(2,6);
		boolean resultat3 = verif.peutRevenirDebutListe(0,8);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
		assertFalse(resultat3);
	}
	
	@Test
	public void peutPoserSurSuivanttest()
	{
		boolean resultat1 = verif.peutPoserSurSuivant(2,4);
		boolean resultat2 = verif.peutPoserSurSuivant(2,8);
		boolean resultat3 = verif.peutPoserSurSuivant(4,3);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
		assertFalse(resultat3);
	}
}
