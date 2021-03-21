import java.util.*;
//import org.apache.commons.math4.util*;

public class BaccaratProbabilities{

        private static ArrayList<CardHand> playerHands;
        private static ArrayList<CardHand> bankerHands;
        private static double playerWinProb;
        private static double bankerWinProb;
        private static double tieProb;

    public static void main(String[] args){
        playerHands = new ArrayList<CardHand>();
        bankerHands = new ArrayList<CardHand>();
        playerWinProb = 0;
        bankerWinProb = 0;
        tieProb = 0;

        cardHandsInit(playerHands);
        cardHandsInit(bankerHands);

        calculateProbabilities();
        
    }

    /*
    calculate and display probabilities of player, banker, and tie

    */
    public static void calculateProbabilities(){

        for (CardHand player : playerHands){
            for (CardHand banker : bankerHands){
                System.out.print(banker.getCardC());
                if (doesPlayerDrawThird(player,banker)){
                    for(int i=0; i<10; i++){
                        player.setCardC(i);

                        if (doesBankerDrawThird(player,banker)){
                            for(int j=0; j<10; j++){
                                banker.setCardC(j);
                                
                                if (player.value() > banker.value()){
                                    playerWinProb += probabilityOfHands(player,banker);
                                }
                                else if (banker.value() > player.value()){
                                    bankerWinProb += probabilityOfHands(player,banker);
                                }
                                else{
                                    tieProb += probabilityOfHands(player,banker);
                                }
                            }
                            banker.setCardC(-1);
                        }
                        else{
                            if (player.value() > banker.value()){
                                playerWinProb += probabilityOfHands(player,banker);
                            }
                            else if (banker.value() > player.value()){
                                bankerWinProb += probabilityOfHands(player,banker);
                            }
                            else{
                                tieProb += probabilityOfHands(player,banker);
                            }
                        }
                    }
                    player.setCardC(-1);
                }
                else{
                    if (doesBankerDrawThird(player,banker)){
                        for(int j=0; j<10; j++){
                            banker.setCardC(j);
                            
                            if (player.value() > banker.value()){
                                playerWinProb += probabilityOfHands(player,banker);
                            }
                            else if (banker.value() > player.value()){
                                bankerWinProb += probabilityOfHands(player,banker);
                            }
                            else{
                                tieProb += probabilityOfHands(player,banker);
                            }
                        }
                        banker.setCardC(-1);
                    }
                    else{
                        if (player.value() > banker.value()){
                            playerWinProb += probabilityOfHands(player,banker);
                         }
                        else if (banker.value() > player.value()){
                            bankerWinProb += probabilityOfHands(player,banker);
                        }
                        else{
                            tieProb += probabilityOfHands(player,banker);
                        }
                    }
                }
            }
        }
        System.out.println(playerWinProb);
        System.out.println(bankerWinProb);
        System.out.println(tieProb);
    }

    /*
    initialize arraylist of all possible 2 card hands

    input: ArrayList - lsit of hands
    */
    public static void cardHandsInit(ArrayList<CardHand> handList){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                boolean nat = false;
                if (((i+j) % 10) > 7){
                    nat = true;
                }
                handList.add(new CardHand(i,j,nat));
           }
        }
    }
    /*
    check if player draws a third card

    input: CardHand - player hand
    output: boolean - whether or not third is drawn
    */
    public static boolean doesPlayerDrawThird(CardHand pHand, CardHand bHand){
        //System.out.print(pHand.value() + " " + bHand.value());
        if (pHand.isNatural() || bHand.isNatural()){
            //System.out.println(" f");
            return false;
        }
        else if (pHand.value() < 6){
            //System.out.println(" t");
            return true;
        }
        //System.out.println(" f");
        return false;
    }



    /*
    check if banker draws a third card

    input:  CardHand - player hand
            CardHand - banker hand
    output: boolean - whether or not third is drawn
    */
    public static boolean doesBankerDrawThird(CardHand pHand, CardHand bHand){
    
        //System.out.print(pHand.value() + " " + bHand.value() + " third card: " + bHand.getCardC()  + "      Cards: " + bHand.getCardA() + bHand.getCardB() + "      ");
    
        boolean result = false;
        if (pHand.isNatural() || bHand.isNatural()){
            result = false;
        }
        else if(bHand.value() <= 2){
            result = true;
        }
        else if(bHand.value() == 7){
            result = false;
        }
        else if(pHand.value() == 6 || pHand.value() == 7){
            if(bHand.value() >=3 && bHand.value() <= 5){
                result = true;
            }
            //else bHand is 6 so stand
            else{
                result = false;
            }
        }
        //player drew third card
        else{   
            if(bHand.value() == 3 && pHand.getCardC() != 8){
                result = true;
            }
            else if ( bHand.value() == 4 && pHand.getCardC() >= 2 && pHand.getCardC() <= 7){
                result = true;
            }
            else if ( bHand.value() == 5 && pHand.getCardC() >= 4 && pHand.getCardC() <= 7){
                result = true;
            }
            else if ( bHand.value() == 6 && pHand.getCardC() >= 6 && pHand.getCardC() <= 7){
                result = true;
            }
        }
    
        //System.out.println(result);
        
        return result;
    }


    /*
    add up probability of hand being drawn

    ex/ probability of player and banker hands{3,} 
    = 4/52 * 4/52 * 16/52 

    input:  CardHand - banker hand
            CardHand - player hand
    output: int probability of this hand occuring
    */
    public static double probabilityOfHands(CardHand player, CardHand banker){
        double binomialCoeffPlayer = 1326;    // value for 52 choose 2
        double binomialCoeffBanker = 1326;

        double totalPlayer = (player.getCardA() == 0) ? 16 : 4;
        totalPlayer*= (player.getCardB() == 0) ? 16 : 4;
        if (player.getCardC() > -1){
            totalPlayer*= (player.getCardC() == 0) ? 16 : 4;
            binomialCoeffPlayer = 22100;
        }
        totalPlayer = totalPlayer/binomialCoeffPlayer;


        double totalBanker= (banker.getCardA() == 0) ? 16 : 4;
        totalBanker*= (banker.getCardB() == 0) ? 16 : 4;
        if (banker.getCardC() > -1){
            totalBanker*= (banker.getCardC() == 0) ? 16 : 4;
            binomialCoeffBanker = 22100;
        }
        totalBanker = totalBanker/binomialCoeffBanker;

        //System.out.println(totalPlayer*totalBanker/100);
        return totalPlayer*totalBanker;
    }
}
