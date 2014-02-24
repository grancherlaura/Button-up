package fr.univlehavre.dpic.grancher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.*;

import static org.junit.Assert.*;

import fr.univlehavre.dpic.grancher.PileButton;
import fr.univlehavre.dpic.grancher.PileButton.Button;
import fr.univlehavre.dpic.grancher.Plateau;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;

public class PlateauTest 
{
	Plateau plateau;
	
	@Before
	public void setup()
	{
		plateau = new Plateau();
		Collections.sort(plateau.getListePiles());
	}
	
	@Test
	public void generePlateauAleatoireTest()
	{		
		Plateau plateau1 = new Plateau(new ArrayList<PileButton>());
		plateau1.genererPlateauAleatoire();
		
		Plateau plateau2 = new Plateau(new ArrayList<PileButton>());
		plateau2.genererPlateauAleatoire();
		
		ArrayList<PileButton> liste1 = plateau1.getListePiles();
		ArrayList<PileButton> liste2 = plateau2.getListePiles();
		
		boolean resultat = liste1.equals(liste2);
		assertFalse(resultat);	
		
		Collections.sort(liste1);
		Collections.sort(liste2);
		
		assertEquals(liste1,liste2);
	}
	
	@Test
	public void supprimerToutesLesPilesTest()
	{
		int resultatAttendu = 0;
		plateau.supprimerToutesLesPiles();
		int resultatTrouvee = plateau.tailleListe();
		
		assertEquals(resultatAttendu,resultatTrouvee);
	}
	
	@Test
	public void melangerPilesTest()
	{
		Plateau plateau2 = new Plateau();
		plateau2.melangerPiles();
		
		boolean resultat = plateau.getListePiles().equals(plateau2.getListePiles());
		
		assertFalse(resultat);		
	}
	
	@Test
	public void getListePilesTest()
	{
		ArrayList<PileButton> listeAttendue = new ArrayList<PileButton>(Arrays.asList(new PileButton(ROUGE), new PileButton(NOIR), new PileButton(BLANC)));
		
		Plateau up = new Plateau(listeAttendue);
		ArrayList<PileButton> listeTrouvee = up.getListePiles();
		
		assertEquals(listeAttendue, listeTrouvee);
	}
	
	@Test
	public void tailleListeTest()
	{
		int resultatAttendu = 9;
		int resultatTrouve = plateau.tailleListe();
		
		ArrayList<PileButton> liste = new ArrayList<PileButton>(Arrays.asList(new PileButton(ROUGE), new PileButton(NOIR), new PileButton(BLANC)));
		Plateau up = new Plateau(liste);
		
		int resultatAttendu2 = 3;
		int resultatTrouve2 = up.tailleListe();
		
		assertEquals(resultatAttendu, resultatTrouve);
		assertEquals(resultatAttendu2, resultatTrouve2);
	}

	
	@Test
	public void getPileTest()
	{		
		PileButton pileAttendue = new PileButton(BLANC);
		PileButton pileTrouvee = plateau.getPile(0);
		
		PileButton pileAttendue2 = new PileButton(ROUGE);
		PileButton pileTrouvee2 = plateau.getPile(3);
		
		PileButton pileAttendue3 = new PileButton(NOIR);
		PileButton pileTrouvee3 = plateau.getPile(8);
		
		assertEquals(pileAttendue,pileTrouvee);
		assertEquals(pileAttendue2,pileTrouvee2);
		assertEquals(pileAttendue3,pileTrouvee3);
	}
	
	@Test
	public void pileSuivanteTest()
	{
		int pileSuivanteAttendue = 5;
		int pileSuivanteTrouvee = plateau.pileSuivante(2,4);
		
		assertEquals(pileSuivanteAttendue,pileSuivanteTrouvee);
		
		int pileSuivanteAttendue2 = 0;
		int pileSuivanteTrouvee2 = plateau.pileSuivante(3,8);
		
		assertEquals(pileSuivanteAttendue2,pileSuivanteTrouvee2);
		
		int pileSuivanteAttendue3 = 3;
		int pileSuivanteTrouvee3 = plateau.pileSuivante(4,3);
		
		assertEquals(pileSuivanteAttendue3,pileSuivanteTrouvee3);
		
		int pileSuivanteAttendue4 = 8;
		int pileSuivanteTrouvee4 = plateau.pileSuivante(0,8);
		
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
		Plateau us = new Plateau(liste);
		
		boolean resultat1 = us.joueurRejoue(1);
		boolean resultat2 = us.joueurRejoue(0);
		boolean resultat3 = us.joueurRejoue(0);
		
		assertTrue(resultat1);
		assertFalse(resultat2);
		assertFalse(resultat3);
		
		PileButton pileBlanc1 = new PileButton(BLANC);
		PileButton pileBlanc2 = new PileButton(BLANC);
		PileButton pileBlanc3 = new PileButton(BLANC);
		PileButton pileRouge1 = new PileButton(ROUGE);
		PileButton pileRouge2 = new PileButton(ROUGE);
		PileButton pileRouge3 = new PileButton(ROUGE);
		PileButton pileNoir1 = new PileButton(NOIR);
		PileButton pileNoir2 = new PileButton(NOIR);
		PileButton pileNoir3 = new PileButton(NOIR);
		
		ArrayList<PileButton> listePiles = new ArrayList<PileButton>(Arrays.asList(pileRouge1,
																				   pileNoir1,
																				   pileBlanc1,
																				   pileBlanc2,
																				   pileBlanc3,
																				   pileNoir2,
																				   pileNoir3,
																				   pileRouge2,
																				   pileRouge3));
		Plateau us2 = new Plateau(listePiles);
		
		us2.semerTouteLaPile(2);
		us2.semerTouteLaPile(2);
		us2.semerTouteLaPile(2);
		us2.semerTouteLaPile(2);
		us2.semerTouteLaPile(2);
		
		boolean resultat = us2.semerTouteLaPile(2);
		
		assertTrue(resultat);
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
		Plateau plateau2 = new Plateau(listePile);
		
		// on seme pile2 sur pile3
		plateau2.poser(1,2);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue3 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = plateau2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = plateau2.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve3 = plateau2.getPile(2).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
		assertEquals(listeAttendue3,listeTrouve3);
		
		int ind = plateau2.pileSuivante(1,2);
		// on seme pile2 sur pile1 donc tout va sur pile1
		plateau2.poser(1,ind);
		
		ArrayList<Button> listeAttendue4 = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue5 = new ArrayList<Button>();
		ArrayList<Button> listeAttendue6 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve4 = plateau2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve5 = plateau2.getPile(1).getListeButtons();
		ArrayList<Button> listeTrouve6 = plateau2.getPile(2).getListeButtons();
		
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
		Plateau plateau2 = new Plateau(listePile);
		
		// on seme pile2
		plateau2.semerTouteLaPile(1);
		
		ArrayList<Button> listeAttendue = new ArrayList<Button>(Arrays.asList(NOIR, BLANC, ROUGE, ROUGE));
		ArrayList<Button> listeAttendue2 = new ArrayList<Button>(Arrays.asList(BLANC, NOIR, NOIR));
		
		ArrayList<Button> listeTrouve = plateau2.getPile(0).getListeButtons();
		ArrayList<Button> listeTrouve2 = plateau2.getPile(1).getListeButtons();
		
		assertEquals(listeAttendue,listeTrouve);
		assertEquals(listeAttendue2,listeTrouve2);
	}
	
	@Test
	public void toStringTest()
	{
		String chaineAttendue = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>";
		String chaineTrouvee = plateau.toString();
		
		assertEquals(chaineAttendue,chaineTrouvee);
	}
}
