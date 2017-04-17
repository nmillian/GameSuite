package com.example.nicole.gamesuite;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Created by Nicole on 2/25/2017.
 */

public class Crazy8sBoard {

    /* *********************************************
`   * Private class variables
    ********************************************* */
    private String topCard = "bluesquaregrid";

    //Hold the hands for the comp and human
    private static ArrayList<String> handHuman = new ArrayList();
    private static ArrayList<String> handComputer = new ArrayList();

    //The deck of cards
    //Key is Suite/Value, second value
    private static ArrayList<String> deck = new ArrayList();

    /* *********************************************
`   * Constructor
    ********************************************* */

    /**
     * Name:
     * Crazy8sBoard
     *
     * Synopsis:
     * public Crazy8sBoard();
     * No params.
     *
     * Description:
     * The constructor used in order to initialize the board, computer, and save for the crazy 8s game board.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public Crazy8sBoard(){
        handComputer.clear();
        handHuman.clear();
        deck.clear();

        InitializeDeck();
        InitializeHumanHand();
        InitializeComputerHand();

        topCard = deck.get(0);
    }

    /* *********************************************
`   * Private functions
    ********************************************* */

    /**
     * Name:
     * InitializeComputerHand
     *
     * Synopsis:
     * private void InitializeComputerHand()
     * No params.
     *
     * Description:
     * Used in order to initialize the computer hand with the first 7 cards from the deck.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private void InitializeComputerHand(){
        for(int i = 0; i < 7; i++){
            //Add to the computer
            handComputer.add(deck.get(i));
        }

        for(int i = 6; i >= 0; i--){
            deck.remove(i);
        }
    }

    /**
     * Name:
     * InitializeHumanHand
     *
     * Synopsis:
     * private void InitializeHumanHand()
     * No params.
     *
     * Description:
     * Used in order to initialize the human hand with 7 cards from the deck.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private void InitializeHumanHand(){
        for(int i = 0; i < 7; i++){
            //Add to the human hand
            handHuman.add(deck.get(i));
        }

        for(int i = 6; i >= 0; i--){
            deck.remove(i);
        }

        /*
        System.out.println("HUMAN");
        for(int i = 0; i < handHuman.size(); i++){
            System.out.println(handHuman.get(i));
        }

        System.out.println("DECK AFTER REMOVE");
        for(int i = 0; i < deck.size(); i++){
            System.out.println(deck.get(i));
        }
        */
    }

    /**
     * Name:
     * InitializeDeck
     *
     * Synopsis:
     * private void InitializeDeck();
     * No params.
     *
     * Description:
     * Used in order to initialize the deck with all the possible cards that can be found in it and shuffle them randomly.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    private void InitializeDeck(){
        //Spades

        deck.add("spadesace");
        deck.add("spadesjack");
        deck.add("spadesqueen");
        deck.add("spadesking");
        deck.add("spades2");
        deck.add("spades3");
        deck.add("spades4");
        deck.add("spades5");
        deck.add("spades6");
        deck.add("spades7");
        deck.add("spades8");
        deck.add("spades9");
        deck.add("spades10");


        //Diamonds
        deck.add("diamondsace");
        deck.add("diamondsjack");
        deck.add("diamondsqueen");
        deck.add("diamondsking");
        deck.add("diamonds2");
        deck.add("diamonds3");
        deck.add("diamonds4");
        deck.add("diamonds5");
        deck.add("diamonds6");
        deck.add("diamonds7");
        deck.add("diamonds8");
        deck.add("diamonds9");
        deck.add("diamonds10");

        //Clubs
        deck.add("clubsace");
        deck.add("clubsjack");
        deck.add("clubsqueen");
        deck.add("clubsking");
        deck.add("clubs2");
        deck.add("clubs3");
        deck.add("clubs4");
        deck.add("clubs5");
        deck.add("clubs6");
        deck.add("clubs7");
        deck.add("clubs8");
        deck.add("clubs9");
        deck.add("clubs10");

        //Hearts
        deck.add("heartsace");
        deck.add("heartsjack");
        deck.add("heartsqueen");
        deck.add("heartsking");
        deck.add("hearts2");
        deck.add("hearts3");
        deck.add("hearts4");
        deck.add("hearts5");
        deck.add("hearts6");
        deck.add("hearts7");
        deck.add("hearts8");
        deck.add("hearts9");
        deck.add("hearts10");

        Collections.shuffle(deck);

        for(int i = 0; i < deck.size(); i++){
            System.out.println(deck.get(i));
        }
    }

    /* *********************************************
`   * Public functions
    ********************************************* */

    /**
     * Name:
     * ClearGame
     *
     * Synopsis:
     * public void ClearGame();
     * No params.
     *
     * Description:
     * Used in order to clear the handComputer, handHuman, and deck array lists.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void ClearGame(){
        handComputer.clear();
        handHuman.clear();
        deck.clear();
    }

    /**
     * Name:
     * ResetGame
     *
     * Synopsis:
     * public void ResetGame();
     * No params.
     *
     * Description:
     * Used in order to clear the handComputer, handHuman, and deck array lists and to re-initialize them with their original values.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void ResetGame(){
        handComputer.clear();
        handHuman.clear();
        deck.clear();

        InitializeDeck();
        InitializeComputerHand();
        InitializeHumanHand();

        topCard = deck.get(0);
    }

    /**
     * Name:
     * AddCardComputerFromSerial
     *
     * Synopsis:
     * public void AddCardComputerFromSerial(String value);
     * @param value -> The card to add to the computer hand.
     *
     * Description:
     * Used in order to add a single card to the computer hand from a serialized text file.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void AddCardComputerFromSerial(String value){
        handComputer.add(value);
    }

    /**
     * Name:
     * AddCardHumanFromSerial
     *
     * Synopsis:
     * public void AddCardHumanFromSerial(String value);
     * @param value -> The card to add to the computer hand.
     *
     * Description:
     * Used in order to add a single card to the human hand from a serialized text file.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void AddCardHumanFromSerial(String value){
        handHuman.add(value);
    }

    /**
     * Name:
     * AddCardDeckFromSerial
     *
     * Synopsis:
     * public void AddCardDeckFromSerial(String value);
     * @param value -> The card to add to the computer hand.
     *
     * Description:
     * Used in order to add a single card to the deck from a serialized text file.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void AddCardDeckFromSerial(String value){
        deck.add(value);
    }

    /**
     * Name:
     * SetTopTrashCard
     *
     * Synopsis:
     * public void SetTopTrashCard(String value);
     * @param value -> The card value to update the trash card to.
     *
     * Description:
     * Used in order to update the top card of the trash pile, which is the left pile.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void SetTopTrashCard(String value){
        topCard = value;
    }

    /**
     * Name:
     * AddCardToHumanHand
     *
     * Synopsis:
     * public void AddCardToHumanHand();
     * No params.
     *
     * Description:
     * Used in order to add a card to the human hand from the top of the deck.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void AddCardToHumanHand(){
        //Add the first card to the human hand
        handHuman.add(deck.get(0));

        //Remove card from deck
        deck.remove(0);
    }

    /**
     * Name:
     * AddCardToComputerHand
     *
     * Synopsis:
     * public void AddCardToComputerHand();
     * No params.
     *
     * Description:
     * Used in order to add a card from the top of the deck to the computer hand.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void AddCardToComputerHand(){
        //Add the first card to the computer
        handComputer.add(deck.get(0));

        //Remove card from deck
        deck.remove(0);
    }

    /**
     * Name:
     * VerifyHumanChoice
     *
     * Synopsis:
     * public boolean VerifyHumanChoice(int value);
     * @param value -> The card to check if it's able to be played.
     *
     * Description:
     * Used in order to verify that the human player selected a card to play that is valid. It must either
     * match the top trash card in rank or suit.
     *
     * Returns:
     * @return Boolean, true if the card is able to be played, false if the card is unable to be played.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public boolean VerifyHumanChoice(int value){
        String card = handHuman.get(value);
        System.out.print("CARD IN VERIFY HUMAN" + card);

        String firstLetter = Character.toString(card.charAt(0));
        String topFirstLetter = Character.toString(topCard.charAt(0));

        if(topCard.contains("8")){
            //Can play any card ontop to determine suite
            topCard = card;
            return true;
        }

        if(card.contains("8")){
            //valid because 8s are wild cards, human can place it
            topCard = card;
            return true;
        }

        //the cards match in suite, doesn't matter what it is
        else if(firstLetter.equals(topFirstLetter)){
            topCard = card;
            return true;
        }

        else{
            if(topFirstLetter.equals("c")){
                String temp = topCard.substring(5, topCard.length());
                System.out.println("TEMP " + temp);

                if(card.contains(temp)){
                    topCard = card;
                    return true;
                }

            }

            else if(topFirstLetter.equals("h")){
                String temp = topCard.substring(6, topCard.length());
                System.out.println("TEMP " + temp);

                if(card.contains(temp)){
                    topCard = card;
                    return true;
                }

            }

            else if(topFirstLetter.equals("d")){
                String temp = topCard.substring(8, topCard.length());
                System.out.println("TEMP " + temp);

                if(card.contains(temp)){
                    topCard = card;
                    return true;
                }

            }

            else if(topFirstLetter.equals("s")){
                String temp = topCard.substring(6, topCard.length());
                System.out.println("TEMP " + temp);

                if(card.contains(temp)){
                    topCard = card;
                    return true;
                }

            }
        }

        //You can't play this card
        return false;
    }

    /**
     * Name:
     * ComputerUpdateTopCard
     *
     * Synopsis:
     * public void ComputerUpdateTopCard(int value);
     * @param value -> The card to check if it's able to be played.
     *
     * Description:
     * Used in order to update the trash card with the card the computer player is going to play.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void ComputerUpdateTopCard(int value){
        topCard = handComputer.get(value);
    }

    /**
     * Name:
     * GetHumanCard
     *
     * Synopsis:
     * public String GetHumanCard(int value);
     * @param value -> The place to get the card from the human hand.
     *
     * Description:
     * Used in order to get the value of the card at a specified place in the array list.
     *
     * Returns:
     * @return String, the value of the card at the specified location.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public String GetHumanCard(int value){
        return handHuman.get(value);
    }

    /**
     * Name:
     * GetComputerCard
     *
     * Synopsis:
     * public String GetComputerCard(int value);
     * @param value -> The place to get the card from the computer hand.
     *
     * Description:
     * Used in order to get the value of a card at a specified location in the computer hand.
     *
     * Returns:
     * @return String, the value of the card at the specified location.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public String GetComputerCard(int value){
        return handComputer.get(value);
    }

    /**
     * Name:
     * GetSizeOfHumanHand
     *
     * Synopsis:
     * public int GetSizeOfHumanHand();
     * No params.
     *
     * Description:
     * Used in order to get the remaining number of cards the human player has.
     *
     * Returns:
     * @return Integer, the number of cards the human player has remaining.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public int GetSizeOfHumanHand(){
        System.out.println("SIZE " + handHuman.size() );
        return handHuman.size();
    }

    /**
     * Name:
     * GetSizeOfComputerHand
     *
     * Synopsis:
     * public int GetSizeOfComputerHand();
     * No params.
     *
     * Description:
     * Used in order to get the remaining number of cards the computer player has.
     *
     * Returns:
     * @return Integer, the number of cards the computer player has remaining.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public int GetSizeOfComputerHand(){
        return handComputer.size();
    }

    /**
     * Name:
     * GetDeckSize
     *
     * Synopsis:
     * public int GetDeckSize();
     * No params.
     *
     * Description:
     * Get the number of cards remaining in the deck.
     *
     * Returns:
     * @return Integer, the number of cards remaining in the playing deck.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public int GetDeckSize(){
        System.out.println("DECK " + deck.size());

        return deck.size();
    }

    /**
     * Name:
     * RemoveCardFromDeck
     *
     * Synopsis:
     * public void RemoveCardFromDeck(int value);
     * @param value
     *
     * Description:
     * Used in order to remove a card from the deck at a specified location.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void RemoveCardFromDeck(int value){
        deck.remove(value);
    }

    /**
     * Name:
     * RemoveCardFromHuman
     *
     * Synopsis:
     * public void RemoveCardFromHuman(int value);
     * @param value -> The location where the card should be removed from.
     *
     * Description:
     * Used in order to remove a card from the human hand from a specified location.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void RemoveCardFromHuman(int value){
        handHuman.remove(value);
    }

    /**
     * Name:
     * RemoveCardFromComputer
     *
     * Synopsis:
     * public void RemoveCardFromComputer(int value);
     * @param value -> The location where the card should be removed from.
     *
     * Description:
     * Used in order to remove a card from the computer hand from a specified location.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void RemoveCardFromComputer(int value){
        handComputer.remove(value);
    }

    /**
     * Name:
     * CheckForUnwinnableCondition
     *
     * Synopsis:
     * public boolean CheckForUnwinnableCondition();
     * No params.
     *
     * Description:
     * Check if it's impossible for the human or computer player to win if there are no more cards able to be drawn from the deck.
     *
     * Returns:
     * @return Boolean, true if it's possible for either player to make a move, false if it's impossible for either player to make a move.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public boolean CheckForUnwinnableCondition(){
        int humanSize = GetSizeOfHumanHand();
        int computerSize = GetSizeOfComputerHand();

        for(int i = 0; i < humanSize; i++) {
            //Human can play a card
            String card = handHuman.get(i);

            String firstLetter = Character.toString(card.charAt(0));
            String topFirstLetter = Character.toString(topCard.charAt(0));

            if (topCard.contains("8")) {
                //Can play any card ontop to determine suite
                return true;
            }

            if (card.contains("8")) {
                //valid because 8s are wild cards, human can place it
                return true;
            }

            //the cards match in suite, doesn't matter what it is
            else if (firstLetter.equals(topFirstLetter)) {
                return true;
            }

            else {
                if (topFirstLetter.equals("c")) {
                    String temp = topCard.substring(5, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("h")) {
                    String temp = topCard.substring(6, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("d")) {
                    String temp = topCard.substring(8, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("s")) {
                    String temp = topCard.substring(6, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }
                }
            }
        }

        for(int i = 0; i < computerSize; i++){
            //Human can play a card
            String card = handComputer.get(i);

            String firstLetter = Character.toString(card.charAt(0));
            String topFirstLetter = Character.toString(topCard.charAt(0));

            if (topCard.contains("8")) {
                //Can play any card ontop to determine suite
                return true;
            }

            if (card.contains("8")) {
                //valid because 8s are wild cards, human can place it
                return true;
            }

            //the cards match in suite, doesn't matter what it is
            else if (firstLetter.equals(topFirstLetter)) {
                return true;
            }

            else {
                if (topFirstLetter.equals("c")) {
                    String temp = topCard.substring(5, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("h")) {
                    String temp = topCard.substring(6, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("d")) {
                    String temp = topCard.substring(8, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }

                } else if (topFirstLetter.equals("s")) {
                    String temp = topCard.substring(6, topCard.length());

                    if (card.contains(temp)) {
                        return true;
                    }
                }
            }
        }

        //Human and computer can't play a card
        return false;
    }

    /**
     * Name:
     * GetTopTrashCard
     *
     * Synopsis:
     * public String GetTopTrashCard();
     * No params.
     *
     * Description:
     * Used in order to get the top card of the trash pile, the left deck.
     *
     * Returns:
     * @return String, the value of the card at the top of the trash pile.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public String GetTopTrashCard(){
        //System.out.println("TOP: " + topCard);
        return topCard;
    }

    /**
     * Name:
     * GetDeckCard
     *
     * Synopsis:
     * public String GetDeckCard(int value);
     * @param value -> The location of the card to get the value at.
     *
     * Description:
     * Get the value of a card at a specified location in the deck.
     *
     * Returns:
     * @return String, the value of a card at a specified location.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public String GetDeckCard(int value){
        return deck.get(value);
    }

    /**
     * Name:
     * printCompHand
     *
     * Synopsis:
     * public void printCompHand();
     * No params.
     *
     * Description:
     * Used in order to print the computer hand to the console for debugging.
     *
     * Returns:
     * None.
     *
     * Author:
     * Nicole Millian
     *
     * Date:
     * 3/12/2017
     */
    public void printCompHand(){
        for(int i = 0; i < GetSizeOfComputerHand(); i++){
            System.out.println(handComputer.get(i));
        }
    }

}
