/*
 * @author Ijenna Okonkwo
 * @date 11/28/2023
 *
 * This class represents the player in the video poker game.
 * It has three instance variables: hand, bankroll, and bet.
 */
import java.util.ArrayList;


public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
		
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 50;
        bet = 0;
	}

    // adds card to hand
	public void addCard(Card c){
	    hand.add(c);
	}

    // removes the card from hand
	public void removeCard(Card c){
	    for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).equals(c)){
                hand.remove(i);
            }
        }
    }
	
    // changes bet to the amount the player wants to bet & decreases bankroll by that amount
    public void bets(double amt){
        bet = amt;
        bankroll -= amt;
    }
    
    // changes bankroll based on what the player won from their hand
    public void winnings(double odds){
        bankroll += odds*bet;
    }

    // returns bankroll
    public double getBankroll(){
        return bankroll;
    }

    // returns hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    // finds the card at position pos
    public Card cardToRemove(int pos){
        return hand.get(pos);
    }


}
