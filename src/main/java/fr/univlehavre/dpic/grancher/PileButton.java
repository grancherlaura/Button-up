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
	
	public void addButton(Button bouton)
	{
		listeButtons.add(bouton);
	}
	
	public void addPileButton(PileButton listeBoutonsAjoutes)
	{
		listeButtons.addAll(listeBoutonsAjoutes.getListeButtons());
	}
	
	public boolean deuxMemesButtons()
	{
		return getSommet().equals(getDeuxieme());
	}
	
	public Button getSommet()
	{
		return listeButtons.get(listeButtons.size()-1);
	}
	
	public Button getDeuxieme()
	{
		return listeButtons.get(listeButtons.size()-2);
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
		return listeButtons.get(0);
	}
	
	// seme la pile sur la pileCourante
	public void semer(PileButton pileCourante, boolean plusieursButtonsSemes)
	{
		//si on doit semer tous les boutons de la pile
		if(plusieursButtonsSemes)
		{
			pileCourante.addPileButton(this);	
			listeButtons.clear();	
		}
		
		//si on doit semer seulement un bouton
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
	
	public boolean contientEspion()
	{
		return listeButtons.contains(Button.BLANC);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listeButtons == null) ? 0 : listeButtons.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		PileButton other = (PileButton) obj;
		
		if (listeButtons == null) 
		{
			if (other.listeButtons != null)
				return false;
		} 
		
		else if (!listeButtons.equals(other.listeButtons))
			return false;
		
		return true;
	}

	// retourne la liste de buttons sous la forme [BLANC,ROUGE,NOIR,ROUGE]
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");
				
		for(int i=0; i<listeButtons.size(); i++)
		{
			builder.append(listeButtons.get(i));
			
			if(i<listeButtons.size()-1)
			{
				builder.append(",");
			}
		}
		
		builder.append("]");
		
		return builder.toString();
	}
}
