package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;

import fr.univlehavre.dpic.grancher.PileButton.Button;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;
import static org.junit.Assert.*;

public class PileButtonTest 
{
	PileButton pile1, pile2;
	
	@Before
	public void setup()
	{
		ArrayList<Button> listeBoutons1 = new ArrayList<Button>(Arrays.asList(ROUGE, NOIR, BLANC, ROUGE));
		pile1 = new PileButton(listeBoutons1);
		
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR));
		pile2 = new PileButton(listeBoutons2);
	}
	
	@Test
	public void addButtonTest()
	{
		ArrayList<Button> listeButtonsAttendue = new ArrayList<Button>(Arrays.asList(ROUGE, NOIR, BLANC, ROUGE, BLANC));
		
		pile1.addButton(BLANC);
		ArrayList<Button> listeButtonsTrouvee = pile1.getListeButtons();
		
		assertEquals(listeButtonsAttendue, listeButtonsTrouvee);
	}
	
	@Test
	public void addPileButtonTest()
	{
		ArrayList<Button> listeButtonsAttendue = new ArrayList<Button>(Arrays.asList(ROUGE, NOIR, BLANC, ROUGE, BLANC, NOIR));
		
		pile1.addPileButton(pile2);
		ArrayList<Button> listeButtonsTrouvee = pile1.getListeButtons();
		
		assertEquals(listeButtonsAttendue, listeButtonsTrouvee);
	}
	
	@Test
	public void sameButtonTest()
	{
		boolean reponseTrouvee = pile1.sameButton(ROUGE);
		assertTrue(reponseTrouvee);
		
		boolean reponseTrouvee2 = pile1.sameButton(NOIR);
		assertFalse(reponseTrouvee2);
		
		boolean reponseTrouvee3 = pile1.sameButton(BLANC);
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
		ArrayList<Button> listeButtonsAttendue = new ArrayList<Button>(Arrays.asList(ROUGE, NOIR, BLANC, ROUGE));
		ArrayList<Button> listeButtonsTrouvee = pile1.getListeButtons();
		
		assertEquals(listeButtonsAttendue, listeButtonsTrouvee);
	}
	
	@Test
	public void toStringTest()
	{
		String resultatAttendu = "[ROUGE,BLANC,NOIR,ROUGE]";
		String resultatTrouve = pile1.toString();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
	}
}
