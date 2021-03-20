import java.util.ArrayList;


public static void main(String[] args){

    Arraylist<CardHand> playerHand = new Arraylist<CardHand>();
    Arraylist<CardHand> bankerHand = new Arraylist<CardHand>();
    int playerWinProb = 0;
    int bankerWinProb = 0;
    int tieProb = 0;

    public void probabilityOfPlayerWin(){

    }

    /*
    initialize arraylist of all possible 2 card hands

    input: ArrayList - lsit of hands
    */
    public static void CardHandsInit(Arraylist handList){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                handlist.add(new CardHand(i,j));
            }
        }
    }
    /*
    check if player draws a third card

    input: CardHand - player hand
    output: boolean - whether or not third is drawn
    */
    public boolean doesPlayerDrawThird(CardHand hand){

        return ;
    }



    /*
    check if banker draws a third card

    input: CardHand - banker hand
    output: boolean - whether or not third is drawn
    */
    public boolean doesBankerDrawThird(CardHand hand){

        return ;
    }


    /*
    add up probability of hand being drawn

    ex/ {3,9,0} probability is
    = 4/52 * 4/52 * 16/52 

    input:  CardHand - banker hand
            CardHand - player hand
    */
    public void probabilityOfHands(CardHand player, CardHand banker){

    }

}

class CardHand{
    public byte cardA;
    public byte cardB;
    public byte cardC;

    //constructor
    public CardHand{byte a, byte, b}{
        cardA = a;
        cardB = b;
        cardC = null;
    }

    public byte value(){
        byte third = 0;
        if(cardC != null){
            third = cardC;
        }
        return ( cardA + cardB + third) % 10;
    }
}
