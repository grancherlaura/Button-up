package fr.univlehavre.dpic.grancher;

public class Affichage 
{
	private UsinePiles usine;
	private Joueurs joueurs;
	
	public Affichage(UsinePiles usine, Joueurs joueurs)
	{
		this.usine=usine;
		this.joueurs=joueurs;
	}
	
	// demande à l'utilisateur de saisir une pile
	public String afficherMessage()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append(usine);
		builder.append("\nJoueur ");
		builder.append(joueurs.getJoueurCourant());
		builder.append("\nPile choisie : ");
		
		return builder.toString();
	}
	
	public String afficherNbPoints()
	{
		StringBuilder builder  = new StringBuilder();

		builder.append("\n==================================\nJoueur rouge : ");
		builder.append(joueurs.getNbPointsJoueurRouge());
		builder.append(", Joueur noir : ");
		builder.append(joueurs.getNbPointsJoueurNoir());
		builder.append("\n==================================\n");
		
		return builder.toString();
	}
	
	// affiche la pile finale avec le nombre de points que rapporte chaque bouton
	public String afficherPileFinale()
	{
		StringBuilder builder  = new StringBuilder();
		PileButton pileFinale = usine.getPile(0);
		
		builder.append("\nPile finale\n");
		
		for(int i=pileFinale.tailleListe()-1; i>=0; i--)
		{
			builder.append(i+1);
			builder.append(" ");
			builder.append(pileFinale.getListeButtons().get(i));
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	// affichage de la couleur du joueur gagnant
	public String messageGagnant()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nJoueur gagnant : ");
		builder.append(joueurs.getGagnant());
		
		return builder.toString();
	}
	
	// explique au joueur les caracteristiques d'une pile valide et lui repropose de rentrer une nouvelle pile
	public String messageErreur()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("\nVeuillez entrer un nombre entre 1 et ");
		builder.append(usine.tailleListe());
		builder.append(" d'une pile qui contient un blanc : ");
		
		return builder.toString();
	}	
	
	public void setUsinePiles(UsinePiles usine)
	{
		this.usine = usine;
	}
	
	protected UsinePiles getUsinePiles()
	{
		return usine;
	}
}
