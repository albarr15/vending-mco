package regular_vm;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Represents the total balances of the machine
 */
public class Balance {
    private int currentBal = 0;
    private int[][] cashStock = { {1,5,10,20,50,100,200,500,1000},
                                     {0,0,0,0,0,0,0,0,0} };

    public Balance () {}

    public int[][] getCashStock() {
        return this.cashStock;
    }

    /**
     * Deposits cash from an input of string containing the different denominations with spaces in between each
     * @param cashList is the string input of denominations with each one space apart
     */
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

    /**
     * Withdraws cash of an amount given based on the current cashStock
     * @param amount is the amount of cash to be withdrawn
     * @return cashWithdraw when withdrawal is successful
     *         null when withdrawal is not successful (change is insufficient)
     */
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
