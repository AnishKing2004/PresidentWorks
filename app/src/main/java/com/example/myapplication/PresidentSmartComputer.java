package com.example.myapplication;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

public class PresidentSmartComputer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PresidentSmartComputer(String name) {
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
