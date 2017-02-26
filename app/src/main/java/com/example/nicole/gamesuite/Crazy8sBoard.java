package com.example.nicole.gamesuite;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Nicole on 2/25/2017.
 */

public class Crazy8sBoard {

    //Hold the hands for the comp and human
    private static Hashtable<String, String> handHuman = new Hashtable<String, String>();
    private static Hashtable<String, String> handComputer = new Hashtable<String, String>();

    //The deck of cards
    //Key is Suite/Value, second value
    private static ArrayList<String> deck = new ArrayList();

    public Crazy8sBoard(){
        InitializeDeck();
    }

    private void InitializeDeck(){
        //Spades
        /*
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
        */

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

    }

    public String getTopCard(int value){
        return deck.get(value);
    }

}
