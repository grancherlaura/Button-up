package fr.univlehavre.dpic.grancher;

import java.util.Scanner;

public class LectureClavier 
{
	private Scanner scanner;
	private Plateau plateau;
	private Affichage affich;
	 
	public LectureClavier(Affichage affich)
	{
		scanner = new Scanner(System.in);
		this.affich = affich;
		plateau = affich.getPlateau();
	}
	
	// retourne le numéro de pile choisi par le joueur
	public int demanderPile()
	{			
		String chainePileChoisie = scanner.nextLine();
		int indicePile = convertirEntier(chainePileChoisie);
		VerifPile verif = plateau.getVerifUsine();
		
		// tant que la pile ne contient pas un pion blanc
		while(verif.neContientPasEspion(indicePile))
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
		String chaineJoueurChoisi = scanner.nextLine();
		
		// tant que la pile ne contient pas un pion blanc
		while(!reponseValide(chaineJoueurChoisi))
		{
			System.err.println(affich.messageErreurJoueur());
			chaineJoueurChoisi = scanner.nextLine();
		}
		
		return chaineJoueurChoisi;
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
	
	public boolean reponseValide(String chaine)
	{
		boolean positif = chaine.equals("y");
		boolean negatif = chaine.equals("n");
		
		return positif || negatif;
	}
}
