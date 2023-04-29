package com.example.myapplication;

import com.example.game.GameComputerPlayer;
import com.example.game.infoMsg.GameInfo;

/**
 * class PresidentSmartComputer
 *
 * This file contains the constructor for the smart AI as well as it's receive info
 *
 */
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
