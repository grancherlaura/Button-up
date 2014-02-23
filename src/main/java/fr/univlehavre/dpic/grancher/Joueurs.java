package fr.univlehavre.dpic.grancher;

public class Joueurs
{
	private int tourJoueur;
	private int nbPointsJoueurRouge;
	private int nbPointsJoueurNoir;
	private int dernierPerdant;
	
	public Joueurs()
	{
		tourJoueur = 1;
		nbPointsJoueurNoir=0;
		nbPointsJoueurRouge=0;
		dernierPerdant = 1;
	}
	
	protected Joueurs(int nbPointsJoueurRouge, int nbPointsJoueurNoir)
	{
		tourJoueur=1;
		this.nbPointsJoueurNoir=nbPointsJoueurNoir;
		this.nbPointsJoueurRouge=nbPointsJoueurRouge;
	}
	
	public void changerJoueur() 
	{
		tourJoueur = 3 - tourJoueur;
	}
	
	// joueur1 = rouge, joueur2 = noir
	protected String couleurJoueur(int joueur)
	{
		String couleur="NOIR";
		
		if(joueur==1)
		{
			couleur="ROUGE";
		}
		
		return couleur;
	}
	
	public String getGagnant()
	{
		String gagnant="NOIR";
		
		if(nbPointsJoueurRouge>nbPointsJoueurNoir)
		{
			gagnant="ROUGE";
		}
		
		return gagnant;
	}
	
	public String getDernierPerdant()
	{
		return couleurJoueur(dernierPerdant);
	}
	
	public String getJoueurCourant()
	{
		return couleurJoueur(tourJoueur);
	}
	
	public void addNbPointsJoueurRouge(int points)
	{
		nbPointsJoueurRouge+=points;
	}
	
	public void addNbPointsJoueurNoir(int points)
	{
		nbPointsJoueurNoir+=points;
	}
	
	public int getNbPointsJoueurRouge()
	{
		return nbPointsJoueurRouge;
	}
	
	public int getNbPointsJoueurNoir()
	{
		return nbPointsJoueurNoir;
	}
	
	public int getTourJoueur()
	{
		return tourJoueur;
	}
	
	public void setTourJoueur(int tour)
	{
		tourJoueur=tour;
	}
	
	public void setDernierPerdant(int perdant)
	{
		dernierPerdant = perdant;
	}
	
	// retourne vrai si un joueur a depasse les 15 points
	public boolean existeGagnant()
	{
		boolean joueurRougeGagnant = nbPointsJoueurRouge > 14;
		boolean joueurNoirGagnant = nbPointsJoueurNoir > 14;
		
		return joueurRougeGagnant || joueurNoirGagnant;
	}	
}
