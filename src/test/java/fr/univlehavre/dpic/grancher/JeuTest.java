package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.*;

import fr.univlehavre.dpic.grancher.PileButton.Button;
import static org.junit.Assert.*;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;

public class JeuTest 
{
	Jeu j;
	UsinePiles usine;
	
	@Before
	public void setup()
	{
		j = new Jeu();
		usine = j.getUsine();
		Collections.sort(usine.getListePiles());
	}
	
	@Test
	public void changerJoueurTest()
	{
		int tourJoueurAttendu = 2;
		j.changerJoueur();
		int tourJoueurTrouve = j.getTourJoueur();
		
		assertEquals(tourJoueurAttendu, tourJoueurTrouve);
		
		int tourJoueurAttendu2 = 1;
		j.changerJoueur();
		int tourJoueurTrouve2 = j.getTourJoueur();
		
		assertEquals(tourJoueurAttendu2, tourJoueurTrouve2);		
	}
	
	@Test
	public void couleurJoueurTest()
	{
		String couleurAttendue = "ROUGE";		
		String couleurTrouvee = j.couleurJoueur(1);
		
		assertEquals(couleurAttendue, couleurTrouvee);
		
		String couleurAttendue2 = "NOIR";		
		String couleurTrouvee2 = j.couleurJoueur(2);
		
		assertEquals(couleurAttendue2, couleurTrouvee2);
	}
	
	@Test
	public void continuerMancheTest()
	{
		PileButton pile = new PileButton(ROUGE);
		ArrayList<PileButton> listePile = new ArrayList<PileButton>();
		listePile.add(pile);
		
		UsinePiles usine2 = new UsinePiles(listePile);
		Jeu j2 = new Jeu(usine2,0,0);
		
		boolean resultat1 = j2.continuerManche();
		assertFalse(resultat1);
		
		PileButton pile2 = new PileButton(NOIR);
		listePile.add(pile2);
		usine2 = new UsinePiles(listePile);
		
		boolean resultat2 = j2.continuerManche();
		assertTrue(resultat2);		
	}
	
	@Test
	public void convertirEntierTest()
	{
		int resultatAttendu1 = 2;
		int resultatTrouve1 = j.convertirEntier("3");
		
		int resultatAttendu2 = -1;
		int resultatTrouve2 = j.convertirEntier("0");
		
		int resultatAttendu3 = -1;
		int resultatTrouve3 = j.convertirEntier("azerty");
		
		assertEquals(resultatAttendu1, resultatTrouve1);
		assertEquals(resultatAttendu2, resultatTrouve2);
		assertEquals(resultatAttendu3, resultatTrouve3);
	}

	@Test
	public void compterPointsTest()
	{		
		for(int i=0; i<8; i++)
		{
			usine.semerTouteLaPile(0);
		}
		
		j.compterPoints();
		
		int nbPointsJoueurNoirAttendu = 2;
		int nbPointsJoueurRougeAttendu = 0;
		
		int nbPointsJoueurNoirTrouve = j.getNbPointsJoueurNoir();
		int nbPointsJoueurRougeTrouve = j.getNbPointsJoueurRouge();
		
		assertEquals(nbPointsJoueurNoirAttendu, nbPointsJoueurNoirTrouve);
		assertEquals(nbPointsJoueurRougeAttendu, nbPointsJoueurRougeTrouve);
	}
	
	@Test
	public void existeGagnantTest()
	{
		Jeu jeu = new Jeu(new UsinePiles(),5,16);
		Jeu jeu2 = new Jeu(new UsinePiles(),5,14);
		
		boolean resultat1 = jeu.existeGagnant();
		boolean resultat2 = jeu2.existeGagnant();
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
	
	@Test
	public void getTourTest()
	{
		int resultatAttendu = 1;
		int resultatTrouve = j.getTourJoueur();
		
		j.changerJoueur();
		int resultatAttendu2 = 2;
		int resultatTrouve2 = j.getTourJoueur();
		
		assertEquals(resultatAttendu, resultatTrouve);
		assertEquals(resultatAttendu2, resultatTrouve2);
	}
	

	@Test
	public void getNbPointsJoueurRougeTest()
	{
		Jeu jeu = new Jeu(new UsinePiles(), 5, 8);
		
		int resultatAttendu = 5;
		int resultatTrouve = jeu.getNbPointsJoueurRouge();
		assertEquals(resultatAttendu, resultatTrouve);
	}
	
	@Test
	public void getNbPointsJoueurNoirTest()
	{
		Jeu jeu = new Jeu(new UsinePiles(), 5, 8);
		
		int resultatAttendu = 8;
		int resultatTrouve = jeu.getNbPointsJoueurNoir();
		assertEquals(resultatAttendu, resultatTrouve);
		
	}
	
	@Test
	public void getUsineTest()
	{
		UsinePiles usAttendue = new UsinePiles();
		Jeu jeu = new Jeu(usAttendue, 5, 8);
		
		UsinePiles usTrouvee = jeu.getUsine();
		
		assertEquals(usAttendue, usTrouvee);	
	}
}
