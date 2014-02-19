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
	
	public Jeu()
	{
		usine = new UsinePiles();
		tourJoueur=1;
		nbPointsJoueurNoir=0;
		nbPointsJoueurRouge=0;
	}
	
	public Jeu(UsinePiles usine, int nbPointsJoueurRouge, int nbPointsJoueurNoir)
	{
		this.usine = usine;
		this.nbPointsJoueurNoir=nbPointsJoueurNoir;
		this.nbPointsJoueurRouge=nbPointsJoueurRouge;
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
	
	// verifie si la manche est terminee
	public boolean continuer()
	{
		return usine.tailleListe()!=1;
	}
	
	// demande à l'utilisateur de saisir une pile
	public String afficherMessage()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(usine);
		buffer.append("\nJoueur ");
		buffer.append(couleurJoueur(tourJoueur));
		buffer.append("\nPile choisie : ");
		
		return buffer.toString();
	}
	
	public String afficherNbPoints()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\n==================================\nJoueur rouge : ");
		buffer.append(nbPointsJoueurRouge);
		buffer.append(", Joueur noir : ");
		buffer.append(nbPointsJoueurNoir);
		buffer.append("\n==================================\n");
		
		return buffer.toString();
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
			System.err.println(messageErreur());
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
	
	// affichage de la couleur du joueur gagnant
	public String messageGagnant()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\nJoueur gagnant : ");
		
		if(nbPointsJoueurRouge>nbPointsJoueurNoir)
		{
			buffer.append("ROUGE");
		}
		
		else
		{
			buffer.append("NOIR");
		}
		
		return buffer.toString();
	}
	
	// explique au joueur les caracteristiques d'une pile valide et lui repropose de rentrer une nouvelle pile
	public String messageErreur()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\nVeuillez entrer un nombre entre 1 et ");
		buffer.append(usine.tailleListe());
		buffer.append(" d'une pile qui contient un blanc : ");
		
		return buffer.toString();
	}
	
	// on fait jouer les 2 joueurs jusqu'a avoir un gagnant
	public void jouer()
	{
		// tant qu'un joueur n'a pas atteint les 15 points
		while(!existeGagnant())
		{
			System.out.println(afficherNbPoints());
			System.out.println("\nNouvelle partie !\n");
			
			// tant que la manche n'est pas terminee
			while(continuer())
			{
				System.out.println(afficherMessage());
				
				int pile=demanderPile();
				
				boolean sameButton = usine.semerTouteLaPile(pile);
				
				if(!sameButton)
				{
					changerJoueur();
				}
			}
		
			compterPoints();
			// on recommence une nouvelle manche
			usine = new UsinePiles();
		}
		
		System.out.println(afficherNbPoints());
		System.out.println(messageGagnant());
	}
	
	public static void main(String args[])
	{
		Jeu j = new Jeu();
		j.jouer();
	}
}
