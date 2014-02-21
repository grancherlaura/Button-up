package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Scanner;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Jeu 
{
	private UsinePiles usine;
	private int tourJoueur;
	private int nbPointsJoueurRouge;
	private int nbPointsJoueurNoir;
	private Scanner scanner;
	private Affichage affich;
	
	public Jeu()
	{
		usine = new UsinePiles();
		tourJoueur=1;
		nbPointsJoueurNoir=0;
		nbPointsJoueurRouge=0;
		affich = new Affichage(this);
		
	}
	
	protected Jeu(UsinePiles usine, int nbPointsJoueurRouge, int nbPointsJoueurNoir)
	{
		this.usine = usine;
		this.nbPointsJoueurNoir=nbPointsJoueurNoir;
		this.nbPointsJoueurRouge=nbPointsJoueurRouge;
		affich = new Affichage(this);
	}

	public void changerJoueur() 
	{
		tourJoueur = 3 - tourJoueur;
	}
	
	// joueur1 = rouge, joueur2 = noir
	public String couleurJoueur(int joueur)
	{
		String couleur="NOIR";
		
		if(joueur==1)
		{
			couleur="ROUGE";
		}
		
		return couleur;
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
		
		// points du joueur gagnant = valeur de ses boutons - valeur des boutons de l'adversaire
		if(boutonsRouges>boutonsNoirs)
		{
			nbPointsJoueurRouge+=boutonsRouges-boutonsNoirs;
		}
		
		else
		{
			nbPointsJoueurNoir+=boutonsNoirs-boutonsRouges;
		}
	}

	// retourne vrai si un joueur a depasse les 15 points
	public boolean existeGagnant()
	{
		boolean joueurRougeGagnant = nbPointsJoueurRouge > 14;
		boolean joueurNoirGagnant = nbPointsJoueurNoir > 14;
		
		return joueurRougeGagnant || joueurNoirGagnant;
	}
	
	public int getTourJoueur()
	{
		return tourJoueur;
	}
	
	public int getNbPointsJoueurRouge()
	{
		return nbPointsJoueurRouge;
	}
	
	public int getNbPointsJoueurNoir()
	{
		return nbPointsJoueurNoir;
	}
	
	public UsinePiles getUsine()
	{
		return usine;
	}
	

	public Affichage getAffich()
	{
		return affich;
	}
	
	// on fait jouer les 2 joueurs jusqu'a avoir un gagnant
	public void jouer()
	{
		// tant qu'un joueur n'a pas atteint les 15 points
		while(!existeGagnant())
		{
			System.out.println(affich.afficherNbPoints());
			System.out.println("\nNouvelle partie !\n");
			
			// tant que la manche n'est pas terminee
			while(continuerManche())
			{
				System.out.println(affich.afficherMessage());
				
				int pile=demanderPile();
				
				boolean sameButton = usine.semerTouteLaPile(pile);
				
				if(!sameButton)
				{
					changerJoueur();
				}
			}
		
			compterPoints();
			System.out.println(affich.afficherPileFinale());
			
			// on recommence une nouvelle manche
			usine = new UsinePiles();
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
