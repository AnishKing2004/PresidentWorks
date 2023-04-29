package com.example.myapplication;

import com.example.game.GamePlayer;
import com.example.game.actionMsg.GameAction;

public class PresidentResetAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PresidentResetAction(GamePlayer player) {
        super(player);
    }
}
