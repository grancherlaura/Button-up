package fr.univlehavre.dpic.grancher;

public class VerifPile
{
	private Plateau plateau;
	
	public VerifPile(Plateau plateau)
	{
		this.plateau = plateau;
	}

	public boolean estFinListe(int indicePileCourante)
	{
		return indicePileCourante==plateau.tailleListe()-1;
	}
	
	// retourne vrai si on revient sur la pile qu'on a choisi de semer
	public boolean estRevenuDepart(int indicePileDepart, int indicePileCourante)
	{
		return indicePileDepart==(indicePileCourante+1);
	}
	
	// retourne vrai si aucun bouton blanc n'apparait dans la pile
	public boolean neContientPasEspion(int indicePile)
	{
		boolean pasEspion = true;
		
		if(pileValide(indicePile))
		{
			PileButton pileValide = plateau.getPile(indicePile);
			pasEspion = !pileValide.contientEspion();
		}
		
		return pasEspion;
	}
	
	// retourne vrai si la pile ne fait pas partie de la liste
	public boolean pileInvalide(int indicePile) 
	{		
		boolean nombreTropPetit = indicePile < 0;
		boolean nombreTropGrand = indicePile >= plateau.tailleListe();
		
		return nombreTropPetit || nombreTropGrand;
	}
	
	// retourne vrai si la pile fait partie de la liste
	public boolean pileValide(int indicePile) 
	{		
		return !pileInvalide(indicePile);
	}

	public boolean peutRevenirDebutListe(int indicePileDepart, int indicePileCourante)
	{
		boolean estFinListe = estFinListe(indicePileCourante);
		boolean pasRevenuDepart = !estRevenuDepart(indicePileDepart, -1);
		
		return estFinListe && pasRevenuDepart;
	}
	
	public boolean peutPoserSurSuivant(int indicePileDepart, int indicePileCourante)
	{
		boolean arrivePasFinListe = !estFinListe(indicePileCourante);
		boolean pasRevenuDepart = !estRevenuDepart(indicePileDepart, indicePileCourante);
		
		return arrivePasFinListe && pasRevenuDepart;
	}
}
