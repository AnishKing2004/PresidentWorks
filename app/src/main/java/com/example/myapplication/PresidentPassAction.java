package com.example.myapplication;

import com.example.game.GamePlayer;
import com.example.game.actionMsg.GameAction;

/**
 * class PresidentPassAction
 *
 * This class contains the constructor for the gameAction PresidentPassAction
 *
 */
public class PresidentPassAction extends GameAction {
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
    public PresidentPassAction(GamePlayer player) {
        super(player);
    }
}
