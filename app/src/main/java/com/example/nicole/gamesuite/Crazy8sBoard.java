package com.example.nicole.gamesuite;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Created by Nicole on 2/25/2017.
 */

public class Crazy8sBoard {

    private String topCard;

    //Hold the hands for the comp and human
    private static ArrayList<String> handHuman = new ArrayList();
    private static ArrayList<String> handComputer = new ArrayList();

    //The deck of cards
    //Key is Suite/Value, second value
    private static ArrayList<String> deck = new ArrayList();

    public Crazy8sBoard(){
        InitializeDeck();
        InitializeHumanHand();
        InitializeComputerHand();

        topCard = deck.get(0);
    }

    private void InitializeComputerHand(){
        for(int i = 0; i < 7; i++){
            //Add to the computer
            handComputer.add(deck.get(i));
        }

        for(int i = 6; i >= 0; i--){
            deck.remove(i);
        }
    }

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

    public void AddCardToHumanHand(){
        //Add the first card to the human hand
        handHuman.add(deck.get(0));

        //Remove card from deck
        deck.remove(0);
    }

    public void AddCardToComputerHand(){
        //Add the first card to the computer
        handComputer.add(deck.get(0));

        //Remove card from deck
        deck.remove(0);
    }

    public boolean VerifyHumanChoice(int value){
        String card = handHuman.get(value);

        String firstLetter = Character.toString(card.charAt(0));
        String topFirstLetter = Character.toString(topCard.charAt(0));

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

    public String GetHumanCard(int value){
        return handHuman.get(value);
    }

    public int GetSizeOfHumanHand(){
        System.out.println("SIZE " + handHuman.size() );
        return handHuman.size();
    }

    public int GetDeckSize(){
        System.out.println("DECK " + deck.size());

        return deck.size();
    }

    public int GetSizeOfComputerHand(){
        return handComputer.size();
    }

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

    public void RemoveCardFromDeck(int value){
        deck.remove(value);
    }

    public void RemoveCardFromHuman(int value){
        handHuman.remove(value);
    }

    //Top trash card, the left pile
    public String GetTopTrashCard(){
        System.out.println("TOP: " + topCard);
        return topCard;
    }

    //Get the top card in the deck
    public String GetTopCard(int value){
        return deck.get(value);
    }

}