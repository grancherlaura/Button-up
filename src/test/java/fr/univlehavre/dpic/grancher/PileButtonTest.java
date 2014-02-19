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
	public void deuxMemesButtonsTest()
	{
		pile1.addButton(ROUGE);
		boolean reponseTrouvee = pile1.deuxMemesButtons();
		assertTrue(reponseTrouvee);
		
		pile2.addButton(NOIR);
		boolean reponseTrouvee2 = pile2.deuxMemesButtons();
		assertTrue(reponseTrouvee2);
		
		pile1.addButton(BLANC);
		boolean reponseTrouvee3 = pile1.deuxMemesButtons();
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
	public void estVideTest()
	{
		pile1.getListeButtons().clear();
		boolean resultat = pile1.estVide();
		
		assertTrue(resultat);
		
		boolean resultat2 = pile2.estVide();
		
		assertFalse(resultat2);	
	}
	
	@Test
	public void getButtonDessousTest()
	{
		Button boutonAttendu = ROUGE;
		Button boutonTrouve = pile1.getButtonDessous();
		
		assertEquals(boutonAttendu,boutonTrouve);
		
		Button boutonAttendu2 = BLANC;
		Button boutonTrouve2 = pile2.getButtonDessous();
		
		assertEquals(boutonAttendu2,boutonTrouve2);	
	}
	
	@Test
	public void semerTest()
	{
		ArrayList<Button> listePile1Attendue = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE));
		ArrayList<Button> listePile2Attendue = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, ROUGE));
		
		pile1.semer(pile2,false);
		
		ArrayList<Button> listePile1Trouvee = pile1.getListeButtons();
		ArrayList<Button> listePile2Trouvee = pile2.getListeButtons();
		
		assertEquals(listePile1Attendue,listePile1Trouvee);
		assertEquals(listePile2Attendue,listePile2Trouvee);
		
		ArrayList<Button> listePile1Attendue2 = new ArrayList<Button>();
		ArrayList<Button> listePile2Attendue2 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, ROUGE,NOIR, BLANC, ROUGE));
		
		pile1.semer(pile2,true);
		
		ArrayList<Button> listePile1Trouvee2 = pile1.getListeButtons();
		ArrayList<Button> listePile2Trouvee2 = pile2.getListeButtons();
		
		assertEquals(listePile1Attendue2,listePile1Trouvee2);
		assertEquals(listePile2Attendue2,listePile2Trouvee2);
	}
	
	@Test
	public void contientEspionTest()
	{
		boolean resultat1 = pile1.contientEspion();
		assertTrue(resultat1);
		
		PileButton pile3 = new PileButton(ROUGE);
		pile3.addButton(NOIR);
		
		boolean resultat2 = pile3.contientEspion();
		assertFalse(resultat2);
	}
	
	@Test
	public void toStringTest()
	{
		String resultatAttendu = "[ROUGE,NOIR,BLANC,ROUGE]";
		String resultatTrouve = pile1.toString();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
	}
}
