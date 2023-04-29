package com.example.myapplication;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.example.game.GamePlayer;
import com.example.game.LocalGame;
import com.example.game.actionMsg.GameAction;

/**
 * class PresidentLocalGame
 *
 * This class contains the constructor for a PresidentLocalGame as well as some checks for the game
 * such as if the game is over and if a player can make a move. This class also hold functions to
 * sent and receive info from players
 *
 */

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
    public void start(GamePlayer[] players){
        super.start(players);
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
        Log.i("wtf", String.format("%s", action.getClass().toString()));
        //Pass Action
        if(action instanceof PresidentPassAction){
            PresidentHumanPlayer php = (PresidentHumanPlayer) action.getPlayer();

            //Update current player's turn
            presidentGameState.updateTurn();
            presidentGameState.passCount++;
            php.views = php.updateCards(php.views, presidentGameState.getCards(presidentGameState.currentPlayer));
            php.views = php.presidentUI.resetCards(php.views);
            php.playerNumberText.setText("Player " + (presidentGameState.currentPlayer + 1));
            presidentGameState.chosenCards.clear();

            //If three passes have occurred, the round resets
            if (presidentGameState.passCount == 3){
                presidentGameState.passCount = 0;
                presidentGameState.cardsAtPlay = 0;
                presidentGameState.currentCardNum = 0;
                php.card.assignImages(500, php.views[14]);
            }
            php.presidentGameState = presidentGameState;
        }
        //Place Action
        else if (action instanceof PresidentPlaceAction){
            PresidentHumanPlayer php = (PresidentHumanPlayer) action.getPlayer();

            if (php.card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)) {
                //Since the cards are the same number, the displayed image is the first chosen card
                php.card.assignImages(presidentGameState.chosenCards.get(0), php.views[14]);
                php.views[0].setVisibility(View.VISIBLE);
                presidentGameState.cardsAtPlay = presidentGameState.chosenCards.size();
                //Updates current card value to chosen card value
                presidentGameState.currentCardNum = presidentGameState.chosenCards.get(0) % 100;
                php.presidentUI.updateChosenCardsTotal(php.chosenCardsTotal, presidentGameState.cardsAtPlay);
                php.presidentUI.updateButtonColor(php.placeCards, Color.RED);
                presidentGameState.passCount = 0;

                //Finds chosen cards in current player's hand and turns those values into 0
                while (presidentGameState.chosenCards.size() != 0) {
                    for (int i = 0; i < 13; i++) {
                        if (presidentGameState.chosenCards.get(0) == presidentGameState.allPlayers[presidentGameState.currentPlayer][i]) {
                            presidentGameState.allPlayers[presidentGameState.currentPlayer][i] = 0;
                            break;
                        }
                    }
                    presidentGameState.chosenCards.remove(Integer.valueOf(presidentGameState.chosenCards.get(0)));
                }

                php.views = php.updateCards(php.views, presidentGameState.getCards(presidentGameState.currentPlayer));
                php.playerNumberText = php.presidentUI.updatePlayerNumberText(php.playerNumberText, presidentGameState.currentPlayer, false);


                //A check to ensure the currentPlayer stays with 0-3 range
                presidentGameState.updateTurn();
                //Resets certain UI elements to prepare for the next player
                php.views = php.presidentUI.updateCards(php.views, presidentGameState.allPlayers[presidentGameState.currentPlayer]);
                php.views = php.presidentUI.resetCards(php.views);
                presidentGameState.chosenCards.clear();
                php.presidentUI.updateButtonColor(php.placeCards, Color.RED);
                php.playerNumberText = php.presidentUI.updatePlayerNumberText(php.playerNumberText, presidentGameState.currentPlayer, false);
            }
            php.presidentGameState = presidentGameState;
        }
        //Reset Action
        else if (action instanceof PresidentResetAction){
            PresidentHumanPlayer php = (PresidentHumanPlayer) action.getPlayer();

            //Resets game variable back to default
            presidentGameState.currentPlayer = 0;
            for (int i = 1; i <= 13; i++){
                php.card.assignImages(presidentGameState.allPlayers[0][i - 1], php.views[i]);
                php.views[i].setVisibility(View.VISIBLE);
            }
            php.card.assignImages(500, php.views[14]);
        }
        //Add/Remove Action
        else if (action instanceof PresidentAddCardAction || action instanceof PresidentRemoveCardAction){
            PresidentHumanPlayer php = (PresidentHumanPlayer) action.getPlayer();

            if (action instanceof PresidentAddCardAction)
                presidentGameState.chosenCards.add(presidentGameState.allPlayers[presidentGameState.currentPlayer][php.chosenCard - 1]);
            else
                presidentGameState.chosenCards.remove(Integer.valueOf(presidentGameState.allPlayers[presidentGameState.currentPlayer][php.chosenCard - 1]));

            //Checks if the chosen cards are legal so the placeCards button
            //can have the correct color
            if (php.card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)){
                php.presidentUI.updateButtonColor(php.placeCards, Color.GREEN);
            }
            else{
                php.presidentUI.updateButtonColor(php.placeCards, Color.RED);
            }
        }
        else if (action instanceof PresidentGiveHandAction){
            PresidentHumanPlayer php = (PresidentHumanPlayer) action.getPlayer();

            for (int i = 1; i <= 13; i++){
                php.card.assignImages(presidentGameState.allPlayers[0][i - 1], php.views[i]);
            }
        }

        return true;
    }

    @Override
    protected void receiveInfo (Object obj){
    }
}
