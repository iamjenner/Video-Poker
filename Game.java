/*
 * @author Ijenna Okonkwo
 * @date 11/28/2023
 *
 * This class simulates a video poker game.
 * It has three instance variables: p, cards, and input.
 * Contains many helper methods to make the code cleaner.
 */
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Game {
	
	private Player p;
	private Deck cards;
	private Scanner input;
	
	
	public Game(String[] testHand){

		p = new Player();
		cards = new Deck();
		input = new Scanner(System.in);
		cards.addCardsToDeck();
		for(int i = 0; i < testHand.length; i++){
			p.addCard(cards.getCard(testHand[i]));
		}
		
	}
	
	public Game(){
		p = new Player();
		cards = new Deck();
		input = new Scanner(System.in);
		cards.addCardsToDeck();
		// This no-argument constructor is to actually play a normal game
		
	}
	
	public void play(){

		boolean playing = true;

		while(playing){
			// reset and shuffle cards
			cards.reset();
			cards.shuffle();

			// ask user how much they are betting
			getBet();


			// deal cards to player and prints them
			if(p.getHand().size() == 0){
				dealPlayerCards();
			}
			printHand();
			/* use the position from the user to remove the card in the players hand and add new ones
			* prints the hand again
			*/
			discardCards();
			printHand();


			// check to see what hand the player has
			System.out.println(checkHand(p.getHand()));

			if(p.getBankroll() == 0){
				System.out.println("You lost! :(");
				playing = false;
			}
			else{
				System.out.print("Do you want to continue playing? (yes/no): ");
				String ans = input.nextLine();

				ans = input.nextLine();

				if(ans.equals("yes")){
					System.out.println("Generating new hand...");
					for(int i = p.getHand().size()-1; i >= 0; i--){
						p.getHand().remove(i);
					}
				} else {
					System.out.println("Congrats! Your bankroll is: " + p.getBankroll());
					playing = false;
				}
			}

			


		}

	}
	
	// Prints out the score of the player's hand and adjusts the player's bankroll
	// based on the score.
	public String checkHand(ArrayList<Card> hand){
		if(isRoyalFlush()){
			p.winnings(250);
			return "Royal Flush";
		}

		else if(isStraightFlush()){
			p.winnings(50);
			return "Straight Flush";
		}

		else if(isFourOfAKind()){
			p.winnings(25);
			return "Four of a Kind";
		}

		else if(isFullHouse()){
			p.winnings(6);
			return "Full House";
		}

		else if(isFlush()){
			p.winnings(5);
			return "Flush";
		}

		else if(isStraight()){
			p.winnings(4);
			return "Straight";
		}

		else if(isThreeOfAKind()){
			p.winnings(3);
			return "Three of a Kind";
		}

		else if(isTwoPair()){
			p.winnings(2);
			return "Two Pair";
		}

		else if(isOnePair()){
			p.winnings(1);
			return "One Pair";
		}

		else{
			return "No pair :(";
		}
	}

	// Asks the user how many tokens they are betting. 
	// Checks to make sure that they enter a number between 0 and their bankroll.
	public void getBet(){
		System.out.print("How many tokens are you betting? Please choose between 0 and " + p.getBankroll() + ": ");
			double b = input.nextDouble();
			if(b < 0 || b > p.getBankroll()){
				System.out.print("Please choose between 0 and " + p.getBankroll());
				b = input.nextDouble();
			}
			p.bets(b);
	}
	
	// Adds 5 cards into the player's hand
	public void dealPlayerCards(){
		for(int i = 0; i < 5; i++){
				p.addCard(cards.deal());
			}
	}
	
	// Asks the user to enter the position of the cards they want to discard. 
	// Must enter one at a time, -1 to end.
	public void discardCards(){
		System.out.print("Enter the position of the card you want to change (-1 to stop): ");
			int pos = input.nextInt();
			int count = 0;
            while(pos != -1){
                int index = pos - 1;
                Card removed = p.cardToRemove(index);
                p.removeCard(removed);
				count++;

				System.out.println("-------");
				printHand();

				System.out.print("Enter the position of the card you want to change (-1 to stop): ");
				pos = input.nextInt();

            }

			System.out.println("Generating your hand...");
			for(int i = 0; i < count; i++){
				p.addCard(cards.deal());
			}

	}

	// Prints the player's hand.
	public void printHand(){
		ArrayList<Card> hand = p.getHand();
        System.out.println("Here is your hand:");
        int count = 1;

        for(Card c : hand){
            System.out.println(count + ". " + c);
            count++;
        }
    }

	// Returns true if players hand is a Royal Flush; returns false if not
	public boolean isRoyalFlush(){
		ArrayList<Card> playerHand = p.getHand();
		int firstSuit = playerHand.get(0).getSuit();
		ArrayList<Integer> royalFlushRanks = new ArrayList<Integer>();

		royalFlushRanks.add(1);
		royalFlushRanks.add(10);
		royalFlushRanks.add(11);
		royalFlushRanks.add(12);
		royalFlushRanks.add(13);

		for(Card c : playerHand){
			if(c.getSuit() == firstSuit){
				int r = c.getRank();
				for(int i = 0; i < royalFlushRanks.size(); i++){
					if(r == royalFlushRanks.get(i)){
						royalFlushRanks.remove(i);
					}
				}
				if(royalFlushRanks.size() == 0){
					return true;
				}
			}
		}
		return false;
	}

	// Returns true if the player's hand is a Straight Flush, returns false if not
	public boolean isStraightFlush(){
		ArrayList<Card> playerHand = p.getHand();
		int firstSuit = playerHand.get(0).getSuit();
		ArrayList<Integer> playerRanks = new ArrayList<>();

		for(int i = 0; i < playerHand.size(); i++){
			playerRanks.add(playerHand.get(i).getRank());
		}

		Collections.sort(playerRanks);

		for(Card c : playerHand){
			if(c.getSuit() != firstSuit){
				return false;
			}
		}
		
		for(int i = 0; i < 4; i++){
			if(playerRanks.get(i) != playerRanks.get(i+1) -1){
				return false;
			}
		}

		return true;


	}

	// Returns true if the players hand is Four of a Kind, false if not.
	public boolean isFourOfAKind(){
		ArrayList<Card> playerHand = p.getHand();
		int count = 0;

		for(Card c : playerHand){
			int rank = c.getRank();
			for(int i = 0; i < playerHand.size(); i++){
				if(rank == playerHand.get(i).getRank()){
					count++;
				}
			}
			if(count == 4){
				return true;
			}
			count = 0;
		}
		return false;
	}

	// Returns true if the players hand is a Full House, false if not.
	public boolean isFullHouse(){
		if( isThreeOfAKind() == true && isOnePair() == true){
			return true;
		}

		return false;
	}

	public boolean isFlush(){
		ArrayList<Card> playerHand = p.getHand();
		int firstSuit = playerHand.get(0).getSuit();

		for(Card c : playerHand){
			if(c.getSuit() != firstSuit){
				return false;
			}
		}

		return true;

	}

	// Returns true if the player's hand is a Straight, false if not.
	public boolean isStraight(){
		ArrayList<Card> playerHand = p.getHand();
		ArrayList<Integer> playerRanks = new ArrayList<>();

		for(int i = 0; i < playerHand.size(); i++){
			playerRanks.add(playerHand.get(i).getRank());
		}

		Collections.sort(playerRanks);

		// check for 10 11 12 13 1
		if(playerRanks.get(0) == 1 && playerRanks.get(1) == 10){
			for(int i = 1; i < 4; i++){
			if(playerRanks.get(i) != playerRanks.get(i+1) - 1){
				return false;
			}
			}
			return true;
		}

		for(int i = 0; i < 4; i++){
			if(playerRanks.get(i) != playerRanks.get(i+1) - 1){
				return false;
			}
		}

		return true;


	}

	// Returns true is the player's hand is a Three of a Kind, false if not.
	public boolean isThreeOfAKind(){
		ArrayList<Card> playerHand = p.getHand();
		int count = 0;

		for(Card c : playerHand){
			int rank = c.getRank();
			for(int i = 0; i < playerHand.size(); i++){
				if(rank == playerHand.get(i).getRank()){
					count++;
				}
			}
			if(count == 3){
				return true;
			}
			count = 0;
		}
		return false;

	}

	// Returns ture if the player's hand is a Two Pair, false if not.
	public boolean isTwoPair() {
    	ArrayList<Card> playerHand = p.getHand();

    	int[] ranks = new int[playerHand.size()];

    	for (int i = 0; i < playerHand.size(); i++) {
        	ranks[i] = playerHand.get(i).getRank();
    	}

    	int pairCount = 0;
		int firstPair = 0;

    	for (int i = 0; i < ranks.length; i++) {
        	int temp = ranks[i];
        	int count = 0;

        	// Count how many cards have the same rank as temp
        	for (int j = 0; j < ranks.length; j++) {
            	if (temp == ranks[j]) {
                count++;
            	}
        	}

        	// If there is a pair, increment pairCount
        	if (count == 2) {
            	pairCount++;
				firstPair = temp;
				break;
        	}
    	}

		for (int i = 0; i < ranks.length; i++) {
        	int temp = ranks[i];
        	int count = 0;

        	// Count how many cards have the same rank as temp
        	for (int j = 0; j < ranks.length; j++) {
            	if (temp == ranks[j] && temp != firstPair) {
                count++;
            	}
        	}

        	// If there is a pair, increment pairCount
        	if (count == 2) {
            	pairCount++;
				break;
        	}
    	}

    	// Check if there are exactly two pairs
    	return pairCount == 2;
	}

	// Returns true if they player's hand has a pair, false if not. 
	public boolean isOnePair(){

		ArrayList<Card> playerHand = p.getHand();

    	int[] ranks = new int[playerHand.size()];

    	for (int i = 0; i < playerHand.size(); i++) {
        	ranks[i] = playerHand.get(i).getRank();
    	}

    	int pairCount = 0;

    	for (int i = 0; i < ranks.length; i++) {
        	int temp = ranks[i];
        	int count = 0;

        	// Count how many cards have the same rank as temp
        	for (int j = 0; j < ranks.length; j++) {
            	if (temp == ranks[j]) {
                count++;
            	}
        	}

        	// If there is a pair, return true
        	if (count == 2) {
            	return true;
        	}
    	}

		return false;

	}

}
