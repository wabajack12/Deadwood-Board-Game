
import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Card{

  private String name;
  private String imageFileName;
  private int budget;
  private int sceneNo;
  private String description;
  private Stage location;
  private ArrayList<Role> roles;
  private JLabel cardLabel;

  public Card(String name, String image, int budget, int sceneNo, String description){
    this.name = name;
    this.imageFileName = image;
    this.budget = budget;
    this.sceneNo = sceneNo;
    this.description = description;


    roles = new ArrayList<Role>();
  }
  public void setCardLabel(JLabel label){
    cardLabel = label;
  }
  public JLabel getCardLabel(){
    return cardLabel;
  }
  public String getName(){
    return name;
  }
  public String getImageFileName(){
    return imageFileName;
  }
  public int getBudget(){
    return budget;
  }
  public int getSceneNo(){
    return sceneNo;
  }
  public String getDescription(){
    return description;
  }
  public Stage getLocation(){
    return location;
  }
  public void setLocation(Stage local){
    this.location = local;
  }
  public void addRole(String name, int level, boolean isTaken, String line, Card card, int[] area) {
    roles.add(new Role(name, level, isTaken, line, card, area));
  }
  public void clearCardLabel(){
    cardLabel.setVisible(false);

  }
  public Role[] getRoles() {
    Role[] cardRoles = new Role[roles.size()];
    cardRoles = roles.toArray(cardRoles);
    return cardRoles;
  }


}
