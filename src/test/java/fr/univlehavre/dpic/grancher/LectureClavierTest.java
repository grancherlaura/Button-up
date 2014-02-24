package fr.univlehavre.dpic.grancher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LectureClavierTest 
{
	LectureClavier clavier;
	Affichage affich;
	
	@Before
	public void setup()
	{
		affich = new Affichage(new Plateau(), new Joueurs());
		clavier = new LectureClavier(affich);
	}
	
	@Test
	public void convertirEntierTest()
	{
		int resultatAttendu1 = 2;
		int resultatTrouve1 = clavier.convertirEntier("3");
		
		int resultatAttendu2 = -1;
		int resultatTrouve2 = clavier.convertirEntier("0");
		
		int resultatAttendu3 = -1;
		int resultatTrouve3 = clavier.convertirEntier("azerty");
		
		assertEquals(resultatAttendu1, resultatTrouve1);
		assertEquals(resultatAttendu2, resultatTrouve2);
		assertEquals(resultatAttendu3, resultatTrouve3);
	}

	@Test
	public void reponseValideTest()
	{
		boolean resultat1 = clavier.reponseValide("y");
		boolean resultat2 = clavier.reponseValide("n");
		boolean resultat3 = clavier.reponseValide("tgded");
		
		assertTrue(resultat1);
		assertTrue(resultat2);
		assertFalse(resultat3);
	}	
}
