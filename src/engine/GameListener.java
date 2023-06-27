package engine;

import javax.swing.JTextArea;

import model.world.Champion;

public interface GameListener {
	 public void addChampions(Game game);
	 public void updateName(String name,int player);
	 public void picChampions(JTextArea jTextArea,int player);
     


}
