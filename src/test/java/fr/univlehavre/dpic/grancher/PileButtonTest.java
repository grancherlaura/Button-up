package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import fr.univlehavre.dpic.grancher.PileButton.Button;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;
import static org.junit.Assert.*;

public class PileButtonTest 
{
	PileButton pile1;
	
	@Before
	public void setup()
	{
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(ROUGE, NOIRE, BLANCHE, ROUGE));
		pile1 = new PileButton(listeBoutons);
	}
	
	@Test
	public void addButtonTest()
	{
		ArrayList<Button> listeButtonsAttendue = new ArrayList<Button>(Arrays.asList(ROUGE, NOIRE, BLANCHE, ROUGE, BLANCHE));
		
		pile1.addButton(BLANCHE);
		ArrayList<Button> listeButtonsTrouvee = pile1.getListeButtons();
		
		assertEquals(listeButtonsAttendue, listeButtonsTrouvee);
	}
	
	@Test
	public void sameButtonTest()
	{
		boolean reponseTrouvee = pile1.sameButton(ROUGE);
		assertTrue(reponseTrouvee);
		
		boolean reponseTrouvee2 = pile1.sameButton(NOIRE);
		assertFalse(reponseTrouvee2);
		
		boolean reponseTrouvee3 = pile1.sameButton(BLANCHE);
		assertFalse(reponseTrouvee3);
	}
	
	@Test
	public void getSommetTest()
	{
		Button boutonAttendu = ROUGE;
		Button boutonTrouve = pile1.getSommet();
		
		assertEquals(boutonAttendu, boutonTrouve);
	}
	
	@Test
	public void getListeButtonsTest()
	{
		ArrayList<Button> listeButtonsAttendue = new ArrayList<Button>(Arrays.asList(ROUGE, NOIRE, BLANCHE, ROUGE));
		ArrayList<Button> listeButtonsTrouvee = pile1.getListeButtons();
		
		assertEquals(listeButtonsAttendue, listeButtonsTrouvee);
	}
}
