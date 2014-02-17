package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class UsinePiles 
{
	private ArrayList<PileButton> listePiles;
	private static int nbBoutons = 3;
	
	public UsinePiles()
	{
		listePiles = new ArrayList<PileButton>();
		
		for(int i=0; i<nbBoutons; i++)
		{
			listePiles.add(new PileButton(Button.BLANC));
			listePiles.add(new PileButton(Button.ROUGE));
			listePiles.add(new PileButton(Button.NOIR));		
		}
	}
	
	public UsinePiles(ArrayList<PileButton> listePiles)
	{
		this.listePiles =  listePiles;
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
	
	public boolean estFinListe(int indicePileCourante)
	{
		return indicePileCourante==tailleListe()-1;
	}
	
	public boolean estRevenuDepart(int indicePileDepart, int indicePileCourante)
	{
		return indicePileDepart==(indicePileCourante+1);
	}
	
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
	
	public boolean pileInvalide(int indicePile) 
	{		
		boolean nombreTropPetit = indicePile < 0;
		boolean nombreTropGrand = indicePile >= listePiles.size();
		
		return nombreTropPetit || nombreTropGrand;
	}
	
	// retourne la pile suivante sur laquelle doit semer pileDepart
		public int pileSuivante(int pileDepart, int pileCourante)
		{
			int pileSuivante=pileCourante;
			
			// si l'on doit revenir au debut de la liste et qu'on ne se retrouve pas sur la pile de depart
			if(estFinListe(pileCourante) && !estRevenuDepart(pileDepart, -1))
			{	
				pileSuivante = 0;
			}
				
			// si l'on peut continuer sur la liste et qu'on ne se retrouve pas sur la pile de depart
			else if(!estFinListe(pileCourante) && !estRevenuDepart(pileDepart, pileCourante))
			{
				pileSuivante++;
			}
			
			return pileSuivante;
		}
		
		// seme la pileDepart sur la pileCourante et retourne si le joueur peut rejouer
		public boolean poser(int indicePileDepart, int indicePileCourante)
		{		
			PileButton pileCourante = getPile(indicePileCourante);
			PileButton pileDepart = getPile(indicePileDepart);

			// verifie si on seme un ou plusieurs boutons sur la pileCourante
			boolean plusieursButtonsSemes = estRevenuDepart(indicePileDepart,indicePileCourante);
			
			return pileDepart.semer(pileCourante, plusieursButtonsSemes);
		}
		
		// seme la pileChoisie sur toutes les piles suivantes
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
		
		public String toString()
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append("<");
			
			for(int i=0; i<listePiles.size(); i++)
			{
				buffer.append(i+1);
				buffer.append(listePiles.get(i));
				
				if(i!=listePiles.size()-1)
				{
					buffer.append("/");
				}
			}
			
			buffer.append(">");
			return buffer.toString();
		}
		
}
