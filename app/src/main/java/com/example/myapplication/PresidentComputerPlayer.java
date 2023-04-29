package com.example.myapplication;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

import java.util.ArrayList;

/**
 * class PresidentComputerPlayer
 *
 *   @author Yutaka Roberts
 *   @author Anish Karumuri
 *   @author Zella Running
 *   @author Calvin Phuong
 *   @version April 2023
 *
 * This file contains the constructor for the Smart AI as well as it's receive info
 *
 */
public class PresidentComputerPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */

    int[] cards;
    int smallest;
    public PresidentComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {

        PresidentGameState pgs = (PresidentGameState) info;
        if(pgs.currentPlayer != playerNum ){
            return;
        }
        PresidentPassAction ppa = new PresidentPassAction(this);
        this.game.sendAction(ppa);
    }

    public ArrayList<Integer> pickCards (PresidentGameState gameState) {
        int[] hand = gameState.allPlayers[gameState.currentPlayer];
        smallest = 14;
        //Finds the smallest card that is not below the current card value
        for (int i = 0; i < 13; i++) {
            if (smallest > hand[i] % 100 && hand[i] % 100 > gameState.currentCardNum) {
                smallest = hand[i] % 100;
            }
        }

        ArrayList<Integer> chosen = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            //Finds every card that has the value of 'smallest'
            //and puts them into an array
            if (hand[i] % 100 == smallest) {
                chosen.add(hand[i]);
            }

            //Break the loop if the round is new and one card has been chosen, or if only one card is necessary
            if (chosen.size() == 1 && gameState.cardsAtPlay == 0) {
                break;
            } else if (chosen.size() == 1 && gameState.cardsAtPlay == 1) {
                break;
            }
        }
        //Sends chosen cards back to the MainActivity to be evaluated
        return chosen;
    }
}
