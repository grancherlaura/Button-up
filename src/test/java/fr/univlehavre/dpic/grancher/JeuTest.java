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
	public void continuerTest()
	{
		PileButton pile = new PileButton(ROUGE);
		ArrayList<PileButton> listePile = new ArrayList<PileButton>();
		listePile.add(pile);
		
		UsinePiles usine2 = new UsinePiles(listePile);
		Jeu j2 = new Jeu(usine2);
		
		boolean resultat1 = j2.continuer();
		assertFalse(resultat1);
		
		PileButton pile2 = new PileButton(NOIR);
		listePile.add(pile2);
		usine2 = new UsinePiles(listePile);
		
		boolean resultat2 = j2.continuer();
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
		
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);

		j.compterPoints();
		
		String resultatAttendu2 = "\n==================================\nJoueur rouge : 0, Joueur noir : 2\n==================================\n";
		String resultatTrouve2 = j.afficherNbPoints();
		
		assertEquals(resultatAttendu2, resultatTrouve2);		
	}

	@Test
	public void compterPointsTest()
	{		
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);
		usine.semerTouteLaPile(0);

		j.compterPoints();
		
		int nbPointsJoueurNoirAttendu = 2;
		int nbPointsJoueurRougeAttendu = 0;
		
		int nbPointsJoueurNoirTrouve = j.getNbPointsJoueurNoir();
		int nbPointsJoueurRougeTrouve = j.getNbPointsJoueurRouge();
		
		assertEquals(nbPointsJoueurNoirAttendu, nbPointsJoueurNoirTrouve);
		assertEquals(nbPointsJoueurRougeAttendu, nbPointsJoueurRougeTrouve);
	}
}
