package fr.univlehavre.dpic.grancher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JoueursTest
{
	Joueurs joueurs;
	
	@Before
	public void setup()
	{
		joueurs = new Joueurs();
	}
	
	@Test
	public void changerJoueurTest()
	{
		int tourJoueurAttendu = 2;
		joueurs.changerJoueur();
		int tourJoueurTrouve = joueurs.getTourJoueur();
		
		assertEquals(tourJoueurAttendu, tourJoueurTrouve);
		
		int tourJoueurAttendu2 = 1;
		joueurs.changerJoueur();
		int tourJoueurTrouve2 = joueurs.getTourJoueur();
		
		assertEquals(tourJoueurAttendu2, tourJoueurTrouve2);		
	}
	
	@Test
	public void couleurJoueurTest()
	{
		String couleurAttendue = "ROUGE";		
		String couleurTrouvee = joueurs.couleurJoueur(1);
		
		assertEquals(couleurAttendue, couleurTrouvee);
		
		String couleurAttendue2 = "NOIR";		
		String couleurTrouvee2 = joueurs.couleurJoueur(2);
		
		assertEquals(couleurAttendue2, couleurTrouvee2);
	}
	
	@Test
	public void getGagnantTest()
	{
		Joueurs joueurs2 = new Joueurs(5,16);
		String joueurAttendu = "NOIR";
		String joueurTrouve = joueurs2.getGagnant();
		
		assertEquals(joueurAttendu, joueurTrouve);
		
		Joueurs joueurs3 = new Joueurs(15,14);
		String joueurAttendu2 = "ROUGE";
		String joueurTrouve2 = joueurs3.getGagnant();
		
		assertEquals(joueurAttendu2, joueurTrouve2);
	}
	
	@Test
	public void getDernierPerdantTest()
	{
		Jeu j = new Jeu();
		
		for(int i=0; i<8; i++)
		{
			j.getUsine().semerTouteLaPile(0);
		}
		
		String resultatAttendu = "ROUGE";
		String resultatTrouve = j.getJoueurs().getDernierPerdant();
		
		assertEquals(resultatAttendu, resultatTrouve);
	}
	
	@Test
	public void getJoueurCourantTest()
	{
		String joueurAttendu = "ROUGE";
		String joueurTrouve = joueurs.getJoueurCourant();
		
		assertEquals(joueurAttendu, joueurTrouve);
		
		joueurs.changerJoueur();
		
		String joueurAttendu2 = "NOIR";
		String joueurTrouve2 = joueurs.getJoueurCourant();
		
		assertEquals(joueurAttendu2, joueurTrouve2);
	}
	
	@Test
	public void addNbPointsJoueurRougeTest()
	{
		Joueurs j = new Joueurs(5,10);
		
		int resultatAttendu = 7;
		j.addNbPointsJoueurRouge(2);
		int resultatTrouve = j.getNbPointsJoueurRouge();
		assertEquals(resultatAttendu, resultatTrouve);
	}
	
	@Test
	public void addNbPointsJoueurNoirTest()
	{
		Joueurs j = new Joueurs(5,10);
		
		int resultatAttendu = 14;
		j.addNbPointsJoueurNoir(4);
		int resultatTrouve = j.getNbPointsJoueurNoir();
		assertEquals(resultatAttendu, resultatTrouve);
	}
	
	@Test
	public void getNbPointsJoueurRougeTest()
	{
		Joueurs j = new Joueurs(5,10);
		
		int resultatAttendu = 5;
		int resultatTrouve = j.getNbPointsJoueurRouge();
		assertEquals(resultatAttendu, resultatTrouve);
	}
	
	@Test
	public void getNbPointsJoueurNoirTest()
	{
		Joueurs j = new Joueurs(5,10);
		
		int resultatAttendu = 10;
		int resultatTrouve = j.getNbPointsJoueurNoir();
		assertEquals(resultatAttendu, resultatTrouve);
		
	}
	
	@Test
	public void getTourJoueurTest()
	{
		int resultatAttendu = 1;
		int resultatTrouve = joueurs.getTourJoueur();
		
		joueurs.changerJoueur();
		int resultatAttendu2 = 2;
		int resultatTrouve2 = joueurs.getTourJoueur();
		
		assertEquals(resultatAttendu, resultatTrouve);
		assertEquals(resultatAttendu2, resultatTrouve2);
	}
	
	@Test
	public void existeGagnantTest()
	{
		Joueurs joueurs = new Joueurs(5,16);
		Joueurs joueurs2 = new Joueurs(5,14);
		
		boolean resultat1 = joueurs.existeGagnant();
		boolean resultat2 = joueurs2.existeGagnant();
		
		assertTrue(resultat1);
		assertFalse(resultat2);
	}
}
