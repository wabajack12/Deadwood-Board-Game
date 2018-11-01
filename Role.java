public class Role {

  private String name;
  private boolean isTaken;
  private int level;
  private String line;
  private boolean onCard;
  private Player curPlayer;
  private Card card;
  private Stage stage;
  private int[] area = new int[4];

  // it's to add a role to the card
  public Role(String name, int level, boolean isTaken, String line, Card card, int[] area){
    this.name = name;
    this.level = level;
    this.isTaken = isTaken;
    this.line = line;
    this.card = card;
    this.onCard = true;
    this.area = area;
  }

  // it's to add a role to the stage
  public Role(String name, int level, boolean isTaken, String line, Stage stage, int[] area){
    this.name = name;
    this.level = level;
    this.isTaken = isTaken;
    this.line = line;
    this.stage = stage;
    this.onCard = false;
    this.area = area;
  }

  public int[] getArea(){
    return area;
  }

  public String getName(){
    return name;
  }
  public int getLevel() {
    return level;
  }
  public void takeRole(Player person){
    curPlayer = person;
  }
  public Player curPlayer(){
    return curPlayer;
  }
  public boolean getisTaken(){
    return isTaken;
  }

  public void setIsTaken(boolean status){
    isTaken = status;
  }

  public String getLine() {
    return line;
  }

  public void setOnCard(boolean status) {
    this.onCard = status;
  }

  public boolean isOnCard() {
    return onCard; // If you are on card, onCard will be true, false if not
  }

  public Card getCard(){
    return card;
  }
}
