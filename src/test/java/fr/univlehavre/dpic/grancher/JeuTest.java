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
	public void afficherMessageTest()
	{
		String resultatAttendu = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>\nJoueur ROUGE\nPile choisie : ";
		String resultatTrouve = j.afficherMessage();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
		j.changerJoueur();
		
		String resultatAttendu2 = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>\nJoueur NOIR\nPile choisie : ";
		String resultatTrouve2 = j.afficherMessage();
		
		assertEquals(resultatAttendu2, resultatTrouve2);
	}
	
	@Test
	public void afficherNbPointsTest()
	{
		String resultatAttendu = "\n==================================\nJoueur rouge : 0, Joueur noir : 0\n==================================\n";
		String resultatTrouve = j.afficherNbPoints();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
		for(int i=0; i<8; i++)
		{
			usine.semerTouteLaPile(0);
		}

		j.compterPoints();
		
		String resultatAttendu2 = "\n==================================\nJoueur rouge : 0, Joueur noir : 2\n==================================\n";
		String resultatTrouve2 = j.afficherNbPoints();
		
		assertEquals(resultatAttendu2, resultatTrouve2);		
	}
	
	@Test
	public void afficherPileFinaleTest()
	{
		String resultatAttendu = "\nPile finale\n9 NOIR\n8 NOIR\n7 NOIR\n6 ROUGE\n5 ROUGE\n4 ROUGE\n3 BLANC\n2 BLANC\n1 BLANC\n";
		
		ArrayList<Button> listeButtons = new ArrayList<Button>(Arrays.asList(BLANC,BLANC,BLANC,ROUGE,ROUGE,ROUGE,NOIR,NOIR,NOIR));
		PileButton pile = new PileButton(listeButtons);
		
		ArrayList<PileButton> listePile = new ArrayList<PileButton>(Arrays.asList(pile));
		UsinePiles us = new UsinePiles(listePile);
		
		Jeu jeu = new Jeu(us,0,0);
		
		String resultatTrouve = jeu.afficherPileFinale();
		
		assertEquals(resultatAttendu,resultatTrouve);		
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
	
	@Test
	public void messageGagnantTest()
	{
		for(int j=0; j<8; j++)
		{
			for(int i=0; i<8; i++)
			{
				usine.semerTouteLaPile(0);
			}
			
			usine = new UsinePiles();
		}
		
		String messageAttendu = "\nJoueur gagnant : NOIR";
		String messageTrouve = j.messageGagnant();
		
		assertEquals(messageAttendu, messageTrouve);
	}
	
	@Test
	public void messageErreurTest()
	{
		String messageAttendu = "\nVeuillez entrer un nombre entre 1 et 8 d'une pile qui contient un blanc : ";
		
		usine.semerTouteLaPile(0);
		String messageTrouve = j.messageErreur();
		
		assertEquals(messageAttendu, messageTrouve);
	}
}
