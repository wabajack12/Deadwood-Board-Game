import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Board{
   private static CastingOffice cOffice;
   private static Trailer trailer;
   private static ArrayList<StageBase> locations;
   private static ArrayList<Stage> stages;

   public Board(){
      locations = new ArrayList<StageBase>();
      stages = new ArrayList<Stage>();
   }

   public ArrayList<Stage> getStages(){
      return stages;
   }

   public ArrayList<StageBase> getLocations(){
      return locations;
   }

   public Trailer getTrailer(){
     return trailer;
   }

   public CastingOffice getOffice(){
     return cOffice;
   }
   public static StageBase getLocation (String strName){
     for(StageBase stg:locations){
       if(strName.equals(stg.getName())){
         return stg;
       }
     }
     return null;
   }

   public static Stage getStage (String strName){
     for(Stage stg:stages){
       if(strName.equals(stg.getName())){
         return stg;
       }
     }
     return null;
   }

   public void initStages(Document stageDoc){
    // public Stage(String name, int initialShotCount, int shotCounter, int cardIndex, Role[] setRoles, String cardStatus,
    // Stage[] stageConnections, Player[] stagePlayers, boolean status)
     Stage temp;
     String tempName;
     int tempScene;
     Role tempRole;
     int numTakes;
     NodeList setList = stageDoc.getElementsByTagName("set");
     NodeList childList;
     NodeList neighbors;
     NodeList parts;
     Node officeN;
     Node trailerN;
     NodeList areaList;
     officeN = stageDoc.getElementsByTagName("office").item(0);
     trailerN = stageDoc.getElementsByTagName("trailer").item(0);

      //create the casting office office information arrays on paying for creddit or cash for leveling up
       int[][] cashArray = new int[][]{
        {2 , 4},
        {3 , 10},
        {4 , 18},
        {5 , 28},
        {6 , 40}
      };
        int[][] creditArray = new int[][]{
        {2 , 4},
        {3 , 10},
        {4 , 15},
        {5 , 20},
        {6 , 25}
        };
       // create the casting office
       // hard coded area
       int [] tempArea = {9,459,208,209};
       cOffice = new CastingOffice("office", cashArray, creditArray,tempArea);
       cOffice.addStageName("Train Station");
       cOffice.addStageName("Ranch");
       cOffice.addStageName("Secret Hideout");
       locations.add(cOffice);

     // create trailer stage
     if (trailerN.getNodeType() == Node.ELEMENT_NODE) {
       Element trailerE = (Element) trailerN;
       Element localArea = (Element)trailerE.getElementsByTagName("area").item(0);
       neighbors = trailerE.getElementsByTagName("neighbor");
       tempArea = new int[4];

       tempArea[0] = Integer.parseInt(localArea.getAttribute("x"));
       tempArea[1] = Integer.parseInt(localArea.getAttribute("y"));
       tempArea[2] = Integer.parseInt(localArea.getAttribute("h"));
       tempArea[3] = Integer.parseInt(localArea.getAttribute("w"));

       trailer = new Trailer ("trailer",tempArea);

       for(int j = 0; j<neighbors.getLength(); j++){
         Element neighborName = (Element)neighbors.item(j);

         trailer.addStageName(neighborName.getAttribute("name"));
       }
       locations.add(trailer);
     }

     //add normal stages
     for (int i = 0; i < setList.getLength(); i++) {

         Node setNode = setList.item(i);
         if (setNode.getNodeType() == Node.ELEMENT_NODE) {

           Element setE = (Element) setNode;
           areaList = setE.getElementsByTagName("area");
           neighbors = setE.getElementsByTagName("neighbor");
           numTakes = setE.getElementsByTagName("take").getLength();
           parts = setE.getElementsByTagName("part");
           tempArea = new int[4];
           int [][] tempTakeAreas = new int[numTakes][4];
           Element localArea = (Element) areaList.item(0);
           tempArea[0] = Integer.parseInt(localArea.getAttribute("x"));
           tempArea[1] = Integer.parseInt(localArea.getAttribute("y"));
           tempArea[2] = Integer.parseInt(localArea.getAttribute("h"));
           tempArea[3] = Integer.parseInt(localArea.getAttribute("w"));

           for(int j = 0; j<numTakes; j++){
             Element takeArea = (Element) areaList.item(j+1);
             tempTakeAreas[j][0] = Integer.parseInt(takeArea.getAttribute("x"));
             tempTakeAreas[j][1] = Integer.parseInt(takeArea.getAttribute("y"));
             tempTakeAreas[j][2] = Integer.parseInt(takeArea.getAttribute("h"));
             tempTakeAreas[j][3] = Integer.parseInt(takeArea.getAttribute("w"));

           }


           tempName = setE.getAttribute("name");

           temp = new Stage(tempName, numTakes, tempArea, tempTakeAreas);

           //Add neighbors
           for(int j = 0; j<neighbors.getLength(); j++){
             Element neighborName = (Element)neighbors.item(j);
             temp.addStageName(neighborName.getAttribute("name"));
           }

           int [] tempRArea = new int[4];
           NodeList roleAreas;
           //add off card roles
           for(int j = 0; j<parts.getLength(); j++){

             Element roleE = (Element)parts.item(j);
             //gets to the right area tag by adding the stage areas and the take areas
             roleAreas = roleE.getElementsByTagName("area");
             Element areaE = (Element)roleAreas.item(0);
             tempRArea[0] = Integer.parseInt(areaE.getAttribute("x"));
             tempRArea[1] = Integer.parseInt(areaE.getAttribute("y"));
             tempRArea[2] = Integer.parseInt(areaE.getAttribute("h"));
             tempRArea[3] = Integer.parseInt(areaE.getAttribute("w"));

             String roleName = roleE.getAttribute("name");
             int level = Integer.parseInt(roleE.getAttribute("level"));
             String line = roleE.getElementsByTagName("line").item(0).getTextContent();
             temp.addRole(new Role(roleName, level, false, line, temp, new int[]{tempRArea[0],tempRArea[1],tempRArea[2],tempRArea[3]} ));
           }
           locations.add(temp);
           stages.add(temp);

         }
     }
     connectStages();
   }

   //go through the list fo stages and connect them via the adding the to the stage conection array
   void connectStages(){
     ArrayList<String>strLinks = cOffice.getStageNames();
     for(String stgName:strLinks){
       cOffice.addNeighbor(stgName);
     }

     strLinks = trailer.getStageNames();
     for(String stgName:strLinks){
       trailer.addNeighbor(stgName);
     }
     for(StageBase stg: stages){
       strLinks = stg.getStageNames();
       for(String stgName:strLinks){
         stg.addNeighbor(stgName);
       }
     }
   }


   public boolean endDay(){
      Stage[] check = stages.toArray(new Stage[stages.size()]);
      boolean status = false;
      int counter = 0;
        for(int i = 0; i < check.length; i++){
          if(check[i].getShotCounter() == 0){
            counter++;
          }
      }
      if(0 == (check.length - 1)){
        status = true;
      }
      return status;
   }

}
