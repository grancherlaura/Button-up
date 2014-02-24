package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.*;

import static org.junit.Assert.*;
import static fr.univlehavre.dpic.grancher.PileButton.Button.*;

public class JeuTest 
{
	Jeu j;
	Plateau plateau;
	Joueurs joueurs;
	Affichage affich;
	
	@Before
	public void setup()
	{
		j = new Jeu();
		plateau = j.getPlateau();
		joueurs = j.getJoueurs();
		affich = j.getAffich();
		Collections.sort(plateau.getListePiles());
	}
	
	@Test
	public void continuerMancheTest()
	{
		PileButton pile = new PileButton(ROUGE);
		ArrayList<PileButton> listePile = new ArrayList<PileButton>();
		listePile.add(pile);
		
		Plateau plateau2 = new Plateau(listePile);
		Joueurs joueurs = new Joueurs();
		Jeu j2 = new Jeu(plateau2,joueurs, new Affichage(plateau2, joueurs));
		
		boolean resultat1 = j2.continuerManche();
		assertFalse(resultat1);
		
		PileButton pile2 = new PileButton(NOIR);
		listePile.add(pile2);
		plateau2 = new Plateau(listePile);
		
		boolean resultat2 = j2.continuerManche();
		assertTrue(resultat2);		
	}
	
	@Test
	public void compterPointsTest()
	{		
		for(int i=0; i<8; i++)
		{
			plateau.semerTouteLaPile(0);
		}
		
		j.compterPoints();
		
		int nbPointsJoueurNoirAttendu = 2;
		int nbPointsJoueurRougeAttendu = 0;
		
		int nbPointsJoueurNoirTrouve = joueurs.getNbPointsJoueurNoir();
		int nbPointsJoueurRougeTrouve = joueurs.getNbPointsJoueurRouge();
		
		assertEquals(nbPointsJoueurNoirAttendu, nbPointsJoueurNoirTrouve);
		assertEquals(nbPointsJoueurRougeAttendu, nbPointsJoueurRougeTrouve);
	}
	
	@Test
	public void enregistrerPointsTest()
	{		
		int nbPointsJoueurRougeAttendus1 = 3;
		int nbPointsJoueurNoirAttendus1 = 0;
		
		j.enregistrerPoints(7, 4);
		
		int nbPointsJoueurRougeTrouves1 = joueurs.getNbPointsJoueurRouge();
		int nbPointsJoueurNoirTrouves1 = joueurs.getNbPointsJoueurNoir();
		
		assertEquals(nbPointsJoueurNoirAttendus1, nbPointsJoueurNoirTrouves1);
		assertEquals(nbPointsJoueurRougeAttendus1, nbPointsJoueurRougeTrouves1);
		
		int nbPointsJoueurRougeAttendus2 = 3;
		int nbPointsJoueurNoirAttendus2 = 5;
		
		j.enregistrerPoints(5, 10);
		
		int nbPointsJoueurRougeTrouves2 = joueurs.getNbPointsJoueurRouge();
		int nbPointsJoueurNoirTrouves2 = joueurs.getNbPointsJoueurNoir();
		
		assertEquals(nbPointsJoueurNoirAttendus2, nbPointsJoueurNoirTrouves2);
		assertEquals(nbPointsJoueurRougeAttendus2, nbPointsJoueurRougeTrouves2);
	}
	
	@Test
	public void numeroPremierJoueurTest()
	{				
		int reponseAttendue = 1;
		int reponseAttendue2 = 2;
		int reponseTrouvee = j.numeroPremierJoueur("y");
		int reponseTrouvee2 = j.numeroPremierJoueur("n");		
		
		assertEquals(reponseAttendue, reponseTrouvee);
		assertEquals(reponseAttendue2, reponseTrouvee2);
		
		plateau.semerTouteLaPile(2);
		plateau.semerTouteLaPile(2);
		plateau.semerTouteLaPile(1);
		plateau.semerTouteLaPile(1);
		plateau.semerTouteLaPile(0);
		plateau.semerTouteLaPile(2);
		plateau.semerTouteLaPile(0);
		plateau.semerTouteLaPile(1);
		j.compterPoints();
	
		int reponseTrouvee3 = j.numeroPremierJoueur("n");
		int reponseTrouvee4 = j.numeroPremierJoueur("y");
		
		assertEquals(reponseAttendue, reponseTrouvee3);
		assertEquals(reponseAttendue2, reponseTrouvee4);
	}
	
	@Test
	public void getUsineTest()
	{
		Plateau usAttendue = new Plateau();
		Joueurs joueurs = new Joueurs(5,8);
		
		Jeu jeu = new Jeu(usAttendue, joueurs, new Affichage(usAttendue, joueurs));
		
		Plateau usTrouvee = jeu.getPlateau();
		
		assertEquals(usAttendue, usTrouvee);	
	}
	
	@Test
	public void getAffichTest()
	{
		Affichage affichAttendue = new Affichage(plateau,joueurs);
		
		Jeu j2 = new Jeu(new Plateau(), new Joueurs(0,0), affichAttendue);
		
		Affichage affichTrouvee = j2.getAffich();
		
		assertEquals(affichAttendue, affichTrouvee);
	}
	
	@Test
	public void getJoueursTest()
	{
		Joueurs joueursAttendus = new Joueurs(5, 4);
		
		Jeu j2 = new Jeu(plateau, joueursAttendus, affich);
		
		Joueurs joueursTrouves = j2.getJoueurs();
		
		assertEquals(joueursAttendus, joueursTrouves);
	}
}
