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
		for(int i=0; i<nbBoutons; i++)
		{
			listePile.add(new PileButton(Button.ROUGE));
			listePile.add(new PileButton(Button.NOIR));
			listePile.add(new PileButton(Button.BLANC));		
		}

		Collections.shuffle(listePile);
	}
	
	public ArrayList<PileButton> getListePile()
	{
		return listePile;
	}
	
	public static void main(String args[])
	{
		Jeu j = new Jeu();
	}
}
