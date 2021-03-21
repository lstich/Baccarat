public class CardHand{
    private int cardA;
    private int cardB;
    private int cardC;
    private boolean natural;

    //constructor
    public CardHand(int a, int b, boolean nat){
        cardA = a;
        cardB = b;
        cardC = 0;
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
    
    public int value(){
        //System.out.println(cardA + " " + cardB + " " + cardC + " " + (( cardA + cardB + third) %10));
        return ( cardA + cardB + cardC) %10;
    }
}