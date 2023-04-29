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
     *   @author Yutaka Roberts
     *   @author Anish Karumuri
     *   @author Zella Running
     *   @author Calvin Phuong
     *   @version April 2023
     *
     * @param player the player who created the action
     */
    public PresidentPlaceAction(GamePlayer player) {
        super(player);
    }
}
