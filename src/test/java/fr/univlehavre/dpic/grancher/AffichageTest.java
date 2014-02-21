package fr.univlehavre.dpic.grancher;

import static fr.univlehavre.dpic.grancher.PileButton.Button.BLANC;
import static fr.univlehavre.dpic.grancher.PileButton.Button.NOIR;
import static fr.univlehavre.dpic.grancher.PileButton.Button.ROUGE;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class AffichageTest 
{
	Affichage affich;
	Jeu j;
	UsinePiles usine;
	
	@Before
	public void setup()
	{
		j = new Jeu();
		usine = j.getUsine();
		Collections.sort(usine.getListePiles());
		affich = new Affichage(j);
	}
	
	@Test
	public void afficherMessageTest()
	{
		String resultatAttendu = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>\nJoueur ROUGE\nPile choisie : ";
		String resultatTrouve = affich.afficherMessage();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
		j.changerJoueur();
		
		String resultatAttendu2 = "<1[BLANC]/2[BLANC]/3[BLANC]/4[ROUGE]/5[ROUGE]/6[ROUGE]/7[NOIR]/8[NOIR]/9[NOIR]>\nJoueur NOIR\nPile choisie : ";
		String resultatTrouve2 = affich.afficherMessage();
		
		assertEquals(resultatAttendu2, resultatTrouve2);
	}
	
	@Test
	public void afficherNbPointsTest()
	{
		String resultatAttendu = "\n==================================\nJoueur rouge : 0, Joueur noir : 0\n==================================\n";
		String resultatTrouve = affich.afficherNbPoints();
		
		assertEquals(resultatAttendu, resultatTrouve);
		
		for(int i=0; i<8; i++)
		{
			j.getUsine().semerTouteLaPile(0);
		}

		j.compterPoints();
		
		String resultatAttendu2 = "\n==================================\nJoueur rouge : 0, Joueur noir : 2\n==================================\n";
		String resultatTrouve2 = affich.afficherNbPoints();
		
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
		
		String resultatTrouve = jeu.getAffich().afficherPileFinale();
		
		assertEquals(resultatAttendu,resultatTrouve);		
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
		String messageTrouve = affich.messageGagnant();
		
		assertEquals(messageAttendu, messageTrouve);
	}
	
	@Test
	public void messageErreurTest()
	{
		String messageAttendu = "\nVeuillez entrer un nombre entre 1 et 8 d'une pile qui contient un blanc : ";
		
		usine.semerTouteLaPile(0);
		String messageTrouve = affich.messageErreur();
		
		assertEquals(messageAttendu, messageTrouve);
	}

}
