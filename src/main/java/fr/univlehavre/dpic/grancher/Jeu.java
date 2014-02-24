package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Jeu 
{
	private Plateau plateau;
	private Affichage affich;
	private Joueurs joueurs;
	private LectureClavier clavier;
	
	public Jeu()
	{
		plateau = new Plateau();
		joueurs = new Joueurs();
		affich = new Affichage(plateau,joueurs);
		clavier = new LectureClavier(affich);
	}
	
	protected Jeu(Plateau plateau, Joueurs joueurs, Affichage affich)
	{
		this.plateau = plateau;
		this.joueurs=joueurs;
		this.affich = affich;
	}


	public boolean continuerManche()
	{
		return plateau.tailleListe()!=1;
	}
	
	public void compterPoints()
	{
		PileButton pileRestante = plateau.getPile(0);
		ArrayList<Button> listeButtons = pileRestante.getListeButtons();
		int boutonsRouges = 0;
		int boutonsNoirs = 0;
		
		// on additionne les valeurs des buttons selon leur place dans la pile
		for(int i=0; i<listeButtons.size(); i++)
		{
			Button boutonCourant = listeButtons.get(i);
			
			if(boutonCourant.equals(Button.ROUGE))
			{
				boutonsRouges+=(i+1);
			}
			
			else if(boutonCourant.equals(Button.NOIR))
			{
				boutonsNoirs+=(i+1);
			}
		}
		
		enregistrerPoints(boutonsRouges,boutonsNoirs);
	}
	
	public void enregistrerPoints(int boutonsRouges, int boutonsNoirs)
	{
		// points du joueur gagnant = valeur de ses boutons - valeur des boutons de l'adversaire
		if(boutonsRouges>boutonsNoirs)
		{
			joueurs.addNbPointsJoueurRouge(boutonsRouges-boutonsNoirs);
			joueurs.setDernierPerdant(2);
		}
		
		else
		{
			joueurs.addNbPointsJoueurNoir(boutonsNoirs-boutonsRouges);
			joueurs.setDernierPerdant(1);
		}
	}
	
	public void choisirPremierJoueur()
	{
		System.out.println(affich.messageChoixJoueur());
		String reponseJoueur = clavier.demanderJoueur();
		
		int tourJoueur = numeroPremierJoueur(reponseJoueur);
		joueurs.setTourJoueur(tourJoueur);
	}
	
	public int numeroPremierJoueur(String reponseJoueur)
	{
		boolean joueur1 = joueurs.getDernierPerdant().equals("ROUGE");
		boolean reponseOui = reponseJoueur.equals("y");
			
		boolean joueur1RepondNon = joueur1 && !reponseOui;
		boolean joueur2RepondOui = !joueur1 && reponseOui;
		int tour = 1;
		
		if(joueur1RepondNon || joueur2RepondOui)
		{
			tour = 2;
		}
		
		return tour;		
	}

	public Plateau getPlateau()
	{
		return plateau;
	}
	
	public Affichage getAffich()
	{
		return affich;
	}
	
	public Joueurs getJoueurs()
	{
		return joueurs;
	}
	
	public void deroulerManche()
	{
		while(continuerManche())
		{
			System.out.println(affich.afficherMessage());
			
			int pile = clavier.demanderPile();
			
			boolean memeButton = plateau.semerTouteLaPile(pile);
			
			if(!memeButton)
			{
				joueurs.changerJoueur();
			}
		}
	}
	
	// on fait jouer les 2 joueurs jusqu'a avoir un gagnant
	public void jouer()
	{
		// tant qu'un joueur n'a pas atteint les 15 points
		while(!joueurs.existeGagnant())
		{
			System.out.println(affich.afficherNbPoints());
			System.out.println("\nNouvelle partie !\n");
			
			choisirPremierJoueur();
			deroulerManche();
			compterPoints();
			
			System.out.println(affich.afficherPileFinale());
			
			// on recommence une nouvelle manche
			plateau.genererPlateauAleatoire();			
		}
		
		System.out.println(affich.afficherNbPoints());
		System.out.println(affich.messageGagnant());
	}
	
	public static void main(String args[])
	{
		Jeu j = new Jeu();
		j.jouer();
	}
}
