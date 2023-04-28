package com.example.myapplication;

import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;


public class PresidentLocalGame extends LocalGame {
    PresidentGameState presidentGameState;
    public PresidentLocalGame() {

        this.presidentGameState = new PresidentGameState();
        //TODO  You will implement this constructor
    }

    public PresidentLocalGame(PresidentLocalGame plg){
        this.presidentGameState = new PresidentGameState(plg.presidentGameState);
    }


    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new PresidentGameState(this.presidentGameState));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == presidentGameState.currentPlayer){
            return true;
        }
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        //Counts all of the 0 values in current player's hand
        int count = 0;
        for (int i = 0; i < 13; i++){
            if (presidentGameState.allPlayers[presidentGameState.currentPlayer][i] == 0)
            {count++;}
        }
        //If 13 zeroes are counted, then the current player has won
        //and all of the buttons become unclickable
        if (count == 13){
          return "Player " + presidentGameState.currentPlayer + " won";
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {

        if(action instanceof PresidentPassAction){
            if(presidentGameState.currentPlayer > 3){
                presidentGameState.currentPlayer = 0;
            }
            presidentGameState.currentPlayer++;
            return true;

        } else if (action instanceof PresidentPlaceAction){

        }

        return false;
    }

    @Override
    protected void receiveInfo (Object obj){
        if (obj instanceof PresidentPassAction){
            presidentGameState.updateTurn();

        }
    }
}
