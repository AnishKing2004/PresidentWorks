package com.example.myapplication;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

/**
 * class PresidentDumbComputer
 *   @author Yutaka Roberts
 *   @author Anish Karumuri
 *   @author Zella Running
 *   @author Calvin Phuong
 *   @version April 2023
 * This file contains the constructor for the dumb AI as well as it's receive info
 *
 */
public class PresidentDumbComputer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PresidentDumbComputer(String name) {
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
}
