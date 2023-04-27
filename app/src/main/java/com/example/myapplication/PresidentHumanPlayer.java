package com.example.myapplication;

import android.graphics.Color;
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

public class PresidentHumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    ImageView iv_deck, iv_card1,iv_card2,iv_card3,iv_card4,iv_card5,iv_card6,iv_card7,iv_card8,iv_card9,iv_card10,iv_card11,iv_card12,iv_card13, imageView2;
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
    PresidentGameState gameState = new PresidentGameState();

    private GameMainActivity myActivity;

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

        iv_deck = (ImageView) activity.findViewById(R.id.iv_deck);
        iv_card1 = (ImageView) activity.findViewById(R.id.iv_card1);
        iv_card2 = (ImageView) activity.findViewById(R.id.iv_card2);
        iv_card3 = (ImageView) activity.findViewById(R.id.iv_card3);
        iv_card4 = (ImageView) activity.findViewById(R.id.iv_card4);
        iv_card5 = (ImageView) activity.findViewById(R.id.iv_card5);
        iv_card6 = (ImageView) activity.findViewById(R.id.iv_card6);
        iv_card7 = (ImageView) activity.findViewById(R.id.iv_card7);
        iv_card8 = (ImageView) activity.findViewById(R.id.iv_card8);
        iv_card9 = (ImageView) activity.findViewById(R.id.iv_card9);
        iv_card10 = (ImageView) activity.findViewById(R.id.iv_card10);
        iv_card11 = (ImageView) activity.findViewById(R.id.iv_card11);
        iv_card12 = (ImageView) activity.findViewById(R.id.iv_card12);
        iv_card13 = (ImageView) activity.findViewById(R.id.iv_card13);
        imageView2 = (ImageView) activity.findViewById(R.id.imageView2);

        card.setCards();

        //Assigns cards to a player's deck before moving
        //to next player and reshuffling
        for (int i = 0; i < 4; i++){
            Collections.shuffle(card.cards);
            for (int j  = 0; j < 13; j++){
                gameState.allPlayers[i][j] = card.cards.get(j);
            }
        }

        //Resets game variable back to default
        gameState.currentPlayer = 0;

        card.assignImages(gameState.allPlayers[0][0], iv_card1);
        card.assignImages(gameState.allPlayers[0][1], iv_card2);
        card.assignImages(gameState.allPlayers[0][2], iv_card3);
        card.assignImages(gameState.allPlayers[0][3], iv_card4);
        card.assignImages(gameState.allPlayers[0][4], iv_card5);
        card.assignImages(gameState.allPlayers[0][5], iv_card6);
        card.assignImages(gameState.allPlayers[0][6], iv_card7);
        card.assignImages(gameState.allPlayers[0][7], iv_card8);
        card.assignImages(gameState.allPlayers[0][8], iv_card9);
        card.assignImages(gameState.allPlayers[0][9], iv_card10);
        card.assignImages(gameState.allPlayers[0][10], iv_card11);
        card.assignImages(gameState.allPlayers[0][11], iv_card12);
        card.assignImages(gameState.allPlayers[0][12], iv_card13);

        iv_card1.setVisibility(View.VISIBLE);
        iv_card2.setVisibility(View.VISIBLE);
        iv_card3.setVisibility(View.VISIBLE);
        iv_card4.setVisibility(View.VISIBLE);
        iv_card5.setVisibility(View.VISIBLE);
        iv_card6.setVisibility(View.VISIBLE);
        iv_card7.setVisibility(View.VISIBLE);
        iv_card8.setVisibility(View.VISIBLE);
        iv_card9.setVisibility(View.VISIBLE);
        iv_card10.setVisibility(View.VISIBLE);
        iv_card11.setVisibility(View.VISIBLE);
        iv_card12.setVisibility(View.VISIBLE);
        iv_card13.setVisibility(View.VISIBLE);
        card.assignImages(500,imageView2);

        placeCards.setOnClickListener(this);
        passButton.setOnClickListener(this);
        playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, gameState.currentPlayer, false);




        //All of the following onClickListeners have the same code tailored
        //to each card
        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If the card isn't raised, then it becomes raised
                //and is added as a chosen card
                if (iv_card1.getPaddingBottom() == 0) {
                    iv_card1.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][0]);
                }
                //If the card is raised, then it returns to its original position
                //and is removed as a chosen card
                else {
                    iv_card1.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][0]));
                }

                //Checks if the chosen cards are legal so the placeCards button
                //can have the correct color
                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card2.getPaddingBottom() == 0) {
                    iv_card2.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][1]);
                }
                else {
                    iv_card2.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][1]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card3.getPaddingBottom() == 0) {
                    iv_card3.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][2]);
                }
                else {
                    iv_card3.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][2]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card4.getPaddingBottom() == 0) {
                    iv_card4.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][3]);
                }
                else {
                    iv_card4.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][3]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card5.getPaddingBottom() == 0) {
                    iv_card5.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][4]);
                }
                else {
                    iv_card5.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][4]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card6.getPaddingBottom() == 0) {
                    iv_card6.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][5]);
                }
                else {
                    iv_card6.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][5]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card7.getPaddingBottom() == 0) {
                    iv_card7.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][6]);
                }
                else {
                    iv_card7.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][6]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });
        iv_card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card8.getPaddingBottom() == 0) {
                    iv_card8.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][7]);
                }
                else {
                    iv_card8.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][7]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });

        iv_card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card9.getPaddingBottom() == 0) {
                    iv_card9.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][8]);
                }
                else {
                    iv_card9.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][8]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });

        iv_card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card10.getPaddingBottom() == 0) {
                    iv_card10.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][9]);
                }
                else {
                    iv_card10.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][9]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });

        iv_card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card11.getPaddingBottom() == 0) {
                    iv_card11.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][10]);
                }
                else {
                    iv_card11.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][10]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });

        iv_card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card12.getPaddingBottom() == 0) {
                    iv_card12.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][11]);
                }
                else {
                    iv_card12.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][11]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });

        iv_card13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_card13.getPaddingBottom() == 0) {
                    iv_card13.setPadding(0, 0, 0, 50);
                    chosenCards.add(gameState.allPlayers[gameState.currentPlayer][12]);
                }
                else {
                    iv_card13.setPadding(0, 0, 0, 0);
                    chosenCards.remove(Integer.valueOf(gameState.allPlayers[gameState.currentPlayer][12]));
                }

                if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                    presidentUI.updateButtonColor(placeCards, Color.GREEN);
                }
                else{
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                }
            }
        });




    }

    @Override
    public void onClick(View view) {
        if(passButton instanceof Button){

            if (gameState.passCount == 3){
                gameState.cardsAtPlay = 0;
                card.assignImages(500,imageView2);
                gameState.passCount = 0;

                presidentUI.updateChosenCardsTotal(chosenCardsTotal,gameState.cardsAtPlay);

            }
            if(gameState.currentPlayer == 3){
                gameState.currentPlayer = 0;
            }
            gameState.currentPlayer++;
            playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, gameState.currentPlayer, false);




            this.game.sendAction(new PresidentPassAction(this));
        }

        if(placeCards instanceof Button){

            if (card.legal(chosenCards, gameState.cardsAtPlay, gameState.currentCardNum)){
                //Since the cards are the same number, the displayed image is the first chosen card
                card.assignImages(chosenCards.get(0), imageView2);
                iv_deck.setVisibility(View.VISIBLE);
                gameState.cardsAtPlay = chosenCards.size();
                //Updates current card value to chosen card value
                gameState.currentCardNum = chosenCards.get(0) % 100;
                presidentUI.updateChosenCardsTotal(chosenCardsTotal, gameState.cardsAtPlay);
                presidentUI.updateButtonColor(placeCards, Color.RED);
                gameState.passCount = 0;

                //Finds chosen cards in current player's hand and turns those values into 0
                while (chosenCards.size() != 0){
                    for (int i = 0; i < 13; i++){
                        if (chosenCards.get(0) == gameState.allPlayers[gameState.currentPlayer][i]){
                            gameState.allPlayers[gameState.currentPlayer][i] = 0;
                            break;
                        }
                    }
                    chosenCards.remove(Integer.valueOf(chosenCards.get(0)));
                }

                ImageView[] views = new ImageView[]{iv_card1,iv_card2,iv_card3,iv_card4,iv_card5,iv_card6,iv_card7,iv_card8,iv_card9,iv_card10,iv_card11,iv_card12,iv_card13, imageView2};
                views = presidentUI.updateCards(views, gameState.allPlayers[gameState.currentPlayer]);
                playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, gameState.currentPlayer, false);
                //Otherwise, turn goes to next player


                    //A check to ensure the currentPlayer stays with 0-3 range
                    if (gameState.currentPlayer > 3) {
                        gameState.currentPlayer = 0;
                    }
                    //Resets certain UI elements to prepare for the next player
                    presidentUI.updateCards(views, gameState.allPlayers[gameState.currentPlayer]);
                    views = presidentUI.resetCards(views);
                    chosenCards.clear();
                    presidentUI.updateButtonColor(placeCards, Color.RED);
                    playerNumberText = presidentUI.updatePlayerNumberText(playerNumberText, gameState.currentPlayer, false);
                this.game.sendAction(new PresidentPassAction(this));


            }





        }
    }
}
