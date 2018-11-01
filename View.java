import java.awt.*;
import javax.swing.*;
//import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class View extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;

  JLabel viewPlayer;
  JLabel viewRank;
  JLabel viewCurrency;
  JLabel viewLocation;
  JLabel viewRole;
  JLabel viewAction;
  JLabel viewMove;
  JLabel viewDays;

  JLabel redPlayer;
  JLabel bluePlayer;
  JLabel yellowPlayer;
  JLabel purplePlayer;

  //JButtons
  JButton work;
  JButton move;
  JButton act;
  JButton rehearse;
  JButton end;
  JButton option1;
  JButton option2;
  JButton option3;
  JButton option4;
  JButton option5;
  JButton option6;
  JButton option7;
  JButton cancel;
  JButton upgrade;
  // JLayered Pane
  JLayeredPane bPane;

  String command;
  int option;

  // Constructor

  public View() {

       // Set the title of the JFrame
       super("Deadwood");
       // Set the exit option for the JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       // Create the JLayeredPane to hold the display, cards, dice and buttons

       bPane = getLayeredPane();

       // Create the deadwood board
       boardlabel = new JLabel();
       ImageIcon icon =  new ImageIcon("board.jpg");
       boardlabel.setIcon(icon);
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
       bPane.add(boardlabel, new Integer(0));

       // Set the size of the GUI
       setSize(icon.getIconWidth()+200,icon.getIconHeight());

       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+66,0,150,20);
       bPane.add(mLabel,new Integer(2));

       // Create Action buttons
       work = new JButton("WORK");
       work.setBackground(Color.white);
       work.setBounds(icon.getIconWidth()+10, 30,150, 20);
       work.addMouseListener(new boardMouseListener());

       move = new JButton("MOVE");
       move.setBackground(Color.white);
       move.setBounds(icon.getIconWidth()+10,60,150, 20);
       move.addMouseListener(new boardMouseListener());

       act = new JButton("ACT");
       act.setBackground(Color.white);
       act.setBounds(icon.getIconWidth()+10,90,150, 20);
       act.addMouseListener(new boardMouseListener());

       rehearse = new JButton("REHEARSE");
       rehearse.setBackground(Color.white);
       rehearse.setBounds(icon.getIconWidth()+10,120,150, 20);
       rehearse.addMouseListener(new boardMouseListener());

       end = new JButton("END TURN");
       end.setBackground(Color.white);
       end.setBounds(icon.getIconWidth()+10,150,150, 20);
       end.addMouseListener(new boardMouseListener());

       option1 = new JButton("1");
       option1.setBackground(Color.white);
       option1.setBounds(icon.getIconWidth()+10,210,180,20);
       option1.addMouseListener(new boardMouseListener());

       option2 = new JButton("2");
       option2.setBackground(Color.white);
       option2.setBounds(icon.getIconWidth()+10,240,180,20);
       option2.addMouseListener(new boardMouseListener());

       option3 = new JButton("3");
       option3.setBackground(Color.white);
       option3.setBounds(icon.getIconWidth()+10,270,180,20);
       option3.addMouseListener(new boardMouseListener());

       option4 = new JButton("4");
       option4.setBackground(Color.white);
       option4.setBounds(icon.getIconWidth()+10,300,180,20);
       option4.addMouseListener(new boardMouseListener());

       option5 = new JButton("5");
       option5.setBackground(Color.white);
       option5.setBounds(icon.getIconWidth()+10,330,180,20);
       option5.addMouseListener(new boardMouseListener());

       option6 = new JButton("6");
       option6.setBackground(Color.white);
       option6.setBounds(icon.getIconWidth()+10,360,180,20);
       option6.addMouseListener(new boardMouseListener());

       option7 = new JButton("7");
       option7.setBackground(Color.white);
       option7.setBounds(icon.getIconWidth()+10,390,180,20);
       option7.addMouseListener(new boardMouseListener());

       upgrade = new JButton("UPGRADE");
       upgrade.setBackground(Color.white);
       upgrade.setBounds(icon.getIconWidth()+ 10, 420, 150, 20);
       upgrade.addMouseListener(new boardMouseListener());

       cancel = new JButton("Cancel");
       cancel.setBackground(Color.white);
       cancel.setBounds(icon.getIconWidth()+10,180,150,20);
       cancel.addMouseListener(new boardMouseListener());

       viewPlayer = new JLabel("Player");
       viewPlayer.setBounds(icon.getIconWidth() +10, 450, 180, 20);
       bPane.add(viewPlayer, new Integer(2));

       viewRank = new JLabel("Rank");
       viewRank.setBounds(icon.getIconWidth() +10, 480, 180, 20);
       bPane.add(viewRank, new Integer(2));

       viewCurrency = new JLabel("Currency");
       viewCurrency.setBounds(icon.getIconWidth() +10, 510, 180, 20);
       bPane.add(viewCurrency, new Integer(2));

       viewLocation = new JLabel("Location");
       viewLocation.setBounds(icon.getIconWidth() +10, 540, 180, 20);
       bPane.add(viewLocation, new Integer(2));

       viewRole = new JLabel("Role");
       viewRole.setBounds(icon.getIconWidth() +10, 570, 180, 20);
       bPane.add(viewRole, new Integer(2));

       viewAction = new JLabel("Action");
       viewAction.setBounds(icon.getIconWidth() +10, 600, 180, 20);
       bPane.add(viewAction, new Integer(2));

       viewMove = new JLabel("Move");
       viewMove.setBounds(icon.getIconWidth() + 10, 630, 180, 20);
       bPane.add(viewMove, new Integer(2));

       viewDays = new JLabel("Days Remaining");
       viewDays.setBounds(icon.getIconWidth() +-780, 0, 385, 20);
       viewDays.setBackground(Color.cyan);
       viewDays.setOpaque(true);
       bPane.add(viewDays, new Integer(2));

       // Place the action buttons in the top layer
       bPane.add(work, new Integer(2));
       bPane.add(end, new Integer(2));
       bPane.add(move, new Integer(2));
       bPane.add(act, new Integer(2));
       bPane.add(rehearse, new Integer(2));
       bPane.add(option1, new Integer(2));
       bPane.add(option2, new Integer(2));
       bPane.add(option3, new Integer(2));
       bPane.add(option4, new Integer(2));
       bPane.add(option5, new Integer(2));
       bPane.add(option6, new Integer(2));
       bPane.add(option7, new Integer(2));
       bPane.add(upgrade, new Integer(2));
       bPane.add(cancel, new Integer(2));
  }

  // This class implements Mouse Events

  class boardMouseListener implements MouseListener{

      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

         if (e.getSource()== work){
            command = "work";
         }
         else if (e.getSource()== end){
            command = "end";
         }
         else if (e.getSource()== move){
            command = "move";
         }else if(e.getSource() ==  act){
           command = "act";
         }else if(e.getSource() == rehearse){
           command = "rehearse";
         }else if (e.getSource()== cancel){
           option = -1;
         }else if(e.getSource()== option1){
           option = 1;
         }else if(e.getSource()== option2){
           option = 2;
         }else if(e.getSource()== option3){
           option = 3;
         }else if(e.getSource()== option4){
           option = 4;
         }else if(e.getSource()== option5){
           option = 5;
         }else if(e.getSource()== option6){
           option = 6;
         }else if(e.getSource()== option7){
           option = 7;
         }else if(e.getSource()== upgrade){
           command = "upgrade";
         }
      }
      public void mousePressed(MouseEvent e) {
      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }



   public void CreatePlayers(int numPlayers,Trailer trailer){
     int[] trailerArea = trailer.getArea();
        redPlayer = new JLabel();
        ImageIcon redPlayerIcon = new ImageIcon("dice/r1.png");
        redPlayer.setIcon(redPlayerIcon);
        bluePlayer = new JLabel();
        ImageIcon bluePlayerIcon = new ImageIcon("dice/b1.png");
        bluePlayer.setIcon(bluePlayerIcon);

        redPlayer.setBounds(trailerArea[0] +50, trailerArea[1]+50, redPlayerIcon.getIconWidth(), redPlayerIcon.getIconHeight());
        bPane.add(redPlayer, new Integer(3));
        bluePlayer.setBounds(trailerArea[0] +100, trailerArea[1]+100, bluePlayerIcon.getIconWidth(), bluePlayerIcon.getIconHeight());
        bPane.add(bluePlayer, new Integer(3));
      if(numPlayers >= 3){
        yellowPlayer = new JLabel();
        ImageIcon yellowPlayerIcon = new ImageIcon("dice/y1.png");
        yellowPlayer.setIcon(yellowPlayerIcon);
        yellowPlayer.setBounds(trailerArea[0] +50, trailerArea[1]+100, yellowPlayerIcon.getIconWidth(), yellowPlayerIcon.getIconHeight());
        bPane.add(yellowPlayer, new Integer(3));
       }
      if(numPlayers >= 4){
        purplePlayer = new JLabel();
        ImageIcon purplePlayerIcon = new ImageIcon("dice/v1.png");
        purplePlayer.setIcon(purplePlayerIcon);
        purplePlayer.setBounds(trailerArea[0] +100, trailerArea[1]+50, purplePlayerIcon.getIconWidth(), purplePlayerIcon.getIconHeight());
        bPane.add(purplePlayer, new Integer(3));

      }
   }

   public void movePlayer(String playerName, int x, int y){
     if(playerName.equals("Red")){

       redPlayer.setLocation(x, y);
     }else if(playerName.equals("Blue")){
       bluePlayer.setLocation(x, y);
     }else if(playerName.equals("Yellow")){
       yellowPlayer.setLocation(x, y);
     }else if(playerName.equals("Purple")){
       purplePlayer.setLocation(x, y);
     }
   }

   public static int findPlayers(){
     Object[] possibilities = {"2", "3", "4"};
     String picked = (String)JOptionPane.showInputDialog(
                  null, "Choose your number of players.\n",
                  "WELCOME TO DEADWOOD", JOptionPane.DEFAULT_OPTION,
                  null, possibilities, "0");
                  Integer numPlayers = Integer.parseInt(picked);
                  return numPlayers;
    }

    public void playerView(Player player){
      viewPlayer.setText("Player: " + player.getName());
      viewRank.setText("Rank: " + player.getRank() + " Rehearse: " + player.getRehearsalMarker());
      viewCurrency.setText("Money: " + player.getMoney() + " Fame: " + player.getFame());
      viewLocation.setText("Location: " + player.getLocation().getName());
      if(player.getCurrentRole() == null){
          viewRole.setText("Current Role: " + null);
      }else{
        viewRole.setText("Current Role: " + player.getCurrentRole().getName());
      }
      viewAction.setText("Has Action: " + player.hasAction());
      viewMove.setText("Has Move: " + player.hasMove());
    }

    public void moveView(StageBase[] neighbors){
      cancel.setVisible(true);
      option1.setText(neighbors[0].getName());
      option2.setText(neighbors[1].getName());
      option3.setText(neighbors[2].getName());

      option1.setVisible(true);
      option2.setVisible(true);
      option3.setVisible(true);

      if(neighbors.length == 4){
        option4.setText(neighbors[3].getName());
        option4.setVisible(true);
      }
    }

    public void dayView(int day, int scene){

      viewDays.setText("  Days Remaining: " + day + ".  # of Scenes Completed Today: " + scene);

    }

    public void viewUpgrade(int[][] cash, int[][] credits){
      option2.setText("Level 2");
      option3.setText("Level 3");
      option4.setText("Level 4");
      option5.setText("Level 5");
      option6.setText("Level 6");

      option2.setVisible(true);
      option3.setVisible(true);
      option4.setVisible(true);
      option5.setVisible(true);
      option6.setVisible(true);
    }

    public void viewUpgrade2(){
      option1.setText("Cash");
      option2.setText("Fame");

      option1.setVisible(true);
      option2.setVisible(true);
    }

    public void updateLevel(String player, int level){
      char firstChar = player.charAt(0);
      firstChar = Character.toLowerCase(firstChar);
      ImageIcon playerIcon  = new ImageIcon("dice/"+firstChar+ level +".png");

      if(firstChar == 'r'){
        redPlayer.setIcon(playerIcon);
      }
      else if(firstChar == 'b'){
        bluePlayer.setIcon(playerIcon);
      }
      else if(firstChar=='y'){
        yellowPlayer.setIcon(playerIcon);
      }
      else if(player.equals("Purple")){
        purplePlayer.setIcon(new ImageIcon("dice/v" + level +".png"));
      }

    }

    public void workView(Role[] card, Role[] stage){
      cancel.setVisible(true);
      option1.setText(card[0].getName());
      option1.setVisible(true);
      if(card.length == 1){
        option2.setText(stage[0].getName());
        option2.setVisible(true);
        option3.setText(stage[1].getName());
        option3.setVisible(true);
        if(stage.length == 3){
          option4.setText(stage[2].getName());
          option4.setVisible(true);
        }else if(stage.length == 4){
          option4.setText(stage[2].getName());
          option5.setText(stage[3].getName());
          option4.setVisible(true);
          option5.setVisible(true);
        }
      }else{
        option2.setText(card[1].getName());
        option2.setVisible(true);
        if(card.length == 3){
          option3.setText(card[2].getName());
          option3.setVisible(true);
          option4.setText(stage[0].getName());
          option5.setText(stage[1].getName());
          option4.setVisible(true);
          option5.setVisible(true);
          if(stage.length == 3){
            option6.setText(stage[2].getName());
            option6.setVisible(true);
          }else if(stage.length == 4){
            option6.setText(stage[2].getName());
            option7.setText(stage[3].getName());
            option6.setVisible(true);
            option7.setVisible(true);
          }
      }else{
        option3.setText(stage[0].getName());
        option3.setVisible(true);
        option4.setText(stage[1].getName());
        option4.setVisible(true);
        if(stage.length == 3){
          option5.setText(stage[2].getName());
          option5.setVisible(true);
        }else if(stage.length == 4){
          option5.setText(stage[2].getName());
          option6.setText(stage[3].getName());
          option5.setVisible(true);
          option6.setVisible(true);
        }
      }
    }
  }

    public JLabel[] drawShotCounts(int[][] area){

      JLabel shot1 = new JLabel();
      ImageIcon sIcon = new ImageIcon("shot.png");
      shot1.setIcon(sIcon);
      JLabel shot2 = new JLabel();
      shot2.setIcon(sIcon);
      JLabel shot3 = new JLabel();
      shot3.setIcon(sIcon);

      JLabel[] labels = new JLabel[3];

      shot1.setBounds(area[0][0], area[0][1], area[0][2], area[0][3]);
      bPane.add(shot1, new Integer(2));

      labels[0] = shot1;

      if(area.length == 2){
        shot2.setBounds(area[1][0], area[1][1], area[1][2], area[1][3]);
        bPane.add(shot2, new Integer(2));
        labels[1] = shot2;
      }else if(area.length == 3){
        shot2.setBounds(area[1][0], area[1][1], area[1][2], area[1][3]);
        shot3.setBounds(area[2][0], area[2][1], area[2][2], area[2][3]);
        bPane.add(shot2, new Integer(2));
        bPane.add(shot3, new Integer(2));
        labels[1] = shot2;
        labels[2] = shot3;
      }
      return labels;
    }

    public void viewRemoveShot(JLabel[] labels, int count){
      for(int i = 0; i < count; i++){
        labels[i].setVisible(false);
      }
    }

    public JLabel drawScenes(int[] area, Card cur){
    JLabel scene = new JLabel();
    ImageIcon sIcon = new ImageIcon("cards/" + cur.getImageFileName());
    scene.setIcon(sIcon);

    scene.setBounds(area[0], area[1] - 150, sIcon.getIconHeight() + area[2],  sIcon.getIconWidth() + area[3]);
    bPane.add(scene, new Integer(2));
    scene.setVisible(false);
    return scene;
  }

  public void discoverScene(JLabel label){
    label.setVisible(true);
  }

  public void removeScene(JLabel label){
    label.setVisible(false);
  }

    public void resetButtons(){
      option1.setText("1");
      option2.setText("2");
      option3.setText("3");
      option4.setText("4");
      option5.setText("5");
      option6.setText("6");
      option7.setText("7");

      option1.setVisible(false);
      option2.setVisible(false);
      option3.setVisible(false);
      option4.setVisible(false);
      option5.setVisible(false);
      option6.setVisible(false);
      option7.setVisible(false);
      cancel.setVisible(false);
    }

    public int getOption(){
      option = 0;
      while(option == 0){
        try{
          Thread.sleep(5);
        }catch(InterruptedException e) {}
      }
      return option;
    }

    public String getCommand(){
      command = null;
      while(command == null){
        try{
          Thread.sleep(500);
        }catch(InterruptedException e) {}
      }
      return command;
    }
    public void displayWinner(Player winner){
      viewDays.setText("The winner is: " + winner.getName() + "! Points: " + winner.getScore());
      viewDays.setLocation(boardlabel.getIcon().getIconWidth()/2-210,boardlabel.getIcon().getIconHeight()/2);
    }

}
