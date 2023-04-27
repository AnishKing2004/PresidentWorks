package com.example.myapplication;


import android.widget.ImageView;

import java.util.ArrayList;
/**
 * @author Yutaka Roberts
 * @author Anish Karumuri
 * @author Zella Run
 * @author Calvin Phuong
 * @version April 2023
 *
 * This class has all of our playing cards
 * Here we assign the value and image to them
 */
public class Cards {
    ArrayList<Integer> cards;
    public void setCards() {

        cards = new ArrayList<>();
        cards.add(103); //clubs3
        cards.add(104); //clubs4
        cards.add(105); //clubs5
        cards.add(106); //clubs6
        cards.add(107); //clubs7
        cards.add(108); //clubs8
        cards.add(109); //clubs9
        cards.add(110); //clubs10
        cards.add(111); //clubsJack
        cards.add(112); //clubsQueen
        cards.add(113); //clubsKing
        cards.add(114); //clubsAce
        cards.add(115); //clubs2
        //test

        cards.add(203); //diamonds3
        cards.add(204); //diamonds4
        cards.add(205); //diamonds5
        cards.add(206); //diamonds6
        cards.add(207); //diamonds7
        cards.add(208); //diamonds8
        cards.add(209); //diamonds9
        cards.add(210); //diamonds10
        cards.add(211); //diamondsJack
        cards.add(212); //diamondsQueen
        cards.add(213); //diamondsKing
        cards.add(214); //diamondsAce
        cards.add(215); //diamonds2

        cards.add(303); //hearts3
        cards.add(304); //hearts4
        cards.add(305); //hearts5
        cards.add(306); //hearts6
        cards.add(307); //hearts7
        cards.add(308); //hearts8
        cards.add(309); //hearts9
        cards.add(310); //hearts10
        cards.add(311); //heartsJack
        cards.add(312); //heartsQueen
        cards.add(313); //heartsKing
        cards.add(314); //heartsAce
        cards.add(315); //hearts2

        cards.add(403); //spades3
        cards.add(404); //spades4
        cards.add(405); //spades5
        cards.add(406); //spades6
        cards.add(407); //spades7
        cards.add(408); //spades8
        cards.add(409); //spades9
        cards.add(410); //spades10
        cards.add(411); //spadesJack
        cards.add(412); //spadesQueen
        cards.add(413); //spadesKing
        cards.add(414); //spadesAce
        cards.add(415); //spades2

        //cards.add(500); //back of card


    }

