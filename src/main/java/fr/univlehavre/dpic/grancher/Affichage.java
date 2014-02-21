package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Affichage 
{
	private Jeu jeu;
	
	public Affichage(Jeu j)
	{
		jeu=j;
	}
	
	// demande à l'utilisateur de saisir une pile
	public String afficherMessage()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(jeu.getUsine());
		builder.append("\nJoueur ");
		builder.append(jeu.couleurJoueur(jeu.getTourJoueur()));
		builder.append("\nPile choisie : ");
		
		return builder.toString();
	}
	
	public String afficherNbPoints()
	{
		StringBuilder builder  = new StringBuilder();
		
		builder.append("\n==================================\nJoueur rouge : ");
		builder.append(jeu.getNbPointsJoueurRouge());
		builder.append(", Joueur noir : ");
		builder.append(jeu.getNbPointsJoueurNoir());
		builder.append("\n==================================\n");
		
		return builder.toString();
	}
	
	// affiche la pile finale avec le nombre de points que rapporte chaque bouton
	public String afficherPileFinale()
	{
		StringBuilder builder  = new StringBuilder();
		
		PileButton pileRestante = jeu.getUsine().getPile(0);
		ArrayList<Button> listeButtons = pileRestante.getListeButtons();
		
		builder.append("\nPile finale\n");
		
		for(int i=pileRestante.tailleListe()-1; i>=0; i--)
		{
			builder.append(i+1);
			builder.append(" ");
			builder.append(listeButtons.get(i));
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	// affichage de la couleur du joueur gagnant
	public String messageGagnant()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nJoueur gagnant : ");
		
		if(jeu.getNbPointsJoueurRouge()>jeu.getNbPointsJoueurNoir())
		{
			builder.append("ROUGE");
		}
		
		else
		{
			builder.append("NOIR");
		}
		
		return builder.toString();
	}
	
	// explique au joueur les caracteristiques d'une pile valide et lui repropose de rentrer une nouvelle pile
	public String messageErreur()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nVeuillez entrer un nombre entre 1 et ");
		builder.append(jeu.getUsine().tailleListe());
		builder.append(" d'une pile qui contient un blanc : ");
		
		return builder.toString();
	}	
}
