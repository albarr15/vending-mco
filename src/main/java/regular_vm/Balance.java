package regular_vm;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Balance {
    private int currentBal = 0;
    private int[][] cashStock = { {1,5,10,20,50,100,200,500,1000},
                                     {0,0,0,0,0,0,0,0,0} };

    public Balance () {}

    public int[][] getCashStock() {
        return this.cashStock;
    }

    // Receives string input containing the different denominations
    public void depositCash(String cashList) {
        // Extract cash values from string input
        ArrayList<Integer> cash = new ArrayList<Integer>();
        int numInvalid = 0;
        StringTokenizer st = new StringTokenizer(cashList," ");
        while(st.hasMoreTokens()) {
            try {
                cash.add(Integer.parseInt(st.nextToken()));
            } catch(NumberFormatException e) {
                numInvalid++;
            }
        }

        // Store in balance
        for(int i=0; i<cash.size(); i++) {
            int j=0;
            for(j=0; j<9; j++)
                if(cash.get(i) == this.cashStock[0][j]) {
                    this.cashStock[1][j]++;
                    this.currentBal += cash.get(i);
                    break;
                }
            if(j==9) numInvalid++;
        } 

        if(numInvalid > 0)
            System.out.println(numInvalid + " invalid entries were found. Skipped entries.");
    }

    // Can be used for getting change or emptying machine stock
    public String withdrawCash(int amount) {
        String cashWithdraw = "";
        int origAmount = amount;

        // Removing cash from stock
        int i=8;
        while(i >= 0)
            if(amount >= this.cashStock[0][i] && this.cashStock[1][i] > 0) {
                this.cashStock[1][i]--;
                cashWithdraw += cashStock[0][i] + " ";
                amount -= cashStock[0][i];
            } else i--;

        if(amount > 0) {
            // Returning extracted cash to stock if change is insufficient
            depositCash(cashWithdraw);
            return null;
        } else {
            this.currentBal -= origAmount;
            return cashWithdraw;
        }
    }

    public int getCurrentBal() {
        return this.currentBal;
    }
}
