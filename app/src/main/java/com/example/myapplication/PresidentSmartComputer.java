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

    }
}
