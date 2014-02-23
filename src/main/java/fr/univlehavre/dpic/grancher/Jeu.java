package fr.univlehavre.dpic.grancher;

import static fr.univlehavre.dpic.grancher.PileButton.Button.BLANC;
import static fr.univlehavre.dpic.grancher.PileButton.Button.NOIR;
import static fr.univlehavre.dpic.grancher.PileButton.Button.ROUGE;

import java.util.ArrayList;
import java.util.Arrays;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Jeu 
{
	private UsinePiles usine;
	private Affichage affich;
	private Joueurs joueurs;
	private LectureClavier clavier;
	
	public Jeu()
	{
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
		
		usine = new UsinePiles(listePiles);
		joueurs = new Joueurs();
		affich = new Affichage(usine,joueurs);
		clavier = new LectureClavier(affich);
	}
	
	protected Jeu(UsinePiles usine, Joueurs joueurs, Affichage affich)
	{
		this.usine = usine;
		this.joueurs=joueurs;
		this.affich = affich;
	}


	public boolean continuerManche()
	{
		return usine.tailleListe()!=1;
	}
	
	public void compterPoints()
	{
		PileButton pileRestante = usine.getPile(0);
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

	public UsinePiles getUsine()
	{
		return usine;
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
			
			boolean memeButton = usine.semerTouteLaPile(pile);
			
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
			usine.genererUsineAleatoire();			
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
