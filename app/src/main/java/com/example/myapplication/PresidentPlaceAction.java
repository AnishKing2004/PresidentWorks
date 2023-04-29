package com.example.myapplication;

import com.example.game.GamePlayer;
import com.example.game.actionMsg.GameAction;

/**
 * class PresidentPlaceAction
 *
 * This class holds the constructor for a gameAction, PresidentPlaceAction
 *
 */
public class PresidentPlaceAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PresidentPlaceAction(GamePlayer player) {
        super(player);
    }
}
