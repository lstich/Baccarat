import java.util.*;

public class BaccaratProbabilities{
        private static ArrayList<CardHand> playerHands;
        private static ArrayList<CardHand> bankerHands;
        private static double playerWinProb;
        private static double bankerWinProb;
        private static double tieProb;
        private static double SINGLE_CARD_PROBABILITY = 0.076923076;    //probability of drawing any 1 card = 1/13

    public static void main(String[] args){
        //initialize variables
        playerHands = new ArrayList<CardHand>();
        bankerHands = new ArrayList<CardHand>();
        playerWinProb = 0.0;
        bankerWinProb = 0;
        tieProb = 0;

        //initialize arrays of all possible 2 card combinations
        cardHandsInit(playerHands);
        cardHandsInit(bankerHands);

        calculateProbabilities();
        
    }

    /*
    calculate and display probabilities of player, banker, and tie
    */
    public static void calculateProbabilities(){

        //loop through every combination of 2 card hands for player and banker
        for (CardHand player : playerHands){
            for (CardHand banker : bankerHands){
                if (doesPlayerDrawThird(player,banker)){
                    //loop through every possible third player card drawn
                    for(int i=0; i<10; i++){
                        player.setCardC(i);

                        //Case: player and banker draw 3rd card
                        if (doesBankerDrawThird(player,banker)){
                             //loop through every possible third banker card drawn
                            for(int j=0; j<10; j++){
                                banker.setCardC(j);
                                
                                //check if who wins and add probabilites of hand combination to respectocve outcome total
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
                        //Case: player only draw 3rd card
                        else{
                            //check if who wins and add probabilites of hand combination to respectocve outcome total

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
                    //Case: banker only draw 3rd card
                    if (doesBankerDrawThird(player,banker)){
                        //loop through every possible third banker card drawn
                        for(int j=0; j<10; j++){
                            banker.setCardC(j);
                            
                            //check if who wins and add probabilites of hand combination to respectocve outcome total
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
                    //Case: neither draw third card
                    else{
                        //check if who wins and add probabilites of hand combination to respectocve outcome total
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
        //display totals
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
                //flag if hand is a natural
                if (((i+j) % 10) > 7){
                    nat = true;
                }
                handList.add(new CardHand(i,j,nat));
            }
        }
    }
    /*
    check if player draws a third card
    player hand does not draws if 
        - either hand is a natural 
        - player hand is 6 or 7
    
    player hand otherwise does draws if 
        - player hand = 0-5
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
    banker does not draw if:
        - either hand is a natural
        - banker hand = 7
        - player hand = 6 or 7 and banker hand = 6
    banker does otherwise draw if:
        - banker hand  = 0-2
        - player hand = 6 or 7 and banker hand = 3-5
        - player hand draws third hand and:
            - banker card = 3 and player third card = 1-8 or 9
            - banker card = 4 and player third card = 2-7
            - banker card = 5 and player third card = 4-7
            - banker card = 6 and player third card = 6 or 7
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
    ex/ probability of player and banker hands = {3,2,0} and {7,7,3}
    = 1/13 * 1/13 * 4/13 * 1/13 * 1/13 * 1/13
    input:  CardHand - banker hand
            CardHand - player hand
    output: double probability of this hand occuring
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
//class for data type of player hand
class CardHand{
    private int cardA;
    private int cardB;
    private int cardC;
    private boolean natural;

    //constructor
    public CardHand(int a, int b, boolean nat){
        cardA = a;
        cardB = b;
        cardC = -1;
        natural = nat;
    }

    //getters and setters
    public boolean isNatural(){
        return natural;
    }
    public int getCardA(){
        return cardA;
    }
    public int getCardB(){
        return cardB;
    }
    public void setCardC(int c){
        cardC = c;
    }
    public int getCardC(){
        return cardC;
    }
    
    //numeric value of hand
    public int value(){
        int third = 0;
        //check if hand contains third card 
        if(cardC != -1){
            third = cardC;
        }
        // % 10 makes sure the value is modulo(10), so the value is always 0-9 with the remainder carrying over
        return ( cardA + cardB + third) %10;
    }
}
