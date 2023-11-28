/*
 * @author Ijenna Okonkwo
 * @date 11/28/2023
 *
 * This class represents a Deck of Cards
 * It has two instance variables: cards and top.
 */

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
        cards = new Card[52];
        top = 0;
	}
	
    public void addCardsToDeck(){
        int index = 0;
        for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 13; j++){
                cards[index] = new Card(i, j);
                index++;
            }
        }
    }


	public void shuffle(){
		Card temp;

        for(int i = 0; i < 52; i++){
            temp = cards[i];
            int rand = (int)(Math.random()*51) + 1;
            if(rand != i){
                cards[i] = cards[rand];
                cards[rand] = temp;
            }
        }
	}
	
	public Card deal(){
		top++;
        return cards[top-1];
	}

    public int getSuit(String s){
        String num = s.substring(0,1);
        switch(num){
            case "s":
                return 1;
            case "h":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
            default:
                return 0;
        }
    }

    public int getRank(String s){
        return Integer.parseInt(s.substring(1));
    }
	
    public Card getCard(String c){

        int suit = getSuit(c);
        int rank = getRank(c);

        for(int i = 0; i < cards.length; i++){
            if(cards[i].getSuit() == suit && cards[i].getRank() == rank){
                return cards[i];
            }
        }
        return cards[0];

    }

    public void reset(){
        top = 0;
    }
	// add more methods here if needed

}
