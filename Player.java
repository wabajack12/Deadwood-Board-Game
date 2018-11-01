public class Player{

   private String name;
   private StageBase location;
   private int rank;
   private int money;
   private int fame;
   private int score;
   private int rehearsalMarker;
   private Role currentRole;
   private boolean hasAction;
   private boolean hasMove;
   private int index;

   public Player(String name, StageBase location, int index)
   {
      this.name = name;
      this.location = location;
      this.rank = 1;
      this.money = 0;
      this.fame = 0;
      this.score = 0;
      this.rehearsalMarker = 0;
      this.currentRole = null;
      this.hasAction = false;
      this.index = index;
   }
   public int getIndex(){
     return this.index;
   }

   public String getName(){
      return name;
   }

   public StageBase getLocation(){
      return location;
   }
   public void setLocation(StageBase newLocation){
     hasMove = true;
     location = newLocation;
   }
   public boolean hasMove(){
     return this.hasMove;
   }
   public void setMove(boolean status){
     this.hasMove = status;
   }
   public int getRank(){
      return rank;
   }

   public boolean hasRole(){
     return currentRole!=null;
   }

   public void setRank(int num){
     rank = num;
   }
   public boolean hasAction(){
     return hasAction;
   }

   public int getMoney(){
      return money;
   }

   public int getFame(){
      return fame;
   }

   public int getScore(){
     int temp = (5 * rank) + fame + money;
      return temp;
   }

   public int getRehearsalMarker(){
      return rehearsalMarker;
   }

   public void clearRehearse(){
     rehearsalMarker = 0;
   }

   public void setCurrentRole(Role newRole){
      currentRole = newRole;
   }

   public Role getCurrentRole(){
      return currentRole;
   }

   public void addMoney(int funds){
      money = money + funds;
   }

   public void subtractMoney(int funds){
      money = money - funds;
   }

   public void addFame(int funds){
    fame = fame + funds;
   }

   public void subtractFame(int funds){
     fame = fame - funds;
   }

   public boolean isOnCard(){
     if(this.currentRole != null){
       return currentRole.isOnCard();
     }else{
       return false;
     }
   }
   public void addScore(int funds){
     score = score + funds;
   }
   public void setAction(boolean status){
     if(status){
       hasMove = true;
     }
     hasAction = status;
   }
   public void rehearse(){
     rehearsalMarker++;
   }

}
