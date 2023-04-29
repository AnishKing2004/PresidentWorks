package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresidentUnitTest { //making sure math is mathing
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void CheckPlayer1Card() { // blank
        PresidentGameState pGameState = new PresidentGameState();

    }

    @Test
    public void checkPassCount() { // making sure the pass count is set to 0
        PresidentGameState pGameState = new PresidentGameState();
        assertEquals(0, pGameState.passCount);
    }

    @Test
    public void checkMaxCardsHand() { // making sure the each player has a max of 13 cards
        PresidentGameState pGameState = new PresidentGameState();
        assertEquals(13, pGameState.maxCardsHand);
    }

    @Test
    public void currentPlayer() { //making sure player 1 is going frist
        PresidentGameState pGameState = new PresidentGameState();
        assertEquals(0, pGameState.currentPlayer);
    }

    @Test
    public void nextCurrentPlayer() { //checks to see if the turn changes
        PresidentGameState pGameState = new PresidentGameState();
        pGameState.currentPlayer++;
        assertEquals(1, pGameState.currentPlayer);
    }

    @Test
    public void cardsAtPlay() { //makes sure the cards are 0 at the start of the game
        PresidentGameState pGameState = new PresidentGameState();
        assertEquals(0, pGameState.cardsAtPlay);
    }

}