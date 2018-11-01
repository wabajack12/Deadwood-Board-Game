import java.util.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class TheGame{

  private View view = new View();

  private ArrayList<Player> listOfPlayers;
  private Deck deck;
  private Board theBoard;
  private DocumentBuilderFactory dbFactory;
  private DocumentBuilder dBuilder;
  private Document cardDoc;
  private Document stageDoc;
  private Player currPlayer;
  private int dayCount = 3;
  private int sceneCount = 0;
  private File cardFile;
  private File boardFile;

  public TheGame(){

    this.listOfPlayers = new ArrayList<Player>();
    this.deck = new Deck();
    this.theBoard = new Board();
    boardFile = new File("board.xml");
    cardFile = new File("cards.xml");
    this.dbFactory = DocumentBuilderFactory.newInstance();

    try{
      this.dBuilder = dbFactory.newDocumentBuilder();
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  private String getStringInput(){
    Scanner Userinput = new Scanner(System.in);
    String word = Userinput.nextLine();
    return word;
  }


  public void runGame(){

    Player[] playerArray = new Player[listOfPlayers.size()];
    playerArray = listOfPlayers.toArray(playerArray);

    int index = 0;

    while(dayCount != 0){
      sceneCount = 0;
      initDay();
      //the scene is complete when there are 9 scenes complete
      while(sceneCount < 9){
        int numPlayers = playerArray.length;
        currPlayer = playerArray[index%numPlayers];
        currPlayer.setAction(true);
        turn();
        index++;
      }
    dayCount--;
  }
  //game over
  Player winner = null;
  int tally = 0;
  for(int i = 0; i < playerArray.length; i++){
    if(playerArray[i].getScore() > tally){
      tally = playerArray[i].getScore();
      winner = playerArray[i];
    }
  }
  view.displayWinner(winner);
    //close
  }

  public void initGame(){
    try{
      cardDoc = dBuilder.parse(cardFile);
      stageDoc = dBuilder.parse(boardFile);
    }catch(Exception e){
      e.printStackTrace();
    }
    deck.init(cardDoc.getElementsByTagName("card"));
    theBoard.initStages(stageDoc);
    initPlayers();
    view.setVisible(true);


  }

  private void initPlayers(){
    int numplayers = view.findPlayers();
    view.CreatePlayers(numplayers, theBoard.getTrailer());

    String[] colors = new String[]{"Red", "Blue", "Yellow", "Purple"};
    for(int i = 0; i < numplayers; i++){
      listOfPlayers.add(new Player(colors[i],theBoard.getTrailer(), i));
    }
  }

  private void initDay(){
     //Sets up shot counts on stages
     //Gets cards
     //Sets status of cards to hidden
     deck.shuffle();
     ArrayList<Stage>stages = theBoard.getStages();
     for(Stage stg: stages){
       stg.resetStage();
       Card cur = deck.getNextCard();
       stg.setCard(cur);
       JLabel[] labels = view.drawShotCounts(stg.getTakeAreas());
       stg.setShotLabel(labels);
       JLabel cardLabel = view.drawScenes(stg.getArea(), cur);
       cur.setCardLabel(cardLabel);
       stg.clearRoles();
     }

     //This loop sends all of players to trailer
     Player[] playerArray = new Player[listOfPlayers.size()];
     playerArray = listOfPlayers.toArray(playerArray);
     for(int i = 0; i < playerArray.length; i++){
       currPlayer = playerArray[i];
       currPlayer.setCurrentRole(null);
       int[] area = theBoard.getTrailer().getArea();
       view.movePlayer(currPlayer.getName(),area[0]+15+(i*45), area[1]+100);
       currPlayer.setLocation(theBoard.getTrailer());
     }
  }
  // for where
  private void printLocations(Player currPlayer){
    String name = currPlayer.getName();
    String curLocal = currPlayer.getLocation().getName();
    Stage curStage = theBoard.getStage(curLocal);
    String curScene = null;
    boolean hasAction = currPlayer.hasAction();
    //print location
    //print scene
    if(curStage!=null && curStage.getStatus()){
      curScene = curStage.getCard().getName();
    }else{
    }
  }

  private void turn(){
    boolean turnOver = false;
    view.resetButtons();
    while(!turnOver){
      view.playerView(currPlayer);
      view.dayView(dayCount, sceneCount);


      String command = view.getCommand();
      //the available option for the playerArray
      // who, where, work, move, upgrade, rehearse, act, or end.
      switch(command){
        case "who":
          break;
        case "where":
          break;
        case "work":
          if(currPlayer.hasAction() && currPlayer.getLocation().isStage()){
            Stage curStage = theBoard.getStage(currPlayer.getLocation().getName());
            if(currPlayer.hasRole()){
              break;
            }else{
              if((curStage.getStatus()) && (curStage.getShotCounter() != 0)){
                takeARole();
              }else{
              }
            }
          }else if(!currPlayer.hasAction()){
          }else{
          }
            break;
        case "move":
          //if player moved
          if(currPlayer.hasMove() && !currPlayer.hasRole() && currPlayer.hasAction()){
            move();
            view.resetButtons();
          }else if(!currPlayer.hasMove()){
          }else{
          }
          break;
      case "act":
        if(currPlayer.hasAction()&&currPlayer.hasRole()){
            act();
        }else if (!currPlayer.hasAction()){
        }else{
        }
        break;
      case "upgrade":
        if(currPlayer.hasAction()){
          StageBase tempLocation = currPlayer.getLocation();
          if((tempLocation.getName().equals("office")) == true){ // Not sure what to compare to, what is name of casting office in game?
            CastingOffice office = (CastingOffice) tempLocation;;
            upgrade();
          }else{
          }
        }else{
        }
        case "rehearse":
            //if !moved rehearse.
            if(currPlayer.hasAction() && currPlayer.hasRole()){
                rehearse();
            }else if(currPlayer.hasAction() && !currPlayer.hasRole()){
            }else{
            }
            break;
        case "end":
          //endTurn = false;
          turnOver = true;
          return;
        case "help":
          break;
      }
    }
  }

  public int roleDice(){
    Random rand = new Random();
    int role = rand.nextInt(6);
    return role;
  }

  public void move(){

    StageBase location = currPlayer.getLocation();
    StageBase[] array = location.stageConnections();
    int index = 0;
    for(int i = 0; i < array.length; i++){
      index++;
    }
    view.moveView(array);
    int numInput = view.getOption();
    //String input = getStringInput();
    //int numInput = 100;
    try{
      if(numInput > index){
        move();
        return;
      }else{
        StageBase newLocation = array[numInput - 1];
        Stage curStage = theBoard.getStage(newLocation.getName());

        if(curStage!=null&&!curStage.getStatus()){
          curStage.flipCard();
          Card cur = curStage.getCard();
          view.discoverScene(cur.getCardLabel());
        }

        currPlayer.setLocation(newLocation);
        int [] newArea = newLocation.getArea();
        view.movePlayer(currPlayer.getName(),newArea[0]+(currPlayer.getIndex()*45), newArea[1]+120);
        currPlayer.setMove(false);
        return;
      }
    }catch(Exception e){
      if(numInput == -1){
        return;
      }else{
        move();
        return;
      }
    }
  }

  public void takeARole(){

    StageBase tempLocation = currPlayer.getLocation();
    Stage location = (Stage) tempLocation;
    Role[] stageRoles = location.getStageRoles();
    Card card = location.getCard();
    Role[] cardRoles = card.getRoles();
    boolean playerRankedEnough = false;
    int cancel = -1;

    int index = 0;
    for(int i = 0; i < cardRoles.length; i++){
        index++;
        if(cardRoles[i].getLevel()<=currPlayer.getRank()&&!cardRoles[i].getisTaken()){
          playerRankedEnough = true;
        }
    }

    for(int i = 0; i < stageRoles.length; i++){
      index++;
      if(stageRoles[i].getLevel()<=currPlayer.getRank()&&!stageRoles[i].getisTaken()){
        playerRankedEnough = true;
      }
    }
    if(!playerRankedEnough){
      return;
    }

    view.workView(cardRoles, stageRoles);

    int input = view.getOption();
    Role desiredRole = null;
       if(input == cancel){
         view.resetButtons();
         return;
       }else if(input > (stageRoles.length + cardRoles.length)||input<0){
          takeARole();
          return;
       }else if(input <= cardRoles.length){
          int reqLvl = cardRoles[input-1].getLevel();
          if(currPlayer.getRank()>= reqLvl){
            desiredRole = cardRoles[input - 1];
            currPlayer.setAction(false);
          }else{
            takeARole();
            return;
          }
       }else{
          input = input - cardRoles.length;
          int reqLvl = stageRoles[input-1].getLevel();
          if(currPlayer.getRank()>= reqLvl){
            desiredRole = stageRoles[input - 1];
            currPlayer.setAction(false);
          }else{
            takeARole();
            return;
          }

       }

       if(desiredRole.getisTaken() == true){
          takeARole();
          return;
       }else{
         int[] tempRoleArea = desiredRole.getArea();
         if(desiredRole.isOnCard()){
           StageBase temp = currPlayer.getLocation();
           int[] stageArea = temp.getArea();
           view.movePlayer(currPlayer.getName() ,tempRoleArea[0] + stageArea[0], tempRoleArea[1] + stageArea[1]);
        }else{
           view.movePlayer(currPlayer.getName() ,tempRoleArea[0],tempRoleArea[1]);
        }
          currPlayer.setCurrentRole(desiredRole);
          desiredRole.setIsTaken(true);
          desiredRole.takeRole(currPlayer);
          currPlayer.getLocation().setStagePlayers(currPlayer);
          view.resetButtons();
       }
  }


  public void rehearse(){
    if(currPlayer.getRehearsalMarker()==6){
      return;
    }
    currPlayer.rehearse();
    currPlayer.setAction(false);
  }

  public void act(){
      Role curRole = currPlayer.getCurrentRole();
      int budget = 0;
      boolean onCard = curRole.isOnCard();
      Stage curStage = theBoard.getStage(currPlayer.getLocation().getName());
      budget = curStage.getCard().getBudget();
      int roll = roleDice()+1;
      if((roll + currPlayer.getRehearsalMarker()) >= budget){
        if(curStage.getShotCounter() == 1){
          currPlayer.getCurrentRole().setIsTaken(false);
          //scurStage.setStatus(false);
          if(onCard){
            int[] bonusRolls = new int[currPlayer.getRank()];
            for(int i = 0; i<currPlayer.getRank();i++){
              bonusRolls[i] = roleDice()+1;
            }
            curStage.itsAWrap(bonusRolls, currPlayer);
            currPlayer.clearRehearse();
            sceneCount++;
          }else{
            currPlayer.addMoney(currPlayer.getRank());
            currPlayer.clearRehearse();
            sceneCount++;
          }
            curStage.subtractShotCount();
            view.viewRemoveShot(curStage.getShotLabel(), curStage.getInitialShotCount() - curStage.getShotCounter());
            view.removeScene(curStage.getCard().getCardLabel());
            currPlayer.setCurrentRole(null); // Add loop to go through list of players on card to remove everybodys roll
        }else{
          if(onCard){
            currPlayer.addFame(2);
            curStage.subtractShotCount();
          }else {
            currPlayer.addFame(1);
            currPlayer.addMoney(1);
            curStage.subtractShotCount();
          }
          view.viewRemoveShot(curStage.getShotLabel(), curStage.getInitialShotCount() - curStage.getShotCounter());
        }
      }else{
        if(!onCard){
          currPlayer.addMoney(1);
        }else{
        }
      }
      currPlayer.setAction(false);
  }

  public void upgrade(){
    CastingOffice office = theBoard.getOffice();
    int[][] cash = office.getCashTable();
    int[][] credits = office.getCreditTable();
    boolean isPlayerRichEnough = false;
    view.viewUpgrade(cash, credits);
    if(cash[0][1]>currPlayer.getMoney()&&credits[0][1]>currPlayer.getMoney()){
      return;
    }
    int input = view.getOption();
    if((input > 6) || (currPlayer.getRank() >= input)){
      return;
    }else{
      view.resetButtons();
      view.viewUpgrade2();
      int input2 = view.getOption();
      if(input2 == 1){
        int funds = currPlayer.getMoney();
        if(funds < cash[input - 2][1]){
          view.resetButtons();
          upgrade();
          return;
        }else{
          currPlayer.setRank(input);
          currPlayer.subtractMoney(cash[input - 2][1]);
          currPlayer.setAction(false);
          view.updateLevel(currPlayer.getName(), input);
        }
      }else if(input2 == 2){
        int fame = currPlayer.getFame();
        if(fame < credits[input - 2][1]){
          view.resetButtons();
          upgrade();
          return;
        }else{
          currPlayer.setRank(input);
          currPlayer.subtractFame(credits[input - 2][1]);
          currPlayer.setAction(false);
          view.updateLevel(currPlayer.getName(), input);
        }
      }else{
        upgrade();
      }

    }
  }
}
