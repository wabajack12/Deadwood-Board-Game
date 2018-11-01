import java.util.*;
import java.util.Arrays;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
public class Stage extends StageBase{

  private int initialShotCount;
  private int shotCounter;
  private int cardIndex;
  private ArrayList<Role> setRoles;
  private String cardStatus;
  private boolean status;
  private Card curCard;
  private int[] stageArea;
  private int[][] takeAreas;
  private JLabel[] shotLabel;

  //constuctor
  public Stage(String name, int shotCount, int[] area, int[][] takeAreas){
    super(name, area);
    this.initialShotCount = shotCount;
    this.shotCounter = shotCount;
    this.cardStatus = "unflipped";
    //this.status = false; // 0 indicates unvisited stage, 1 indicates visited stage
    this.takeAreas = takeAreas;
    setRoles = new ArrayList<Role>();
  }

  public void addRole(Role roleToAdd){
    setRoles.add(roleToAdd);
  }
  // resetsStage to base values for the start of a new day
  public void resetStage(){
    if(curCard!=null){
      curCard.clearCardLabel();
    }
    this.shotCounter = this.initialShotCount;
    this.cardStatus = "unflipped";
    this.status = false;
  }
  public void setStatus(Boolean stat){
    this.status = stat;
  }
  public int getInitialShotCount() {
    return initialShotCount;
  }

  public int[][] getTakeAreas(){
    return takeAreas;
  }

  public void setShotLabel(JLabel[] labels){
    shotLabel = labels;
  }

  public JLabel[] getShotLabel(){
    return shotLabel;
  }

//  public int[] getStageArea(){
  //  return stageArea;
//  }
  public int getShotCounter() {
    return shotCounter;
  }

  public Role[] getStageRoles(){
    return setRoles.toArray(new Role[setRoles.size()]);
  }

  public int getCardIndex() {
    return cardIndex;
  }

  public String getCardStatus(){
   return cardStatus;
  }

  public boolean getStatus(){
    return status;
  }

  public void subtractShotCount(){
      shotCounter--;
  }

  public void setCard(Card newCard){
    curCard = newCard;
  }

  public Card getCard(){
    return curCard;
  }

  public void itsAWrap(int[] diceRolls, Player player){
     Role curRole = player.getCurrentRole();
     boolean status = curRole.isOnCard();
     Arrays.sort(diceRolls);

     for(int i=0; i<diceRolls.length/2; i++){
       int temp = diceRolls[i];
       diceRolls[i] = diceRolls[diceRolls.length -i -1];
       diceRolls[diceRolls.length -i -1] = temp;
     }

     Player[] stagePlayers = player.getLocation().stagePlayers();
     if(status == true){
       Role[] cardRoles = curCard.getRoles();
       int index = 0;
       int mod = cardRoles.length;
       for(int i = 0; index != diceRolls.length; i++){
         if(cardRoles[i % mod].curPlayer() != null){
           cardRoles[i % mod].curPlayer().addMoney(diceRolls[index]);
           index++;
         }
       }
      Role[] stageRoles = setRoles.toArray(new Role[setRoles.size()]);
       for(int i = 0; i < stageRoles.length; i++){
         if(stageRoles[i].curPlayer() != null){
           stageRoles[i].curPlayer().addMoney(stageRoles[i].curPlayer().getRank());
         }
       }
     }else{
       int bonus = player.getRank();
       for(int i =0; i < stagePlayers.length; i++){
         if(stagePlayers[i] != null){
           stagePlayers[i].addMoney(bonus);
         }
       }
     }
     clearRoles();
   }

   public void clearRoles(){
     Player[] stagePlayers = this.stagePlayers();
     for(int i = 0; i < stagePlayers.length; i++){
       if(stagePlayers[i].getCurrentRole() != null){
         stagePlayers[i].getCurrentRole().setIsTaken(false);
       }
       stagePlayers[i].setCurrentRole(null);
       stagePlayers[i].clearRehearse();
       stagePlayers[i] = null;
     }
   }
  public void flipCard(){
   this.cardStatus = "flipped";
   this.status = true;
  }
}