    public void assignImages(int card, ImageView image) {
        switch (card){
            case 103:
                image.setImageResource(R.drawable.clubs3);
                break;
            case 104:
                image.setImageResource(R.drawable.clubs4);
                break;
            case 105:
                image.setImageResource(R.drawable.clubs5);
                break;
            case 106:
                image.setImageResource(R.drawable.clubs6);
                break;
            case 107:
                image.setImageResource(R.drawable.clubs7);
                break;
            case 108:
                image.setImageResource(R.drawable.clubs8);
                break;
            case 109:
                image.setImageResource(R.drawable.clubs9);
                break;
            case 110:
                image.setImageResource(R.drawable.clubs10);
                break;
            case 111:
                image.setImageResource(R.drawable.jack_of_clubs2);
                break;
            case 112:
                image.setImageResource(R.drawable.queen_of_clubs2);
                break;
            case 113:
                image.setImageResource(R.drawable.king_of_clubs2);
                break;
            case 114:
                image.setImageResource(R.drawable.ace_of_clubs);
                break;
            case 115:
                image.setImageResource(R.drawable.clubs2);
                break;

            // A break so it don't get confusing :)

            case 203:
                image.setImageResource(R.drawable.diamonds3);
                break;
            case 204:
                image.setImageResource(R.drawable.diamonds4);
                break;
            case 205:
                image.setImageResource(R.drawable.diamonds5);
                break;
            case 206:
                image.setImageResource(R.drawable.diamonds6);
                break;
            case 207:
                image.setImageResource(R.drawable.diamonds7);
                break;
            case 208:
                image.setImageResource(R.drawable.diamonds8);
                break;
            case 209:
                image.setImageResource(R.drawable.diamonds9);
                break;
            case 210:
                image.setImageResource(R.drawable.diamonds10);
                break;
            case 211:
                image.setImageResource(R.drawable.jack_of_diamonds2);
                break;
            case 212:
                image.setImageResource(R.drawable.queen_of_diamonds2);
                break;
            case 213:
                image.setImageResource(R.drawable.king_of_diamonds2);
                break;
            case 214:
                image.setImageResource(R.drawable.ace_of_diamonds);
                break;
            case 215:
                image.setImageResource(R.drawable.diamonds2);
                break;

            // A break so it don't get confusing :)

            case 303:
                image.setImageResource(R.drawable.hearts3);
                break;
            case 304:
                image.setImageResource(R.drawable.hearts4);
                break;
            case 305:
                image.setImageResource(R.drawable.hearts5);
                break;
            case 306:
                image.setImageResource(R.drawable.hearts6);
                break;
            case 307:
                image.setImageResource(R.drawable.hearts7);
                break;
            case 308:
                image.setImageResource(R.drawable.hearts8);
                break;
            case 309:
                image.setImageResource(R.drawable.hearts9);
                break;
            case 310:
                image.setImageResource(R.drawable.hearts10);
                break;
            case 311:
                image.setImageResource(R.drawable.jack_of_hearts2);
                break;
            case 312:
                image.setImageResource(R.drawable.queen_of_hearts2);
                break;
            case 313:
                image.setImageResource(R.drawable.king_of_hearts2);
                break;
            case 314:
                image.setImageResource(R.drawable.ace_of_hearts);
                break;
            case 315:
                image.setImageResource(R.drawable.hearts2);
                break;

            // A break so it don't get confusing :)

            case 403:
                image.setImageResource(R.drawable.spades3);
                break;
            case 404:
                image.setImageResource(R.drawable.spades4);
                break;
            case 405:
                image.setImageResource(R.drawable.spades5);
                break;
            case 406:
                image.setImageResource(R.drawable.spades6);
                break;
            case 407:
                image.setImageResource(R.drawable.spades7);
                break;
            case 408:
                image.setImageResource(R.drawable.spades8);
                break;
            case 409:
                image.setImageResource(R.drawable.spades9);
                break;
            case 410:
                image.setImageResource(R.drawable.spades10);
                break;
            case 411:
                image.setImageResource(R.drawable.jack_of_spades2);
                break;
            case 412:
                image.setImageResource(R.drawable.queen_of_spades2);
                break;
            case 413:
                image.setImageResource(R.drawable.king_of_spades2);
                break;
            case 414:
                image.setImageResource(R.drawable.ace_of_spades);
                break;
            case 415:
                image.setImageResource(R.drawable.spades2);
                break;

            case 500:
                image.setImageResource(R.drawable.back_card);
                break;




        }

    }

    public boolean legal(ArrayList<Integer> chosenCards, int cardPlayNum, int currentCardNum){
        //If the required number of cards is 0, then a new round has started and the player
        //choses the next card value and required number of cards
        if (cardPlayNum == 0){
            //Returns false if no cards have been chosen
            if (chosenCards.size() == 0){
                return false;
            }
            //Checks that every chosen card is the same value
            for (int i = 0; i < chosenCards.size() - 1; i++){
                int num1 = chosenCards.get(i);
                int num2 = chosenCards.get(i + 1);
                if (!(num1 % 100 == num2 % 100)){
                    return false;
                }
            }
            return true;
        }
        //Returns false if player hasn't chosen the correct number of cards
        else if (chosenCards.size() != cardPlayNum){
            return false;
        }
        else{
            //A check if the chosen card is correct
            //(When only one card is required)
            if (chosenCards.size() == 1){
                if (chosenCards.get(0) % 100 > currentCardNum){
                    return true;
                }
                return false;
            }
            else{
                //Checks that every chosen card is the same value
                for (int i = 0; i < chosenCards.size() - 1; i++){
                    int num1 = chosenCards.get(i);
                    int num2 = chosenCards.get(i + 1);
                    if (!(num1 % 100 == num2 % 100)){
                        return false;
                    }
                }
                //A check if chosen cards are correct
                //(When more than one card is required)
                if (chosenCards.get(0) % 100 > currentCardNum){
                    return true;
                }
                return false;
            }
        }
    }
}

