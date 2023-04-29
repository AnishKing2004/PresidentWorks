package com.example.myapplication;

import com.example.game.GamePlayer;
import com.example.game.actionMsg.GameAction;

public class PresidentAddCardAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PresidentAddCardAction(GamePlayer player) {
        super(player);
    }
}
