package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;
import java.util.Collections;

import fr.univlehavre.dpic.grancher.PileButton.Button;

public class Jeu 
{
	private ArrayList<PileButton> listePile;
	private static int nbBoutons = 3;
	
	public Jeu()
	{
		listePile =  new ArrayList<PileButton>();
		
		for(int i=0; i<nbBoutons; i++)
		{
			listePile.add(new PileButton(Button.ROUGE));
			listePile.add(new PileButton(Button.NOIR));
			listePile.add(new PileButton(Button.BLANC));		
		}

		Collections.shuffle(listePile);
	}
	
	public Jeu(ArrayList<PileButton> listePile)
	{
		this.listePile =  listePile;
	}
	
	public ArrayList<PileButton> getListePile()
	{
		return listePile;
	}
	
	public boolean revenirDebutListe(int indicePileCourante)
	{
		return indicePileCourante==listePile.size()-1;
	}
	
	public boolean revenirPileDepart(int indicePileDepart, int indicePileCourante)
	{
		return indicePileDepart==(indicePileCourante+1);
	}
	
	public int pileSuivante(int pileDepart, int pileCourante)
	{
		int pileSuivante=pileCourante;
		
		if(revenirDebutListe(pileCourante))
		{	
			pileSuivante = 0;
		}
			
		else
		{
			if(!revenirPileDepart(pileDepart, pileCourante))
			{
				pileSuivante++;
			}
		}
		
		return pileSuivante;
	}
	
	public PileButton getPile(int indicePile)
	{
		return listePile.get(indicePile);
	}
	
	public void poser(int indicePileDepart, int indicePileCourante)
	{		
		PileButton pileCourante = getPile(indicePileCourante);
		PileButton pileDepart = getPile(indicePileDepart);

		boolean plusieursButtonsSemes = revenirPileDepart(indicePileDepart,indicePileCourante);
		pileDepart.semer(pileCourante, plusieursButtonsSemes);
	}
	
	public void semerTouteLaPile(int indicePileChoisie)
	{
		int indicePileCourante=indicePileChoisie;
		PileButton pileChoisie = getPile(indicePileChoisie);
		
		while(!pileChoisie.estVide())
		{
			indicePileCourante = pileSuivante(indicePileChoisie,indicePileCourante);
			poser(indicePileChoisie,indicePileCourante);			
		}
		
		listePile.remove(pileChoisie);
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<");
		
		for(int i=0; i<listePile.size(); i++)
		{
			buffer.append(i+1);
			buffer.append(listePile.get(i));
			
			if(i!=listePile.size()-1)
			{
				buffer.append("/");
			}
		}
		
		buffer.append(">");
		return buffer.toString();
	}
	
	public static void main(String args[])
	{
		Jeu j = new Jeu();
		System.out.println(j);
	}
}
