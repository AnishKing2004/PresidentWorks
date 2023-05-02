package com.example.myapplication;

import com.example.game.GameMainActivity;
import com.example.game.LocalGame;
import com.example.game.config.GameConfig;
import com.example.game.config.GamePlayerType;
import com.example.game.GamePlayer;

import java.util.ArrayList;

/**
 * class MainActivity
 *
 *   @author Yutaka Roberts
 *   @author Anish Karumuri
 *   @author Zella Running
 *   @author Calvin Phuong
 *   @version April 2023
 *
 * This class creates the lading screen and type of players
 * For now the game is only one payer, one dumb AI player and 2 smart AI players
 * We weren't able to let user decide but we hope to implement that option in the future
 *
 * Bugs:
 * We do have some bugs
 * Bug #1: is that player 1 cards arent placed correctly on the first turn, but after that they r placed correctly
 * Bug #2: is that left hand deck comes back everytime its player 1's turn
 * Bug #3: is that player's 1 cards change at times, usually when nearing the end of the game
 * Bug #4: Have to skip the first round otherwise the cards aren't set correctly
 * Bug #4: When the screen is tilted to either verticle or horizontal the game resets to default
 *
 *
 */
public class MainActivity extends GameMainActivity {

    final public int PORT_NUMBER = 98898;



    @Override
    public GameConfig createDefaultConfig() {
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Human Player") {
            @Override
            public GamePlayer createPlayer(String name) {

                return new PresidentHumanPlayer(name);

            }
        });

        playerTypes.add(new GamePlayerType("Dumb AI Player") {
            @Override
            public GamePlayer createPlayer(String name) {


                return new PresidentComputerPlayer(name);

            }
        });

        playerTypes.add(new GamePlayerType("Smart Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentComputerPlayer(name);
            }
        });

        playerTypes.add(new GamePlayerType("Smart Player") {
            @Override
            public GamePlayer createPlayer(String name) {
                return new PresidentComputerPlayer(name);
            }
        });

        GameConfig defaultConfig = new GameConfig(playerTypes,4,4,"President",PORT_NUMBER);
        defaultConfig.addPlayer("HumanPlayer",0);
        defaultConfig.addPlayer("DumbComputer",1);
        defaultConfig.addPlayer("SmartComputer",2);
        defaultConfig.addPlayer("SmartComputer",3);



        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame() {

        return new PresidentLocalGame();
    }
}