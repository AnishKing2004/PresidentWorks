package com.example.myapplication;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

/**
 * class PresidentComputerPlayer
 *
 * This class holds the constructor for a computer player as well as a function to
 * receive info.
 *
 */
public class PresidentComputerPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
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
}
