# Video-Poker

## Overview

This is a simple console-based video poker simulator written in
Java. The program allows users to play video poker by discarding
and drawing cards to achieve the best possible hand.

## Features

PokerTest.java is the test class that allows you to check sample hands
and play the actual game.

Card.java is a class that represents a single card. 

    A Card contains four instance variables, its suit (detonated by numbers 1-4) 
    and its rank (detonated by numbers 1-13). There is also two String arrays that
    hold the names of the suits and ranks.

    It is comparable, so there is a compareTo() method that compares the ranks
    between two cards.

    There is a toString method that returns the name of the card.

Deck.java is a class that represents a Deck of Cards.

    A Deck contains an array of Cards that holds 52 cards, and an integer, top, that
    holds the index of the top card in the deck.

    The addCardsToDeck() method adds the 52 Cards to the empty array of Cards.

    The shuffle() method shuffles the deck of cards by switching random cards 52 times.

    The deal() method returns the card at the top of the deck and increases top by 1.

    The getSuit() and getRank() methods return the numerical value of the suit and rank of
    the String parameter. They are helper methods for the getCard() method.

    The getCard() method uses the String parameter to find the corresponding card.

    The reset() method sets top to the first index.

Player.java is a class that represents the player in the game.

    A Player contains three instance variables: an ArrayList of Cards called hand,
    and two doubles called bankroll and bet. When a new Player is created, bankroll
    is initalized as 50 and bet 0.

    The addCard() and removeCard() methods add/remove the Card in the parameter.

    The bets() method takes in a double called amt as its parameter. The double bet is assigned
    amt, and bankroll is decreased bt amt.

    The winnings() method takes in a double odds as its parameter. Bankroll is increased by
    the product of odds and bet.

    The getBankroll() and getHand() method are accessor methods that return the bankroll and hand, 
    respectively. 

    The cardToRemove() is a helper method that returns the Card at the position given in the 
    parameter.

Game.java is a class that simulates the video poker game.

    It has three instance variables: a Player p, a Deck cards, and a Scanner input.

    It contains an overloaded constructor. The constructor that contains an array of Strings
    as its parameter is the constructor that allows a user to test hands of their choosing.
    The no-argument constructor is to play a normal game.

    The play() method plays the game for as long as the user indicates they want to play. I 
    used a boolean called playing to be true as long as the user says they want to play 
    another round after the first round. If the users bankroll is 0, they lost and can not
    play another round.

    I used several helper methods: checkHand(), getBet(), dealPlayerCard().. ect to keep
    the code cleaner and to separate my thoughts. The getBet() method gets the bet from the user.
    The user can bet however much tokens they want, as long as it is between 0 and their bankroll.
    The dealPlayerCard() adds 5 cards to players hand from the deck as long as the player does not
    already have 5 cards. The discardCards() method asks the user which cards they want to discard.
    They MUST enter the number one at a time because after a number is entered, the position of the
    cards shift based on the number of cards left. New cards are not added until the user enters -1 to
    indicate that they are finished. New cards are added to the players Hand based on how many cards they
    removed. The printHand() method simply uses the toString method from the Card class to print out each
    card in the players hand.

    There are also methods that return booleans like isRoyalFlush(), isStraight, isTwoPair, ect. These
    are used in the checkHand() method to check the hand of the player. The highest score (Royal flush) is 
    put in the first if statement, and then i used else if statements for the rest in order until I got to the
    last score isOnePair. I did it this so that (for example) a Straight Flush would print out Straight Flush instead
    of Straight or Flush.

    I used Collections.sort() in many of these methods in order to sort the ranks of the cards in the player's hand.
    This was useful to determine if a card had consecutive values or not.

## Gameplay

    Upon starting, the player is dealt a hand of five cards.
    The player can choose to discard and draw new cards.
    The final hand is evaluated, and the outcome is displayed.
    The player can choose to continue playing or exit the game.

