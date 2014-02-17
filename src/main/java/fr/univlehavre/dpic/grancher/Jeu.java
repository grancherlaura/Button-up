package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public Jeu(UsinePiles usine)
	{
		this.usine = usine;
	}

	public void changerJoueur() 
	{
		tourJoueur = 3 - tourJoueur;
	}
	
	public String couleurJoueur(int joueur)
	{
		String couleur;
		
		if(joueur==1)
		{
			couleur="ROUGE";
		}
		
		else
		{
			couleur="NOIR";
		}
		
		return couleur;
	}
	
	public boolean continuer()
	{
		return usine.tailleListe()!=1;
	}
	
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
	
	public int convertirEntier(String pileChoisie)
	{
		int pile;
		
		try 
		{
			pile=Integer.parseInt(pileChoisie)-1;
		} 
		
		catch (NumberFormatException e) 
		{
			pile=-1;	
		}
		
		return pile;
	}
	
	public int demanderPile()
	{
		scanner = new Scanner(System.in);
		
		String pileChoisie = scanner.nextLine();
		int pile = convertirEntier(pileChoisie);
		
		// tant que la pile ne contient pas un pion blanc
		while(usine.neContientPasEspion(pile))
		{
			System.err.println(messageErreur());
			pileChoisie = scanner.nextLine();
			pile = convertirEntier(pileChoisie);
		}
		
		return pile;
	}
	
	public void compterPoints()
	{
		PileButton pileRestante = usine.getPile(0);
		ArrayList<Button> listeButtons = pileRestante.getListeButtons();
		int boutonsRouges = 0;
		int boutonsNoirs = 0;
		
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
		
		if(boutonsRouges>boutonsNoirs)
		{
			nbPointsJoueurRouge+=boutonsRouges-boutonsNoirs;
		}
		
		else
		{
			nbPointsJoueurNoir+=boutonsNoirs-boutonsRouges;
		}
	}

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
	
	public String messageErreur()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\nVeuillez entre un nombre entre 1 et ");
		buffer.append(usine.tailleListe());
		buffer.append(" d'une pile qui contient un blanc : ");
		
		return buffer.toString();
	}
	
	public void jouer()
	{
		// tant qu'un joueur n'a pas atteint les 15 points
		while(!existeGagnant())
		{
			System.out.println(afficherNbPoints());
			System.out.println("\nNouvelle partie !\n");
			
			// tant que la partie n'est pas terminee
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
