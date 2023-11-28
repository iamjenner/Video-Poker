/*
 * @author Ijenna Okonkwo
 * @date 11/28/2023
 *
 * This class represents a Card.
 *
 * It has four instance variables, two of which determine the
 * suit and rank of the card. suitName and rankName are
 * String arrays that contain the names of the cards.
 */

public class Card implements Comparable<Card>{
	
	private String[] suitName = { "Spades", "Hearts", "Clubs", "Diamonds" };
    private String[] rankName = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "Jack", "Queen", "King"};
    
    private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	// Constructs a new Card
    public Card(int s, int r){
		suit = s;
        rank = r;
	}
	
	public int compareTo(Card c){
		return getRank() - c.getRank();

	}
	
	public String toString(){
		return rankName[rank-1] + " of " + suitName[suit-1];
	}

    // The getSuit() method returns the suit of the Card.
	public int getSuit(){
        return suit;
    }

    // The getRank() method returns the rank of the Card.
    public int getRank(){
        return rank;
    }


    

}
