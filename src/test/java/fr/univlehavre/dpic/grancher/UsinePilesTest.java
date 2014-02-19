package fr.univlehavre.dpic.grancher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.*;

import static org.junit.Assert.*;

import fr.univlehavre.dpic.grancher.PileButton;
import fr.univlehavre.dpic.grancher.PileButton.Button;
import fr.univlehavre.dpic.grancher.UsinePiles;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;

public class UsinePilesTest 
{
	UsinePiles usine;
	
	@Before
	public void setup()
	{
		usine = new UsinePiles();
		Collections.sort(usine.getListePiles());
	}
	
	@Test
	public void getListePilesTest()
	{
		ArrayList<PileButton> listeAttendue = new ArrayList<PileButton>(Arrays.asList(new PileButton(ROUGE), new PileButton(NOIR), new PileButton(BLANC)));
		
		UsinePiles up = new UsinePiles(listeAttendue);
		ArrayList<PileButton> listeTrouvee = up.getListePiles();
		
		assertEquals(listeAttendue, listeTrouvee);
	}
	
	@Test
	public void tailleListeTest()
	{
		int resultatAttendu = 9;
		int resultatTrouve = usine.tailleListe();
		
		ArrayList<PileButton> liste = new ArrayList<PileButton>(Arrays.asList(new PileButton(ROUGE), new PileButton(NOIR), new PileButton(BLANC)));
		UsinePiles up = new UsinePiles(liste);
		
		int resultatAttendu2 = 3;
		int resultatTrouve2 = up.tailleListe();
		
		assertEquals(resultatAttendu, resultatTrouve);
		assertEquals(resultatAttendu2, resultatTrouve2);
	}
	
	@Test
	public void estFinListeTest()
	{
		boolean resultat1 = usine.estFinListe(8);
		boolean resultat2 = usine.estFinListe(7);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
	
	@Test
	public void estRevenuDepartTest()
	{
		boolean resultat = usine.estRevenuDepart(6, 5);
		assertTrue(resultat);
		
		boolean resultat2 = usine.estRevenuDepart(4, 7);
		assertFalse(resultat2);
	}
	
	@Test
	public void neContientPasEspionTest()
	{
		boolean resultat1 = usine.neContientPasEspion(5);
		boolean resultat2 = usine.neContientPasEspion(1);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
	
	@Test
	public void getPileTest()
	{		
		PileButton pileAttendue = new PileButton(BLANC);
		PileButton pileTrouvee = usine.getPile(0);
		
		PileButton pileAttendue2 = new PileButton(ROUGE);
		PileButton pileTrouvee2 = usine.getPile(3);
		
		PileButton pileAttendue3 = new PileButton(NOIR);
		PileButton pileTrouvee3 = usine.getPile(8);
		
		assertEquals(pileAttendue,pileTrouvee);
		assertEquals(pileAttendue2,pileTrouvee2);
		assertEquals(pileAttendue3,pileTrouvee3);
	}
	
	@Test
	public void pileInvalideTest()
	{
		boolean resultat1 = usine.pileInvalide(-2);
		boolean resultat2 = usine.pileInvalide(9);
		boolean resultat3 = usine.pileInvalide(3);
		
		assertTrue(resultat1);
		assertTrue(resultat2);
		assertFalse(resultat3);
	}
	
	@Test
	public void pileSuivanteTest()
	{
		int pileSuivanteAttendue = 5;
		int pileSuivanteTrouvee = usine.pileSuivante(2,4);
		
		assertEquals(pileSuivanteAttendue,pileSuivanteTrouvee);
		
		int pileSuivanteAttendue2 = 0;
		int pileSuivanteTrouvee2 = usine.pileSuivante(3,8);
		
		assertEquals(pileSuivanteAttendue2,pileSuivanteTrouvee2);
		
		int pileSuivanteAttendue3 = 3;
		int pileSuivanteTrouvee3 = usine.pileSuivante(4,3);
		
		assertEquals(pileSuivanteAttendue3,pileSuivanteTrouvee3);
		
		int pileSuivanteAttendue4 = 8;
		int pileSuivanteTrouvee4 = usine.pileSuivante(0,8);
		
		assertEquals(pileSuivanteAttendue4,pileSuivanteTrouvee4);
	}
	
	@Test
	public void joueurRejoueTest()
	{
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(NOIR, BLANC));
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		
		PileButton pile1 = new PileButton(listeBoutons);
		PileButton pile2 = new PileButton(listeBoutons2);
		
		ArrayList<PileButton> liste = new ArrayList<PileButton>(Arrays.asList(pile1, pile2));
		UsinePiles us = new UsinePiles(liste);
		
		boolean resultat1 = us.joueurRejoue(1, false);
		boolean resultat2 = us.joueurRejoue(0, false);
		boolean resultat3 = us.joueurRejoue(0, true);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
		assertFalse(resultat3);
	}
	
	@Test
	public void poserTest()
	{
		// initialisation du jeu
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeBoutons3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR));
		
		PileButton pile1 = new PileButton(listeBoutons);
		PileButton pile2 = new PileButton(listeBoutons2);
		PileButton pile3 = new PileButton(listeBoutons3);
			
		ArrayList<PileButton> listePile = new ArrayList<PileButton>(Arrays.asList(pile1,pile2,pile3));
		UsinePiles usine2 = new UsinePiles(listePile);
		
		// on seme pile2 sur pile3
		usine2.poser(1,2);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = usine2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = usine2.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve3 = usine2.getPile(2).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
		assertEquals(listeAttendue3,listeTrouve3);
		
		// on seme pile2 sur pile1 donc tout va sur pile1
		usine2.poser(1,0);
		
		ArrayList<Button> listeAttendue4 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue5 = new ArrayList<Button>();
		ArrayList<Button> listeAttendue6 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve4 = usine2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve5 = usine2.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve6 = usine2.getPile(2).getListeButtons();
		
		assertEquals(listeAttendue4,listeTrouve4);
		assertEquals(listeAttendue5,listeTrouve5);
		assertEquals(listeAttendue6,listeTrouve6);
	}
	
	@Test
	public void semerTouteLaPileTest()
	{
		// initialisation des piles
		ArrayList<Button> listeBoutons = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeBoutons2 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeBoutons3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR));
		
		PileButton pile1 = new PileButton(listeBoutons);
		PileButton pile2 = new PileButton(listeBoutons2);
		PileButton pile3 = new PileButton(listeBoutons3);
			
		ArrayList<PileButton> listePile = new ArrayList<PileButton>(Arrays.asList(pile1,pile2,pile3));
		UsinePiles usine2 = new UsinePiles(listePile);
		
		// on seme pile2
		usine2.semerTouteLaPile(1);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = usine2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = usine2.getPile(1).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
	}
	
	@Test
	public void toStringTest()
	{
		String chaineAttendue = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>";
		String chaineTrouvee = usine.toString();
		
		assertEquals(chaineAttendue,chaineTrouvee);
	}
}
