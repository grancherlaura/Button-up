package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Scanner;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Jeu 
{
	private UsinePiles usine;
	private Scanner scanner;
	private Affichage affich;
	private Joueurs joueurs;
	
	public Jeu()
	{
		usine = new UsinePiles();
		joueurs = new Joueurs();
		affich = new Affichage(usine,joueurs);
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
	
	// convertit le string en int, retourne -1 si ce n'est pas possible
	public int convertirEntier(String chainePileChoisie)
	{
		int pile;
		
		try 
		{
			pile=Integer.parseInt(chainePileChoisie)-1;
		} 
		
		catch (NumberFormatException e) 
		{
			pile=-1;	
		}
		
		return pile;
	}
	
	// retourne le numéro de pile choisi par le joueur
	public int demanderPile()
	{
		scanner = new Scanner(System.in);
		
		String chainePileChoisie = scanner.nextLine();
		int indicePile = convertirEntier(chainePileChoisie);
		
		// tant que la pile ne contient pas un pion blanc
		while(usine.neContientPasEspion(indicePile))
		{
			System.err.println(affich.messageErreur());
			chainePileChoisie = scanner.nextLine();
			indicePile = convertirEntier(chainePileChoisie);
		}
		
		return indicePile;
	}
	
	// retourne le numéro de pile choisi par le joueur
	public String demanderJoueur()
	{
		scanner = new Scanner(System.in);
		
		String chaineJoueurChoisi = scanner.nextLine();
		
		// tant que la pile ne contient pas un pion blanc
		while(!reponseValide(chaineJoueurChoisi))
		{
			System.err.println(affich.messageErreurJoueur());
			chaineJoueurChoisi = scanner.nextLine();
		}
		
		return chaineJoueurChoisi;
	}
	
	public boolean reponseValide(String chaine)
	{
		boolean positif = chaine.equals("y");
		boolean negatif = chaine.equals("n");
		
		return positif || negatif;
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
		}
		
		else
		{
			joueurs.addNbPointsJoueurNoir(boutonsNoirs-boutonsRouges);
		}
	}
	
	public void choisirPremierJoueur()
	{
		System.out.println(affich.messageChoixJoueur());
		String reponseJoueur = demanderJoueur();
		
		int tourJoueur = recupererPremierJoueur(reponseJoueur);
		joueurs.setTourJoueur(tourJoueur);
	}
	
	public int recupererPremierJoueur(String reponseJoueur)
	{
		boolean joueur1 = joueurs.getPerdant().equals("ROUGE");
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
	
	// on fait jouer les 2 joueurs jusqu'a avoir un gagnant
	public void jouer()
	{
		// tant qu'un joueur n'a pas atteint les 15 points
		while(!joueurs.existeGagnant())
		{
			System.out.println(affich.afficherNbPoints());
			System.out.println("\nNouvelle partie !\n");
			
			choisirPremierJoueur();
			
			// tant que la manche n'est pas terminee
			while(continuerManche())
			{
				System.out.println(affich.afficherMessage());
				
				int pile=demanderPile();
				
				boolean sameButton = usine.semerTouteLaPile(pile);
				
				if(!sameButton)
				{
					joueurs.changerJoueur();
				}
			}
		
			compterPoints();
			System.out.println(affich.afficherPileFinale());
			
			// on recommence une nouvelle manche
			usine = new UsinePiles();
			affich.setUsinePiles(usine);
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
