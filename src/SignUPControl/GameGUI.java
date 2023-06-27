package SignUPControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import SignUpView.view;
import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.effects.EffectType;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class GameGUI extends JFrame implements ActionListener {
  private Game game;
  private Player one;
  private Player two;
  private view View;
  private JPanel test ;
  private JTextArea player;
  private JPanel playerone_de;
  private JPanel playertwo_de;
  private JPanel functions;
  private JPanel curr;
  private JPanel board;
  private JButton champ1p1;
  private JButton champ2p1;
  private JButton champ3p1;
  private JButton champ1p2;
  private JButton champ2p2;
  private JButton champ3p2;
  private ArrayList<Champion> team1;
  private ArrayList<Champion> team2;
  private ArrayList<String> names;
  private JTextArea champ_info_p1;
  private JTextArea champ_info_p2;
  private JButton move;
  private JButton attack;
  private JButton castabb;
  private JButton abb1;
  private JButton abb2;
  private JButton abb3;
  private JButton Upp1;
  private JButton Downp1;
  private JButton rightp1;
  private JButton leftp1;
  private JButton Upp2;
  private JButton Downp2;
  private JButton rightp2;
  private JButton leftp2;  
  private JPanel controlp1;
  private JPanel controlp2;
  private JButton leaderp;
  private JTextArea theTurn;
  private JButton Endturn;
  private boolean isMove=false;
  private boolean isAttack=false;
  private boolean iscastdir=false;
  private boolean isCastsing=false;
  private boolean normalcast = false;
  private ArrayList<JButton> boardButtons;
  private JProgressBar HPbar;
  private JProgressBar manabar;
  private Ability ability;
  private JPanel currinfo;
  private JTextArea currinfotext;
  private ArrayList<Champion> infocurr;
  
  
	
	public GameGUI(Player one , Player two) throws IOException  {
	 	this.one = one;
	 	this.two=two;
		game = new Game(one,two);
	 	ImageIcon x =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\logo.png");
	    Image last = x.getImage().getScaledInstance(1000, 1000,  java.awt.Image.SCALE_SMOOTH);
	   
	     this.setIconImage(last);
	       this.setTitle("Marvel Game");
	       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	       this.setSize(1366,768);
	       this.setLayout(null);
	       this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	     
	       //open
	       infocurr = new ArrayList<Champion>();
	       currinfo = new JPanel();
	       currinfotext = new JTextArea();
	       board = new JPanel();
	       boardButtons = new ArrayList<JButton>();
	     HPbar = new JProgressBar();
	     manabar = new JProgressBar();
	      HPbar.setValue(0);
	     manabar.setValue(0);
	       Endturn = new JButton("End Turn");
	       leaderp= new JButton("Leader Abiity");
	       theTurn = new JTextArea("Helo");
	       controlp1 = new JPanel();
	       controlp2 = new JPanel();
	       Upp1 = new JButton("Down");
	       Downp1 = new JButton("UP");
	       rightp1 = new JButton("Right");
	       leftp1 = new JButton("Left");
	       Upp2 = new JButton("Down");
	       Downp2 = new JButton("UP");
	       rightp2 = new JButton("Right");
	       leftp2 = new JButton("Left");
	       
	       move = new JButton("Move");
	       attack = new JButton("Basic Attack");
	       castabb = new JButton("Ability");
	       abb1= new JButton();
	       abb2= new JButton();
	       abb3= new JButton();
	       champ_info_p1 = new JTextArea();
	       champ_info_p2 = new JTextArea();
	       playerone_de = new JPanel();
	       playertwo_de = new JPanel();
	       curr = new JPanel();
	       board = new JPanel();
	       functions = new JPanel();
	       team1 = one.getTeam();
	       team2 =two.getTeam();
	       names = new ArrayList<String>();
	       for(Champion c : team1) {
	    	   names.add(c.getName());
	       }
	       champ1p1 = new JButton(names.get(0));
	       champ2p1 = new JButton(names.get(1));
	       champ3p1 = new JButton(names.get(2));
	       names.clear();
	       for(Champion c : team2) {
	    	   names.add(c.getName());
	       }
	       champ1p2 = new JButton(names.get(0));
	       champ2p2 = new JButton(names.get(1));
	       champ3p2 = new JButton(names.get(2));
	       while(!game.getTurnOrder().isEmpty()) {
		    	  infocurr.add((Champion) game.getTurnOrder().remove());
		      }
		      for(Champion c : infocurr) {
		    	  game.getTurnOrder().insert(c);
		      }
		     
		      Collections.reverse(infocurr);
	       
	       
	       
	       
	       //layots
	       controlp1.setLayout(null);
	       controlp2.setLayout(null);
	       playerone_de.setLayout(null);
	       playertwo_de.setLayout(null);
	       curr.setLayout(null);
	       currinfo.setLayout(null);
	       functions.setLayout(new GridLayout(1,7));
	       board.setLayout(new GridLayout(5,5));
	       // Buttons lestiner
	       Endturn.addActionListener(this);
	       move.addActionListener(this);
	       attack.addActionListener(this);
	       castabb.addActionListener(this);
	       abb1.addActionListener(this);
	       abb2.addActionListener(this);
	       abb3.addActionListener(this);
	       /*abb1.setEnabled(false);
	       abb2.setEnabled(false);
	       abb3.setEnabled(false);*/
	       champ1p1.addActionListener(this);
	       champ2p1.addActionListener(this);
	       champ3p1.addActionListener(this);
	       champ1p2.addActionListener(this);
	       champ2p2.addActionListener(this);
	       champ3p2.addActionListener(this);
	       Upp1.addActionListener(this);
	       Downp1.addActionListener(this);
	       rightp1.addActionListener(this);
	       leftp1.addActionListener(this);
	       Upp2.addActionListener(this);
	       Downp2.addActionListener(this);
	       rightp2.addActionListener(this);
	       leftp2.addActionListener(this);
	      
	       Upp2.setEnabled(false);
	       Downp2.setEnabled(false);
	       rightp2.setEnabled(false);
	       leftp2.setEnabled(false);
	       champ_info_p1.setEditable(false);
	       champ_info_p2.setEditable(false);
	       
	       leaderp.addActionListener(this);
	       theTurn.setEditable(false);
	       for(int i=0; i<game.getBoardheight(); i++){
	    	  
	    	   for(int j=0 ;j<game.getBoardwidth();j++) {
	    		   
				JButton button = new JButton();
				button.setName(i+""+j+"");
				boardButtons.add(button);
				button.addActionListener(this);
				board.add(button);
				
			}}
	       
	       
	    //Start loc
	       champ_info_p1.setBounds(0, 0, 300,632 );
	       champ_info_p2.setBounds(0, 0, 300, 632);
	       controlp1.setBounds(0, 0, 300, 482);
	       controlp2.setBounds(0, 0, 300, 482);
	       Upp1.setSize(50, 50);
	       champ1p1.setBounds(0, 0, 300, 50);
	       champ2p1.setBounds(0, 50, 300, 50);
	       champ3p1.setBounds(0,100, 300, 50);
	       champ1p2.setBounds(0,0, 300, 50);
	       champ2p2.setBounds(0, 50, 300, 50);
	       champ3p2.setBounds(0,100, 300, 50);
	       board.setBounds(300, 150, 766, 468);
	     
	       Downp2.setBounds(0,640, 75, 100);
	       Upp2.setBounds(75,640, 75, 100);
	       rightp2.setBounds(150,640, 75, 100);
	       leftp2.setBounds(225,640, 75, 100);
	       theTurn.setBounds(0, 20, 200, 200);
	       Endturn.setBounds(200, 20, 90, 50);
	       currinfo.setBounds(290, 20, 600, 350);
	    
	    playerone_de.setBounds(0, 0, 300, 768);
	    playertwo_de.setBounds(1066, 0, 300, 768);
	    
	    curr.setBounds(300, 0, 766, 150);
	    
	 
	    
	    functions.setBounds(300, 618, 766, 150);
	    functions.add(move);
	    functions.add(attack);
	    functions.add(castabb);
	    
	    functions.add(abb1);
	    functions.add(abb3);
	    functions.add(abb2);
	    functions.add(leaderp);
	    
	       
	       
	       
	    // colors
	    champ_info_p1.setBackground(Color.green);
	    champ_info_p2.setBackground(Color.BLUE);
	    playerone_de.setBackground(Color.red);
	    playertwo_de.setBackground(Color.blue);
	    curr.setBackground(Color.gray);
	    theTurn.setBackground(Color.gray);
	    functions.setBackground(Color.WHITE);
	    board.setBackground(Color.yellow);
	   
	    controlp1.setBackground(Color.black);
	    controlp2.setBackground(Color.black);
	    Endturn.setBackground(Color.gray);
	    
  // adding 
	    
	   
	   
	    playerone_de.add(champ_info_p1);
	   

	   playertwo_de.add(champ_info_p2);
	    playertwo_de.add(Downp2);
	    playertwo_de.add(Upp2);
	    playertwo_de.add(leftp2);
	    playertwo_de.add(rightp2);
	    
	 
	   playertwo_de.add(controlp2);
	   
	   currinfo.add(currinfotext);
	   curr.add(currinfo);
	   curr.add(theTurn);
	   curr.add(Endturn);
	
	   
	   
	    this.add(playerone_de);
	    this.add(playertwo_de);
	   this.getContentPane().add(functions);
	   this.add(board);
	    this.add(curr);
	    
	    
	   
	       
	       
	       makeboard();
	       whoisplaying();
	       this.setVisible(true);
	
	}
	public GameGUI() {
		
	 	ImageIcon x =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\logo.png");
	    Image last = x.getImage().getScaledInstance(1000, 1000,  java.awt.Image.SCALE_SMOOTH);
	   
	     this.setIconImage(last);
	       this.setTitle("Marvel Game");
	       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	       this.setSize(1366,768);
	       this.setLayout(null);
	       this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	     
	       //open
	       playerone_de = new JPanel();
	       playertwo_de = new JPanel();
	       curr = new JPanel();
	       board = new JPanel();
	       functions = new JPanel();

	       champ1p1 = new JButton("hello");
	       champ2p1 = new JButton("hello");
	       champ3p1 = new JButton("hello");
	      
	       champ1p2 = new JButton("hello");
	       champ2p2 = new JButton("hello");
	       champ3p2 = new JButton("hello");
	       
	       //
	       playerone_de.setLayout(null );
	       playertwo_de.setLayout(null);
	       curr.setLayout(null);
	       functions.setLayout(new GridLayout(1,6));
	       board.setLayout(null);
	       
	       
	       
	    //Start loc
	       champ1p1.setBounds(0, 0, 300, 50);
	       champ2p1.setBounds(0, 50, 300, 50);
	       champ3p1.setBounds(0,100, 300, 50);
	       champ1p2.setBounds(0,0, 300, 50);
	       champ2p2.setBounds(0, 50, 300, 50);
	       champ3p2.setBounds(0,100, 300, 50);
	    playerone_de.setBounds(0, 0, 300, 768);
	    playertwo_de.setBounds(1066, 0, 300, 768);
	    curr.setBounds(300, 0, 766, 150);
	    functions.setBounds(300, 618, 766, 150);
	    board.setBounds(300, 150, 766, 468);
	       
	       
	    // colors
	    
	    playerone_de.setBackground(Color.red);
	    playertwo_de.setBackground(Color.blue);
	    curr.setBackground(Color.gray);
	    functions.setBackground(Color.green);
	    board.setBackground(Color.yellow);
	    
  // adding 
	    
	    
	   
	   
	    this.add(functions);
	       
	  /*  playerone_de.add(champ1p1);
	    playerone_de.add(champ2p1);
	    playerone_de.add(champ3p1);
	    
	    playertwo_de.add(champ1p2);
	    playertwo_de.add(champ2p2);
	    playertwo_de.add(champ3p2);
	       */
	       
	       
	       
	       this.setVisible(true);
	
	
	}
	
	public void animation(Champion c) {
		JFrame ani = new JFrame();
	    
		ImageIcon x =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\logo.png");
	    Image last = x.getImage().getScaledInstance(1000, 1000,  java.awt.Image.SCALE_SMOOTH);
	   
	    ani.setIconImage(last);
	    ani.setTitle("Marvel Game");
	    ani.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    ani.setSize(1366,768);
	    ani.setLayout(null);
	    ani.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    
	}
	public void whoisplaying() {
		currinfo.removeAll();
		int curr = game.getTurnOrder().size()-1;
		Champion c = infocurr.get(curr);
		champ_info_p1.removeAll();
		champ_info_p2.removeAll();
		
		if(game.getFirstPlayer().getTeam().contains(c)) {
			champ_info_p2.removeAll();
			 String s ="";
			
			 if(c instanceof Hero) {
				 s+="this Champion Typr is: Hero"+"\n";
			 }
			 if(c instanceof Villain) {
				 s+="this Champion Typr is: Villain"+"\n";
			 }
			 if(c instanceof AntiHero) {
				 s+="this Champion Typr is: AntiHero"+"\n";
			 }
			 
			 
			 
			 for(Ability a : c.getAbilities()) {
				 s+="\n"+a.getName()+":" +"\n"+"--> Mana cost :"+a.getManaCost();
				 s+="\n"+"--> baseCoolDown: "+a.getBaseCooldown();
				 s+="\n"+"--> CastRange: "+a.getCastRange()+"Block";
				 s+="\n"+"--> AreaOfEffect: "+a.getCastArea();
				 s+="\n"+"--> requiredActionPoints: "+a.getRequiredActionPoints();
				 if(a instanceof DamagingAbility) {
					 s+="\n"+"--> Ability type: Damaging Ability ";

				 }
				 else  if(a instanceof HealingAbility) {
					 s+="\n"+"--> Ability type: Healing Ability ";

				 }
				 else  if(a instanceof CrowdControlAbility) {
					 if(((CrowdControlAbility)a).getEffect().getType()==EffectType.BUFF) {
					 s+="\n"+"--> Ability type: Crowd Control Ability BUFF";}
					 else
						 s+="\n"+"--> Ability type: Crowd Control Ability DEBUFF";

				 }
				 
			 }
			 for(Effect w : c.getAppliedEffects()) {
				 s+="\n"+ w.getName()+":"+"--> Duration: "+w.getDuration();
				 s+="\n"+"--> Type"+w.getType();
			 }
			 
			 champ_info_p1.setText(s);
			 champ_info_p1.setFont(new Font("Serif", 10, 15));
			
			
		}
		if(game.getSecondPlayer().getTeam().contains(c)) {
			 String s ="";
				champ_info_p1.removeAll();
			 if(c instanceof Hero) {
				 s+="this Champion Typr is: Hero"+"\n";
			 }
			 if(c instanceof Villain) {
				 s+="this Champion Typr is: Villain"+"\n";
			 }
			 if(c instanceof AntiHero) {
				 s+="this Champion Typr is: AntiHero"+"\n";
			 }
			
			 for(Ability a : c.getAbilities()) {
				 s+="\n"+a.getName()+":" +"\n"+"--> Mana cost :"+a.getManaCost();
				 s+="\n"+"--> baseCoolDown: "+a.getBaseCooldown();
				 s+="\n"+"--> CastRange: "+a.getCastRange()+"Block";
				 s+="\n"+"--> AreaOfEffect: "+a.getCastArea();
				 s+="\n"+"--> requiredActionPoints: "+a.getRequiredActionPoints();
				 if(a instanceof DamagingAbility) {
					 s+="\n"+"--> Ability type: Damaging Ability ";

				 }
				 else  if(a instanceof HealingAbility) {
					 s+="\n"+"--> Ability type: Healing Ability ";

				 }
				 else  if(a instanceof CrowdControlAbility) {
					 if(((CrowdControlAbility)a).getEffect().getType()==EffectType.BUFF) {
					 s+="\n"+"--> Ability type: Crowd Control Ability BUFF";}
					 else
						 s+="\n"+"--> Ability type: Crowd Control Ability DEBUFF";

				 }
				 
			 }
			 for(Effect w : c.getAppliedEffects()) {
				 s+="\n"+ w.getName()+":"+"--> Duration: "+w.getDuration();
				 s+="\n"+"--> Type"+w.getType();
			 }
			 
			 champ_info_p2.setText(s);
			 champ_info_p2.setFont(new Font("Serif", 10, 15));
			
			
		}
		
		if(game.getFirstPlayer().getTeam().contains(c)) {
			String s = "The current Champion is: "+c.getName()+"\n";
			s+="He has "+c.getCurrentActionPoints()+ " Action Points"+"\n";
			s+="He has "+c.getSpeed()+" Speed"+"\n";
		  for(Ability a:c.getAbilities()) {
			  s+="\n"+"-> Ability: "+a.getName()+"-has cooldown: "+a.getCurrentCooldown();
		  }
		  s+= ","+"the champion's health is "+ c.getCurrentHP() +"\n";
		s+= "the champion's mana is "+ c.getMana() +"\n";
			 
	       currinfo.setBackground(Color.green);
	       JTextArea thecurr = new JTextArea();
	       thecurr.setBackground(Color.green);
	       thecurr.setText(s);
	       thecurr.add(Pics(c.getName(), 50, 50));
	       thecurr.setBounds(0, 0, 600, 150);
	       thecurr.setEditable(false);
	       currinfo.add(thecurr);
	       currinfo.add(thecurr);
	       currinfo.invalidate();
	       currinfo.validate();
	       currinfo.repaint();
	       
	      
		}
		else if(game.getSecondPlayer().getTeam().contains(c)) {
			String s = "The current Champion is: "+c.getName()+"\n";
			s+="He has "+c.getCurrentActionPoints()+ " Action Points"+"\n";
			s+="He has "+c.getSpeed()+" Speed"+"\n";
		  for(Ability a:c.getAbilities()) {
			  s+="-> Ability: "+a.getName()+"\n"+"-has cooldown: "+a.getCurrentCooldown();
		  }
		  s+= ","+"the champion's health is "+ c.getCurrentHP() +"\n";
			 s+= "the champion's mana is "+ c.getMana() +"\n";
			 
		       currinfo.setBackground(Color.red);
		       
		       JTextArea thecurr = new JTextArea();
		       thecurr.setText(s);
		       thecurr.setBackground(Color.red);
		       thecurr.add(Pics(c.getName(), 50, 50));
		       thecurr.setBounds(0, 0, 600, 150);
		       thecurr.setEditable(false);
		       currinfo.add(thecurr);
		       currinfo.invalidate();
		       currinfo.validate();
		       currinfo.repaint();
		       
		      
			}
		
	}
	
	
	public int whoisturn() {

Champion c = game.getCurrentChampion();
if(one.getTeam().contains(c)) {
	return 1;
}
else if(two.getTeam().contains(c)) {
	return 2;
}
return 0;
		
	}
	public void creatGame(Player one ,Player two) throws IOException {
		new GameGUI(one,two);
	}
	
	
	private int getx(String s) {
		
		
		return Character.getNumericValue(s.charAt(0)) ;

	}
	private int gety(String s) {
		
		
		return Character.getNumericValue(s.charAt(1));

	}
	public void makeboard() {
		  whoisplaying();
		for(int i=0;i<boardButtons.size();i++) {
			
			int x = getx(boardButtons.get(i).getName());
			int y = gety(boardButtons.get(i).getName());
			if(game.getBoard()[x][y] instanceof Cover) {
			Cover c =(Cover) game.getBoard()[x][y];
		        	boardButtons.get(i).removeAll();
				ImageIcon s= new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\cover1.png");
				Image ne = s.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
				ImageIcon s1 = new ImageIcon(ne);
				String w = "current HP: "+c.getCurrentHP();
				JLabel photo = new JLabel(s1);
				photo.setText(w);
				photo.setHorizontalTextPosition(JLabel.CENTER);
				photo.setVerticalTextPosition(JLabel.BOTTOM);
				photo.setFont(new Font("Serif", 20, 12));
				boardButtons.get(i).add(photo);
				
			}
			else if(game.getBoard()[x][y] ==null) {
				boardButtons.get(i).removeAll();
				boardButtons.get(i).setBackground(Color.gray);
				
			}
			else if (game.getBoard()[x][y] instanceof Champion) {
				
				Champion c = (Champion)(game.getBoard()[x][y]);
				boardButtons.get(i).removeAll();
				JLabel photo = Pics(c.getName(), 40, 40);
				photo.setLayout(new GridLayout(1,2));
				HPbar.setValue(c.getCurrentHP());
				manabar.setValue(c.getMana());
				String s=c.getName()+"\n";
				s +="HP:"+c.getCurrentHP()+"\n"+" Mana:"+c.getMana();
				photo.setText(s);
				photo.setFont(new Font("Arial",Font.BOLD,3));
				photo.setHorizontalTextPosition(JLabel.CENTER);
				photo.setVerticalTextPosition(JLabel.BOTTOM);
				photo.setFont(new Font("Serif", 20, 12));
				if(one.getTeam().contains(c)) {
					boardButtons.get(i).setBackground(Color.green);
				}
				else if(two.getTeam().contains(c)) {
					boardButtons.get(i).setBackground(Color.red);
				}
				boardButtons.get(i).add(photo);
				
				
			}
		}
		theTurn.setText("Turn Order :"+"\n");
		ArrayList<Champion> temp = new ArrayList<Champion>();
		while(!game.getTurnOrder().isEmpty())
			temp.add((Champion) game.getTurnOrder().remove());
		for(int i=0; i<temp.size(); i++){
			game.getTurnOrder().insert(temp.get(i));
			theTurn.setText(theTurn.getText() + temp.get(i).getName()+"\n");
		}
	}
	public JLabel Pics(String chm,int hight,int width) {
		
		ImageIcon icon =new ImageIcon();
		
			switch(chm) {
			case "Captain America" :icon =new ImageIcon("C:\\\\Users\\\\GUC\\\\Dropbox\\\\My PC (DESKTOP-V5L084B)\\\\Downloads\\\\Capw.jfif");break;
			case "Deadpool":  icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Deadp.jfif");break;
			case "Dr Strange" : icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\DRs.jfif");break;
			case "Electro" : icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Electro.jfif");break;
			case "Ghost Rider" :  icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Gostr.jfif");break;
			case "Hela" :  icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Hela.jfif");break;
			case "Hulk" : icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Hulk.jfif");break;
			case "Iceman": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\iceman.jfif");break;
			case "Ironman": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\ironman.jfif");break;
			case "Loki": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\loki.jfif");break;
			case "Quicksilver": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Qs.jfif");break;
			case "Spiderman": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\Spyder.jfif");break;
			case "Thor": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\thor.jfif");break;
			case "Venom": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\venom.jfif");break;
			case "Yellow Jacket": icon =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\jact.jfif");break;


			}
			Image image = icon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(hight, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			ImageIcon icon2= new ImageIcon(newimg);  // t
			JLabel cont = new JLabel(icon2);
			
			return cont;
			
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		whoisplaying();
	
	

	

		if(e.getSource()==move) {
			isMove=true;
			
			Upp2.setEnabled(true);
			Downp2.setEnabled(true);
			leftp2.setEnabled(true);
			rightp2.setEnabled(true);
			this.invalidate();
			this.validate();
			this.repaint();
		}
		if(e.getSource()==attack) {
			isAttack = true;
			Upp2.setEnabled(true);
			Downp2.setEnabled(true);
			leftp2.setEnabled(true);
			rightp2.setEnabled(true);
		}
		if(e.getSource()==castabb) {
			Champion c = game.getCurrentChampion();
			for(int i=0;i<c.getAbilities().size();i++) {
				if(i==0) 
					abb1.setText(c.getAbilities().get(i).getName());
				if(i==1)
					abb2.setText(c.getAbilities().get(i).getName());
				if(i==2)
					abb3.setText(c.getAbilities().get(i).getName());
					
				
			
			
				
			}
			if(c==two.getLeader()) {
				abb1.setEnabled(true);
				abb2.setEnabled(true);
				abb3.setEnabled(true);
				leaderp.setEnabled(true);
			}
			else {
				abb1.setEnabled(true);
				abb2.setEnabled(true);
				abb3.setEnabled(true);
			}
		}
	
	if(e.getSource()==abb1) {
		Champion c = game.getCurrentChampion();
		for(Ability a:c.getAbilities()) {
			if(a.getName().equals(abb1.getText())) {
				ability =a;
				if(a.getCastArea()==AreaOfEffect.DIRECTIONAL) {
					iscastdir = true;
					Upp2.setEnabled(true);
					Downp2.setEnabled(true);
					leftp2.setEnabled(true);
					rightp2.setEnabled(true);
				}
				else if(a.getCastArea()==AreaOfEffect.SINGLETARGET) {
					isCastsing = true;
				}
				else if(a.getCastArea()==AreaOfEffect.SELFTARGET||a.getCastArea()==AreaOfEffect.SURROUND||a.getCastArea()==AreaOfEffect.TEAMTARGET) {
					normalcast = true;
				}
			}
		}
	}
	if(e.getSource()==abb2) {
		Champion c = game.getCurrentChampion();
		for(Ability a:c.getAbilities()) {
			if(a.getName().equals(abb2.getText())) {
				ability =a;
				if(a.getCastArea()==AreaOfEffect.DIRECTIONAL) {
					iscastdir = true;
					Upp2.setEnabled(true);
					Downp2.setEnabled(true);
					leftp2.setEnabled(true);
					rightp2.setEnabled(true);
				}
				else if(a.getCastArea()==AreaOfEffect.SINGLETARGET) {
					isCastsing = true;
				}
				else if(a.getCastArea()==AreaOfEffect.SELFTARGET||a.getCastArea()==AreaOfEffect.SURROUND||a.getCastArea()==AreaOfEffect.TEAMTARGET) {
					normalcast = true;
				}
			}
		}
	}
	if(e.getSource()==abb3) {
		Champion c = game.getCurrentChampion();
		for(Ability a:c.getAbilities()) {
			if(a.getName().equals(abb3.getText())) {
				ability =a;
				if(a.getCastArea()==AreaOfEffect.DIRECTIONAL) {
					iscastdir = true;
				}
				else if(a.getCastArea()==AreaOfEffect.SINGLETARGET) {
					isCastsing = true;
				}
				else if(a.getCastArea()==AreaOfEffect.SELFTARGET||a.getCastArea()==AreaOfEffect.SURROUND||a.getCastArea()==AreaOfEffect.TEAMTARGET) {
					normalcast = true;
				}
			}
		}
	}
	if(e.getSource()==leaderp) {
		try {
			game.useLeaderAbility();
		} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
		}
		makeboard();
		
	}
	if(e.getSource()==Endturn) {
		game.endTurn();
		if(game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			
			champ_info_p2.setText("");
		}
if(game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			
			champ_info_p1.setText("");
		}
		makeboard();
		whoisplaying();
	}
	if(isMove) {
		Champion w =game.getCurrentChampion();
		
		if(e.getSource()==Upp1||e.getSource()==Upp2) {
			try {
				game.move(Direction.UP);
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isMove=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
			
		}
		if(e.getSource()==Downp1||e.getSource()==Downp2) {
			try {
				game.move(Direction.DOWN);
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isMove=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==leftp1||e.getSource()==leftp2) {
			try {
				game.move(Direction.LEFT);
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isMove=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==rightp1||e.getSource()==rightp2) {
			try {
				game.move(Direction.RIGHT);
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isMove=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		
	}
	else if(isAttack) {
		if(e.getSource()==Upp1||e.getSource()==Upp2) {
			try {
				game.attack(Direction.UP);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isAttack=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==Downp1||e.getSource()==Downp2) {
			try {
				game.attack(Direction.DOWN);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isAttack=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==leftp1||e.getSource()==leftp2) {
			try {
				game.attack(Direction.LEFT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isAttack=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==rightp1||e.getSource()==rightp2) {
			try {
				game.attack(Direction.RIGHT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			isAttack=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		
		
	}
	if(iscastdir) {
		if(e.getSource()==Upp2) {
			try {
				game.castAbility(ability, Direction.UP);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			iscastdir=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==Downp2) {
			try {
				game.castAbility(ability, Direction.DOWN);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			iscastdir=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==leftp2) {
			try {
				game.castAbility(ability, Direction.LEFT);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			iscastdir=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		if(e.getSource()==rightp2) {
			try {
				game.castAbility(ability, Direction.RIGHT);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
			}
			iscastdir=false;
			Upp2.setEnabled(false);
			Downp2.setEnabled(false);
			leftp2.setEnabled(false);
			rightp2.setEnabled(false);
			
			makeboard();
		}
		
		
	}
	else if(isCastsing) {
		
		System.out.println("sldjfljsbdvfl");
		for(JButton q:boardButtons) {
			if(e.getSource()==q) {
				int x = getx(q.getName());
				int y = gety(q.getName());
				System.out.println("sldjfljsbdvfl");
				System.out.println(y);
				try {
					game.castAbility(ability, x, y);
				} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
				}
				isCastsing = false;
				
				makeboard();
			}
		}
	}
	if(normalcast) {
		try {
			game.castAbility(ability);
		} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(new JFrame(), e1.getMessage());
		}
		normalcast = false;
		makeboard();
		
	}
	if(game.checkGameOver()!=null) {
		this.dispose();
		JOptionPane.showMessageDialog(new JFrame(), game.checkGameOver().getName() +" Wins");
	}

}}
