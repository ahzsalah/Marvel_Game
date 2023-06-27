package SignUpView;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import SignUPControl.GameGUI;
import engine.*;

import engine.Game;
import engine.GameListener;
import model.abilities.Ability;
import model.world.Champion;

public class view extends JFrame implements GameListener , ActionListener{
   private static boolean stop =false;
    private JPanel allChamp;
    private JPanel firstPlayername;
    private JPanel firstPlayerchamps;
    private JPanel secondPlayerchamps;
    private JPanel secondPlayername;
    private Game game;
    private ArrayList<Champion> champ;
    private JTextField playeronename;
   private boolean choostime=false;
	private JTextField playertwoname;
    private JButton playeroneboo;
    private JButton playertwoboo;
    private String player1name;
    private String player2name;
    private ArrayList<Champion> player1team;
    private ArrayList<Champion> player2team;
    private Champion leader1player;
    private Champion leader2player;
    private ActionListener champion;
    private ActionListener name;
    private ArrayList<JButton> champsles;
    private JTextField random;
    private JButton randomboo;
    private JPanel TextInfo;
    private int randome_num;
    private JLabel ai7aga;
    private JButton next;
    private JTextArea rules;
    private  Player player1;
    private  Player player2;
    private GameGUI play;
    private ArrayList<JButton> choose;
    private boolean heconf = false;
    private JFrame mes;
    private boolean last1=false;
    private boolean last2=false;
    private boolean disp=false;
    private String  namechamp;
    private ArrayList<String> d;
    private Champion ibrahim;
    
    
    @SuppressWarnings("deprecation")
	public view() throws IOException {
    	choose = new ArrayList<JButton>();
    
    //ins	
    	player1 = new Player("");
    	player2 = new Player("");
    	d = new ArrayList<String>();
    	
    game = new Game();
   champ = game.getAvailableChampions();
   player1team = new ArrayList<Champion>();
   player2team = new ArrayList<Champion>();
   champsles = new ArrayList<JButton>();
   ImageIcon x =new ImageIcon("C:\\Users\\GUC\\Dropbox\\My PC (DESKTOP-V5L084B)\\Downloads\\logo.png");
   Image last = x.getImage().getScaledInstance(1000, 1000,  java.awt.Image.SCALE_SMOOTH);
  
    this.setIconImage(last);
      this.setTitle("Marvel Game");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(1000,500);
      this.setLayout(new BorderLayout(0,0));
      this.setExtendedState(JFrame.MAXIMIZED_BOTH);
      
      
      
      //main
      allChamp = new JPanel();
      firstPlayerchamps = new JPanel();
      secondPlayerchamps = new JPanel();
      TextInfo = new JPanel();
       //sub panel 
      secondPlayername = new JPanel();
      firstPlayername = new JPanel();
      
      //next
      TextInfo.setLayout(null);
      next = new JButton("Press to play");
      next.addActionListener(this);
      next.setEnabled(false);
      //random
      random = new JTextField();
      randomboo = new JButton("press toss");
      random.setEditable(false);
      randomboo.addActionListener(this);
      //rules 
      String s = "Hello, Welcome to the best game \n in the GUC!! \n Press on the champ to see their info\n The first picked Champion \n will be your leader:) \n Choose Carfully!!";
      rules = new JTextArea(s);
      rules.setBackground(Color.gray);
      
      rules.setFont(new Font("Arial",Font.BOLD,15));
      rules.setEditable(false);
      
      
    
      //location
      rules.setBounds(20, 300, 800, 700);
      random.setBounds(10, 10, 150, 50);
      randomboo.setBounds(200, 10, 150, 50);
      next.setBounds(100, 200, 150, 50);
      
      
      
     //ading to textarea
      TextInfo.add(rules);
     TextInfo.add(random);
     TextInfo.add(next);
    TextInfo.add(randomboo);
      
     
      
      //set colors
      allChamp.setBackground(Color.gray);
      firstPlayerchamps.setBackground(Color.BLUE);
      secondPlayerchamps.setBackground(Color.BLUE);
      secondPlayername.setBackground(new Color(205,9,35));
      firstPlayername.setBackground(new Color(205,9,35));
      TextInfo.setBackground(Color.gray);
      
      //diminsion
      TextInfo.setPreferredSize(new Dimension(233,440));
      allChamp.setPreferredSize(new Dimension(1000,300));
      firstPlayerchamps.setPreferredSize(new Dimension(500,400));
      secondPlayerchamps.setPreferredSize(new Dimension(500,400));
      secondPlayername.setPreferredSize(new Dimension(500,100));
      firstPlayername.setPreferredSize(new Dimension(500,100));
      
      //name Bootons and names first player
     playeroneboo = new JButton("press here to enter your name");
     playeroneboo.addActionListener(this);
     playeronename = new JTextField("Enter your name: ");
      firstPlayername.add(playeroneboo);
      firstPlayername.add(playeronename);
      
      //name Bootons and names second player
     playertwoboo = new JButton("press here to enter your name");
     playertwoboo.addActionListener(this);
     playertwoname = new JTextField("Enter your name: ");
      secondPlayername.add(playertwoboo);
      secondPlayername.add(playertwoname);
      
      
      
       //sub
      firstPlayerchamps.setLayout(new BorderLayout());
      firstPlayerchamps.add(firstPlayername , BorderLayout.NORTH);
     
      
      secondPlayerchamps.setLayout(new BorderLayout());
      secondPlayerchamps.add(secondPlayername , BorderLayout.NORTH);
      
      //gride
    allChamp.setLayout(new GridLayout(0,3));
   
     
      //main
    this.getContentPane().add(TextInfo,BorderLayout.CENTER );
      this.getContentPane().add(allChamp ,BorderLayout.SOUTH);
      this.getContentPane().add(firstPlayerchamps ,BorderLayout.WEST);
      this.getContentPane().add(secondPlayerchamps ,BorderLayout.EAST);
     
    addChampions(game);
      
    this.setVisible(true);
    	
    	
      
    	
     
    }
    public Champion make(Champion c) {
    	 mes = new JFrame();
  
    	mes.setTitle(c.getName()+"  Stuff");
    	mes.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	mes.setSize(1000,1000);
    	mes.setLayout(new GridLayout());
    	mes.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	JPanel all = new JPanel();
    	JLabel pic = Pics(c.getName(),500,650);
    	pic.setAlignmentX(CENTER_ALIGNMENT);
    	pic.setAlignmentY(CENTER_ALIGNMENT);
    	pic.setBounds(0, 0, 2000, 2000);
    	all.setBackground(Color.gray);
        all.add(pic);
    	JPanel Buttons = new JPanel();
    	Buttons.setLayout(new GridLayout(1,3));
    	Buttons.setBounds(0, 0, 1000, 1000);
    	JButton ok = new JButton("ADD HIM");
    	ok.addActionListener(this);
    	ok.setBounds(50, 30, 500, 500);
    	ok.setBackground(Color.green);
    	JButton no = new JButton("Dont ADD HIM");
    	no.addActionListener(this);
    	no.setBounds(70, 30, 150, 50);
    	no.setBackground(Color.red);
    	choose.add(no);
    	choose.add(ok);
    	Buttons.add(ok,BorderLayout.WEST);
    	Buttons.add(no,BorderLayout.WEST);
    	
    	
    
    	String s = "The abbilitys is: ";
    	for(Ability a: c.getAbilities()) {
    		s+="\n"+a.getName()+"\n"+"-> Cooldown is:"+a.getBaseCooldown()+"\n"+"-> Range is:"+a.getCastRange()+"\n"+"-> mana cost is:"+a.getManaCost()+"\n";
    	}
    	s+= "\n" +"The Chmapion's Attack Range is :"+ c.getAttackRange();
    	s+= "\n" +"The Chmapion's Attack Dmage is :"+ c.getAttackDamage();
    	s+= "\n" +"The Chmapion's Health is :"+ c.getMaxHP();
    	s+= "\n" +"The Chmapion's Mana is :"+ c.getMana();
    	s+= "\n" +"The Chmapion's Speed is :"+ c.getSpeed();
    		
    	JTextArea text = new JTextArea(s);
    	text.setFont(new Font("Serif", 10, 20));
    	text.setLayout(null);
    	text.setBounds(0, 0, 1000, 1000);
    	text.setBackground(Color.gray);
    	text.setEditable(false);
    	Buttons.add(text,BorderLayout.CENTER);
    	Buttons.add(all,BorderLayout.EAST);
    	Buttons.setEnabled(false);
    	mes.add(Buttons);
    	mes.setVisible(true);
    	return c;
    	
    }
    public void addChampions(Game game) {
    	JTextArea s = new JTextArea("The Champions are: ");
    	s.setBackground(allChamp.getBackground());
    	s.setEditable(false);
    	s.setFont(new Font("Serif", 20, 40));
    	allChamp.add(s);
        
      ArrayList<Champion> champ = game.getAvailableChampions();
         for(Champion e: champ) {
	  JButton name = new JButton(e.getName());
	  name.addActionListener(this);
	  champsles.add(name);
	  allChamp.add(name);
}

    }
    public void doplayer(String player1name , Champion Leaderone , ArrayList<Champion> team1,String player2name , Champion Leadertwo , ArrayList<Champion> team2) {
    	player1.setName(player1name);
    	player1.setLeader(Leaderone);
    	player1.setTeam(team1);
    	player2.setName(player2name);
    	player2.setLeader(Leadertwo);
    	player2.setTeam(team2);
    	
    }
    
