package fr.univlehavre.dpic.grancher;

import java.util.ArrayList;

public class PileButton implements Comparable<PileButton>
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
		Button boutonSommet;
		
		if(estVide())
		{
			boutonSommet=null;
		}
		
		else
		{
			boutonSommet=listeButtons.get(listeButtons.size()-1);
		}
		
		return boutonSommet;
	}
	
	public ArrayList<Button> getListeButtons()
	{
		return listeButtons;
	}
	
	public boolean estVide()
	{
		return listeButtons.isEmpty();
	}
	
	public Button getButtonDessous()
	{
		Button boutonDessous;
		
		if(estVide())
		{
			boutonDessous=null;
		}
		
		else
		{
			boutonDessous=listeButtons.get(0);
		}
		
		return boutonDessous;
	}
	
	public void semer(PileButton pileCourante, boolean plusieursButtonsSemes)
	{
		if(plusieursButtonsSemes)
		{
			pileCourante.addPileButton(this);	
			listeButtons.clear();	
		}
		
		else
		{
			pileCourante.addButton(this.getButtonDessous());	
			listeButtons.remove(this.getButtonDessous());
		}
	}
	
	@Override
	public int compareTo(PileButton pileButton) 
	{
		return pileButton.getSommet().compareTo(this.getSommet());	
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listeButtons == null) ? 0 : listeButtons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PileButton other = (PileButton) obj;
		if (listeButtons == null) {
			if (other.listeButtons != null)
				return false;
		} else if (!listeButtons.equals(other.listeButtons))
			return false;
		return true;
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
