package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Plateau 
{
	private ArrayList<PileButton> listePiles;
	private final static int NB_BOUTONS = 3;
	private boolean revenuDepart;
	private VerifPile verif;
	
	public Plateau()
	{
		listePiles = new ArrayList<PileButton>();
		genererPlateauAleatoire();
		revenuDepart = false;
		verif = new VerifPile(this);
	}
	
	protected Plateau(ArrayList<PileButton> listePiles)
	{
		this.listePiles =  listePiles;
		revenuDepart = false;
		verif = new VerifPile(this);
	}
	
	public void genererPlateauAleatoire()
	{
		supprimerToutesLesPiles();
		
		for(int i=0; i<NB_BOUTONS; i++)
		{
			for(int j=0; j<Button.values().length; j++)
			{
				listePiles.add(new PileButton(Button.values()[j]));
			}	
		}	
		
		melangerPiles();
	}
	
	public void supprimerToutesLesPiles()
	{
		listePiles.clear();
	}
	
	public void melangerPiles()
	{
		Collections.shuffle(listePiles);
	}
	
	public ArrayList<PileButton> getListePiles()
	{
		return listePiles;
	}
	
	public int tailleListe()
	{
		return listePiles.size();
	}
	
	public PileButton getPile(int indicePile)
	{
		return listePiles.get(indicePile);
	}
	
	public VerifPile getVerifUsine()
	{
		return verif;
	}
	
	// retourne la pile suivante sur laquelle ont doit semer pileDepart
	public int pileSuivante(int indicePileDepart, int indicePileCourante)
	{
		int indicePileSuivante=indicePileCourante;
		
		if(verif.peutRevenirDebutListe(indicePileDepart, indicePileCourante))
		{	
			indicePileSuivante = 0;
			revenuDepart = false;
		}
			
		else if(verif.peutPoserSurSuivant(indicePileDepart, indicePileCourante))
		{
			indicePileSuivante++;
			revenuDepart = false;
		}
		
		else
		{
			revenuDepart = true;
		}
		
		return indicePileSuivante;
	}
	
	public boolean joueurRejoue(int indicePile)
	{
		boolean unSeulButtonPose = !revenuDepart;
		boolean deuxMemesButtons = getPile(indicePile).deuxMemesButtons();
		
		return unSeulButtonPose && deuxMemesButtons;
	}

	// seme la pileDepart sur la pileCourante
	public void poser(int indicePileDepart, int indicePileCourante)
	{		
		PileButton pileCourante = getPile(indicePileCourante);
		PileButton pileDepart = getPile(indicePileDepart);

		boolean plusieursButtons = verif.estRevenuDepart(indicePileDepart, indicePileCourante);
		
		// verifie si on seme un ou plusieurs boutons sur la pileCourante
		pileDepart.semer(pileCourante, plusieursButtons);
	}
	
	// seme la pileChoisie sur toutes les piles suivantes et retourve vrai si le joueur peut rejouer
	public boolean semerTouteLaPile(int indicePileChoisie)
	{
		int indicePileCourante=indicePileChoisie;
		PileButton pileChoisie = getPile(indicePileChoisie);
		
		while(!pileChoisie.estVide())
		{
			// pile sur laquelle on doit semer
			indicePileCourante = pileSuivante(indicePileChoisie,indicePileCourante);
			
			// on pose le ou les boutons
			poser(indicePileChoisie,indicePileCourante);		
		}
		
		boolean joueurRejoue = joueurRejoue(indicePileCourante);
		listePiles.remove(pileChoisie);
		
		return joueurRejoue;
	}
	
	// retourne la liste de piles sous la forme <1[NOIR,BLANC]/2[ROUGE,ROUGE],/3[BLANC,ROUGE,NOIR]>
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		
		for(int i=0; i<tailleListe(); i++)
		{
			builder.append(i+1);
			builder.append(getPile(i));
			
			if(i!=tailleListe()-1)
			{
				builder.append("/");
			}
		}
		
		builder.append(">");
		return builder.toString();
	}		
}
