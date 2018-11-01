import java.util.*;
public class StageBase{

  private String name;
  private ArrayList<String> stageNames;
  private ArrayList<StageBase> stageConnections;
  private  ArrayList<Player> stagePlayers;
  private int[] area = new int[4];

  public StageBase(String name, int[] area){
    this.name = name;
    this.stageConnections = new ArrayList<StageBase>();
    this.stagePlayers = new ArrayList<Player>();
    this.stageNames = new ArrayList<String>();
    this.area = area;

  }

  // allow us to add stages stage connection arraylist allowing for player move to work
  public void addNeighbor(String stgName){
    StageBase temp = Board.getLocation(stgName);
    if(temp == null){
    }else{
      this.stageConnections.add(temp);
    }
  }

  // checks if a location is a stage
  public boolean isStage(){
    return Board.getStage(this.name)!=null;
  }
  public void addStageName(String stgName){
    this.stageNames.add(stgName);
  }

  public int[] getArea(){
    return area;
  }

  public String getName(){
    return name;
  }

  //for initializing stages so they know names of stages to connect later
  public ArrayList<String> getStageNames(){
    return this.stageNames;
  }
  // has the list of what stages are connected to the current stage to get the move options
  public StageBase[] stageConnections(){
   return stageConnections.toArray(new StageBase[stageConnections.size()]);
  }
  // holds the listOfPlayers players that are on the current stage
  public Player[] stagePlayers(){
   return stagePlayers.toArray(new Player[stagePlayers.size()]);
  }

  public void setStagePlayers(Player player){
    stagePlayers.add(player);
  }
}
