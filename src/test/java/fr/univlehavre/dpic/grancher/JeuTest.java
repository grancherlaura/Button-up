package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.*;
import static org.junit.Assert.*;

import fr.univlehavre.dpic.grancher.PileButton.Button;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;

public class JeuTest 
{
	Jeu j;
	
	@Before
	public void setup()
	{
		j = new Jeu();
		Collections.sort(j.getListePile());
	}
	
	@Test
	public void revenirDebutListeTest()
	{
		boolean resultat = j.revenirDebutListe(8);
		assertTrue(resultat);
		
		boolean resultat2 = j.revenirDebutListe(7);
		assertFalse(resultat2);			
	}
	
	@Test
	public void revenirPileDepart()
	{
		boolean resultat = j.revenirPileDepart(6, 5);
		assertTrue(resultat);
		
		boolean resultat2 = j.revenirPileDepart(4, 7);
		assertFalse(resultat2);
	}
	
	@Test
	public void pileSuivanteTest()
	{
		int pileSuivanteAttendue = 5;
		int pileSuivanteTrouvee = j.pileSuivante(2,4);
		
		assertEquals(pileSuivanteAttendue,pileSuivanteTrouvee);
		
		int pileSuivanteAttendue2 = 0;
		int pileSuivanteTrouvee2 = j.pileSuivante(3,8);
		
		assertEquals(pileSuivanteAttendue2,pileSuivanteTrouvee2);
		
		int pileSuivanteAttendue3 = 3;
		int pileSuivanteTrouvee3 = j.pileSuivante(4,3);
		
		assertEquals(pileSuivanteAttendue3,pileSuivanteTrouvee3);
	}
	
	@Test
	public void getPileTest()
	{		
		PileButton pileAttendue = new PileButton(BLANC);
		PileButton pileTrouvee = j.getPile(1);
		
		PileButton pileAttendue2 = new PileButton(ROUGE);
		PileButton pileTrouvee2 = j.getPile(3);
		
		PileButton pileAttendue3 = new PileButton(NOIR);
		PileButton pileTrouvee3 = j.getPile(8);
		
		assertEquals(pileAttendue,pileTrouvee);
		assertEquals(pileAttendue2,pileTrouvee2);
		assertEquals(pileAttendue3,pileTrouvee3);
	}
	
	@Test
	public void poserTest()
	{
		// Piles au départ
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeBoutons3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR));
		
		PileButton pile1 = new PileButton(listeBoutons);
		PileButton pile2 = new PileButton(listeBoutons2);
		PileButton pile3 = new PileButton(listeBoutons3);
			
		ArrayList<PileButton> listePile = new ArrayList<PileButton>(Arrays.asList(pile1,pile2,pile3));
		Jeu jeu = new Jeu(listePile);
		
		// on seme pile2 sur pile3
		jeu.poser(1,2);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = jeu.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = jeu.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve3 = jeu.getPile(2).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
		assertEquals(listeAttendue3,listeTrouve3);
		
		// on seme pile2 sur pile1 donc tout va sur pile1
		jeu.poser(1,0);
		
		ArrayList<Button> listeAttendue4 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue5 = new ArrayList<Button>();
		ArrayList<Button> listeAttendue6 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve4 = jeu.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve5 = jeu.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve6 = jeu.getPile(2).getListeButtons();
		
		assertEquals(listeAttendue4,listeTrouve4);
		assertEquals(listeAttendue5,listeTrouve5);
		assertEquals(listeAttendue6,listeTrouve6);
	}
	
	@Test
	public void poserTouteLaPileTest()
	{
		// Piles au départ
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeBoutons3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR));
		
		PileButton pile1 = new PileButton(listeBoutons);
		PileButton pile2 = new PileButton(listeBoutons2);
		PileButton pile3 = new PileButton(listeBoutons3);
			
		ArrayList<PileButton> listePile = new ArrayList<PileButton>(Arrays.asList(pile1,pile2,pile3));
		Jeu jeu = new Jeu(listePile);
		
		// on seme pile2
		jeu.semerTouteLaPile(1);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = jeu.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = jeu.getPile(1).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
	}
	
	@Test
	public void toStringTest()
	{
		String resultatAttendu = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>";
		
		Collections.sort(j.getListePile());
		String resultatTrouve = j.toString();
		
		assertEquals(resultatAttendu, resultatTrouve);
	}
}
