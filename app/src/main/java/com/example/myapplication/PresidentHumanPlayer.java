package com.example.myapplication;

import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game.GameHumanPlayer;
import com.example.game.GameMainActivity;
import com.example.game.infoMsg.GameInfo;
import com.example.game.infoMsg.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * class PresidentHumanPlayer
 * This class lets us setup the default UI
 * We can also edit the UI here based on each of the players action
 *
 *
 */
public class PresidentHumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    public ImageView[] views = new ImageView[15];
    Cards card = new Cards();

    //PresidentComputerPlayer dumbAI = new PresidentComputerPlayer("kickDock");

    //Array of cards that the player chooses
    ArrayList<Integer> chosenCards = new ArrayList<>();
    Button placeCards;


    Button passButton;

    //Text of required number of cards to play
    TextView chosenCardsTotal;

    //Text of current player
    TextView playerNumberText;

    PresidentUI presidentUI = new PresidentUI();

    int chosenCard = 0;

    private GameMainActivity myActivity;
    private int actionCount = 0;
    public PresidentGameState presidentGameState = new PresidentGameState();

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public PresidentHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(!(info instanceof GameState)){
            this.flash(1,2000);
            return;
        }

        PresidentGameState pgs = (PresidentGameState) info;

        /*
        for (int i = 1; i <= 13; i++){
            card.assignImages(gameState.allPlayers[0][i - 1], views[i]);
        }
        */



        Log.i("Debug",name + " " + pgs.currentPlayer);


    }


    @Override
    public void setAsGui(GameMainActivity activity) {

        myActivity = activity;
        activity.setContentView(R.layout.activity_main);

        passButton = (Button) activity.findViewById((R.id.passButton));
        placeCards = (Button) activity.findViewById(R.id.placeCards);
        placeCards = presidentUI.updateButtonColor(placeCards, Color.RED);
        chosenCardsTotal = (TextView) activity.findViewById(R.id.cardPlay);
        playerNumberText = (TextView) activity.findViewById((R.id.playerNumber));

        views[0] = (ImageView) activity.findViewById(R.id.iv_deck);
        views[1] = (ImageView) activity.findViewById(R.id.iv_card1);
        views[2] = (ImageView) activity.findViewById(R.id.iv_card2);
        views[3] = (ImageView) activity.findViewById(R.id.iv_card3);
        views[4] = (ImageView) activity.findViewById(R.id.iv_card4);
        views[5] = (ImageView) activity.findViewById(R.id.iv_card5);
        views[6] = (ImageView) activity.findViewById(R.id.iv_card6);
        views[7] = (ImageView) activity.findViewById(R.id.iv_card7);
        views[8] = (ImageView) activity.findViewById(R.id.iv_card8);
        views[9] = (ImageView) activity.findViewById(R.id.iv_card9);
        views[10] = (ImageView) activity.findViewById(R.id.iv_card10);
        views[11] = (ImageView) activity.findViewById(R.id.iv_card11);
        views[12] = (ImageView) activity.findViewById(R.id.iv_card12);
        views[13] = (ImageView) activity.findViewById(R.id.iv_card13);
        views[14] = (ImageView) activity.findViewById(R.id.imageView2);

        for (int i = 1; i <= 13; i++){
            views[i].setVisibility(View.VISIBLE);
        }
        card.assignImages(500, views[14]);


        placeCards.setOnClickListener(this);
        passButton.setOnClickListener(this);

        for (int i = 0; i <= 13; i++){
            views[i].setOnClickListener(this);
        }

        playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, 0, false);

        int count = 0;

        count++;







    }

    @Override
    public void onClick(View view) {
        PresidentLocalGame plg = new PresidentLocalGame();
        if (passButton.equals(view)) {
            this.game.sendAction(new PresidentPassAction(this));
            if (actionCount > 0){
                //Update current player's turn
                presidentGameState.updateTurn();
                presidentGameState.passCount++;
                views = updateCards(views, presidentGameState.getCards(presidentGameState.currentPlayer));
                views = presidentUI.resetCards(views);
                playerNumberText.setText("Player " + (presidentGameState.currentPlayer + 1));
                presidentGameState.chosenCards.clear();
                views[0].setVisibility(View.INVISIBLE);
                //Counts all of the 0 values in current player's hand
                int count = 0;
                for (int i = 0; i < 13; i++){
                    if (presidentGameState.allPlayers[presidentGameState.currentPlayer][i] == 0)
                        count++;
                }
                //If 13 zeroes are counted, then the current player has won
                //and all of the buttons become unclickable
                if (count == 13){
                    for (int i = 0; i < 13; i++){
                        views[i].setClickable(false);
                    }
                    passButton.setClickable(false);
                    playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, presidentGameState.currentPlayer, true);
                }


                //If three passes have occurred, the round resets
                if (presidentGameState.passCount == 3){
                    presidentGameState.passCount = 0;
                    presidentGameState.cardsAtPlay = 0;
                    presidentGameState.currentCardNum = 0;
                    card.assignImages(500, views[14]);
                }
            }


            if (presidentGameState.currentPlayer == 1) {
                views[0].setVisibility(View.INVISIBLE);
                views[0].setClickable(false);
                passButton.performClick();
            }


            if (presidentGameState.currentPlayer == 3){
                views[0].setVisibility(View.INVISIBLE);
                views[0].setClickable(false);
                PresidentComputerPlayer dumbAI = new PresidentComputerPlayer("Dumb");
                //AI chooses the lowest possible card(s) to play
                chosenCards = dumbAI.pickCards(presidentGameState);
                //Will play the chosen cards if they're legal
                if (card.legal(chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)){
                    placeCards.performClick();
                }
                //Will pass otherwise
                else{
                    passButton.performClick();
                }
            }
            actionCount++;
        }
        else if (placeCards.equals(view)) {
            this.game.sendAction(new PresidentPlaceAction(this));
            if (actionCount > 0){
                if (card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)) {
                    //Since the cards are the same number, the displayed image is the first chosen card
                    card.assignImages(presidentGameState.chosenCards.get(0), views[14]);
                    views[0].setVisibility(View.VISIBLE);
                    presidentGameState.cardsAtPlay = presidentGameState.chosenCards.size();
                    //Updates current card value to chosen card value
                    presidentGameState.currentCardNum = presidentGameState.chosenCards.get(0) % 100;
                    presidentUI.updateChosenCardsTotal(chosenCardsTotal, presidentGameState.cardsAtPlay);
                    presidentUI.updateButtonColor(placeCards, Color.RED);
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

                    views = updateCards(views, presidentGameState.getCards(presidentGameState.currentPlayer));
                    playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, presidentGameState.currentPlayer, false);


                    //A check to ensure the currentPlayer stays with 0-3 range
                    presidentGameState.updateTurn();
                    //Resets certain UI elements to prepare for the next player
                    views = presidentUI.updateCards(views, presidentGameState.allPlayers[presidentGameState.currentPlayer]);
                    views = presidentUI.resetCards(views);
                    presidentGameState.chosenCards.clear();
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                    playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, presidentGameState.currentPlayer, false);
                }

                //Counts all of the 0 values in current player's hand
                int count = 0;
                for (int i = 0; i < 13; i++){
                    if (presidentGameState.allPlayers[presidentGameState.currentPlayer][i] == 0)
                        count++;
                }
                //If 13 zeroes are counted, then the current player has won
                //and all of the buttons become unclickable
                if (count == 13){
                    for (int i = 1; i < 13; i++){
                        views[i].setClickable(false);
                    }
                    passButton.setClickable(false);
                    playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, presidentGameState.currentPlayer, true);
                }

                if (presidentGameState.currentPlayer == 1) {
                    passButton.performClick();
                }
                if (presidentGameState.currentPlayer == 3){
                    PresidentComputerPlayer dumbAI = new PresidentComputerPlayer("Dumb");
                    //AI chooses the lowest possible card(s) to play
                    presidentGameState.chosenCards = dumbAI.pickCards(presidentGameState);
                    //Will play the chosen cards if they're legal
                    if (card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)){
                        placeCards.performClick();
                    }
                    //Will pass otherwise
                    else{
                        passButton.performClick();
                    }
                }
            }
            actionCount++;
        }
        else if (views[0].equals(view)){
            views[0].setVisibility(View.INVISIBLE);
            views[0].setClickable(false);
            this.game.sendAction(new PresidentGiveHandAction(this));
            if (actionCount > 0){

            }
            actionCount++;
        }
        else{
            //All of the following onClickListeners have the same code tailored
            //to each card
            for (int i = 1; i <= 13; i++){
                if (views[i].equals(view)){
                    //If the card isn't raised, then it becomes raised
                    //and is added as a chosen card
                    if (views[i].getPaddingBottom() == 0) {
                        views[i].setPadding(0, 0, 0, 50);
                        chosenCard = i;
                        this.game.sendAction(new PresidentAddCardAction(this));
                        if (actionCount > 0){
                            presidentGameState.chosenCards.add(presidentGameState.allPlayers[presidentGameState.currentPlayer][chosenCard - 1]);

                            //Checks if the chosen cards are legal so the placeCards button
                            //can have the correct color
                            if (card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)){
                                presidentUI.updateButtonColor(placeCards, Color.GREEN);
                            }
                            else{
                                presidentUI.updateButtonColor(placeCards, Color.RED);
                            }
                        }
                    }
                    //If the card is raised, then it returns to its original position
                    //and is removed as a chosen card
                    else {
                        views[i].setPadding(0, 0, 0, 0);
                        chosenCard = i;
                        this.game.sendAction(new PresidentRemoveCardAction(this));
                        if (actionCount > 0){
                            presidentGameState.chosenCards.remove(Integer.valueOf(presidentGameState.allPlayers[presidentGameState.currentPlayer][chosenCard - 1]));

                            //Checks if the chosen cards are legal so the placeCards button
                            //can have the correct color
                            if (card.legal(presidentGameState.chosenCards, presidentGameState.cardsAtPlay, presidentGameState.currentCardNum)){
                                presidentUI.updateButtonColor(placeCards, Color.GREEN);
                            }
                            else{
                                presidentUI.updateButtonColor(placeCards, Color.RED);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    public ImageView[] updateCards(ImageView[] views, int[] stack){
        for (int i = 1; i <= 13; i++){
            //Turns card invisible if value is 0
            if (stack[i - 1] == 0) {
                views[i].setVisibility(View.INVISIBLE);
                views[i].setClickable(false);
            }
            //Assigns image based on cards value
            else{
                card.assignImages(stack[i - 1], views[i]);
                views[i].setVisibility(View.VISIBLE);
                views[i].setClickable(true);
            }

            views[i].postInvalidate();
        }
        return views;
    }
}
