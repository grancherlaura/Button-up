package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class UsinePiles 
{
	private ArrayList<PileButton> listePiles;
	private final static int NB_BOUTONS = 3;
	
	public UsinePiles()
	{
		listePiles = new ArrayList<PileButton>();
		
		for(int i=0; i<NB_BOUTONS; i++)
		{
			listePiles.add(new PileButton(Button.BLANC));
			listePiles.add(new PileButton(Button.ROUGE));
			listePiles.add(new PileButton(Button.NOIR));		
		}
		
		// on mélange les piles de boutons
		Collections.shuffle(listePiles);
	}
	
	public UsinePiles(ArrayList<PileButton> listePiles)
	{
		this.listePiles =  listePiles;
	}
	
	public ArrayList<PileButton> getListePiles()
	{
		return listePiles;
	}
	
	public int tailleListe()
	{
		return listePiles.size();
	}
	
	// retourne vrai si on arrive à la fin de la liste
	public boolean estFinListe(int indicePileCourante)
	{
		return indicePileCourante==tailleListe()-1;
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
		
		if(!pileInvalide(indicePile))
		{
			PileButton pileValide = getPile(indicePile);
			pasEspion = !pileValide.contientEspion();
		}
		
		return pasEspion;
	}
	
	public PileButton getPile(int indicePile)
	{
		return listePiles.get(indicePile);
	}
	
	// retourne vrai si la pile ne fait pas partie de la liste
	public boolean pileInvalide(int indicePile) 
	{		
		boolean nombreTropPetit = indicePile < 0;
		boolean nombreTropGrand = indicePile >= tailleListe();
		
		return nombreTropPetit || nombreTropGrand;
	}
	
	// retourne la pile suivante sur laquelle ont doit semer pileDepart
	public int pileSuivante(int indicePileDepart, int indicePileCourante)
	{
		int indicePileSuivante=indicePileCourante;
		
		// si l'on doit revenir au debut de la liste et qu'on ne se retrouve pas sur la pile de depart
		if(estFinListe(indicePileCourante) && !estRevenuDepart(indicePileDepart, -1))
		{	
			indicePileSuivante = 0;
		}
			
		// si l'on peut continuer sur la liste et qu'on ne se retrouve pas sur la pile de depart
		else if(!estFinListe(indicePileCourante) && !estRevenuDepart(indicePileDepart, indicePileCourante))
		{
			indicePileSuivante++;
		}
		
		return indicePileSuivante;
	}
		
	// seme la pileDepart sur la pileCourante et retourne vrai si le joueur peut rejouer
	public boolean poser(int indicePileDepart, int indicePileCourante)
	{		
		PileButton pileCourante = getPile(indicePileCourante);
		PileButton pileDepart = getPile(indicePileDepart);

		// verifie si on seme un ou plusieurs boutons sur la pileCourante
		boolean plusieursButtonsSemes = estRevenuDepart(indicePileDepart,indicePileCourante);
		
		return pileDepart.semer(pileCourante, plusieursButtonsSemes);
	}
	
	// seme la pileChoisie sur toutes les piles suivantes et retourve vrai si le joueur peut rejouer
	public boolean semerTouteLaPile(int indicePileChoisie)
	{
		int indicePileCourante=indicePileChoisie;
		PileButton pileChoisie = getPile(indicePileChoisie);
		boolean sameButton=false;
		
		while(!pileChoisie.estVide())
		{
			// pile sur laquelle on doit semer
			indicePileCourante = pileSuivante(indicePileChoisie,indicePileCourante);
			
			// on pose le ou les boutons et on recupere si le joueur peut rejouer
			sameButton=poser(indicePileChoisie,indicePileCourante);			
		}
		
		listePiles.remove(pileChoisie);
		
		return sameButton;
	}
	
	// retourne la liste de piles sous la forme <1[NOIR,BLANC]/2[ROUGE,ROUGE],/3[BLANC,ROUGE,NOIR]>
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<");
		
		for(int i=0; i<tailleListe(); i++)
		{
			buffer.append(i+1);
			buffer.append(getPile(i));
			
			if(i!=tailleListe()-1)
			{
				buffer.append("/");
			}
		}
		
		buffer.append(">");
		return buffer.toString();
	}		
}
