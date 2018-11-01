import java.util.*;

public class CastingOffice extends StageBase{
    private int[][] cashTable; //cashTable[i][0]=level,cashTable[i][1]=amt
    private int[][] creditTable;
    private int nextUpgradeToAdd = 0;
    public CastingOffice(String name, int[][] cashTbl, int[][]creditTbl, int[] area){
      super(name, area);
      cashTable = cashTbl;
      creditTable = creditTbl;
  }

  public int[][] getCashTable(){
    return cashTable;
  }

  public int[][] getCreditTable(){
    return creditTable;
  }
}
