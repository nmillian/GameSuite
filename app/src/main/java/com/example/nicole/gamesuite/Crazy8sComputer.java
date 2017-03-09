package com.example.nicole.gamesuite;

/**
 * Created by Nicole on 3/3/2017.
 */

public class Crazy8sComputer {

    public Crazy8sComputer(){

    }

    public boolean DecideMove(Crazy8sBoard board){
        boolean haveAnEight = false;
        int locationOf8Card = 0;

        String topCard = board.GetTopTrashCard();
        String topFirstLetter = Character.toString(topCard.charAt(0));

        for(int i = 0; i < board.GetSizeOfComputerHand(); i++){

            String card = board.GetComputerCard(i);
            String firstLetter = Character.toString(card.charAt(0));

            if(topCard.contains("8")){
                //Play whatever card, get to choose suite
                board.ComputerUpdateTopCard(i);
                board.RemoveCardFromComputer(i);
                return true;
            }

            if(card.contains("8")){
                haveAnEight = true;
                locationOf8Card = i;
            }

            else {
                //the cards match in suite, doesn't matter what it is
                if (firstLetter.equals(topFirstLetter)) {
                    board.ComputerUpdateTopCard(i);
                    board.RemoveCardFromComputer(i);
                    return true;
                }

                else {
                    if (topFirstLetter.equals("c")) {
                        String temp = topCard.substring(5, topCard.length());
                        System.out.println("TEMP " + temp);

                        if (card.contains(temp)) {
                            board.ComputerUpdateTopCard(i);
                            board.RemoveCardFromComputer(i);
                            return true;
                        }

                    } else if (topFirstLetter.equals("h")) {
                        String temp = topCard.substring(6, topCard.length());
                        System.out.println("TEMP " + temp);

                        if (card.contains(temp)) {
                            board.ComputerUpdateTopCard(i);
                            board.RemoveCardFromComputer(i);
                            return true;
                        }

                    } else if (topFirstLetter.equals("d")) {
                        String temp = topCard.substring(8, topCard.length());
                        System.out.println("TEMP " + temp);

                        if (card.contains(temp)) {
                            board.ComputerUpdateTopCard(i);
                            board.RemoveCardFromComputer(i);
                            return true;
                        }

                    } else if (topFirstLetter.equals("s")) {
                        String temp = topCard.substring(6, topCard.length());
                        System.out.println("TEMP " + temp);

                        if (card.contains(temp)) {
                            board.ComputerUpdateTopCard(i);
                            board.RemoveCardFromComputer(i);
                            return true;
                        }
                    }
                }
            }
        }

        //Only play an 8 if all other cards are exhausted
        if(haveAnEight){
            //valid because 8s are wild cards, can place it
            board.ComputerUpdateTopCard(locationOf8Card);
            board.RemoveCardFromComputer(locationOf8Card);
            return true;
        }

        //couldn't play any card
        return false;
    }


}
