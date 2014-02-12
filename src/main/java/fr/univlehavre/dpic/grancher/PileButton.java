package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;

public class PileButton
{
	public static enum Button { NOIR, ROUGE, BLANC};
	private ArrayList<Button> listeButtons;
	
	public PileButton(Button bouton)
	{
		listeButtons = new ArrayList<Button>();
		listeButtons.add(bouton);
	}
	
	public PileButton(ArrayList<Button> listeBoutons)
	{
		listeButtons = new ArrayList<Button>();
		listeButtons.addAll(listeBoutons);
	}
	
	public boolean addButton(Button bouton)
	{
		boolean sameButton = sameButton(bouton);
		listeButtons.add(bouton);
		
		return sameButton;	
	}
	
	public void addPileButton(PileButton listeBoutonsAjoutes)
	{
		listeButtons.addAll(listeBoutonsAjoutes.getListeButtons());
	}
	
	public boolean sameButton(Button bouton)
	{
		return getSommet().equals(bouton);
	}
	
	public Button getSommet()
	{
		return listeButtons.get(listeButtons.size()-1);
	}
	
	public ArrayList<Button> getListeButtons()
	{
		return listeButtons;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
				
		for(int i=listeButtons.size()-1; i>=0; i--)
		{
			builder.append(listeButtons.get(i));
			
			if(i>0)
			{
				builder.append(",");
			}
		}
		
		builder.append("]");
		
		return builder.toString();
	}
}
