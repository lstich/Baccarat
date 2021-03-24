import java.util.*;

public class BaccaratProbabilities{
        private static ArrayList<CardHand> playerHands;
        private static ArrayList<CardHand> bankerHands;
        private static double playerWinProb;
        private static double bankerWinProb;
        private static double tieProb;
        private static double SINGLE_CARD_PROBABILITY = 0.076923076;

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
        System.out.println("Probability of winning: \nPlayer: " + playerWinProb);
        System.out.println("Banker: " + bankerWinProb);
        System.out.println("Tie:    " + tieProb);
    }

    /*
    initialize arraylist of all possible 2 card hands

    input: ArrayList - list of hands
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
        if (pHand.isNatural() || bHand.isNatural()){
            return false;
        }
        else if (pHand.value() < 6){
            return true;
        }

        return false;
    }

    /*
    check if banker draws a third card

    input:  CardHand - player hand
            CardHand - banker hand
    output: boolean - whether or not third is drawn
    */
    public static boolean doesBankerDrawThird(CardHand pHand, CardHand bHand){

        boolean result = false;
        if (pHand.isNatural() || bHand.isNatural()){
            result = false;
        }
        else if(bHand.value() < 3){
            result = true;
        }
        else if(bHand.value() == 7){
            result = false;
        }
        else if((pHand.value() == 6 || pHand.value() == 7) && pHand.getCardC() == -1){

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
            //else result is already false
        }
    
        return result;
    }

    /*
    add up probability of hand being drawn

    ex/ probability of player and banker hands{3,2,0} 
    = 1/13 * 1/13 * 4/13 

    input:  CardHand - banker hand
            CardHand - player hand
    output: int probability of this hand occuring
    */
    public static double probabilityOfHands(CardHand player, CardHand banker){

        //if card is 0 probability is 4 times that of a single card since 10,J,Q,K all have value 0
        double totalPlayer = (player.getCardA() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        totalPlayer*= (player.getCardB() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        if (player.getCardC() > -1){
            totalPlayer*= (player.getCardC() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        }

        double totalBanker = (banker.getCardA() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        totalBanker*= (banker.getCardB() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        if (banker.getCardC() > -1){
            totalBanker*= (banker.getCardC() == 0) ? 4 * SINGLE_CARD_PROBABILITY : SINGLE_CARD_PROBABILITY;
        }

        return totalPlayer*totalBanker;
    }
}
