package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.example.game.infoMsg.GameState;

/**
 * @author Yutaka Roberts
 * @author Anish Karumuri
 * @author Zella Run
 * @author Calvin Phuong
 * @version April 2023
 *
 * Has a bunch of variables which track current cards, whose cards are whose, how many cards each player has currently
 *
 */
public class PresidentGameState extends GameState {
    private int playerId; // the player
    final int maxCardsHand = 13;
    final int numCardsDeck = 52;
    public boolean isCardCorrect; //check if the card is playable
    public boolean isCardVisible;
    public boolean is3Spade; // dictates which player starts first

    // public Object card;

    //2D array of all four players and each of their hands
    int allPlayers[][];

    //Index of current player playing
    int currentPlayer;

    //Number of required cards to play
    public int cardsAtPlay = 0;

    //Number of passes that have occurred
    public int passCount = 0;

    //Value of card in play
    public int currentCardNum = 0;



    // ArrayList <GamePlayerType> player = new ArrayList<GamePlayerType>();

    boolean emptyStack = true;





    public PresidentGameState() { // basic constructor intializing the variables

        allPlayers = new int[4][13];
        /*player.add(new GamePlayerType("Local Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new HumanPlayer(name);
            }
        });

        player.add(new GamePlayerType("Local Computer Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentComputerPlayer(name);
            }
        });*/

        currentPlayer = 0;


    }

    public PresidentGameState(PresidentGameState president){ // copy costructor that makes deep copies
        this.playerId = playerId;
        // cards;
        this.isCardCorrect = president.isCardCorrect;
        this.isCardVisible = president.isCardVisible;
        this.is3Spade = president.is3Spade;

    }

    public void updateTurn(){
        currentPlayer++;
        if (currentPlayer > 3) {
            currentPlayer = 0;
        }
    }

    /*//public void setPlayerId(int id){
        this.playerId = id;
    }*/

    /*  public int getPlayerId() {return playerId;}*/

//    //public int getCurrentPlayer(){
//        return currentPlayer;
//    }






}




