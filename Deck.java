import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Deck{
  private ArrayList<Card> deck;
  private int topOfDeck;

  public Deck(){
    this.deck = new ArrayList<Card>();
    this.topOfDeck = 0;
  }

  public void init(NodeList cards){
    Card temp;
    String tempName;
    String tempImage = null;
    int tempBudget;
    int tempScene;
    String tempDesc;
    NodeList roleList;
    NodeList areaList;

    for (int i = 0; i < cards.getLength(); i++) {

        Node cardNode = cards.item(i);

        if (cardNode.getNodeType() == Node.ELEMENT_NODE) {

          Element cardE = (Element) cardNode;
          Element sceneE = (Element)cardE.getElementsByTagName("scene").item(0);

          roleList = cardE.getElementsByTagName("part"); //This contains roles
          areaList = cardE.getElementsByTagName("area"); //This contains areas

          tempScene = Integer.parseInt(sceneE.getAttribute("number"));
          tempName = cardE.getAttribute("name");
          tempImage = cardE.getAttribute("img");
          tempBudget =Integer.parseInt(cardE.getAttribute("budget"));
          tempDesc = sceneE.getTextContent();

          temp = new Card(tempName, tempImage, tempBudget, tempScene, tempDesc);

          //Add roles
          for(int j = 0; j<roleList.getLength(); j++){
            Element roleE = (Element)roleList.item(j);
            Element areaE = (Element)areaList.item(j);
            int[] tempArea = new int [4];

            tempArea[0] = Integer.parseInt(areaE.getAttribute("x"));
            tempArea[1] = Integer.parseInt(areaE.getAttribute("y"));
            tempArea[2] = Integer.parseInt(areaE.getAttribute("h"));
            tempArea[3] = Integer.parseInt(areaE.getAttribute("w"));



            String roleName = roleE.getAttribute("name");
            int level = Integer.parseInt(roleE.getAttribute("level"));
            String line = roleE.getElementsByTagName("line").item(0).getTextContent();

            temp.addRole(roleName, level, false, line, temp, tempArea);
          }

          deck.add(temp);

        }
    }
    this.shuffle();
  }

  public void shuffle(){
		Random rand = new Random();
		int initSize = this.deck.size();
    int value = 0;
    ArrayList<Card> tempDeck = new ArrayList<Card>();
		for(int cardsLeft = initSize; cardsLeft>0;cardsLeft--){
			value = rand.nextInt(cardsLeft);
			Card tempCard = this.deck.get(value);
      this.deck.remove(value);
      tempDeck.add(tempCard);
    }
    this.deck = tempDeck;
  }

  public Card getNextCard(){
    int topOfDeck = this.topOfDeck;
    this.topOfDeck++;
    return deck.get(topOfDeck);
  }
  public int getTopOfDeck(){
    return topOfDeck;
  }
}