    public void updateName(String name,int player) {
   
    	if(player==1) {
    	
    	String output ="First Player name is: \n "+ name;
     	JTextArea nameO = new JTextArea(output); 
     	nameO.setBackground(firstPlayername.getBackground());
     	nameO.setEditable(false);
     	nameO.setFont(new Font("Serif", 20, 20));
     	nameO.setLayout(new BorderLayout());
     
     	firstPlayername.add(nameO,BorderLayout.CENTER);
    	
    	
    	}else if(player==2) {
    		String output ="Secound  Player name is:  \n "+ name;
    		JTextArea nameO = new JTextArea(output);
    		nameO.setBackground(secondPlayername.getBackground());
         	nameO.setEditable(false);
         	nameO.setFont(new Font("Serif", 20, 20));

         	nameO.setLayout(new BorderLayout());
    		secondPlayername.add(nameO);
    	}
    	
    	
    }
    public void picChampions(JTextArea jTextArea,int player) {
    	if(player==1) {
    		jTextArea.setFont(new Font("Serif", 20, 20));
    		firstPlayerchamps.add(jTextArea,BorderLayout.CENTER);
    	}
    	else if(player==2) {
    		jTextArea.setFont(new Font("Serif", 20, 20));

    		secondPlayerchamps.add(jTextArea);
    	}
    	}
    	
    
	
public static void main(String[] args) throws IOException {
	new view();
	
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
public void setsname(String name,int player) {
	if(player==1) {
		player1name = name;
	}
	else if(player==2) {
		player2name = name;
	}
	
}
@Override
public void actionPerformed(ActionEvent e) {
	 for(JButton q :choose) {
			if(e.getSource()==q) {
				if(q.getText().equals("ADD HIM")) {
					JButton b = new JButton();
					for(JButton c : champsles) {
						if(c.getText()==ibrahim.getName()) {
							b=c;
						}
					}
					
					if(randome_num==1&&player1team.size()==0) {
						
						 ai7aga = new JLabel();
						String s = "The Leader is : ";
						
						String namechamp=b.getText();
						
						for(Champion c : champ) {
							if(c.getName().equals(namechamp)) {
								 ai7aga = Pics(c.getName(),150,100);
								leader1player = c;
								player1team.add(c);
								ai7aga.setText(s+c.getName());
								System.out.println(c.getName());
							}
							
						}
						randome_num =2;
						ai7aga.setVerticalAlignment(JLabel.TOP);
						ai7aga.setHorizontalAlignment(JLabel.LEFT);
						firstPlayerchamps.add(ai7aga);
						b.setEnabled(false);
						this.invalidate();
						this.validate();
						this.repaint();
						if(heconf) {
							 heconf = false;
						 }
						break;
						}
				if(randome_num==1&&player1team.size()==1) {
					 ai7aga = new JLabel();
					String s = "The Second Champion is : ";
							String namechamp=b.getText();
							for(Champion c : champ) {
								if(c.getName().equals(namechamp)) {
									ai7aga = Pics(c.getName(),150,100);
									ai7aga.setText(s+c.getName());
									player1team.add(c);
									System.out.println(c.getName());
								}
								
							}
							ai7aga.setVerticalAlignment(JLabel.CENTER);
							ai7aga.setHorizontalAlignment(JLabel.LEFT);
							firstPlayerchamps.add(ai7aga);
							randome_num=2;
							b.setEnabled(false);
							this.invalidate();
							this.validate();
							this.repaint();
							if(heconf) {
								 heconf = false;
							 }
							break;
							}
				if(randome_num==1&&player1team.size()==2) {
					 ai7aga = new JLabel();
					String s = "The Third Champion is : ";
							String namechamp=b.getText();
							for(Champion c : champ) {
								if(c.getName().equals(namechamp)) {
									ai7aga = Pics(c.getName(),150,100);
									ai7aga.setText(s+c.getName());
									player1team.add(c);
									System.out.println(c.getName());
								}
								
							}
							ai7aga.setVerticalAlignment(JLabel.BOTTOM);
							ai7aga.setHorizontalAlignment(JLabel.LEFT);
							firstPlayerchamps.add(ai7aga);
							randome_num=2;
							b.setEnabled(false);
							this.invalidate();
							this.validate();
							this.repaint();
							if(heconf) {
								 heconf = false;
							 }
			last1 = true;
							break;
							}
				if(randome_num==2&&player2team.size()==0) {
					 ai7aga = new JLabel();
					String s ="The Leader is : ";
					String namechamp=b.getText();
					for(Champion c : champ) {
						if(c.getName().equals(namechamp)) {
							ai7aga = Pics(c.getName(),150,100);
							leader2player = c;
							ai7aga.setText(s+c.getName());
							player2team.add(c);
							System.out.println(c.getName());
						}
						
					}
					ai7aga.setVerticalAlignment(JLabel.TOP);
					ai7aga.setHorizontalAlignment(JLabel.LEFT);
					randome_num =1;
					secondPlayerchamps.add(ai7aga);
					b.setEnabled(false);
					this.invalidate();
					this.validate();
					this.repaint();
					if(heconf) {
						 heconf = false;
					 }
					break;
					}
			if(randome_num==2&&player2team.size() ==1) {
				 ai7aga = new JLabel();
				String s = "The Second Champion is : ";
						String namechamp=b.getText();
						for(Champion c : champ) {
							if(c.getName().equals(namechamp)) {
								ai7aga = Pics(c.getName(),150,100);
								ai7aga.setText(s+c.getName());
								player2team.add(c);
								System.out.println(c.getName());
							}
							
						}
						randome_num=1;
						ai7aga.setVerticalAlignment(JLabel.CENTER);
						ai7aga.setHorizontalAlignment(JLabel.LEFT);
						secondPlayerchamps.add(ai7aga);
						b.setEnabled(false);
						this.invalidate();
						this.validate();
						this.repaint();
						if(heconf) {
							 heconf = false;
						 }
						break;
						
						}
			if(randome_num==2&&player2team.size() ==2 ) {
				 ai7aga = new JLabel();
				String s ="The Third Champion is : ";
						String namechamp=b.getText();
						for(Champion c : champ) {
							if(c.getName().equals(namechamp)) {
								ai7aga = Pics(c.getName(),150,100);
								ai7aga.setText(s+c.getName());
								player2team.add(c);
								System.out.println(c.getName());
							}
							
						}
						randome_num=1;
						ai7aga.setVerticalAlignment(JLabel.BOTTOM);
						ai7aga.setHorizontalAlignment(JLabel.LEFT);
						secondPlayerchamps.add(ai7aga);
						b.setEnabled(false);
						this.invalidate();
						this.validate();
						this.repaint();
						if(heconf) {
							 heconf = false;
						 }
						last2 = true;
						break;
						
						}

				
					mes.setVisible(false);
					}}
				
				else if(q.getText().equals("Dont ADD HIM"))
					heconf = false;
				
				   mes.setVisible(false);
					
				
			}
		
	if(e.getSource()==next) {
		this.setVisible(false);
		doplayer(player1name,leader1player,player1team,player2name,leader2player,player2team);
		try {
			play = new GameGUI(player1,player2);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
	}
	

	if(e.getSource()==randomboo) {
		 randome_num = (int) ((Math.random()*2)+1);
		if( randome_num ==1) {
		random.setText(player1name +" will start");
		randomboo.setEnabled(false);
		random.setEditable(false);
		choostime = true;
		}else {
			random.setText(player2name+" will start");
			randomboo.setEnabled(false);
			random.setEditable(false);
			choostime = true;
		}
	}
	
	
	if(e.getSource()==playertwoboo) {
      player2name = playertwoname.getText();
      playertwoboo.setEnabled(false);
      playertwoname.setEditable(false);
      System.out.println(player2name);
      
     
	}
	else if(e.getSource()==playeroneboo) {
	      player1name = playeronename.getText();
	      playeroneboo.setEnabled(false);
	      playeronename.setEditable(false);
	      System.out.println(player1name);
		}
	
	
	
	
	 for(JButton b : champsles) {
			if(player1team.size()==3&&player2team.size()==3) {
				for(JButton q : champsles) {
					q.setEnabled(false);
				}
				next.setEnabled(true);
				
			}
		
		
		if(e.getSource()==b) {

			 namechamp=b.getText();
			
			for(Champion c : champ) {
				if(c.getName().equals(namechamp)&&!heconf&&choostime) {
					ibrahim=make(c);
					break;
					
					
				}}
			
		}
		
		
	
	
	}
	 
}public Player getPlayer1() {
	return player1;
}

	
public Player getPlayer2() {
	return player2;
}


}